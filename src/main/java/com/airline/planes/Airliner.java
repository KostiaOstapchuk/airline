package com.airline.planes;
import java.io.Console;
import java.lang.Math;
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

    public UserInput takeUserInput(){
        int distance;
        String flightClass;
        int checkInBags;
        int additionalWeight;
        boolean validInput = true;
        UserInput userInput = new UserInput();
        do{
            validInput = true;
            distance = Integer.parseInt(console.readLine("Enter the distance of the flight in km: "));
            if(distance > range){
                System.out.println("The distance entered is greater than the range of the plane. Please enter a valid distance.");
                validInput = false;
                continue;
            } 
            flightClass = console.readLine("Enter the flight class (Economy, Business): ");
            if(!flightClass.equals("Economy") && !flightClass.equals("Business")){
                System.out.println("The flight class entered is invalid. Please enter a valid flight class.");
                validInput = false;
                continue;
            }
            checkInBags = Integer.parseInt(console.readLine("Enter the amount of check-in bags (30$ per bag, up to " + checkInBagsAllowed + " bags): "));
            if(checkInBags > checkInBagsAllowed){
                System.out.println("The amount of check-in bags entered is greater than the allowed amount. Please enter a valid amount.");
                validInput = false;
                continue;
            }
            additionalWeight = Integer.parseInt(console.readLine("Weight of any additional baggage / special equipment (2$ per kg, up to " + (allowedWeightPerPassenger - BagWeight * checkInBagsAllowed) + " kg): "));
            if(additionalWeight > (allowedWeightPerPassenger - BagWeight * checkInBagsAllowed)){
                System.out.println("The weight of additional baggage / special equipment entered is greater than the allowed amount. Please enter a valid amount.");
                validInput = false;
                continue;
            }
            userInput.setDistance(distance);
            userInput.setFlightClass(flightClass);
            userInput.setCheckInBags(checkInBags);
            userInput.setAdditionalWeight(additionalWeight);
        }while(!validInput);
        return userInput;
    }

    public float flightPrice(UserInput userInput) {
        float price = ((userInput.getDistance() * fuelConsumption * fuelPrice) / (passengerCapacity / 2));
        if(price < minFlightPrice){
            price = minFlightPrice;
        }
        switch(userInput.getFlightClass()){
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
        float baggagePrice = userInput.getCheckInBags() * 30;
        baggagePrice += userInput.getAdditionalWeight() * 2;
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
