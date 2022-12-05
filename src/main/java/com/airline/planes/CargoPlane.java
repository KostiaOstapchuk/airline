package com.airline.planes;

import java.io.Console;
import com.airline.UserInput;

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

    public UserInput takeUserInput(){
        int distance;
        int packages;
        int pallets;
        int containers;
        int containerW = 0;
        int containerTotalWeight = 0;
        boolean validInput = true;
        UserInput userInput = new UserInput();
        int totalWeight = 0;
        do{
            validInput = true;
            distance = Integer.parseInt(console.readLine("Enter the distance of the flight in km: "));
            if(distance > range){
                System.out.println("The distance entered is greater than the range of the plane. Please enter a valid distance.");
                validInput = false;
                continue;
            } 
            System.out.println("Maximum weight of cargo: " + loadCapacity + " kg");
            packages = Integer.parseInt(console.readLine("Enter the amount of packages (up to " + packageWeight + "kg, 30$ per package, max: " + Math.floorDiv(loadCapacity, packageWeight) + "): "));
            totalWeight += packages * packageWeight;
            pallets = Integer.parseInt(console.readLine("Enter the amount of pallets (up to " + palletWeight + "kg, 230$ per pallet, " + Math.floorDiv(loadCapacity, palletWeight)  + "): "));
            totalWeight += pallets * palletWeight;
            containers = Integer.parseInt(console.readLine("Enter the amount of containers: "));
            for(int i = 0; i < containers; i++){
                containerW = Integer.parseInt(console.readLine("Enter the weight of container " + (i+1) + ": "));
                if(containerW > containerWeight){
                    System.out.println("The weight of container " + (i+1) + " is greater than the allowed weight of " + containerWeight + " kg. Please enter a valid weight.");
                    i -= 1;
                }
                totalWeight += containerW;
                containerTotalWeight += containerW;
            }
            if(totalWeight > loadCapacity){
                System.out.println("The total weight of the cargo entered is greater than the planes load capacity (" + loadCapacity + " kg). Please enter a valid amount.");
                validInput = false;
                continue;
            }
            userInput.setDistance(distance);
            userInput.setPackages(packages);
            userInput.setPallets(pallets);
            userInput.setContainerWeight(containerTotalWeight);
        }while(!validInput);
        return userInput;
    }

    public float flightPrice(UserInput userInput) {
        float price = 0;

        price += userInput.getPackages() * 30;
        price += userInput.getPallets() * 230;
        price += userInput.getContainerWeight() * 0.6f + fuelPrice * userInput.getDistance();
        System.out.println("Total price: " + price + "$");
        return price;
    }

    @Override
    public String toString() {
        return  "CargoPlane " + super.toString();
    }
}
