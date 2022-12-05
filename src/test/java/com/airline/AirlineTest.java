package com.airline;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.airline.planes.Airliner;
import com.airline.planes.*;

public class AirlineTest {
    
    @Test
    public void testGetPlane() {
        Airline airline = new Airline("Test Airline");
        Plane plane = new Airliner(1, "Test Plane", 100, 1000, 10000, 8000);
        airline.addPlane(plane);
        assertEquals(airline.getPlane(1), plane);
    }

    @Test
    public void testGetPlanes() {
        Airline airline = new Airline("Test Airline");
        Plane plane1 = new Airliner(1, "Test Plane 1", 100, 1000, 10000, 8000);
        Plane plane2 = new Airliner(2, "Test Plane 2", 100, 1000, 10000, 8000);
        airline.addPlane(plane1);
        airline.addPlane(plane2);
        ArrayList<Plane> planes = airline.getPlanes();
        assertEquals(planes.size(), 2);
        assertEquals(planes.get(0), plane1);
        assertEquals(planes.get(1), plane2);
    }

    @Test
    public void testAddPlane() {
        Airline airline = new Airline("Test Airline");
        Plane plane = new Airliner(1, "Test Plane", 100, 1000, 10000, 8000);
        airline.addPlane(plane);
        assertEquals(airline.getPlanes().size(), 1);
        assertEquals(airline.getPlane(1), plane);
    }

    @Test
    public void testCalculateTotalLoadCapacity() {
        Airline airline = new Airline("Test Airline");
        Plane plane1 = new Airliner(1, "Test Plane 1", 100, 1000, 10000, 8000);
        Plane plane2 = new Airliner(2, "Test Plane 2", 100, 1000, 10000, 8000);
        airline.addPlane(plane1);
        airline.addPlane(plane2);
        assertEquals(airline.calculateTotalLoadCapacity(), 2000);
    }

    @Test
    public void testCalculateTotalPassengerCapacity() {
        Airline airline = new Airline("Test Airline");
        Plane plane1 = new Airliner(1, "Test Plane 1", 100, 1000, 10000, 8000);
        Plane plane2 = new Airliner(2, "Test Plane 2", 100, 1000, 10000, 8000);
        airline.addPlane(plane1);
        airline.addPlane(plane2);
        assertEquals(airline.calculateTotalPassengerCapacity(), 200);
    }

    @Test
    public void testRemovePlane() {
        Airline airline = new Airline("Test Airline");
        Plane plane1 = new Airliner(1, "Test Plane 1", 100, 1000, 10000, 8000);
        Plane plane2 = new Airliner(2, "Test Plane 2", 100, 1000, 10000, 8000);
        airline.addPlane(plane1);
        airline.addPlane(plane2);
        airline.removePlane(plane1);
        assertEquals(airline.getPlanes().size(), 1);
        assertEquals(airline.getPlane(1), null);
        assertEquals(airline.getPlane(2), plane2);
    }

    @Test
    public void testSortPlanesByRange() {
        Airline airline = new Airline("Test Airline");
        Plane plane1 = new Airliner(1, "Test Plane 1", 100, 1000, 10000, 2);
        Plane plane2 = new Airliner(2, "Test Plane 2", 100, 1000, 10000, 3);
        Plane plane3 = new Airliner(3, "Test Plane 3", 100, 1000, 10000, 1);
        airline.addPlane(plane1);
        airline.addPlane(plane2);
        airline.addPlane(plane3);
        airline.sortPlanesByRange();
        ArrayList<Plane> planes = airline.getPlanes();
        assertEquals(planes.get(0), plane3);
        assertEquals(planes.get(1), plane1);
        assertEquals(planes.get(2), plane2);
    }
}
