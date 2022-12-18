package com.airline.ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AddPlaneWindow extends JFrame implements ActionListener{
    
    JButton addButton = new JButton("Add");

    public AddPlaneWindow() {
        super("Add plane");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.LEFT));

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

        addButton.setPreferredSize(new Dimension(100, 30));
        
        add(optionMenu);
        add(typeLabel);
        add(modelField);
        add(modelLabel);
        add(loadCapacityField);
        add(loadCapacityLabel);
        add(fuelConsumptionField);
        add(fuelConsumptionLabel);
        add(passengerCapacityField);
        add(passengerCapacityLabel);
        add(rangeField);
        add(rangeLabel);
        add(speedField);
        add(speedLabel);
        add(addButton);
        addButton.setAlignmentX(Component.BOTTOM_ALIGNMENT);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){
            //todo
        }
    }
}
