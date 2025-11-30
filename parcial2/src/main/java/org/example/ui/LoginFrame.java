package org.example.ui;

import org.example.DAO.EmpleadoDAOHImpl;
import org.example.DAO.interfaces.EmpleadoDAO;
import org.example.modelo.Adopcion;
import org.example.modelo.Empleado;
import org.example.sesion.SesionIniciada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

    public class LoginFrame extends JFrame {

        private JTextField nombreField;
        private JTextField passwordFIeld;

        public LoginFrame() {

            setTitle("Login");
            setSize(300, 200);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 2));

            JLabel nameLabel = new JLabel("Nombre de usuario:");
            nombreField = new JTextField();
            JLabel surnameLabel = new JLabel("Contraseña:");
            passwordFIeld = new JTextField();


            panel.add(nameLabel);
            panel.add(nombreField);
            panel.add(surnameLabel);
            panel.add(passwordFIeld);


            JButton iniciarSesión = new JButton("Iniciar sesión");
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
                        System.out.println("Usuario o contraseña incorrectos");
                    }
                }
            });

            panel.add(iniciarSesión);

            add(panel);

            JButton registrarButton = new JButton("Registrarse");
            registrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    dispose();
                    RegistroFrame registroFrame = new RegistroFrame();
                    registroFrame.setVisible(true);


                }
            });
            panel.add(registrarButton);

            add(panel);
        }

    }