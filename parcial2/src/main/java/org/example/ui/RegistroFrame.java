package org.example.ui;

import org.example.DAO.EmpleadoDAOHImpl;
import org.example.DAO.interfaces.EmpleadoDAO;
import org.example.modelo.Empleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RegistroFrame extends JFrame {

    private JTextField nombreField;
    private JTextField cargoField;
    private JTextField passwordField;

    public RegistroFrame() {

        setTitle("Registro de Usuario");
        setSize(350, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));

        JLabel titulo = new JLabel("Crear nuevo usuario");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        panel.add(titulo);


        JPanel campos = new JPanel();
        campos.setLayout(new GridLayout(3, 2, 10, 10));
        campos.setOpaque(false);

        JLabel nombreLabel = new JLabel("Usuario:");
        nombreField = new JTextField();

        JLabel cargoLabel = new JLabel("Cargo:");
        cargoField = new JTextField();

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JTextField();

        campos.add(nombreLabel);
        campos.add(nombreField);
        campos.add(cargoLabel);
        campos.add(cargoField);
        campos.add(passwordLabel);
        campos.add(passwordField);

        panel.add(campos);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton submitR = new JButton("Registrar");
        submitR.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(submitR);

        add(panel);


        submitR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = nombreField.getText();
                String cargo = cargoField.getText();
                String password = passwordField.getText();

                EmpleadoDAO empleadoDAO = new EmpleadoDAOHImpl();
                Empleado empleado = new Empleado(nombre, cargo, password);

                empleadoDAO.registrarEmpleado(empleado, false);

                dispose();
                JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");

                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}
