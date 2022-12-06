package com.airline.commands;
import com.airline.Airline;
import com.airline.planes.*;
import java.util.ArrayList;
import java.io.Console;

public class SortPlanesCommand extends Command {
    private ArrayList<Plane> backup;
    private final Console console = System.console();

    private final String[] options = {
        "ID",
        "Range"
    };

    public SortPlanesCommand(Airline airline) {
        super(airline);
    }

    public void backup() {
        backup = airline.getPlanes();
    }

    @Override
    public boolean execute() {
        backup();
        System.out.println("\nSort by:");
        for (int i = 0; i < options.length; i++) {
            System.out.println(i+1 + ". " + options[i]);
        }
        int choice = Integer.parseInt(console.readLine("\n"));
        switch (choice) {
            case 1:
                airline.sortPlanesById();
                System.out.println("\nPlanes sorted by ID\n");
                break;
            case 2:
                airline.sortPlanesByRange();
                System.out.println("\nPlanes sorted by range\n");
                break;
            default:
                System.out.println("Invalid option");
                return false;
        }
        return true;
    }

    @Override
    public void undo() {
        airline.setPlanes(backup);
        System.out.println("\nUndo sort\n");
    }
}
