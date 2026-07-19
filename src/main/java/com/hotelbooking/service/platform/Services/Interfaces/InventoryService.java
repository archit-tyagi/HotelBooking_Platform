package com.hotelbooking.service.platform.Services.Interfaces;

import com.hotelbooking.service.platform.Dto.HotelPriceDTO;
import com.hotelbooking.service.platform.Dto.HotelSearchRequest;
import com.hotelbooking.service.platform.Dto.InventoryDTO;
import com.hotelbooking.service.platform.Dto.PagedResponse;
import com.hotelbooking.service.platform.Dto.UpdateInventoryRequestDTO;
import com.hotelbooking.service.platform.Entities.RoomEntity;

import java.util.List;

public interface InventoryService {
    void initializeRoomForAnYear(RoomEntity room);

    void deleteAllInventories(RoomEntity room);

    PagedResponse<HotelPriceDTO> searchHotels(HotelSearchRequest hotelSearchRequest);

    List<InventoryDTO> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDTO updateInventoryRequestDto);
}