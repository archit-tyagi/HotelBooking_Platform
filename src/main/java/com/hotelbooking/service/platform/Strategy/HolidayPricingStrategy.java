package com.hotelbooking.service.platform.Strategy;

import com.hotelbooking.service.platform.Entities.InventoryEntity;
import com.hotelbooking.service.platform.Services.Interfaces.HolidayService;
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
