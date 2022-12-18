package com.airline.commands;

import com.airline.Airline;
import com.airline.planes.*;
import java.io.Console;
import java.util.ArrayList;

public class AddPlaneCommand extends Command {
    private Plane plane;
    private Console console = System.console();;

    public AddPlaneCommand(Airline airline) {
        super(airline);
    }

    @Override
    public boolean execute(ArrayList<String> params) {
        Plane plane;
        String type = params.get(0);
        String model = params.get(1);
        int loadCapacity = Integer.parseInt(params.get(2).equals("") ? "0" : params.get(2));
        int passengerCapacity = Integer.parseInt(params.get(3).equals("") ? "0" : params.get(3));
        int fuelConsumption = Integer.parseInt(params.get(4));
        int range = Integer.parseInt(params.get(5));
        int speed = Integer.parseInt(params.get(6).equals("") ? "0" : params.get(6));
        switch (params.get(0)) {
            case "Airliner":
                plane = new Airliner(airline.getPlanes().size() + 1, model, loadCapacity, fuelConsumption, passengerCapacity, range);
                break;
            case "CargoPlane":
                plane = new CargoPlane(airline.getPlanes().size() + 1, model, loadCapacity, fuelConsumption, range);        
                break;
            case "PrivateJet":
                plane = new PrivateJet(airline.getPlanes().size() + 1, model, loadCapacity, fuelConsumption, range, speed);    
                break;
            default:
                plane = null;
                break;
        }
        airline.addPlane(plane);
        return true;
    }

    @Override
    public void undo(){
        System.out.println("Plane:\n" + plane + "\nremoved\n");
        airline.removePlane(plane);
    }

}