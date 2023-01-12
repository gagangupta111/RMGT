package com.example.geektrust.controller;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.model.Structure;
import com.example.geektrust.model.TimeSlot;
import com.example.geektrust.model.VehicleBookingRequest;
import com.example.geektrust.utility.Utility;

public class BookingController {

    public static String bookNormalSlots(Structure structure, String vehicle_id, String vehicle_type, String entry_time){

        TimeSlot timeSlot = new TimeSlot(entry_time, Utility.timeStringAddDuration(entry_time, Constants.durationOfBooking));
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest(vehicle_id, vehicle_type, entry_time, "", null, timeSlot, true);

        if (!Validations.validateBookingEntryTime(structure, vehicleBookingRequest)){
            return Constants.INVALID_ENTRY_TIME;
        }

        if (Validations.validateBookingAvailability(structure, vehicleBookingRequest)){
            return BusinessLogic.bookNormalSlots(structure, vehicleBookingRequest);
        }else {
            vehicleBookingRequest.setRegularORVIP(false);
            if (!Constants.BIKE.equals(vehicle_type) && Validations.validateBookingAvailability(structure, vehicleBookingRequest)){
                return BusinessLogic.bookNormalSlots(structure, vehicleBookingRequest);
            }else {
                return Constants.RACETRACK_FULL;
            }
        }
    }

    public static String bookAdditionalSlots(Structure structure, String vehicle_id, String exit_time){

        TimeSlot requestedTimeSlot = new TimeSlot("", exit_time);
        String vehicle_type = structure.vehicles_id_type_map.get(vehicle_id);
        TimeSlot previousBookingSlot = structure.vehicles_slot_map.get(vehicle_id);

        String newEntryTime = Utility.timeStringAddMinute(previousBookingSlot.getToString(), 1);
        requestedTimeSlot = new TimeSlot(newEntryTime, requestedTimeSlot.getToString());
        VehicleBookingRequest vehicleBookingRequest = new VehicleBookingRequest(vehicle_id, vehicle_type, "", exit_time, previousBookingSlot, requestedTimeSlot, true);

        if (!Validations.validateAdditionalBookingExitTime(structure, vehicleBookingRequest)){
            return Constants.INVALID_EXIT_TIME;
        }

        if (Validations.validateBookingAvailability(structure, vehicleBookingRequest)){
            return BusinessLogic.bookAdditionalSlots(structure, vehicleBookingRequest);
        }else {
            vehicleBookingRequest.setRegularORVIP(false);
            if (!Constants.BIKE.equals(vehicle_type) && Validations.validateBookingAvailability(structure, vehicleBookingRequest)){
                return BusinessLogic.bookAdditionalSlots(structure, vehicleBookingRequest);
            }else {
                return Constants.RACETRACK_FULL;
            }
        }
    }

    public static String printRevenue(Structure structure){

        return BusinessLogic.printRevenue(structure);
    }

}
