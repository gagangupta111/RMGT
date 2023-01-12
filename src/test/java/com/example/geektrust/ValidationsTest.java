package com.example.geektrust;

import com.example.geektrust.controller.Validations;
import com.example.geektrust.model.Structure;
import com.example.geektrust.model.TimeSlot;
import com.example.geektrust.model.VehicleBookingRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidationsTest {

    @Test
    public void validateNormalBookingEntryTime_false(){

        Structure structure = new Structure();
        TimeSlot timeSlot = new TimeSlot("12:40", "15:40");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("", "", "", "", null, timeSlot, true);
        assert !Validations.validateBookingEntryTime(structure, vehicleBookingRequest);

    }

    @Test
    public void validateNormalBookingEntryTime_true(){

        Structure structure = new Structure();
        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("", "", "", "", null, timeSlot, true);
        assert Validations.validateBookingEntryTime(structure, vehicleBookingRequest);

    }

    @Test
    public void validateNormalBookingAvailability_R_true(){

        Structure structure = new Structure();
        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("abc", "SUV", "", "", null, timeSlot, true);
        assert Validations.validateBookingAvailability(structure, vehicleBookingRequest);
    }

    @Test
    public void validateNormalBookingAvailability_V_true(){

        Structure structure = new Structure();
        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("abc", "SUV", "", "", null, timeSlot, false);
        assert Validations.validateBookingAvailability(structure, vehicleBookingRequest);
    }

    @Test
    public void validateNormalBookingAvailability_R_true_1booked(){

        Structure structure = new Structure();
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));

        structure.getAllTimeSlots().put("SUV_R", timeSlots);
        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("abc", "SUV", "", "", null, timeSlot, true);
        assert Validations.validateBookingAvailability(structure, vehicleBookingRequest);
    }

    @Test
    public void validateNormalBookingAvailability_V_false_1booked(){

        Structure structure = new Structure();
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));

        structure.getAllTimeSlots().put("SUV_V", timeSlots);
        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("abc", "SUV", "", "", null, timeSlot, false);
        assert !Validations.validateBookingAvailability(structure, vehicleBookingRequest);
    }

    @Test
    public void validateNormalBookingAvailability_R_false_alBooked(){

        Structure structure = new Structure();
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("13:05", "16:05"));

        structure.getAllTimeSlots().put("SUV_R", timeSlots);
        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("abc", "SUV", "", "", null, timeSlot, true);
        assert !Validations.validateBookingAvailability(structure, vehicleBookingRequest);
    }

    @Test
    public void validateAdditionalBookingExitTime_false(){

        Structure structure = new Structure();
        TimeSlot timeSlot = new TimeSlot("19:40", "22:40");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("abc", "SUV", "", "", null, timeSlot, false);
        assert !Validations.validateAdditionalBookingExitTime(structure, vehicleBookingRequest);
    }

    @Test
    public void validateAdditionalBookingExitTime_true(){

        Structure structure = new Structure();
        TimeSlot timeSlot = new TimeSlot("16:40", "19:40");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("abc", "SUV", "", "", null, timeSlot, false);
        assert Validations.validateAdditionalBookingExitTime(structure, vehicleBookingRequest);
    }


}
