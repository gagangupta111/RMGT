package com.example.geektrust.model;

import com.example.geektrust.constants.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Structure {

    // revenues for R and VIP
    public Map<String, Integer> revenues = new HashMap<>();
    // all time slots lists for all vehicles types, R and VIP
    public Map<String, List<TimeSlot>> allTimeSlots = new HashMap<>();
    // vehicle id to vehicle type
    public Map<String, String> vehicles_id_type_map = new HashMap<>();
    // vehicle id to slot booked
    public Map<String, TimeSlot> vehicles_slot_map = new HashMap<>();
    public Structure() {
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
