package com.airline.menu;
import com.Airline;
import com.airline.*;
import com.airline.Plane.*;

public class RemovePlaneCommand implements ICommand {
    private Airline airline;
    private Plane plane;

    public RemovePlaneCommand(Airline airline) {
        this.airline = airline;
    }

    @Override
    public void execute() {
        airline.removePlane(plane);
    }

    @Override
    public void undo() {
        airline.addPlane(plane);
    }

}

