package com.airline.commands;

import com.airline.Airline;
import com.airline.planes.*;
import java.io.Console;

public class ShowPlanesCommand extends Command {

    public ShowPlanesCommand(Airline airline) {
        super(airline);
    }

    @Override
    public boolean execute() {
        if(airline.getPlanes().size() == 0){
            System.out.println("\nNo planes to show\n");
            return false;
        }
        for (Plane plane : airline.getPlanes()) {
            System.out.println(plane);
        }
        System.out.println(' ');
        return false;
    }

    @Override
    public void undo(){
    }

}