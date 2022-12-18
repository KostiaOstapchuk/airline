package com.airline.ui;
import javax.swing.*;

import com.airline.Airline;
import com.airline.ui.AddPlaneWindow;
import com.airline.ui.RemovePlaneWindow;
import com.airline.ui.CalculatePriceWindow;
import com.airline.planes.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 950;
    private static final int HEIGHT = 600;
    private static final int WESTWIDTH = 150;
    private static final String TITLE = "Airline";
    private Airline airline;
    private Connection connection;
    private JButton button1 = new JButton("Add plane");
    private JButton button2 = new JButton("Remove plane");
    private JButton button3 = new JButton("Load planes");
    private JButton button4 = new JButton("Calculate price");
    private JButton button5 = new JButton("Exit");
    private JTable table;

    public MainWindow(Airline airline, Connection connection) {
        super(TITLE);
        this.airline = airline;
        this.connection = connection;
        DefaultTableModel model = new DefaultTableModel();
        try{

            String[] columnNames = {"ID", "Model", "Load capacity", "Fuel consumption", "Passenger capacity", "Range", "Speed", "Plane type"};
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Planes");

            // Get the metadata for the result set
            ResultSetMetaData metadata = resultSet.getMetaData();

            // Get the number of columns in the result set
            int columnCount = metadata.getColumnCount();

            // Add the column names to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metadata.getColumnName(i));
            }

            // Add the rows to the model
            while (resultSet.next()) {
                Plane plane;
                String type = resultSet.getString(8);
                int id = resultSet.getInt(1);
                String pmodel = resultSet.getString(2);
                int loadCapacity = resultSet.getInt(3);
                int passengerCapacity = resultSet.getInt(4);
                int fuelConsumption = resultSet.getInt(5);
                int range = resultSet.getInt(6);
                int speed = resultSet.getInt(7);
                switch (type) {
                    case "Airliner":
                        plane = new Airliner(id, pmodel, loadCapacity, fuelConsumption, passengerCapacity, range);
                        break;
                    case "CargoPlane":
                        plane = new CargoPlane(id, pmodel, loadCapacity, fuelConsumption, range);        
                        break;
                    case "PrivateJet":
                        plane = new PrivateJet(id, pmodel, loadCapacity, fuelConsumption, range, speed);    
                        break;
                    default:
                        plane = null;
                        break;
                }
                airline.addPlane(plane);
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        // Set the window properties
        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create the panels
        JPanel westPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        
        // Set the panel properties
        westPanel.setPreferredSize(new Dimension(WESTWIDTH, HEIGHT));
        westPanel.setBackground(Color.CYAN);
        centerPanel.setPreferredSize(new Dimension(WIDTH - WESTWIDTH, HEIGHT));
        centerPanel.setBackground(Color.YELLOW);

        // Create JTable
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setEnabled(false);
        
        // Add the table to a scrollpane, and add the scrollpane to the center panel
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(centerPanel.getPreferredSize());
        GridLayout gridLayout = new GridLayout();
        centerPanel.setLayout(gridLayout);
        centerPanel.add(scrollPane);

        // Add the buttons to the west panel
        button1.setPreferredSize(new Dimension(WESTWIDTH, 50));
        button1.addActionListener(this);
        button2.setPreferredSize(new Dimension(WESTWIDTH, 50));
        button2.addActionListener(this);
        button3.setPreferredSize(new Dimension(WESTWIDTH, 50));
        button3.addActionListener(this);
        button4.setPreferredSize(new Dimension(WESTWIDTH, 50));
        button4.addActionListener(this);
        button5.setPreferredSize(new Dimension(WESTWIDTH, 50));
        button5.addActionListener(this);

        westPanel.add(button1);
        westPanel.add(button2);
        westPanel.add(button3);
        westPanel.add(button4);
        westPanel.add(button5);
        
        // Add the panels to the window
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1){
            AddPlaneWindow addPlaneWindow = new AddPlaneWindow(airline, connection);
        }
        if(e.getSource() == button2){
            RemovePlaneWindow removePlaneWindow = new RemovePlaneWindow(airline, connection);
        }
        if(e.getSource() == button3){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Planes");
                ResultSetMetaData metadata = resultSet.getMetaData();
                int columnCount = metadata.getColumnCount();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                while (resultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(row);
                }
                table.setModel(model);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if(e.getSource() == button4){
            CalculatePriceWindow calculatePriceWindow = new CalculatePriceWindow(connection);
        }
        if(e.getSource() == button5){
            try {
                connection.close();
                System.exit(0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}