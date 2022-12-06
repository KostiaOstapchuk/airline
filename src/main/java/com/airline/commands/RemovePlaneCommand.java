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
        if(airline.getPlanes().size() == 0){
            System.out.println("\nNo planes to remove\n");
            return false;
        }
        System.out.println("\nChoose plane:");
        for (int i = 0; i < airline.getPlanes().size(); i++) {
            System.out.println(i+1 + ". " + airline.getPlanes().get(i));
        }
        System.out.println("");
        int choice = Integer.parseInt(console.readLine());
        this.plane = airline.getPlanes().get(choice-1);
        airline.removePlane(plane);
        System.out.println("\nPlane:\n" + plane + "\nremoved\n");
        return true;
    }

    @Override
    public void undo(){
        System.out.println("\nPlane:\n" + plane + "\nadded\n");
        airline.addPlane(plane);
    }

}