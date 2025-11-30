package org.example.ui;

import org.example.DAO.AdoptanteDAOH2Impl;
import org.example.DAO.interfaces.AdoptanteDAO;
import org.example.modelo.AdopcionIniciada;
import org.example.modelo.Adoptante;
import org.example.modelo.Empleado;
import org.example.sesion.SesionIniciada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroAdoptanteFrame extends JFrame {

    private JTextField nombreField;
    private JTextField direccionField;
    private JTextField edadField;

    public RegistroAdoptanteFrame(boolean adopcion) {

        setTitle("Registrar un adoptante");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelR = new JPanel();
        panelR.setLayout(new GridLayout(6, 2));

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionField = new JTextField();

        JLabel edadLabel = new JLabel("edad:");
        edadField = new JTextField();


        panelR.add(nombreLabel);
        panelR.add(nombreField);

        panelR.add(direccionLabel);
        panelR.add(direccionField);

        panelR.add(edadLabel);
        panelR.add(edadField);

        JButton submitR = new JButton("Registrar");

        submitR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = nombreField.getText();
                String direccion = direccionField.getText();
                String edadText = edadField.getText();

                int edad;
                try {
                    edad = Integer.parseInt(edadText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para la edad");
                    return;
                }

                AdoptanteDAO adoptanteDAO = new AdoptanteDAOH2Impl();

                Adoptante adoptante = new Adoptante(nombre, direccion, edad);
                adoptanteDAO.registrar(adoptante, false);


                if(adopcion){

                    AdopcionIniciada sesionAdopcion = AdopcionIniciada.getInstancia();
                    sesionAdopcion.setIdAdoptante(adoptante.getId());


                    RegistroMascotaFrame registroMascota = new RegistroMascotaFrame(true);
                    registroMascota.setVisible(true);
                }else{

                    JOptionPane.showMessageDialog(null, "Adoptante registrado");
                }


                dispose();


            }
        });

        panelR.add(submitR);
        add(panelR);
    }
}

