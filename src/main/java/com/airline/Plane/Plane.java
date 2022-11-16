package com.airline.Plane;

public abstract class Plane {
    protected int id;
    protected String model;
    protected int passengerCapacity;
    protected int loadCapacity;
    protected int gasConsumption;
    protected int range;

    public Plane(int id, String model, int passengerCapacity, int loadCapacity, int gasConsumption, int range) {
        this.id = id;
        this.model = model;
        this.passengerCapacity = passengerCapacity;
        this.loadCapacity = loadCapacity;
        this.gasConsumption = gasConsumption;
        this.range = range;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public int getGasConsumption() {
        return gasConsumption;
    }

    public int getRange() {
        return range;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", passengerCapacity=" + passengerCapacity +
                ", loadCapacity=" + loadCapacity +
                ", gasConsumption=" + gasConsumption +
                ", range=" + range +
                '}';
    }

}
