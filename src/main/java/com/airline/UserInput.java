package com.airline;

public class UserInput {
    private int distance;
    private String flightClass;
    private int checkInBags;
    private int additionalWeight;
    private int packages;
    private int pallets;
    private int containerWeight;

    public UserInput() {}

    public int getDistance() {
        return distance;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public int getCheckInBags() {
        return checkInBags;
    }

    public int getAdditionalWeight() {
        return additionalWeight;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public void setCheckInBags(int checkInBags) {
        this.checkInBags = checkInBags;
    }

    public void setAdditionalWeight(int additionalWeight) {
        this.additionalWeight = additionalWeight;
    }

    public int getPackages() {
        return packages;
    }

    public int getPallets() {
        return pallets;
    }

    public int getContainerWeight() {
        return containerWeight;
    }

    public void setPackages(int packages) {
        this.packages = packages;
    }

    public void setPallets(int pallets) {
        this.pallets = pallets;
    }

    public void setContainerWeight(int containerWeight) {
        this.containerWeight = containerWeight;
    }
}
