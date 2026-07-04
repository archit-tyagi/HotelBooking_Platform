package com.airbnb.service.airbnb_project.Dto;

import com.airbnb.service.airbnb_project.Enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileUpdateRequestDTO {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}
