package com.airbnb.service.airbnb_project.Dto;

import com.airbnb.service.airbnb_project.Entities.HotelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelPriceQueryResult {
    private HotelEntity hotel;
    private Double price;
}
