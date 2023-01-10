package com.example.geektrust.inputOutput;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.controller.BookingController;
import com.example.geektrust.structure.Structure;

public class InputOutput {

    public static String receiveAndForwardInput(String[] splits, Structure structure){

        if (Constants.BOOK.equals(splits[0])){
            String vehicle_type = splits[1];
            String vehicle_id = splits[2];
            String entry_time = splits[3];
            return BookingController.bookNormalSlots(structure, vehicle_id, vehicle_type, entry_time);
        }else if (Constants.ADDITIONAL.equals(splits[0])){
            String vehicle_id = splits[1];
            String exit_time = splits[2];
            return BookingController.bookAdditionalSlots(structure, vehicle_id, exit_time);
        }else{
            return BookingController.printRevenue(structure);
        }
    }

}
