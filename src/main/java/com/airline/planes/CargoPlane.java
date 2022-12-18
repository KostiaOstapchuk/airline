package com.airline.planes;

import java.io.Console;
import com.airline.UserInput;
import java.util.ArrayList;

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


    public float flightPrice(ArrayList<String> params) {
        float price = 0;
        int distance = Integer.parseInt(params.get(0));
        int packages = Integer.parseInt(params.get(3));
        int pallets = Integer.parseInt(params.get(4));
        int containers = Integer.parseInt(params.get(5));
        price += packages * 30;
        price += pallets * 230;
        price += containers * 500 + fuelPrice * distance;
        System.out.println("Total price: " + price + "$");
        return price;
    }

    @Override
    public String toString() {
        return  "CargoPlane " + super.toString();
    }
}
