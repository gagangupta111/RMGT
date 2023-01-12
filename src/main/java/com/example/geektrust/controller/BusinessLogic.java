package com.example.geektrust.controller;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.model.Structure;
import com.example.geektrust.model.VehicleBookingRequest;
import com.example.geektrust.utility.Utility;

public class BusinessLogic {

    public static String bookNormalSlots(Structure structure, VehicleBookingRequest vehicleBookingRequest) {

        try {
            String key = vehicleBookingRequest.isRegularORVIP() ? Utility.regularVehicleKey(vehicleBookingRequest.getVehicle_type())
                    : Utility.vipVehicleKey(vehicleBookingRequest.getVehicle_type());
            structure.updateStructureNormalBookings(vehicleBookingRequest, key);
            structure.initializeRevenues();
            int changeCost = Constants.costOfVehiclesTypesPerHour.get(key)*Constants.durationOfBooking;
            structure.updateRevenues(vehicleBookingRequest, changeCost);
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
            structure.updateStructureAdditionalBookings(vehicleBookingRequest, key);
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
            structure.updateRevenues(vehicleBookingRequest, changeCost);
        }catch (Exception exception){
            return Constants.FAILURE;
        }

        return Constants.SUCCESS;
    }

    public static String printRevenue(Structure structure){

        return structure.getRevenues().get(Constants.REGULAR) + Constants.SPACE + structure.getRevenues().get(Constants.VIP);
    }


}
