package com.airbnb.service.airbnb_project.Services;

import com.airbnb.service.airbnb_project.Services.Interfaces.HolidayService;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class HolidayServiceImpl implements HolidayService {

    private final HolidayManager holidayManager;

    public HolidayServiceImpl(@Value("${app.holiday.calendar}") String calendar) {
        HolidayCalendar holidayCalendar = HolidayCalendar.valueOf(calendar.trim().toUpperCase());
        log.info("Initializing holiday calendar for region: {}", holidayCalendar);
        this.holidayManager = HolidayManager.getInstance(ManagerParameters.create(holidayCalendar));
    }

    @Override
    public boolean isHoliday(LocalDate date) {
        return holidayManager.isHoliday(date);
    }
}
