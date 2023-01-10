package com.example.geektrust;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.controller.BookingController;
import com.example.geektrust.inputOutput.InputOutput;
import com.example.geektrust.structure.Structure;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Structure structure = new Structure();
        String space = " ";

        try {

            FileInputStream inputStream = new FileInputStream(args[0]);
            Scanner scanner = new Scanner(inputStream); // file to be scanned

            while (scanner.hasNextLine()) {

                String currentLine = scanner.nextLine();
                String[] splits = currentLine.split(space);
                System.out.println(InputOutput.receiveAndForwardInput(splits, structure));
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Exception occurred:" + e.getLocalizedMessage());
        }
    }
}
