package com.airline.commands;

import com.airline.Airline;
import com.airline.planes.*;
import java.io.Console;

public class CalculatePriceCommand extends Command {
    private Console console = System.console();
    private String[] options = {
        "1. Commercial",
        "2. Cargo",
        "3. Private"
    };

    public CalculatePriceCommand(Airline airline) {
        super(airline);
    }

    private void planesByType(String type) {
        for (Plane plane : airline.getPlanes()) {
            if (plane.getType().equals(type)) {
                System.out.println(plane);
            }
        }
    }
    

    private void calculateCommercialPrice(){
        System.out.println("Enter plane id: \n");
        planesByType("Commertial");
        int id = Integer.parseInt(console.readLine());
        Plane plane = airline.getPlane(id);
        plane.flightPrice();
    }

    private void calculateCargoPrice(){
        System.out.println("Enter plane id: \n");
        planesByType("Cargo");
        int id = Integer.parseInt(console.readLine());
        Plane plane = airline.getPlane(id);
        plane.flightPrice();
    }

    private void calculatePrivatePrice(){
        System.out.println("Enter plane id: \n");
        planesByType("Private");
        int id = Integer.parseInt(console.readLine());
        Plane plane = airline.getPlane(id);
        plane.flightPrice();
    }

    @Override
    public boolean execute() {
        System.out.println("Choose flight type:");
        for (String option : options) {
            System.out.println(option);
        }
        int choice = Integer.parseInt(console.readLine());
        switch (choice) {
            case 1:
                calculateCommercialPrice();
                break;
            case 2:
                calculateCargoPrice();
                break;
            case 3:
                calculatePrivatePrice();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

        return false;
    }

    @Override
    public void undo(){
        return;
    }

}
