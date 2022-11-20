package com.airline.planes;

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

    public int getfuelConsumption() {
        return fuelConsumption;
    }

    public int getRange() {
        return range;
    }
    
    public abstract int getPassengerCapacity();
    public abstract String getType();
    public abstract float flightPrice();

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