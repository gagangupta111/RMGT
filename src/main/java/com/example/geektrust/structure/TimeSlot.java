package com.example.geektrust.structure;

public class TimeSlot {

    private String from;
    private String to;

    public TimeSlot(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public Integer getFromInteger() {
        return 0;
    }

    public Integer getToInteger() {
        return 0;
    }

    public String getFromString() {
        return from;
    }

    public String getToString() {
        return to;
    }

}
