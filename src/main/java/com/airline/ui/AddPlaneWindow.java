package com.airline.ui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.*;

import com.airline.Airline;
import com.airline.commands.*;
import java.util.ArrayList;

public class AddPlaneWindow extends JFrame implements ActionListener{
    
    private Airline airline;
    private Connection connection;

    JButton addButton = new JButton("Add");
    String[] options = {"Airliner", "CargoPlane", "PrivateJet"};
    JComboBox<String> optionMenu = new JComboBox<>(options);
    JLabel typeLabel = new JLabel("Type");

    JLabel modelLabel = new JLabel("Model");
    JTextField modelField = new JTextField(20);

    JLabel loadCapacityLabel = new JLabel("Load capacity");
    JTextField loadCapacityField = new JTextField(20);

    JLabel fuelConsumptionLabel = new JLabel("Fuel consumption");
    JTextField fuelConsumptionField = new JTextField(20);

    JLabel passengerCapacityLabel = new JLabel("Passenger capacity");
    JTextField passengerCapacityField = new JTextField(20);

    JLabel rangeLabel = new JLabel("Range");
    JTextField rangeField = new JTextField(20);

    JLabel speedLabel = new JLabel("Speed");
    JTextField speedField = new JTextField(20);

    public AddPlaneWindow(Airline airline, Connection connection) {
        super("Add plane");
        this.airline = airline;
        this.connection = connection;
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        addButton.setPreferredSize(new Dimension(100, 30));
        
        optionMenu.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (optionMenu.getSelectedIndex() == 0) {
                    System.out.println("Airliner");
                    loadCapacityField.setEnabled(true);
                    passengerCapacityField.setEnabled(true);
                    speedField.setEnabled(false);
                } else if (optionMenu.getSelectedIndex() == 1) {
                    System.out.println("CargoPlane");
                    loadCapacityField.setEnabled(true);
                    passengerCapacityField.setEnabled(false);
                    speedField.setEnabled(false);
                } else {
                    System.out.println("PrivateJet");
                    loadCapacityField.setEnabled(false);
                    passengerCapacityField.setEnabled(false);
                    speedField.setEnabled(true);
                }
            }
        });
        add(optionMenu);
        add(typeLabel);
        add(modelField);
        add(modelLabel);
        add(loadCapacityField);
        add(loadCapacityLabel);
        add(passengerCapacityField);
        add(passengerCapacityLabel);
        add(fuelConsumptionField);
        add(fuelConsumptionLabel);
        add(rangeField);
        add(rangeLabel);
        add(speedField);
        add(speedLabel);
        add(addButton);

        speedField.setEnabled(false);
        addButton.addActionListener(this);
        addButton.setAlignmentX(Component.BOTTOM_ALIGNMENT);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){
            ArrayList<String> params = new ArrayList<>();
            params.add(optionMenu.getSelectedItem().toString());
            params.add(modelField.getText());
            params.add(loadCapacityField.getText());
            params.add(passengerCapacityField.getText());
            params.add(fuelConsumptionField.getText());
            params.add(rangeField.getText());
            params.add(speedField.getText());

            try{
                String sql = "INSERT INTO Planes (plane_id, plane_model, plane_load_capacity, plane_passenger_capacity, plane_fuel_consumption, plane_range, plane_speed, plane_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, airline.getPlanes().size() + 1);
                statement.setString(2, modelField.getText());
                if(loadCapacityField.getText().equals("")){
                    statement.setNull(3, java.sql.Types.INTEGER);
                } else {
                    statement.setInt(3, Integer.parseInt(loadCapacityField.getText()));
                }
                if(passengerCapacityField.getText().equals("")){
                    statement.setNull(4, java.sql.Types.INTEGER);
                } else {
                    statement.setInt(4, Integer.parseInt(passengerCapacityField.getText()));
                }
                statement.setInt(5, Integer.parseInt(fuelConsumptionField.getText()));
                statement.setInt(6, Integer.parseInt(rangeField.getText()));
                if(speedField.getText().equals("")){
                    statement.setNull(7, java.sql.Types.INTEGER);
                } else {
                    statement.setInt(7, Integer.parseInt(speedField.getText()));
                }
                statement.setString(8, optionMenu.getSelectedItem().toString());
                statement.executeUpdate();
    
                new AddPlaneCommand(airline).execute(params);
            } catch (Exception ex){
                ex.printStackTrace();
            }

            dispose();
        }
    }
}
