package com.hotelbooking.service.platform.Services.Interfaces;

import com.hotelbooking.service.platform.Dto.HotelDTO;
import com.hotelbooking.service.platform.Dto.HotelInfoDTO;

import java.util.List;

public interface HotelService {
    HotelDTO createNewHotel(HotelDTO hotelDto);

    HotelDTO getHotelById(Long id);

    HotelDTO updateHotelById(Long id, HotelDTO hotelDto);

    void deleteHotelById(Long id);

    void activateHotel(Long hotelId);

    HotelInfoDTO getHotelInfoById(Long hotelId);

    List<HotelDTO> getAllHotels();
}