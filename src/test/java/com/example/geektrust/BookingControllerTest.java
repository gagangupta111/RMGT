package com.example.geektrust;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.controller.BookingController;
import com.example.geektrust.structure.Structure;
import com.example.geektrust.structure.TimeSlot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BookingControllerTest {

    @Test
    public void bookNormalSlots_return_INVALID_ENTRY_TIME(){

        Structure structure = new Structure();
        String returnValue = BookingController.bookNormalSlots(structure, "", "", "12:00");

        assert returnValue.equals(Constants.INVALID_ENTRY_TIME);

    }

    @Test
    public void bookNormalSlots_return_RACETRACK_FULL(){

        Structure structure = new Structure();

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("13:05", "16:05"));
        structure.timeSlots.put("SUV_R", timeSlots);
        structure.timeSlots.put("SUV_V", timeSlots);

        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");

        String returnValue = BookingController.bookNormalSlots(structure, "", "SUV", "13:00");

        assert returnValue.equals(Constants.RACETRACK_FULL);

    }

}
