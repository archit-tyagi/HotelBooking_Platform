package com.airbnb.service.airbnb_project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelInfoDTO {
    private HotelDTO hotel;
    private List<RoomDTO> rooms;
}
