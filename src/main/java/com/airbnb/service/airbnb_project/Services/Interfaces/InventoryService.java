package com.airbnb.service.airbnb_project.Services.Interfaces;

import com.airbnb.service.airbnb_project.Dto.HotelPriceDTO;
import com.airbnb.service.airbnb_project.Dto.HotelSearchRequest;
import com.airbnb.service.airbnb_project.Dto.InventoryDTO;
import com.airbnb.service.airbnb_project.Dto.PagedResponse;
import com.airbnb.service.airbnb_project.Dto.UpdateInventoryRequestDTO;
import com.airbnb.service.airbnb_project.Entities.RoomEntity;

import java.util.List;

public interface InventoryService {
    void initializeRoomForAnYear(RoomEntity room);

    void deleteAllInventories(RoomEntity room);

    PagedResponse<HotelPriceDTO> searchHotels(HotelSearchRequest hotelSearchRequest);

    List<InventoryDTO> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDTO updateInventoryRequestDto);
}