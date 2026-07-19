package com.hotelbooking.service.platform.Services.Interfaces;

import com.hotelbooking.service.platform.Entities.BookingEntity;

public interface CheckoutService {

    String getCheckoutSession(BookingEntity booking);

}