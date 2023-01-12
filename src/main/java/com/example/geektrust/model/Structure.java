package com.example.geektrust.model;

import com.example.geektrust.constants.Constants;

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

}
