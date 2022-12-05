package com.airline.planes;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.airline.UserInput;

public class PlaneTest {
    Plane airliner = new Airliner(1, "Test Airliner", 100, 1000, 10, 1000);
    Plane cargoPlane = new CargoPlane(1, "Test Cargo Plane", 100, 10, 1000);
    Plane privateJet = new PrivateJet(1, "Test Private Jet", 100, 5, 1000, 500);
    @Test
    public void testGetId() {  
        assertEquals(airliner.getId(), 1);
    }

    @Test
    public void testGetLoadCapacity() {
        assertEquals(airliner.getLoadCapacity(), 1000);
    }

    @Test
    public void testGetModel() {
        assertEquals(airliner.getModel(), "Test Airliner");
    }

    @Test
    public void testGetPassengerCapacity() {
        assertEquals(airliner.getPassengerCapacity(), 100);
    }

    @Test
    public void testGetRange() {
        assertEquals(airliner.getRange(), 1000);
    }

    @Test
    public void testGetType() {
        assertEquals(airliner.getType(), "Commertial");
        assertEquals(cargoPlane.getType(), "Cargo");
        assertEquals(privateJet.getType(), "Private");
    }

    @Test
    public void testGetfuelConsumption() {
        assertEquals(airliner.getFuelConsumption(), 10);
    }

    @Test
    public void testSetId() {
        airliner.setId(2);
        assertEquals(airliner.getId(), 2);
    }

    @Test
    public void testAirlinerFlightPrice() {
        UserInput userInput = new UserInput();
        userInput.setDistance(1000);
        userInput.setFlightClass("Economy");
        userInput.setCheckInBags(2);
        userInput.setAdditionalWeight(100);
        assertEquals(airliner.flightPrice(userInput), 432, 0.1);
    }

    @Test
    public void testCargoPlaneFlightPrice() {
        UserInput userInput = new UserInput();
        userInput.setDistance(1000);
        userInput.setPackages(1);
        userInput.setPallets(1);
        userInput.setContainerWeight(100);
        assertEquals(cargoPlane.flightPrice(userInput), 1180, 0.1);
    }

    @Test
    public void testPrivateJetFlightPrice() {
        UserInput userInput = new UserInput();
        userInput.setDistance(1000);
        assertEquals(privateJet.flightPrice(userInput), 6880, 0.1);
    }
}
