package com.airline;
import com.airline.commands.*;
import com.airline.planes.*;
import java.io.Console;
import com.airline.ui.MainWindow;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    private Airline airline;
    private Command command;
    private CommandHistory history = new CommandHistory();
    private Console console = System.console();

    App(Airline airline) {
        this.airline = airline;
    }

    public void init(){
        String url = "jdbc:mysql://localhost:3306/airline?autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            MainWindow mainWindow = new MainWindow(airline, connection);
            // connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

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