package com.hotelbooking.service.platform.Strategy;

import com.hotelbooking.service.platform.Entities.InventoryEntity;

import java.math.BigDecimal;

@FunctionalInterface
public interface PricingStrategy {

    BigDecimal calculatePrice(InventoryEntity inventory);
}
