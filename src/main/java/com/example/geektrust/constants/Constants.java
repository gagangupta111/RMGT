package com.example.geektrust.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static Map<String, Integer> limitOfVehiclesAllowed = new HashMap<>();
    public static Map<String, Integer> costOfVehiclesTypesPerHour = new HashMap<>();

    static {

        limitOfVehiclesAllowed.put("BIKE_R", 4);
        limitOfVehiclesAllowed.put("CAR_R", 2);
        limitOfVehiclesAllowed.put("CAR_V", 1);
        limitOfVehiclesAllowed.put("SUV_R", 2);
        limitOfVehiclesAllowed.put("SUV_V", 1);
        limitOfVehiclesAllowed.put("BIKE_R", 4);
    }

    static {

        costOfVehiclesTypesPerHour.put("BIKE_R", 60);
        costOfVehiclesTypesPerHour.put("CAR_R", 120);
        costOfVehiclesTypesPerHour.put("CAR_V", 250);
        costOfVehiclesTypesPerHour.put("SUV_R", 200);
        costOfVehiclesTypesPerHour.put("SUV_V", 300);
    }

    public static final String BIKE = "BIKE";
    public static final String CAR = "CAR";
    public static final String SUV = "SUV";

    public static final String REGULAR = "REGULAR";
    public static final String VIP = "VIP";

    public static final String REVENUE = "REVENUE";
    public static final String BOOK = "BOOK";
    public static final String ADDITIONAL = "ADDITIONAL";

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
    public static final String INVALID_ENTRY_TIME = "INVALID_ENTRY_TIME";
    public static final String INVALID_EXIT_TIME = "INVALID_EXIT_TIME";
    public static final String RACETRACK_FULL = "RACETRACK_FULL";

    public static final String ENTRY_TIME_START_STRING = "13:00";
    public static final String ENTRY_TIME_END_STRING = "17:00";
    public static final Integer ENTRY_TIME_START_INTEGER = 780;
    public static final Integer ENTRY_TIME_END_INTEGER = 1020;
    public static final Integer EXIT_TIME_END_INTEGER = 1200;

    public static final Integer durationOfBooking = 3;
    public static final Integer freeDurationMinutes = 15;
    public static final Integer extraHourChargesPerHour = 50;
    public static final Integer minutesInHour = 60;
    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final String COLON = ":";
    public static final String R = "R";
    public static final String V = "V";
}
