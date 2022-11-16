package com.airline;

import javax.print.attribute.standard.PageRanges;

import com.Airline;
import com.airline.Plane.*;

public class App 
{
    public static void main( String[] args )
    {
        Airline airline = new Airline("Airline");
        airline.addPlane(new PassengerPlane(1, "Boeing 737", 150, 10000, 500, 1000));
        airline.addPlane(new PassengerPlane(2, "Boeing 737", 150, 10000, 500, 1002));
        airline.addPlane(new PassengerPlane(3, "Boeing 737", 150, 10000, 500, 1001));
        airline.addPlane(new PassengerPlane(4, "Boeing 737", 150, 10000, 500, 1003));
        airline.addPlane(new PassengerPlane(5, "Boeing 737", 150, 10000, 500, 999));


        airline.sortPlanesByRange();
        System.out.println(airline.getPlanes());
    }
}
