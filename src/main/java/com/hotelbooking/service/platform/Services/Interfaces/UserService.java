package com.hotelbooking.service.platform.Services.Interfaces;

import com.hotelbooking.service.platform.Dto.ProfileUpdateRequestDTO;
import com.hotelbooking.service.platform.Dto.UserDTO;
import com.hotelbooking.service.platform.Entities.UserEntity;

public interface UserService {
    UserEntity getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDTO profileUpdateRequestDto);

    UserDTO getMyProfile();
}