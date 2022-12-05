package com.airline.planes;

import java.io.Console;

import com.airline.UserInput;

public class PrivateJet extends Plane{
    private float speed;
    private float hourlyRate;
    private Console console = System.console();

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

    public UserInput takeUserInput(){
        int distance = Integer.parseInt(console.readLine("Enter the distance of the flight in km: "));
        boolean validInput = true;
        do{
            validInput = true;
            if(distance > range){
                System.out.println("The distance entered is greater than the range of the plane. Please enter a valid distance.");
                validInput = false;
                continue;
            }
        }while(!validInput);
        UserInput userInput = new UserInput();
        userInput.setDistance(distance);
        return userInput;
    }

    public float flightPrice(UserInput userInput) {
        float price = (userInput.getDistance()/speed) * hourlyRate;
        if(userInput.getDistance()/speed < 1){
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
