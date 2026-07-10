package com.airbnb.service.airbnb_project.Services;

import com.airbnb.service.airbnb_project.Dto.HotelDTO;
import com.airbnb.service.airbnb_project.Dto.HotelPriceDTO;
import com.airbnb.service.airbnb_project.Dto.HotelPriceQueryResult;
import com.airbnb.service.airbnb_project.Dto.HotelSearchRequest;
import com.airbnb.service.airbnb_project.Dto.InventoryDTO;
import com.airbnb.service.airbnb_project.Dto.PagedResponse;
import com.airbnb.service.airbnb_project.Dto.UpdateInventoryRequestDTO;
import com.airbnb.service.airbnb_project.Entities.InventoryEntity;
import com.airbnb.service.airbnb_project.Entities.RoomEntity;
import com.airbnb.service.airbnb_project.Entities.UserEntity;
import com.airbnb.service.airbnb_project.Exceptions.ResourceNotFoundException;
import com.airbnb.service.airbnb_project.Repositories.HotelMinPriceRepository;
import com.airbnb.service.airbnb_project.Repositories.InventoryRepository;
import com.airbnb.service.airbnb_project.Repositories.RoomRepository;
import com.airbnb.service.airbnb_project.Services.Interfaces.InventoryService;
import com.airbnb.service.airbnb_project.Utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;
    private final HotelMinPriceRepository hotelMinPriceRepository;
    private final RoomRepository roomRepository;

    @Override
    public void initializeRoomForAnYear(RoomEntity room) {
        Set<LocalDate> existingDates = inventoryRepository.findByRoomOrderByDate(room).stream()
                .map(InventoryEntity::getDate)
                .collect(Collectors.toSet());

        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        List<InventoryEntity> toCreate = new ArrayList<>();

        while (!today.isAfter(endDate)) {
            if (existingDates.contains(today)) continue;
            toCreate.add(InventoryEntity.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookCount(0)
                    .reservedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build());
            today = today.plusDays(1);
        }
        inventoryRepository.saveAll(toCreate);
    }

    @Override
    public void deleteAllInventories(RoomEntity room) {
        log.info("Deleting the inventories of room with id: {}", room.getId());
        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public PagedResponse<HotelPriceDTO> searchHotels(HotelSearchRequest hotelSearchRequest) {
        log.info("Searching hotels for {} city, from {} to {}", hotelSearchRequest.getCity(), hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate());
        Pageable pageable = PageRequest.of(hotelSearchRequest.getPage(), hotelSearchRequest.getSize());
        long dateCount =
                ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate()) + 1;

        Page<HotelPriceQueryResult> hotelPage = hotelMinPriceRepository.findHotelsWithAvailableInventory(hotelSearchRequest.getCity(),
                hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate(), hotelSearchRequest.getRoomsCount(),
                dateCount, pageable);

        Page<HotelPriceDTO> dtoPage = hotelPage.map((element) ->
                new HotelPriceDTO(modelMapper.map(element.getHotel(), HotelDTO.class), element.getPrice()));

        return PagedResponse.from(dtoPage);
    }

    @Override
    public List<InventoryDTO> getAllInventoryByRoom(Long roomId) {
        log.info("Getting All inventory by room for room with id: {}", roomId);
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));

        UserEntity user = AppUtils.getCurrentUser();
        if (!user.equals(room.getHotel().getOwner()))
            throw new AccessDeniedException("You are not the owner of room with id: " + roomId);

        return inventoryRepository.findByRoomOrderByDate(room).stream()
                .map((element) -> modelMapper.map(element,
                        InventoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateInventory(Long roomId, UpdateInventoryRequestDTO updateInventoryRequestDto) {
        log.info("Updating All inventory by room for room with id: {} between date range: {} - {}", roomId,
                updateInventoryRequestDto.getStartDate(), updateInventoryRequestDto.getEndDate());

        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));

        UserEntity user = AppUtils.getCurrentUser();
        if (!user.equals(room.getHotel().getOwner()))
            throw new AccessDeniedException("You are not the owner of room with id: " + roomId);

        inventoryRepository.getInventoryAndLockBeforeUpdate(roomId, updateInventoryRequestDto.getStartDate(),
                updateInventoryRequestDto.getEndDate());

        inventoryRepository.updateInventory(roomId, updateInventoryRequestDto.getStartDate(),
                updateInventoryRequestDto.getEndDate(), updateInventoryRequestDto.getClosed(),
                updateInventoryRequestDto.getSurgeFactor());
    }

}