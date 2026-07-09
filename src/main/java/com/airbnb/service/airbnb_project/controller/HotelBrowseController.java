package com.airbnb.service.airbnb_project.controller;

import com.airbnb.service.airbnb_project.Dto.HotelInfoDTO;
import com.airbnb.service.airbnb_project.Dto.HotelPriceDTO;
import com.airbnb.service.airbnb_project.Dto.HotelSearchRequest;
import com.airbnb.service.airbnb_project.Dto.PagedResponse;
import com.airbnb.service.airbnb_project.Services.Interfaces.HotelService;
import com.airbnb.service.airbnb_project.Services.Interfaces.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Hotel Browse", description = "Browse and search for hotels")
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    @Operation(summary = "Search for hotels", description = "Filter hotels based on location, price, availability, etc.")
    public ResponseEntity<PagedResponse<HotelPriceDTO>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {

        PagedResponse<HotelPriceDTO> page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    @Operation(summary = "Get detailed hotel information", description = "Retrieve information about a specific hotel")
    public ResponseEntity<HotelInfoDTO> getHotelInfo(@PathVariable Long hotelId) {
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }
}

