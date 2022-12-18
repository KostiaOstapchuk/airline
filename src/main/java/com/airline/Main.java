package com.airline;
import com.airline.App;
import com.airline.Airline;
import com.airline.planes.*;

public class Main {
    public static void main( String[] args ){
        App app = new App(new Airline("Alpha"));
        app.init();
    }
}
