package com.hotelbooking.service.platform.Dto;

import com.hotelbooking.service.platform.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GuestDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
}
