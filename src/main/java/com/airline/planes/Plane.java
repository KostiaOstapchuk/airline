package com.airline.planes;
import com.airline.UserInput;

public abstract class Plane {
    protected int id;
    protected String model;
    protected int loadCapacity;
    protected int fuelConsumption;
    protected int range;
    protected float fuelPrice = 0.86f;
    protected int minFlightPrice = 29;

    public Plane(){

    }

    public Plane(int id, String model, int loadCapacity, int fuelConsumption, int range) {
        this.id = id;
        this.model = model;
        this.loadCapacity = loadCapacity;
        this.fuelConsumption = fuelConsumption;
        this.range = range;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public int getRange() {
        return range;
    }
    
    public abstract int getPassengerCapacity();
    public abstract String getType();
    public abstract UserInput takeUserInput();
    public abstract float flightPrice(UserInput userInput);

    @Override
    public String toString() {
        return  "id: " + id +
                ", model: " + model +
                ", load capacity: " + loadCapacity +
                ", fuel consumption: " + fuelConsumption +
                ", range: " + range;
    }

    public void setId(int id) {
        this.id = id;
    }

}