package com.airbnb.service.airbnb_project.Services.Interfaces;

import java.time.LocalDate;

public interface HolidayService {

    boolean isHoliday(LocalDate date);
}
