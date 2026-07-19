package com.hotelbooking.service.platform.Services;

import com.hotelbooking.service.platform.Dto.HotelDTO;
import com.hotelbooking.service.platform.Dto.HotelInfoDTO;
import com.hotelbooking.service.platform.Dto.RoomDTO;
import com.hotelbooking.service.platform.Entities.HotelEntity;
import com.hotelbooking.service.platform.Entities.RoomEntity;
import com.hotelbooking.service.platform.Entities.UserEntity;
import com.hotelbooking.service.platform.Exceptions.ResourceNotFoundException;
import com.hotelbooking.service.platform.Exceptions.UnAuthorisedException;
import com.hotelbooking.service.platform.Repositories.HotelRepository;
import com.hotelbooking.service.platform.Repositories.RoomRepository;
import com.hotelbooking.service.platform.Services.Interfaces.HotelService;
import com.hotelbooking.service.platform.Services.Interfaces.InventoryService;
import com.hotelbooking.service.platform.Utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;

    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDto) {
        log.info("Creating a new hotel with name: {}", hotelDto.getName());
        HotelEntity hotel = modelMapper.map(hotelDto, HotelEntity.class);
        hotel.setActive(false);

        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        hotel.setOwner(user);

        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with ID: {}", hotel.getId());
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        log.info("Getting the hotel with ID: {}", id);
        HotelEntity hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + id));

        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!user.equals(hotel.getOwner())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: " + id);
        }

        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO updateHotelById(Long id, HotelDTO hotelDto) {
        log.info("Updating the hotel with ID: {}", id);
        HotelEntity hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + id));

        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals(hotel.getOwner())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: " + id);
        }

        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel.setPhotos(hotelDto.getPhotos());
        hotel.setAmenities(hotelDto.getAmenities());
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        HotelEntity hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + id));

        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals(hotel.getOwner())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: " + id);
        }

        for (RoomEntity room : hotel.getRooms()) {
            inventoryService.deleteAllInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("Activating the hotel with ID: {}", hotelId);
        HotelEntity hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals(hotel.getOwner())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: " + hotelId);
        }

        if (Boolean.TRUE.equals(hotel.getActive())) {
            log.info("Hotel with ID: {} is already active, skipping initialization", hotelId);
        }

        hotel.setActive(true);

        for (RoomEntity room : hotel.getRooms()) {
            inventoryService.initializeRoomForAnYear(room);
        }
    }

    //public method
    @Override
    public HotelInfoDTO getHotelInfoById(Long hotelId) {
        HotelEntity hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        List<RoomDTO> rooms = hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDTO.class))
                .toList();

        return new HotelInfoDTO(modelMapper.map(hotel, HotelDTO.class), rooms);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        UserEntity user = AppUtils.getCurrentUser();
        log.info("Getting all hotels for the admin user with ID: {}", user.getId());
        List<HotelEntity> hotels = hotelRepository.findByOwner(user);

        return hotels
                .stream()
                .map((element) -> modelMapper.map(element, HotelDTO.class))
                .collect(Collectors.toList());
    }
}