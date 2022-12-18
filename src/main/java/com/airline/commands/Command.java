package com.airline.commands;

import com.airline.Airline;
import java.util.ArrayList;

public abstract class Command {
    public Airline airline;

    Command(Airline airline) {
        this.airline = airline;
    }

    public abstract boolean execute(ArrayList<String> params);

    public abstract void undo();

}
