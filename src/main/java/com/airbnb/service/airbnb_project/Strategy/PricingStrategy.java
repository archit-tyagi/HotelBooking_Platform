package com.airbnb.service.airbnb_project.Strategy;

import com.airbnb.service.airbnb_project.Entities.InventoryEntity;

import java.math.BigDecimal;

@FunctionalInterface
public interface PricingStrategy {

    BigDecimal calculatePrice(InventoryEntity inventory);
}
