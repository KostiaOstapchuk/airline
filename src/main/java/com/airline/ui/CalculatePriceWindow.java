package com.airline.ui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.*;

import com.airline.Airline;
import com.airline.commands.CalculatePriceCommand;
import java.util.ArrayList;

public class CalculatePriceWindow extends JFrame implements ActionListener{
    
    Airline airline;

    JButton calcButton = new JButton("Calculate");
    JLabel priceLabel = new JLabel("Price:");
    JComboBox<String> optionMenu;

    JLabel idLabel = new JLabel("Plane ID   ");

    JTextField distanceField = new JTextField(20);
    JLabel distanceLabel = new JLabel("Flight distance");

    JComboBox<String> classOptions = new JComboBox<>(new String[]{"Economy", "Business"});
    JLabel classLabel = new JLabel("Flight class");

    JTextField checkInBags = new JTextField(20);
    JLabel checkInBagsLabel = new JLabel("Check-in bags");

    JTextField packages = new JTextField(20);
    JLabel packagesLabel = new JLabel("Packages");

    JTextField pallets = new JTextField(20);
    JLabel palletsLabel = new JLabel("Pallets");

    JTextField containers = new JTextField(20);
    JLabel containersLabel = new JLabel("Containers");
    

    public CalculatePriceWindow(Airline airline, Connection connection) {

        super("Calculate Pirce");
        this.airline = airline;
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        calcButton.setPreferredSize(new Dimension(100, 30));
        calcButton.addActionListener(this);

        try {
            String sql = "SELECT plane_id FROM Planes";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                model.addElement(resultSet.getString("plane_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        optionMenu = new JComboBox<>(model);

        add(optionMenu);
        add(idLabel);
        add(classOptions);
        add(classLabel);
        add(distanceField);
        add(distanceLabel);
        add(checkInBags);
        add(checkInBagsLabel);
        add(packages);
        add(packagesLabel);
        add(pallets);
        add(palletsLabel);
        add(containers);
        add(containersLabel);
        add(calcButton);
        add(priceLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calcButton){
            int plane_id = Integer.parseInt(optionMenu.getSelectedItem().toString());
            ArrayList<String> params = new ArrayList<>();
            params.add(distanceField.getText());
            params.add(classOptions.getSelectedItem().toString());
            params.add(checkInBags.getText());
            params.add(packages.getText());
            params.add(pallets.getText());
            params.add(containers.getText());

            float price = airline.getPlane(plane_id).flightPrice(params);
            priceLabel.setText("Price: " + price + " $");
        }
    }
}
