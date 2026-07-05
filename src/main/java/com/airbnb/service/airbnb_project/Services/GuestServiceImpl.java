package com.airbnb.service.airbnb_project.Services;

import com.airbnb.service.airbnb_project.Dto.GuestDTO;
import com.airbnb.service.airbnb_project.Entities.GuestEntity;
import com.airbnb.service.airbnb_project.Entities.UserEntity;
import com.airbnb.service.airbnb_project.Repositories.GuestRepository;
import com.airbnb.service.airbnb_project.Services.Interfaces.GuestService;
import com.airbnb.service.airbnb_project.Utils.AppUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GuestDTO> getAllGuests() {
        UserEntity user = AppUtils.getCurrentUser();
        log.info("Fetching all guests of user with id: {}", user.getId());
        List<GuestEntity> guests = guestRepository.findByUser(user);
        return guests.stream()
                .map(guest -> modelMapper.map(guest, GuestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateGuest(Long guestId, GuestDTO guestDto) {
        log.info("Updating guest with ID: {}", guestId);
        GuestEntity guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new EntityNotFoundException("Guest not found"));

        UserEntity user = AppUtils.getCurrentUser();
        if(!user.equals(guest.getUser())) throw new AccessDeniedException("You are not the owner of this guest");

        modelMapper.map(guestDto, guest);
        guest.setUser(user);
        guest.setId(guestId);

        guestRepository.save(guest);
        log.info("Guest with ID: {} updated successfully", guestId);
    }

    @Override
    public void deleteGuest(Long guestId) {
        log.info("Deleting guest with ID: {}", guestId);
        GuestEntity guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new EntityNotFoundException("Guest not found"));

        UserEntity user = AppUtils.getCurrentUser();
        if(!user.equals(guest.getUser())) throw new AccessDeniedException("You are not the owner of this guest");

        guestRepository.deleteById(guestId);
        log.info("Guest with ID: {} deleted successfully", guestId);
    }

    @Override
    public GuestDTO addNewGuest(GuestDTO guestDto) {
        log.info("Adding new guest: {}", guestDto);
        UserEntity user = AppUtils.getCurrentUser();
        GuestEntity guest = modelMapper.map(guestDto, GuestEntity.class);
        guest.setUser(user);
        GuestEntity savedGuest = guestRepository.save(guest);
        log.info("Guest added with ID: {}", savedGuest.getId());
        return modelMapper.map(savedGuest, GuestDTO.class);
    }
}