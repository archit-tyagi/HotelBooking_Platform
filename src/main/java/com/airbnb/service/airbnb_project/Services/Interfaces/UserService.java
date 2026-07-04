package com.airbnb.service.airbnb_project.Services.Interfaces;

import com.airbnb.service.airbnb_project.Dto.ProfileUpdateRequestDTO;
import com.airbnb.service.airbnb_project.Dto.UserDTO;
import com.airbnb.service.airbnb_project.Entities.UserEntity;

public interface UserService {
    UserEntity getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDTO profileUpdateRequestDto);

    UserDTO getMyProfile();
}