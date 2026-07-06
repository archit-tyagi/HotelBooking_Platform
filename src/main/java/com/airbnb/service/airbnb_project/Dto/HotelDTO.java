package com.airbnb.service.airbnb_project.Dto;

import com.airbnb.service.airbnb_project.Entities.HotelContactInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class HotelDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;
}