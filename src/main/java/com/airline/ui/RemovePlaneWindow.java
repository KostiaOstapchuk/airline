package com.airline.ui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.*;

public class RemovePlaneWindow extends JFrame implements ActionListener{
    
    JButton remButton = new JButton("Remove");

    public RemovePlaneWindow(Connection connection) {
        super("Remove plane");
        setSize(200, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

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
        
        JComboBox<String> optionMenu = new JComboBox<>(model);
        JLabel typeLabel = new JLabel("ID");
        
        add(optionMenu);

        remButton.setPreferredSize(new Dimension(100, 30));
        
        add(optionMenu);
        add(typeLabel);
        add(remButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == remButton){
            //todo
        }
    }
}
