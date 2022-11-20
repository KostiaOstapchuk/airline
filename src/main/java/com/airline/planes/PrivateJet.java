package com.airline.planes;

import java.io.Console;

public class PrivateJet extends Plane{
    private int speed;
    private Console console = System.console();

    public PrivateJet() {
        super(0, "Beech BE100 King Air", 100, 2, 2450);
        this.speed = 490;
    }

    public PrivateJet(int id, String model, int loadCapacity, int fuelConsumption, int range, int speed){
        super(id, model, loadCapacity, fuelConsumption, range);
        this.speed = speed;
    }

    @Override
    public int getPassengerCapacity() {
        return 1;
    }

    public String getType() {
        return "Private";
    }

    public float flightPrice() {
        System.out.println("Enter travel distance: ");
        int distance = Integer.parseInt(console.readLine());
        if(distance > range){
            System.out.println("This plane can't fly that far!");
            return 0;
        }
        float price = (distance * fuelConsumption * fuelPrice);
        float ticketPrice = price;
        float airportTax = price * 0.6f;
        System.out.println("Total price: " + (ticketPrice + airportTax) + "$");
        return price;
    }

    @Override
    public String toString() {
        return  "PrivateJet " + super.toString() + " speed: " + speed;
    }
}
