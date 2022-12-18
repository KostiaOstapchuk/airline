package com.airline.commands;

import com.airline.Airline;
import com.airline.planes.*;
import java.util.ArrayList;

public class RemovePlaneCommand extends Command {
    private Plane plane;

    public RemovePlaneCommand(Airline airline) {
        super(airline);
    }

    @Override
    public boolean execute(ArrayList<String> params) {
        int id = Integer.parseInt(params.get(0));
        plane = airline.getPlane(id);
        airline.removePlane(plane);
        return true;
    }

    @Override
    public void undo(){
        System.out.println("\nPlane:\n" + plane + "\nadded\n");
        airline.addPlane(plane);
    }

}