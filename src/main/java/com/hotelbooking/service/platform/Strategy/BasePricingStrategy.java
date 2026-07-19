package com.hotelbooking.service.platform.Strategy;

import com.hotelbooking.service.platform.Entities.InventoryEntity;

import java.math.BigDecimal;

public class BasePricingStrategy implements PricingStrategy {
    @Override
    public BigDecimal calculatePrice(InventoryEntity inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
