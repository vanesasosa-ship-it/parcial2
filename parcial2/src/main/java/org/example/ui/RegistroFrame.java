package org.example.ui;

import org.example.DAO.EmpleadoDAOHImpl;
import org.example.DAO.interfaces.EmpleadoDAO;
import org.example.modelo.Empleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroFrame extends JFrame {

    private JTextField nombreField;
    private JTextField cargoField;
    private JTextField passwordField;


    public RegistroFrame() {

        setTitle("Registro");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelR = new JPanel();
        panelR.setLayout(new GridLayout(6, 2));

        JLabel nombreLabel = new JLabel("Nombre de usuario:");
        nombreField = new JTextField();

        JLabel cargoLabel = new JLabel("Cargo:");
        cargoField = new JTextField();

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JTextField();


        panelR.add(nombreLabel);
        panelR.add(nombreField);

        panelR.add(cargoLabel);
        panelR.add(cargoField);

        panelR.add(passwordLabel);
        panelR.add(passwordField);

        JButton submitR = new JButton("Registrar");

        submitR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = nombreField.getText();
                String cargo = cargoField.getText();
                String password = passwordField.getText();


                EmpleadoDAO empleadoDAO = new EmpleadoDAOHImpl();


                Empleado empleado = new Empleado(nombre, cargo, password);

                empleadoDAO.registrarEmpleado(empleado , false);


                 dispose();
                JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);

            }
        });

        panelR.add(submitR);
        add(panelR);
    }
}
