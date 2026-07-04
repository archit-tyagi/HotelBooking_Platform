package com.airbnb.service.airbnb_project.Dto;

import com.airbnb.service.airbnb_project.Enums.Gender;
import lombok.Data;

@Data
public class GuestDTO {
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
}
