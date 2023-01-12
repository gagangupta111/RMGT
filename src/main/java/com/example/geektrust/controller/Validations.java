package com.example.geektrust.controller;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.model.Structure;
import com.example.geektrust.model.TimeSlot;
import com.example.geektrust.model.VehicleBookingRequest;
import com.example.geektrust.utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Validations {

    public static boolean validateBookingEntryTime(Structure structure, VehicleBookingRequest vehicleBookingRequest){

        return vehicleBookingRequest.getRequestedBookingSlot().getFromInteger() >= Constants.ENTRY_TIME_START_INTEGER
                && vehicleBookingRequest.getRequestedBookingSlot().getFromInteger() <= Constants.ENTRY_TIME_END_INTEGER;
    }

    public static boolean validateBookingAvailability(Structure structure, VehicleBookingRequest vehicleBookingRequest){

        int bookingsConflicts = Constants.ZERO;
        String key = vehicleBookingRequest.isRegularORVIP() ? Utility.regularVehicleKey(vehicleBookingRequest.getVehicle_type())
                : Utility.vipVehicleKey(vehicleBookingRequest.getVehicle_type());
        int limitBookingsConflicts = Constants.limitOfVehiclesAllowed.get(key);
        List<TimeSlot> timeSlots = structure.allTimeSlots.get(key) == null ? new ArrayList<>() : structure.allTimeSlots.get(key);

        for (TimeSlot timeSlot : timeSlots){
            if ((vehicleBookingRequest.getRequestedBookingSlot().getFromInteger() >= timeSlot.getFromInteger()
                    && vehicleBookingRequest.getRequestedBookingSlot().getFromInteger() < timeSlot.getToInteger())
            || (vehicleBookingRequest.getRequestedBookingSlot().getToInteger() > timeSlot.getFromInteger() && vehicleBookingRequest.getRequestedBookingSlot().getToInteger() <= timeSlot.getToInteger())){
                bookingsConflicts++;
            }

            if (bookingsConflicts >= limitBookingsConflicts){
                return false;
            }
        }

        return true;
    }

    public static boolean validateAdditionalBookingExitTime(Structure structure, VehicleBookingRequest vehicleBookingRequest){

        return vehicleBookingRequest.getRequestedBookingSlot().getToInteger() <= Constants.EXIT_TIME_END_INTEGER
                && vehicleBookingRequest.getRequestedBookingSlot().getToInteger() >= Constants.ENTRY_TIME_START_INTEGER;
    }

}
