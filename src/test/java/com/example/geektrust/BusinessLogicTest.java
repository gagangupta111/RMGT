package com.example.geektrust;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.controller.BusinessLogic;
import com.example.geektrust.model.Structure;
import com.example.geektrust.model.TimeSlot;
import com.example.geektrust.model.VehicleBookingRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BusinessLogicTest {

    @Test
    public void bookNormalSlots_return_SUCCESS(){

        Structure structure = new Structure();

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("13:05", "16:05"));
        structure.getAllTimeSlots().put("SUV_R", timeSlots);
        structure.getAllTimeSlots().put("SUV_V", timeSlots);

        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("abc", "SUV", "", "", null, timeSlot, true);
        String returnValue = BusinessLogic.bookNormalSlots(structure, vehicleBookingRequest);

        assert returnValue.equals(Constants.SUCCESS);
        assert structure.getAllTimeSlots().get("SUV_R").size() == 3;
        assert structure.getRevenues().get(Constants.REGULAR) == Constants.costOfVehiclesTypesPerHour.get("SUV_R")*Constants.durationOfBooking;
        assert structure.getVehicles_slot_map().get("abc").getFromString().equals(timeSlot.getFromString());
        assert structure.getVehicles_id_type_map().get("abc").equals(Constants.SUV);
    }

    @Test
    public void bookAdditionalSlots_return_SUCCESS_ZERO_COST(){

        Structure structure = new Structure();
        structure.getVehicles_id_type_map().put("abc", Constants.SUV);

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("13:05", "16:05"));
        structure.getAllTimeSlots().put("SUV_R", timeSlots);
        structure.getAllTimeSlots().put("SUV_V", timeSlots);

        TimeSlot previousBookedTimeSlot = new TimeSlot("13:00", "16:00");
        TimeSlot requestedTimeSlot = new TimeSlot("16:01", "16:15");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("abc", "SUV", "", "", previousBookedTimeSlot, requestedTimeSlot, true);
        String returnValue = BusinessLogic.bookAdditionalSlots(structure, vehicleBookingRequest);

        assert returnValue.equals(Constants.SUCCESS);
        assert structure.getAllTimeSlots().get("SUV_R").size() == 3;
        assert structure.getRevenues().get(Constants.REGULAR) == 0;
        assert structure.getVehicles_slot_map().get("abc").getFromString().equals(previousBookedTimeSlot.getFromString());
        assert structure.getVehicles_slot_map().get("abc").getToString().equals(requestedTimeSlot.getToString());

    }

    @Test
    public void bookAdditionalSlots_return_SUCCESS_MORE_COST(){

        Structure structure = new Structure();
        structure.getVehicles_id_type_map().put("abc", Constants.SUV);

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("13:05", "16:05"));
        structure.getAllTimeSlots().put("SUV_R", timeSlots);
        structure.getAllTimeSlots().put("SUV_V", timeSlots);

        TimeSlot previousBookedTimeSlot = new TimeSlot("13:00", "16:00");
        TimeSlot requestedTimeSlot = new TimeSlot("16:01", "17:15");
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest("abc", "SUV", "", "", previousBookedTimeSlot, requestedTimeSlot, true);
        String returnValue = BusinessLogic.bookAdditionalSlots(structure, vehicleBookingRequest);

        assert returnValue.equals(Constants.SUCCESS);
        assert structure.getAllTimeSlots().get("SUV_R").size() == 3;
        assert structure.getRevenues().get(Constants.REGULAR) == 100;
        assert structure.getVehicles_slot_map().get("abc").getFromString().equals(previousBookedTimeSlot.getFromString());
        assert structure.getVehicles_slot_map().get("abc").getToString().equals(requestedTimeSlot.getToString());

    }

}
