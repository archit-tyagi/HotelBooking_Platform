package com.airbnb.service.airbnb_project.Services.Interfaces;

import com.airbnb.service.airbnb_project.Entities.BookingEntity;

public interface CheckoutService {

    String getCheckoutSession(BookingEntity booking);

}