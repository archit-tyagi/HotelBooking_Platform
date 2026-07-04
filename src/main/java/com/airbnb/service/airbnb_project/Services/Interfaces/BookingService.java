package com.airbnb.service.airbnb_project.Services.Interfaces;

import com.airbnb.service.airbnb_project.Dto.BookingDTO;
import com.airbnb.service.airbnb_project.Dto.BookingRequest;
import com.airbnb.service.airbnb_project.Dto.GuestDTO;
import com.airbnb.service.airbnb_project.Dto.HotelReportDTO;
import com.airbnb.service.airbnb_project.Enums.BookingStatus;
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
