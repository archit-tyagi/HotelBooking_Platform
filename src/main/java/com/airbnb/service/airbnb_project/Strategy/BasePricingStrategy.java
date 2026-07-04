package com.airbnb.service.airbnb_project.Strategy;

import com.airbnb.service.airbnb_project.Entities.InventoryEntity;
import java.math.BigDecimal;

public class BasePricingStrategy implements PricingStrategy{
    @Override
    public BigDecimal calculatePrice(InventoryEntity inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
