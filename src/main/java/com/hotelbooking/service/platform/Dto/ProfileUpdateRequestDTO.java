package com.hotelbooking.service.platform.Dto;

import com.hotelbooking.service.platform.Enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileUpdateRequestDTO {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}
