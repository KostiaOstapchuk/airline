package com.airline.planes;
import java.io.Console;
import java.lang.Math;

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

    public float flightPrice() {
        System.out.println("Enter travel distance: ");
        int distance = Integer.parseInt(console.readLine());
        if(distance > range){
            System.out.println("This plane can't fly that far!");
            return 0;
        }
        float price = ((distance * fuelConsumption * fuelPrice) / (passengerCapacity / 2));
        if(price < minFlightPrice){
            price = minFlightPrice;
        }
        System.out.println("Choose flight class: ");
        System.out.println("1. Economy\n2. Business\n");
        int choice = Integer.parseInt(console.readLine());
        switch(choice){
            case 1:
            break;
            case 2:
            price *= businessClassModifier;
            break;
            default:
            System.out.println("Invalid choice");
            return 0;
        }
        float ticketPrice = price;
        float airportTax = price * 0.6f;
        System.out.println("Enter the amount of check-in bags (30$ per bag, up to " + checkInBagsAllowed + " bags): ");
        float baggage = Integer.parseInt(console.readLine()) * 30;

        System.out.println("Weight of any additional baggage / special equipment (2$ per kg, up to " + (allowedWeightPerPassenger - BagWeight * checkInBagsAllowed) + " kg): ");
        baggage += Integer.parseInt(console.readLine()) * 2;
        
        price += baggage;
        System.out.printf("Ticket price: %.2f$\nAirport tax: %.2f$\nBaggage: %.2f$\nTotal price: %.2f$\n", ticketPrice, airportTax, baggage, price);
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
