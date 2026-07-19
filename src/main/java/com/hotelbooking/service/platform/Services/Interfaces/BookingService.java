package com.hotelbooking.service.platform.Services.Interfaces;

import com.hotelbooking.service.platform.Dto.BookingDTO;
import com.hotelbooking.service.platform.Dto.BookingRequest;
import com.hotelbooking.service.platform.Dto.GuestDTO;
import com.hotelbooking.service.platform.Dto.HotelReportDTO;
import com.hotelbooking.service.platform.Enums.BookingStatus;
import com.stripe.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingDTO initialiseBooking(BookingRequest bookingRequest);

    BookingDTO addGuests(Long bookingId, List<GuestDTO> guestDtoList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    BookingStatus getBookingStatus(Long bookingId);

    List<BookingDTO> getAllBookingsByHotelId(Long hotelId);

    HotelReportDTO getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);

    List<BookingDTO> getMyBookings();
}
