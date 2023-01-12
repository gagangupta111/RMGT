package com.example.geektrust.model;

public class VehicleBookingRequest {

    private String vehicle_id;
    private String vehicle_type;
    private String entry_time;
    private String exit_time;
    private TimeSlot previousBookedSlot;
    private TimeSlot requestedBookingSlot;

    private boolean regularORVIP;

    public VehicleBookingRequest() {
    }

    public VehicleBookingRequest(String vehicle_id, String vehicle_type, String entry_time, String exit_time,
                                 TimeSlot previousBookedSlot, TimeSlot requestedBookingSlot, boolean regularORVIP) {
        this.vehicle_id = vehicle_id;
        this.vehicle_type = vehicle_type;
        this.entry_time = entry_time;
        this.exit_time = exit_time;
        this.previousBookedSlot = previousBookedSlot;
        this.requestedBookingSlot = requestedBookingSlot;
        this.regularORVIP = regularORVIP;
    }

    public boolean isRegularORVIP() {
        return regularORVIP;
    }

    public void setRegularORVIP(boolean regularORVIP) {
        this.regularORVIP = regularORVIP;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(String entry_time) {
        this.entry_time = entry_time;
    }

    public String getExit_time() {
        return exit_time;
    }

    public void setExit_time(String exit_time) {
        this.exit_time = exit_time;
    }

    public TimeSlot getPreviousBookedSlot() {
        return previousBookedSlot;
    }

    public void setPreviousBookedSlot(TimeSlot previousBookedSlot) {
        this.previousBookedSlot = previousBookedSlot;
    }

    public TimeSlot getRequestedBookingSlot() {
        return requestedBookingSlot;
    }

    public void setRequestedBookingSlot(TimeSlot requestedBookingSlot) {
        this.requestedBookingSlot = requestedBookingSlot;
    }
}
