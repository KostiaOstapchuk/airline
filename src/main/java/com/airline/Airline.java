package com.airline;
import com.airline.planes.*;
import com.airline.commands.*;
import java.util.ArrayList;

public class Airline {
    private String name;
    private ArrayList<Plane> planes;

    public Airline(String name) {
        this.name = name;
        this.planes = new ArrayList<>();
    }

    public int calculateTotalPassengerCapacity() {
        int totalPassangerCapacity = 0;
        for (Plane plane : planes) {
            totalPassangerCapacity += plane.getPassengerCapacity();
        }
        return totalPassangerCapacity;
    }

    public int calculateTotalLoadCapacity() {
        int totalLoadCapacity = 0;
        for (Plane plane : planes) {
            totalLoadCapacity += plane.getLoadCapacity();
        }
        return totalLoadCapacity;
    }

    public void addPlane(Plane plane) {
        planes.add(plane);
    }

    public void removePlane(Plane plane) {
        planes.remove(plane);
    }

    public void sortPlanesByRange(){
        planes.sort((Plane p1, Plane p2) -> p1.getRange() - p2.getRange());
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    public Plane getPlane(int id) {
        for (Plane plane : planes) {
            if (plane.getId() == id) {
                return plane;
            }
        }
        return null;
    }

}