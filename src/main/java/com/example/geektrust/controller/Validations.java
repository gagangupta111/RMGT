package com.example.geektrust.controller;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.structure.Structure;
import com.example.geektrust.structure.TimeSlot;
import com.example.geektrust.utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Validations {

    public static boolean validateNormalBookingEntryTime(Structure structure, String vehicle_id, String vehicle_type, TimeSlot slot){

        return slot.getFromInteger() >= Constants.ENTRY_TIME_START_INTEGER
                && slot.getFromInteger() <= Constants.ENTRY_TIME_END_INTEGER;
    }

    public static boolean validateNormalBookingAvailability(Structure structure, String vehicle_id, String vehicle_type, boolean regular, TimeSlot slot){

        int bookingsConflicts = 0;
        String key = regular ? Utility.regularVehicleKey(vehicle_type) : Utility.vipVehicleKey(vehicle_type);
        int limitBookingsConflicts = Constants.limitOfVehiclesAllowed.get(key);
        List<TimeSlot> timeSlots = structure.timeSlots.get(key) == null ? new ArrayList<>() : structure.timeSlots.get(key);

        for (TimeSlot timeSlot : timeSlots){
            if ((slot.getFromInteger() >= timeSlot.getFromInteger() && slot.getFromInteger() <= timeSlot.getToInteger())
            || (slot.getToInteger() >= timeSlot.getFromInteger() && slot.getToInteger() <= timeSlot.getToInteger())){
                bookingsConflicts++;
            }

            if (bookingsConflicts >= limitBookingsConflicts){
                return false;
            }
        }

        return true;
    }

    public static boolean validateAdditionalBookingEntryTime(Structure structure, String vehicle_id, String vehicle_type, TimeSlot slot){

        return slot.getFromInteger() >= Constants.ENTRY_TIME_START_INTEGER
                && slot.getFromInteger() <= Constants.ENTRY_TIME_END_INTEGER;
    }


    public static boolean validateAdditionalBookingExitTime(Structure structure, String vehicle_id, String vehicle_type, TimeSlot slot){

        return slot.getToInteger() <= Constants.EXIT_TIME_END_INTEGER;
    }

    public static boolean validateAdditionalBookingAvailability(Structure structure, String vehicle_id, String vehicle_type, TimeSlot slot){

        int bookingsConflicts = 0;
        String key = Utility.regularVehicleKey(vehicle_type);
        int limitBookingsConflicts = Constants.limitOfVehiclesAllowed.get(key);
        List<TimeSlot> timeSlots = structure.timeSlots.get(key);

        for (TimeSlot timeSlot : timeSlots){
            if ((slot.getFromInteger() >= timeSlot.getFromInteger() && slot.getFromInteger() <= timeSlot.getToInteger())
                    || (slot.getToInteger() >= timeSlot.getFromInteger() && slot.getToInteger() <= timeSlot.getToInteger())){
                bookingsConflicts++;
            }

            if (bookingsConflicts >= limitBookingsConflicts){
                return false;
            }
        }

        return true;
    }

}
