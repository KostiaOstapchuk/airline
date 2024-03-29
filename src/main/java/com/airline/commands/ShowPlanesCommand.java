package com.airline.commands;

import com.airline.Airline;
import com.airline.planes.*;
import java.io.Console;
import java.util.ArrayList;

public class ShowPlanesCommand extends Command {

    private Console console = System.console();
    private String[] options = {
        "All",
        "By parameter"
    };

    private String[] parameters = {
        "ID",
        "Load capacity",
        "Range",
        "Fuel consumption"
    };

    public ShowPlanesCommand(Airline airline) {
        super(airline);
    }

    private void showByParameter(){
        System.out.println("\nChoose parameter:");
        for (int i = 0; i < parameters.length; i++) {
            System.out.println(i+1 + ". " + parameters[i]);
        }
        int choice = Integer.parseInt(console.readLine("\n"));
        float lowerBound, upperBound;
        System.out.println(' ');
        lowerBound = Float.parseFloat(console.readLine("Lower bound: "));
        upperBound = Float.parseFloat(console.readLine("Upper bound: "));
        System.out.println(' ');
        switch (choice) {
            case 1:
                airline.getPlanes().stream()
                    .filter(plane -> plane.getId() >= lowerBound && plane.getId() <= upperBound)
                    .forEach(plane -> System.out.println(plane.toString()));
                break;
            case 2:
                airline.getPlanes().stream()
                    .filter(plane -> plane.getLoadCapacity() >= lowerBound && plane.getLoadCapacity() <= upperBound)
                    .forEach(plane -> System.out.println(plane.toString()));
                break;
            case 3:
                airline.getPlanes().stream()
                    .filter(plane -> plane.getRange() >= lowerBound && plane.getRange() <= upperBound)
                    .forEach(plane -> System.out.println(plane.toString()));
                break;
            case 4:
                airline.getPlanes().stream()
                    .filter(plane -> plane.getFuelConsumption() >= lowerBound && plane.getFuelConsumption() <= upperBound)
                    .forEach(plane -> System.out.println(plane.toString()));
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

    }

    @Override
    public boolean execute(ArrayList<String> params) {
        if(airline.getPlanes().size() == 0){
            System.out.println("\nNo planes to show\n");
            return false;
        }
        System.out.println("\nShow:");
        for (int i = 0; i < options.length; i++) {
            System.out.println(i+1 + ". " + options[i]);
        }
        int choice = Integer.parseInt(console.readLine("\n"));
        switch (choice) {
            case 1:
                System.out.println("\nAll planes:");
                airline.getPlanes().forEach(plane -> System.out.println(plane.toString()));
                System.out.println(' ');
                break;
            case 2:
                showByParameter();
                break;
            default:
                System.out.println("Invalid option");
                return false;
        }
        System.out.println(' ');
        return false;
    }

    @Override
    public void undo(){
    }

}