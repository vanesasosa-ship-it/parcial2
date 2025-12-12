package org.example.ui;

import org.example.DAO.AdoptanteDAOH2Impl;
import org.example.DAO.MascotaDAOH2Impl;
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
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelR = new JPanel();
        panelR.setLayout(new BoxLayout(panelR, BoxLayout.Y_AXIS));
        panelR.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelR.setBackground(new Color(245, 245, 245));


        JLabel titulo = new JLabel("Ingrese los datos del adoptante");
        titulo.setFont(new Font("Arial", Font.BOLD, 15));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        panelR.add(titulo);


        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionField = new JTextField();

        JLabel edadLabel = new JLabel("edad:");
        edadField = new JTextField();

        JPanel campos = new JPanel();
        campos.setLayout(new GridLayout(3, 3, 10, 10));
        campos.setOpaque(false);

        campos.add(nombreLabel);
        campos.add(nombreField);

        campos.add(direccionLabel);
        campos.add(direccionField);

        campos.add(edadLabel);
        campos.add(edadField);

        panelR.add(campos);

        JButton submitR = new JButton("Registrar");
        submitR.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelR.add(Box.createRigidArea(new Dimension(0, 10)));
        panelR.add(submitR);

        add(panelR);

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
                    sesionAdopcion.setAdoptanteNombre(adoptante.getNombre());

                    new RegistroMascotaFrame(true, new MascotaDAOH2Impl()).setVisible(true);
                }else{

                    JOptionPane.showMessageDialog(null, "Adoptante registrado");
                }


                dispose();


            }
        });

    }
}

