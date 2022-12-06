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
        System.out.println("Enter plane type: ");
        System.out.println("1. Airliner\n2. Cargo plane\n3. Private Jet\n");
        int coice = Integer.parseInt(console.readLine());
        System.out.println("\nChoose option: ");
        System.out.println("1. Default plane\n2. Custom plane\n");
        int option = Integer.parseInt(console.readLine());
        System.out.println("");
        switch(option){
            case 1:
                switch(coice){
                    case 1:
                        plane = new Airliner();
                        break;
                    case 2:
                        plane = new CargoPlane();
                        break;
                    case 3:
                        plane = new PrivateJet();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        return false;
                }
                plane.setId(airline.getPlanes().size());
                airline.addPlane(plane);
                System.out.println("Plane:\n" + plane + "\nadded\n");
                break;
            case 2:
                String model = console.readLine("Enter plane model: ");
                int loadCapacity = Integer.parseInt(console.readLine("Enter load capacity: "));
                int fuelConsumption = Integer.parseInt(console.readLine("Enter fuel consumption: "));
                int range = Integer.parseInt(console.readLine("Enter range: "));
                switch(coice){
                    case 1:
                        int passengerCapacity = Integer.parseInt(console.readLine("Enter passenger capacity: "));
                        plane = new Airliner(airline.getPlanes().size(), model, passengerCapacity, loadCapacity, fuelConsumption, range);
                        break;
                    case 2:
                        plane = new CargoPlane(airline.getPlanes().size(), model, loadCapacity, fuelConsumption, range);
                        break;
                    case 3:
                        int speed = Integer.parseInt(console.readLine("Enter speed: "));
                        plane = new PrivateJet(airline.getPlanes().size(), model, loadCapacity, fuelConsumption, range, speed);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        return false;
                }
                airline.addPlane(plane);
                System.out.println("\nPlane:\n" + plane + "\nadded\n");
                break;
            default:
                System.out.println("Invalid choice");
                return false;
        }
        return true;
    }

    @Override
    public void undo(){
        System.out.println("Plane:\n" + plane + "\nremoved\n");
        airline.removePlane(plane);
    }

}