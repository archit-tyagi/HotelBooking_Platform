package com.airbnb.service.airbnb_project.Strategy;

import com.airbnb.service.airbnb_project.Entities.InventoryEntity;
import com.airbnb.service.airbnb_project.Services.Interfaces.HolidayService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class HolidayPricingStrategy implements PricingStrategy {

    private final PricingStrategy wrapped;
    private final HolidayService holidayService;

    @Override
    public BigDecimal calculatePrice(InventoryEntity inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        if (holidayService.isHoliday(inventory.getDate())) {
            price = price.multiply(BigDecimal.valueOf(1.25));
        }
        return price;
    }
}
