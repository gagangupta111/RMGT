package com.example.geektrust.structure;

public class TimeSlot {

    private String from;
    private String to;

    private int fromInt;
    private int toInt;

    public TimeSlot(String from, String to) {
        this.from = from;
        this.to = to;

        if (from != null && !"".equals(from)){
            String[] hourMin = from.split(":");
            int hour = Integer.parseInt(hourMin[0]);
            int mins = Integer.parseInt(hourMin[1]);
            int hoursInMins = hour * 60;
            fromInt = hoursInMins + mins;
        }

        if (to != null && !"".equals(to)){
            String[] hourMin = to.split(":");
            int hour = Integer.parseInt(hourMin[0]);
            int mins = Integer.parseInt(hourMin[1]);
            int hoursInMins = hour * 60;
            toInt = hoursInMins + mins;
        }

    }

    public int getFromInteger() {
        return fromInt;
    }

    public int getToInteger() {
        return toInt;
    }

    public String getFromString() {
        return from;
    }

    public String getToString() {
        return to;
    }

}
