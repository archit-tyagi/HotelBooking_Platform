package com.hotelbooking.service.platform.Dto;

import com.hotelbooking.service.platform.Entities.HotelEntity;
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
