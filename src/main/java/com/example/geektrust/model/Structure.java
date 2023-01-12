package com.example.geektrust.model;

import com.example.geektrust.constants.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Structure {

    // revenues for R and VIP
    private Map<String, Integer> revenues;
    // all time slots lists for all vehicles types, R and VIP
    private Map<String, List<TimeSlot>> allTimeSlots;
    // vehicle id to vehicle type
    private Map<String, String> vehicles_id_type_map;
    // vehicle id to slot booked
    private Map<String, TimeSlot> vehicles_slot_map;
    public Structure() {
        revenues = new HashMap<>();
        // all time slots lists for all vehicles types, R and VIP
        allTimeSlots = new HashMap<>();
        // vehicle id to vehicle type
        vehicles_id_type_map = new HashMap<>();
        // vehicle id to slot booked
        vehicles_slot_map = new HashMap<>();
    }

    public Map<String, Integer> getRevenues() {
        return revenues;
    }

    public Map<String, List<TimeSlot>> getAllTimeSlots() {
        return allTimeSlots;
    }

    public Map<String, String> getVehicles_id_type_map() {
        return vehicles_id_type_map;
    }

    public Map<String, TimeSlot> getVehicles_slot_map() {
        return vehicles_slot_map;
    }

    public void initializeRevenues(){
        if (revenues.get(Constants.REGULAR) == null){
            revenues.put(Constants.REGULAR, Constants.ZERO);
        }
        if (revenues.get(Constants.VIP) == null){
            revenues.put(Constants.VIP, Constants.ZERO);
        }
    }

    public void initializeAllTimeSlots(String key){
        if (allTimeSlots.get(key) == null){
            allTimeSlots.put(key, new ArrayList<>());
        }
    }

    public void updateRevenues(VehicleBookingRequest vehicleBookingRequest, int changeCost){
        if (vehicleBookingRequest.isRegularORVIP()) {
            revenues.put(Constants.REGULAR, revenues.get(Constants.REGULAR) + changeCost);
        } else {
            revenues.put(Constants.VIP, revenues.get(Constants.VIP) + changeCost);
        }
    }

    public void updateStructureNormalBookings(VehicleBookingRequest vehicleBookingRequest, String key){
        initializeAllTimeSlots(key);
        allTimeSlots.get(key).add(vehicleBookingRequest.getRequestedBookingSlot());
        vehicles_slot_map.put(vehicleBookingRequest.getVehicle_id(), vehicleBookingRequest.getRequestedBookingSlot());
        vehicles_id_type_map.put(vehicleBookingRequest.getVehicle_id(), vehicleBookingRequest.getVehicle_type());
    }

    public void updateStructureAdditionalBookings(VehicleBookingRequest vehicleBookingRequest, String key){
        initializeAllTimeSlots(key);
        allTimeSlots.get(key).add(vehicleBookingRequest.getRequestedBookingSlot());
        vehicles_slot_map.put(vehicleBookingRequest.getVehicle_id(),
                new TimeSlot(vehicleBookingRequest.getPreviousBookedSlot().getFromString(), vehicleBookingRequest.getRequestedBookingSlot().getToString()));

    }



}
