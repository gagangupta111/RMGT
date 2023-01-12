package com.example.geektrust.utility;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.model.TimeSlot;

public class Utility {

    public static int timeDifferenceInMinutes(TimeSlot requestedTimeSlot){

        String[] from = requestedTimeSlot.getFromString().split(Constants.COLON);
        String[] to = requestedTimeSlot.getToString().split(Constants.COLON);
        int hourFrom = Integer.parseInt(from[0]);
        int minFrom = Integer.parseInt(from[1]);
        int hourTo = Integer.parseInt(to[0]);
        int minTo = Integer.parseInt(to[1]);
        int minDifference = ((hourTo - hourFrom)* Constants.minutesInHour) + (minTo - minFrom);
        return minDifference;
    }

    public static String timeStringAddDuration(String time, int hoursDuration){

        String[] hourMin = time.split(Constants.COLON);
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        hour = hour + hoursDuration;
        String hourString = (hour + "").length() == 1 ? "0" + hour : hour + "";
        String minString = (mins + "").length() == 1 ? "0" + mins : mins + "";
        return hourString + Constants.COLON + minString;
    }

    public static String timeStringAddMinute(String time, int addMinutes){

        String[] hourMin = time.split(Constants.COLON);
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);

        mins += addMinutes;
        if (mins >= Constants.minutesInHour){
            mins = mins - Constants.minutesInHour;
            hour++;
        }
        String hourString = (hour + "").length() == 1 ? "0" + hour : hour + "";
        String minString = (mins + "").length() == 1 ? "0" + mins : mins + "";
        return hourString + Constants.COLON + minString;
    }

    public static String regularVehicleKey(String vehicle_type){

        return vehicle_type.toUpperCase() + "_" + Constants.R;
    }

    public static String vipVehicleKey(String vehicle_type){

        return vehicle_type.toUpperCase() + "_" + Constants.V;
    }

}
