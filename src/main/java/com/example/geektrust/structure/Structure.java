package com.example.geektrust.structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Structure {

    // revenues for R and VIP
    public Map<String, Integer> revenues = new HashMap<>();
    // all time slots lists for all vehicles types, R and VIP
    public Map<String, List<TimeSlot>> timeSlots = new HashMap<>();
    // vehicle id to vehicle type
    public Map<String, String> vehicles_id_map = new HashMap<>();
    // vehicle id to slot booked
    public Map<String, TimeSlot> vehicles_slot_map = new HashMap<>();
    public Structure() {
    }
}
