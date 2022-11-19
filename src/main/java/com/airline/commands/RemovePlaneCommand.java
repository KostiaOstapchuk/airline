package com.airline.commands;

import com.airline.Airline;
import com.airline.planes.*;
import java.io.Console;

public class RemovePlaneCommand extends Command {
    private Plane plane;
    private Console console = System.console();

    public RemovePlaneCommand(Airline airline) {
        super(airline);
    }

    @Override
    public boolean execute() {
        System.out.println("\nChoose plane:");
        for (int i = 0; i < airline.getPlanes().size(); i++) {
            System.out.println(i+1 + ". " + airline.getPlanes().get(i));
        }
        int choice = Integer.parseInt(console.readLine());
        this.plane = airline.getPlanes().get(choice-1);
        airline.removePlane(plane);
        return true;
    }

    @Override
    public void undo(){
        airline.addPlane(plane);
    }

}