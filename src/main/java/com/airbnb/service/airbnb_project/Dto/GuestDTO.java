package com.airbnb.service.airbnb_project.Dto;

import com.airbnb.service.airbnb_project.Enums.Gender;
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
