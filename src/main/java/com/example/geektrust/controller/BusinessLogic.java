package com.example.geektrust.controller;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.structure.Structure;
import com.example.geektrust.structure.TimeSlot;
import com.example.geektrust.utility.Utility;

import java.util.ArrayList;

public class BusinessLogic {

    public static String bookNormalSlots(Structure structure, String vehicle_id, String vehicle_type, boolean regular, TimeSlot slot) {

        try {
            String key = regular ? Utility.regularVehicleKey(vehicle_type) : Utility.vipVehicleKey(vehicle_type);
            if (structure.timeSlots.get(key) == null){
                structure.timeSlots.put(key, new ArrayList<>());
            }
            structure.timeSlots.get(key).add(slot);

            structure.vehicles_slot_map.put(vehicle_id, slot);
            structure.vehicles_id_map.put(vehicle_id, vehicle_type);
            if (structure.revenues.get(Constants.REGULAR) == null){
                structure.revenues.put(Constants.REGULAR, 0);
            }
            if (structure.revenues.get(Constants.VIP) == null){
                structure.revenues.put(Constants.VIP, 0);
            }

            int changeCost = Constants.costOfVehiclesTypesPerHour.get(key)*Constants.durationOfBooking;

            if (regular) {
                structure.revenues.put(Constants.REGULAR, structure.revenues.get(Constants.REGULAR) + changeCost);
            } else {
                structure.revenues.put(Constants.VIP, structure.revenues.get(Constants.VIP) + changeCost);
            }

        }catch (Exception exception){
            return Constants.FAILURE;
        }

        return Constants.SUCCESS;
    }

    public static String bookAdditionalSlots(Structure structure, String vehicle_id, String vehicle_type, String entry_time, String exit_time) {

        return "";
    }

    public static String printRevenue(Structure structure){


        return "";
    }


}
