package com.airline.planes;
import java.io.Console;
import java.lang.Math;
import java.util.ArrayList;

import com.airline.UserInput;

public class Airliner extends Plane{
    private int passengerCapacity;
    private int allowedWeightPerPassenger;
    private float businessClassModifier = 1.7f;
    private int checkInBagsAllowed;
    private int BagWeight = 20;
    private Console console = System.console();
    
    public Airliner(){
        super(0, "Boeing 747-8F", 93000, 12, 7899);
        this.passengerCapacity = 467;
        this.allowedWeightPerPassenger = loadCapacity/passengerCapacity;
        this.checkInBagsAllowed = Math.floorDiv((allowedWeightPerPassenger)/2, BagWeight);
    }
    public Airliner(int id, String model, int passengerCapacity, int loadCapacity, int fuelConsumption, int range){
        super(id, model, loadCapacity, fuelConsumption, range);
        this.passengerCapacity = passengerCapacity;
        this.allowedWeightPerPassenger = (((loadCapacity - passengerCapacity*80) / 2)/passengerCapacity);
        this.checkInBagsAllowed = Math.floorDiv(allowedWeightPerPassenger, BagWeight);
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }


    public float flightPrice(ArrayList<String> params){
        int distance = Integer.parseInt(params.get(0));
        String flightClass = params.get(1);
        int checkInBags = Integer.parseInt(params.get(2));
        float price = ((distance * fuelConsumption * fuelPrice) / (passengerCapacity / 2));
        if(price < minFlightPrice){
            price = minFlightPrice;
        }
        switch(flightClass){
            case "Economy":
                break;
            case "Business":
                price *= businessClassModifier;
                break;
            default:
                return 0;
        }
        float ticketPrice = price;
        float airportTax = price * 0.6f;
        float baggagePrice = checkInBags * 30;
        price += baggagePrice;
        System.out.printf("\nTicket price: %.2f$\nAirport tax: %.2f$\nBaggage: %.2f$\nTotal price: %.2f$\n", ticketPrice, airportTax, baggagePrice, price);
        return price;
    }

    public String getType() {
        return "Commertial";
    }

    @Override
    public String toString() {
        return  "Airliner " + super.toString() +
                ", passenger capacity: " + passengerCapacity;
    }
}
