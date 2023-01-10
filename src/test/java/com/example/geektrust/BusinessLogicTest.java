package com.example.geektrust;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.controller.BusinessLogic;
import com.example.geektrust.structure.Structure;
import com.example.geektrust.structure.TimeSlot;
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
        structure.timeSlots.put("SUV_R", timeSlots);
        structure.timeSlots.put("SUV_V", timeSlots);

        TimeSlot timeSlot = new TimeSlot("13:40", "16:40");

        String returnValue = BusinessLogic.bookNormalSlots(structure, "abc", "SUV", true, timeSlot);

        assert returnValue.equals(Constants.SUCCESS);
        assert structure.timeSlots.get("SUV_R").size() == 3;
        assert structure.revenues.get(Constants.REGULAR) == Constants.costOfVehiclesTypesPerHour.get("SUV_R")*Constants.durationOfBooking;
        assert structure.vehicles_slot_map.get("abc").getFromString().equals(timeSlot.getFromString());
        assert structure.vehicles_id_map.get("abc").equals(Constants.SUV);
    }

}
