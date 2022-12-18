package com.airline.ui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.*;

import com.airline.Airline;
import com.airline.commands.RemovePlaneCommand;

public class RemovePlaneWindow extends JFrame implements ActionListener{
    
    JButton remButton = new JButton("Remove");
    Airline airline;
    Connection connection;
    JComboBox<Integer> optionMenu;

    public RemovePlaneWindow(Airline airline, Connection connection) {
        super("Remove plane");
        this.airline = airline;
        this.connection = connection;
        setSize(200, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();

        try {
            String sql = "SELECT plane_id FROM Planes";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                model.addElement(resultSet.getInt("plane_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        optionMenu = new JComboBox<>(model);
        JLabel typeLabel = new JLabel("ID");
        
        remButton.setPreferredSize(new Dimension(100, 30));
        remButton.addActionListener(this);

        add(optionMenu);
        add(optionMenu);
        add(typeLabel);
        add(remButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == remButton){
            try {
                String sql = "DELETE FROM Planes WHERE plane_id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                int id = Integer.parseInt(optionMenu.getSelectedItem().toString());
                statement.setInt(1, id);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Plane removed");
                ArrayList<String> param = new ArrayList<>();
                param.add(Integer.toString(id));
                new RemovePlaneCommand(airline).execute(param);
                dispose();
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
