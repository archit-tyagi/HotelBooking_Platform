package com.airbnb.service.airbnb_project.Services.Interfaces;

import com.airbnb.service.airbnb_project.Dto.HotelPriceDTO;
import com.airbnb.service.airbnb_project.Dto.HotelSearchRequest;
import com.airbnb.service.airbnb_project.Dto.InventoryDTO;
import com.airbnb.service.airbnb_project.Dto.UpdateInventoryRequestDTO;
import com.airbnb.service.airbnb_project.Entities.RoomEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {
    void initializeRoomForAYear(RoomEntity room);

    void deleteAllInventories(RoomEntity room);

    Page<HotelPriceDTO> searchHotels(HotelSearchRequest hotelSearchRequest);

    List<InventoryDTO> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDTO updateInventoryRequestDto);
}