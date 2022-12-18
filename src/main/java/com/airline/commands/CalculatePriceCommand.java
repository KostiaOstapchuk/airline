package com.airline.commands;

import com.airline.Airline;
import com.airline.planes.*;
import java.io.Console;
import com.airline.UserInput;
import java.util.ArrayList;

public class CalculatePriceCommand extends Command {
    private Console console = System.console();
    private String[] options = {
        "Commertial",
        "Cargo",
        "Private"
    };

    public CalculatePriceCommand(Airline airline) {
        super(airline);
    }

    private void printPlanesByType(String type) {
        for (Plane plane : airline.getPlanes()) {
            if (plane.getType().equals(type)) {
                System.out.println(plane);
            }
        }
    }

    @Override
    public boolean execute(ArrayList<String> params) {
        System.out.println("Choose flight type:");
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + 1 + ". " + options[i]);
        }
        int choice = Integer.parseInt(console.readLine());
        printPlanesByType(options[choice - 1]);
        
        int id = Integer.parseInt(console.readLine("Enter plane id: "));
        
        Plane plane = airline.getPlane(id);
        UserInput userInput = plane.takeUserInput();
        plane.flightPrice(userInput);

        return false;
    }

    @Override
    public void undo(){
        return;
    }

}
