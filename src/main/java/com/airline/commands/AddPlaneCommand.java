package com.airline.commands;

import com.airline.Airline;
import com.airline.planes.*;
import java.io.Console;

public class AddPlaneCommand extends Command {
    private Plane plane;
    private Console console = System.console();;

    public AddPlaneCommand(Airline airline) {
        super(airline);
    }

    @Override
    public boolean execute() {
        int id = airline.getPlanes().size() + 1;
        System.out.println("Plane model:");
        String model = console.readLine();
        System.out.println("Plane passenger capacity:");
        int pc = Integer.parseInt(console.readLine());
        System.out.println("Plane load capacity:");
        int lc = Integer.parseInt(console.readLine());
        System.out.println("Plane fuel consumption:");
        int fc = Integer.parseInt(console.readLine());
        System.out.println("Plane range:");
        int range = Integer.parseInt(console.readLine());
        System.out.println(' ');
        
        this.plane = new PassengerPlane(id, model, pc, lc, fc, range);
        airline.addPlane(plane);
        return true;
    }

    @Override
    public void undo(){
        airline.removePlane(plane);
    }

}