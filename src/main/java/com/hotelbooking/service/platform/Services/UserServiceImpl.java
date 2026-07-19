package com.hotelbooking.service.platform.Services;

import com.hotelbooking.service.platform.Dto.ProfileUpdateRequestDTO;
import com.hotelbooking.service.platform.Dto.UserDTO;
import com.hotelbooking.service.platform.Entities.UserEntity;
import com.hotelbooking.service.platform.Exceptions.ResourceNotFoundException;
import com.hotelbooking.service.platform.Repositories.UserRepository;
import com.hotelbooking.service.platform.Services.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.hotelbooking.service.platform.Utils.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public void updateProfile(ProfileUpdateRequestDTO profileUpdateRequestDto) {
        UserEntity user = getCurrentUser();

        if (profileUpdateRequestDto.getDateOfBirth() != null)
            user.setDateOfBirth(profileUpdateRequestDto.getDateOfBirth());
        if (profileUpdateRequestDto.getGender() != null) user.setGender(profileUpdateRequestDto.getGender());
        if (profileUpdateRequestDto.getName() != null) user.setName(profileUpdateRequestDto.getName());

        userRepository.save(user);
    }

    @Override
    public UserDTO getMyProfile() {
        UserEntity user = getCurrentUser();
        log.info("Getting the profile for user with id: {}", user.getId());
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }
}