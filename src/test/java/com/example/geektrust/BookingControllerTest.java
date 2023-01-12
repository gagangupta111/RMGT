package com.example.geektrust;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.controller.BookingController;
import com.example.geektrust.model.Structure;
import com.example.geektrust.model.TimeSlot;
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
    public void bookNormalSlots_return_SUCCESS(){

        Structure structure = new Structure();

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("13:05", "16:05"));
        structure.getAllTimeSlots().put("SUV_R", timeSlots);
        structure.getAllTimeSlots().put("SUV_V", timeSlots);

        String returnValue = BookingController.bookNormalSlots(structure, "", "SUV", "17:00");

        assert returnValue.equals(Constants.SUCCESS);

    }

    @Test
    public void bookNormalSlots_return_SUCCESS1(){

        Structure structure = new Structure();

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("15:05", "18:05"));
        structure.getAllTimeSlots().put("SUV_R", timeSlots);
        structure.getAllTimeSlots().put("SUV_V", timeSlots);

        String returnValue = BookingController.bookNormalSlots(structure, "", "SUV", "17:00");

        assert returnValue.equals(Constants.SUCCESS);

    }

    @Test
    public void bookNormalSlots_return_RACETRACK_FULL(){

        Structure structure = new Structure();

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("13:05", "16:05"));
        structure.getAllTimeSlots().put("SUV_R", timeSlots);
        structure.getAllTimeSlots().put("SUV_V", timeSlots);

        String returnValue = BookingController.bookNormalSlots(structure, "", "SUV", "13:00");

        assert returnValue.equals(Constants.RACETRACK_FULL);

    }

    @Test
    public void bookAdditionalSlots_return_INVALID_EXIT_TIME(){

        Structure structure = new Structure();
        structure.getVehicles_id_type_map().put("abc", "SUV");
        structure.getVehicles_slot_map().put("abc", new TimeSlot("13:00", "16:00"));
        String returnValue = BookingController.bookAdditionalSlots(structure, "abc", "20:01");

        assert returnValue.equals(Constants.INVALID_EXIT_TIME);

    }

    @Test
    public void bookAdditionalSlots_return_INVALID_EXIT_TIME1(){

        Structure structure = new Structure();
        structure.getVehicles_id_type_map().put("abc", "SUV");
        structure.getVehicles_slot_map().put("abc", new TimeSlot("13:00", "16:00"));
        String returnValue = BookingController.bookAdditionalSlots(structure, "abc", "12:01");

        assert returnValue.equals(Constants.INVALID_EXIT_TIME);

    }

    @Test
    public void bookAdditionalSlots_return_SUCCESS(){

        Structure structure = new Structure();
        structure.getVehicles_id_type_map().put("abc", "SUV");
        structure.getVehicles_slot_map().put("abc", new TimeSlot("13:00", "16:00"));

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("13:05", "16:05"));
        structure.getAllTimeSlots().put("SUV_R", timeSlots);
        structure.getAllTimeSlots().put("SUV_V", timeSlots);

        String returnValue = BookingController.bookAdditionalSlots(structure, "abc", "16:15");

        assert returnValue.equals(Constants.SUCCESS);

    }

    @Test
    public void bookAdditionalSlots_return_SUCCESS1(){

        Structure structure = new Structure();
        structure.getVehicles_id_type_map().put("abc", "SUV");
        structure.getVehicles_slot_map().put("abc", new TimeSlot("13:00", "16:00"));

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("16:05", "19:05"));
        structure.getAllTimeSlots().put("SUV_R", timeSlots);
        structure.getAllTimeSlots().put("SUV_V", timeSlots);

        String returnValue = BookingController.bookAdditionalSlots(structure, "abc", "16:15");

        assert returnValue.equals(Constants.SUCCESS);

    }

    @Test
    public void bookAdditionalSlots_return_RACETRACK_FULL(){

        Structure structure = new Structure();
        structure.getVehicles_id_type_map().put("abc", "SUV");
        structure.getVehicles_slot_map().put("abc", new TimeSlot("13:00", "16:00"));

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("13:00", "16:00"));
        timeSlots.add(new TimeSlot("16:05", "19:05"));
        timeSlots.add(new TimeSlot("16:06", "19:06"));
        structure.getAllTimeSlots().put("SUV_R", timeSlots);
        structure.getAllTimeSlots().put("SUV_V", timeSlots);

        String returnValue = BookingController.bookAdditionalSlots(structure, "abc", "16:15");

        assert returnValue.equals(Constants.RACETRACK_FULL);

    }

}
