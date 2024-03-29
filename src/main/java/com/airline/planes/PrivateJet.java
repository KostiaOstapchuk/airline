package com.airline.planes;

import java.util.ArrayList;

import com.airline.UserInput;


public class PrivateJet extends Plane{
    private float speed;
    private float hourlyRate;

    public PrivateJet() {
        super(0, "Beech BE100 King Air", 100, 2, 2450);
        this.speed = 490;
        this.hourlyRate = speed * fuelConsumption * fuelPrice;
    }

    public PrivateJet(int id, String model, int loadCapacity, int fuelConsumption, int range, int speed){
        super(id, model, loadCapacity, fuelConsumption, range);
        this.speed = speed;
        this.hourlyRate = speed * fuelConsumption * fuelPrice;
    }

    @Override
    public int getPassengerCapacity() {
        return 1;
    }

    public String getType() {
        return "Private";
    }

    public float flightPrice(ArrayList<String> params) {
        int distance = Integer.parseInt(params.get(0));
        float price = (distance/speed) * hourlyRate;
        if(distance/speed < 1){
            price = hourlyRate;
        }
        float ticketPrice = price;
        float airportTax = price * 0.6f;
        price += airportTax;
        System.out.println("Total price: " + (ticketPrice + airportTax) + "$");
        return price;
    }

    @Override
    public String toString() {
        return  "PrivateJet " + super.toString() + " speed: " + speed;
    }
}
