package com.airline.menu;
import com.Airline;
import com.airline.*;
import com.airline.Plane.*;

public class AddPlaneCommand implements ICommand {
    private Airline airline;
    private Plane plane;

    public AddPlaneCommand(Airline airline) {
        this.airline = airline;
    }

    @Override
    public void execute() {
        airline.addPlane(plane);
    }

    @Override
    public void undo() {
        airline.removePlane(plane);
    }

}

