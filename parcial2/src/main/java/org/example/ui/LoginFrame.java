package org.example.ui;

import org.example.DAO.EmpleadoDAOHImpl;
import org.example.DAO.interfaces.EmpleadoDAO;

import org.example.modelo.Empleado;
import org.example.sesion.SesionIniciada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame {

    private JTextField nombreField;
    private JTextField passwordFIeld;

    public LoginFrame() {

        setTitle("Login");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));


        JLabel titulo = new JLabel("Inicio de Sesión");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        panel.add(titulo);


        JPanel campos = new JPanel();
        campos.setLayout(new GridLayout(2, 2, 10, 10));
        campos.setOpaque(false);

        JLabel nameLabel = new JLabel("Usuario:");
        nombreField = new JTextField();

        JLabel surnameLabel = new JLabel("Contraseña:");
        passwordFIeld = new JTextField();

        campos.add(nameLabel);
        campos.add(nombreField);
        campos.add(surnameLabel);
        campos.add(passwordFIeld);

        panel.add(campos);


        JPanel botones = new JPanel();
        botones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botones.setOpaque(false);

        JButton iniciarSesión = new JButton("Iniciar sesión");
        JButton registrarButton = new JButton("Registrarse");

        botones.add(iniciarSesión);
        botones.add(registrarButton);

        panel.add(Box.createRigidArea(new Dimension(0, 10))); // espacio extra abajo
        panel.add(botones);

        add(panel);


        iniciarSesión.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = nombreField.getText();
                String password = passwordFIeld.getText();

                EmpleadoDAO empleadoDAO = new EmpleadoDAOHImpl();
                Empleado empleado = empleadoDAO.login(nombre, password);
                SesionIniciada.iniciarSesion(empleado);

                if (empleado != null) {
                    JOptionPane.showMessageDialog(null, "Usuario Logueado");

                    dispose();
                    MenuFrame menuFrame = new MenuFrame();
                    menuFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegistroFrame registroFrame = new RegistroFrame();
                registroFrame.setVisible(true);
            }
        });
    }
}
