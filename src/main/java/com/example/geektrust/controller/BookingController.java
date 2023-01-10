package com.example.geektrust.controller;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.structure.Structure;
import com.example.geektrust.structure.TimeSlot;
import com.example.geektrust.utility.Utility;

public class BookingController {

    // todo : write single if and return result if met condition one by one
    public static String bookNormalSlots(Structure structure, String vehicle_id, String vehicle_type, String entry_time){

        TimeSlot timeSlot = new TimeSlot(entry_time, Utility.timeStringToMinutes(entry_time, Constants.durationOfBooking));
        if (!Validations.validateNormalBookingEntryTime(structure, vehicle_id, vehicle_type, timeSlot)){
            return Constants.INVALID_ENTRY_TIME;
        }

        if (Validations.validateNormalBookingAvailability(structure, vehicle_id, vehicle_type, true, timeSlot)){
            return BusinessLogic.bookNormalSlots(structure, vehicle_id, vehicle_type,true, timeSlot);
        }else {
            if (Validations.validateNormalBookingAvailability(structure, vehicle_id, vehicle_type, false, timeSlot)){
                return BusinessLogic.bookNormalSlots(structure, vehicle_id, vehicle_type,false, timeSlot);
            }else {
                return Constants.RACETRACK_FULL;
            }
        }
    }

    public static String bookAdditionalSlots(Structure structure, String vehicle_id, String exit_time){

        TimeSlot timeSlot = new TimeSlot("", exit_time);
        String vehicle_type = structure.vehicles_id_map.get(vehicle_id);
        TimeSlot slot = structure.vehicles_slot_map.get(vehicle_id);

        if (Validations.validateAdditionalBookingEntryTime(structure, vehicle_id, vehicle_type, timeSlot)){
            if (Validations.validateAdditionalBookingExitTime(structure, vehicle_id, vehicle_type, timeSlot)){
                if (Validations.validateAdditionalBookingAvailability(structure, vehicle_id, vehicle_type, timeSlot)){
                    return BusinessLogic.bookAdditionalSlots(structure, vehicle_id, vehicle_type, slot.getFromString(), exit_time);
                }else {
                    return Constants.RACETRACK_FULL;
                }
            }else {
                return Constants.INVALID_EXIT_TIME;
            }
        }else {
            return Constants.INVALID_ENTRY_TIME;
        }
    }

    public static String printRevenue(Structure structure){

        return BusinessLogic.printRevenue(structure);
    }

}
