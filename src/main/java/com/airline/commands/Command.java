package com.airline.commands;

import com.airline.Airline;

public abstract class Command {
    public Airline airline;

    Command(Airline airline) {
        this.airline = airline;
    }

    public abstract boolean execute();

    public abstract void undo();

}
