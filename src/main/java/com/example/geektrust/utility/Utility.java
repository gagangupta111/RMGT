package com.example.geektrust.utility;

public class Utility {

    public static int timeStringToMinutes(String time){

        String[] hourMin = time.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int hoursInMins = hour * 60;
        return hoursInMins + mins;
    }

    public static String timeStringToMinutes(String time, int duration){

        String[] hourMin = time.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        hour = hour + duration;
        return hour + ":" + mins;
    }

    public static String regularVehicleKey(String vehicle_type){

        return vehicle_type.toUpperCase() + "_" + "R";
    }

    public static String vipVehicleKey(String vehicle_type){

        return vehicle_type.toUpperCase() + "_" + "V";
    }

}
