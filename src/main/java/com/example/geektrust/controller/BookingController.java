package com.example.geektrust.controller;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.structure.Structure;
import com.example.geektrust.structure.TimeSlot;

public class BookingController {

    // todo : write single if and return result if met condition one by one
    public static String bookNormalSlots(Structure structure, String vehicle_id, String vehicle_type, String entry_time){

        if (Validations.validateNormalBookingEntryTime(structure, vehicle_id, vehicle_type, entry_time)){
            if (Validations.validateNormalBookingAvailability(structure, vehicle_id, vehicle_type, entry_time)){
                    return BusinessLogic.bookNormalSlots(structure, vehicle_id, vehicle_type, entry_time);
            }else {
                return Constants.RACETRACK_FULL;
            }
        }else {
            return Constants.INVALID_ENTRY_TIME;
        }
    }

    public static String bookAdditionalSlots(Structure structure, String vehicle_id, String exit_time){

        String vehicle_type = structure.vehicles_id_map.get(vehicle_id);
        TimeSlot slot = structure.vehicles_slot_map.get(vehicle_id);

        if (Validations.validateAdditionalBookingEntryTime(structure, vehicle_id, vehicle_type, slot.getFromString())){
            if (Validations.validateAdditionalBookingExitTime(structure, vehicle_id, vehicle_type, slot.getToString())){
                if (Validations.validateAdditionalBookingAvailability(structure, vehicle_id, vehicle_type, slot.getFromString(), slot.getToString())){
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
