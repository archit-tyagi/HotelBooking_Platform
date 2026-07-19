package com.hotelbooking.service.platform.Strategy;

import com.hotelbooking.service.platform.Entities.InventoryEntity;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class OccupancyPricingStrategy implements PricingStrategy {

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(InventoryEntity inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        Integer totalCount = inventory.getTotalCount();
        if (totalCount != null && totalCount > 0) {
            double occupancyRate = (double) inventory.getBookCount() / inventory.getTotalCount();
            if (occupancyRate > 0.8) {
                price = price.multiply(BigDecimal.valueOf(1.2));
            }
        }
        return price;
    }
}
