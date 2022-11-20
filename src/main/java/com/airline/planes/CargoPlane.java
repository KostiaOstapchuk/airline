package com.airline.planes;

import java.io.Console;

public class CargoPlane extends Plane{
    private int packageWeight = 70;
    private int palletWeight = 300;
    private int containerWeight = 2000;
    private Console console = System.console();

    public CargoPlane(){
        super(0, "Airbus Beluga", 48000, 16, 1600);
    }

    public CargoPlane(int id, String model, int loadCapacity, int fuelConsumption, int range){
        super(id, model, loadCapacity, fuelConsumption, range);
    }

    @Override
    public int getPassengerCapacity() {
        return 0;
    }

    public String getType() {
        return "Cargo";
    }

    public float flightPrice() {
        System.out.println("Enter travel distance: ");
        int distance = Integer.parseInt(console.readLine());
        if(distance > range){
            System.out.println("This plane can't fly that far!");
            return 0;
        }
        float price = 0;
        System.out.println("Choose cargo type: ");
        System.out.println("1. Package (up to 70 kg)\n2. Pallet (up to 250kg)\n3. Container (up to 2000kg)\n");
        int choice = Integer.parseInt(console.readLine());

        switch(choice){
            case 1:
                System.out.println("Enter the amount of packages (up to " + Math.floorDiv(loadCapacity, packageWeight)  + "): ");
                int packages = Integer.parseInt(console.readLine());
                price += packages * 30;
                break;
            case 2:
                System.out.println("Enter the amount of pallets (up to " + Math.floorDiv(loadCapacity, palletWeight)  + "): ");
                int pallets = Integer.parseInt(console.readLine());
                price += pallets * 230;
                break;
            case 3:
                System.out.println("Enter the container weight (up to " + containerWeight + "): ");
                int containerW = Integer.parseInt(console.readLine());
                price += containerW * 0.6f + 0.86f * distance;
                break;
        }
        System.out.println("Total price: " + price + "$");
        return price;
    }

    @Override
    public String toString() {
        return  "CargoPlane " + super.toString();
    }
}
