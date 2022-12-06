package com.airline;
import com.airline.commands.*;
import com.airline.planes.*;
import java.io.Console;

public class App {
    private Airline airline;
    private Command command;
    private CommandHistory history = new CommandHistory();
    private Console console = System.console();

    App(Airline airline) {
        this.airline = airline;
    }

    private final String[] mainOptions = {
            "1. Add plane",
            "2. Remove plane",
            "3. Show planes",
            "4. Sort planes",
            "5. Calculate price",
            "6. Exit"
    };

    private void showMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        if(!history.isEmpty()){
            System.out.println("\n0. Undo last action");
        }
    }

    private int getUserInput() {
        int input = Integer.parseInt(console.readLine("\nChoose option: "));
        return input;
    }

    public int executeOption(int option){
        switch (option) {
            case 0:
                undo();
                break;
            case 1:
                executeCommand(new AddPlaneCommand(airline));
                break;
            case 2:
                executeCommand(new RemovePlaneCommand(airline));
                break;
            case 3:
                executeCommand(new ShowPlanesCommand(airline));
                break;
            case 4:
                executeCommand(new SortPlanesCommand(airline));
                break;
            case 5:
                executeCommand(new CalculatePriceCommand(airline));
                break;
            case 6:
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + option);
        }
        return option;
    }

    public void run(){
        while (true) {
            showMenu(mainOptions);
            int option = executeOption(getUserInput());
            if(option == 6){
                break;
            }
        }
        System.exit(0);
    }

    private void executeCommand(Command command) {
        if (command.execute()) {
            history.push(command);
        }
    }

    private void undo() {
        if (history.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }

        Command command = history.pop();
        if (command != null) {
            command.undo();
        }
    }
}