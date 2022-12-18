package com.airline;
import com.airline.commands.*;
import com.airline.planes.*;
import java.io.Console;
import com.airline.ui.MainWindow;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class App {
    private static Airline airline;
    private Command command;
    private CommandHistory history = new CommandHistory();
    private Console console = System.console();

    App(Airline airline) {
        App.airline = airline;
    }

    public void init(){
        String url = "jdbc:mysql://localhost:3306/airline?autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "donbass10kv";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            MainWindow mainWindow = new MainWindow(airline, connection);
            // connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void executeCommand(Command command, ArrayList<String> params) {
        if (command.execute(params)) {
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