package com.example.geektrust.controller;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.model.Structure;
import com.example.geektrust.model.TimeSlot;
import com.example.geektrust.model.VehicleBookingRequest;
import com.example.geektrust.utility.Utility;

import java.util.ArrayList;

public class BusinessLogic {

    public static String bookNormalSlots(Structure structure, VehicleBookingRequest vehicleBookingRequest) {

        try {
            String key = vehicleBookingRequest.isRegularORVIP() ? Utility.regularVehicleKey(vehicleBookingRequest.getVehicle_type())
                    : Utility.vipVehicleKey(vehicleBookingRequest.getVehicle_type());
            if (structure.getAllTimeSlots().get(key) == null){
                structure.getAllTimeSlots().put(key, new ArrayList<>());
            }
            structure.getAllTimeSlots().get(key).add(vehicleBookingRequest.getRequestedBookingSlot());

            structure.getVehicles_slot_map().put(vehicleBookingRequest.getVehicle_id(), vehicleBookingRequest.getRequestedBookingSlot());
            structure.getVehicles_id_type_map().put(vehicleBookingRequest.getVehicle_id(), vehicleBookingRequest.getVehicle_type());
            structure.initializeRevenues();

            int changeCost = Constants.costOfVehiclesTypesPerHour.get(key)*Constants.durationOfBooking;

            if (vehicleBookingRequest.isRegularORVIP()) {
                structure.getRevenues().put(Constants.REGULAR, structure.getRevenues().get(Constants.REGULAR) + changeCost);
            } else {
                structure.getRevenues().put(Constants.VIP, structure.getRevenues().get(Constants.VIP) + changeCost);
            }

        }catch (Exception exception){
            return Constants.FAILURE;
        }

        return Constants.SUCCESS;
    }

    public static String bookAdditionalSlots(Structure structure, VehicleBookingRequest vehicleBookingRequest) {

        try {

            int minDifference = Utility.timeDifferenceInMinutes(vehicleBookingRequest.getRequestedBookingSlot());
            String key = vehicleBookingRequest.isRegularORVIP() ? Utility.regularVehicleKey(vehicleBookingRequest.getVehicle_type())
                    : Utility.vipVehicleKey(vehicleBookingRequest.getVehicle_type());
            if (structure.getAllTimeSlots().get(key) == null){
                structure.getAllTimeSlots().put(key, new ArrayList<>());
            }

            structure.getAllTimeSlots().get(key).add(vehicleBookingRequest.getRequestedBookingSlot());
            structure.getVehicles_slot_map().put(vehicleBookingRequest.getVehicle_id(),
                    new TimeSlot(vehicleBookingRequest.getPreviousBookedSlot().getFromString(), vehicleBookingRequest.getRequestedBookingSlot().getToString()));

            structure.initializeRevenues();
            int hoursDifference = minDifference/Constants.minutesInHour;
            minDifference = minDifference - (hoursDifference*Constants.minutesInHour);
            int changeCost = Constants.ZERO;

            if (hoursDifference == Constants.ZERO && minDifference <= Constants.freeDurationMinutes){
                changeCost = Constants.ZERO;
            }else {
                changeCost += hoursDifference*Constants.extraHourChargesPerHour;
                changeCost += minDifference == Constants.ZERO ? Constants.ZERO : Constants.extraHourChargesPerHour;
            }

            if (vehicleBookingRequest.isRegularORVIP()) {
                structure.getRevenues().put(Constants.REGULAR, structure.getRevenues().get(Constants.REGULAR) + changeCost);
            } else {
                structure.getRevenues().put(Constants.VIP, structure.getRevenues().get(Constants.VIP) + changeCost);
            }

        }catch (Exception exception){
            return Constants.FAILURE;
        }

        return Constants.SUCCESS;
    }

    public static String printRevenue(Structure structure){

        return structure.getRevenues().get(Constants.REGULAR) + Constants.SPACE + structure.getRevenues().get(Constants.VIP);
    }


}
