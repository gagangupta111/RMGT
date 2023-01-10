package com.example.geektrust;

import com.example.geektrust.controller.Validations;
import com.example.geektrust.structure.Structure;
import com.example.geektrust.structure.TimeSlot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidationsTest {

    @Test
    public void validateNormalBookingEntryTime_false(){

        Structure structure = new Structure();
        TimeSlot timeSlot = new TimeSlot("12:40", "15:40");
        assert !Validations.validateNormalBookingEntryTime(structure, "", "", timeSlot);

    }

    @Test
    public void validateNormalBookingEntryTime_true(){

        Structure structure = new Structure();
        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");
        assert Validations.validateNormalBookingEntryTime(structure, "", "", timeSlot);

    }

    @Test
    public void validateNormalBookingAvailability_R_true(){

        Structure structure = new Structure();
        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");
        assert Validations.validateNormalBookingAvailability(structure, "abc", "SUV", true, timeSlot);
    }

    @Test
    public void validateNormalBookingAvailability_R_true_1booked(){

        Structure structure = new Structure();
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));

        structure.timeSlots.put("SUV_R", timeSlots);
        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");
        assert Validations.validateNormalBookingAvailability(structure, "abc", "SUV", true, timeSlot);
    }

    @Test
    public void validateNormalBookingAvailability_R_false_alBooked(){

        Structure structure = new Structure();
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("13:05", "16:05"));

        structure.timeSlots.put("SUV_R", timeSlots);
        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");
        assert !Validations.validateNormalBookingAvailability(structure, "abc", "SUV", true, timeSlot);
    }


}
