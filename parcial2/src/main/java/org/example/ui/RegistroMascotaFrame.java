package org.example.ui;

import org.example.DAO.MascotaDAOH2Impl;
import org.example.DAO.interfaces.MascotaDAO;
import org.example.modelo.AdopcionIniciada;
import org.example.modelo.SeleccionEspecie;
import org.example.modelo.Mascota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RegistroMascotaFrame extends JFrame {

    private JTextField nombreField;
    private JTextField especieField;
    private JTextField especieSeleccionada;
    private JTextField fechaNacimientoField;
    private JTextField pesoField;

    public RegistroMascotaFrame(boolean adopcion)  {

        setTitle("Registrar una mascota");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] opciones = {"Perro", "Gato", "Conejo", "Otra"};

        int eleccion = JOptionPane.showOptionDialog(
                null,
                "Por favor, Seleccione la especie a la que pertenece la mascota",
                "Especie",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opciones,
                opciones[0]
        );


        JPanel panelR = new JPanel();
        panelR.setLayout(new GridLayout(6, 2));

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();

        JLabel fechaNacimientoLabel = new JLabel("Fecha de nacimiento:");
        fechaNacimientoField = new JTextField();

        JLabel pesoLabel = new JLabel("Peso:");
        pesoField = new JTextField();

        panelR.add(nombreLabel);
        panelR.add(nombreField);


        panelR.add(fechaNacimientoLabel);
        panelR.add(fechaNacimientoField);


        panelR.add(pesoLabel);
        panelR.add(pesoField);

        JButton submitR = new JButton("Registrar");

        submitR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = nombreField.getText();
                String fechaText = fechaNacimientoField.getText().trim();
                String pesoText = pesoField.getText();


                int peso;

                try {
                    peso = Integer.parseInt(pesoText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el peso expresado en kg");
                    return;
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fechaNacimiento;
                try {
                    fechaNacimiento = LocalDate.parse(fechaText, formatter);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese la fecha valida en el formato dd/MM/yyyy");
                    return;
                }

                MascotaDAO mascotaDAO = new MascotaDAOH2Impl();

                Mascota mascota = SeleccionEspecie.seleccionEspecie(nombre, eleccion, fechaNacimiento, peso);
                mascotaDAO.guardarMascota(mascota, false);

                if(adopcion){

                    int idMacotaCargada = mascota.getId();
                    AdopcionIniciada sesionAdopcion = AdopcionIniciada.getInstancia();
                    sesionAdopcion.setIdMascota(idMacotaCargada);
                    dispose();
                    RegistrarAdopcionFrame registrarAdopcion = new RegistrarAdopcionFrame();
                    registrarAdopcion.setVisible(true);

                }else{
                    dispose();
                    JOptionPane.showMessageDialog(null, "Mascota registrada");
                }


            }
        });

        panelR.add(submitR);
        add(panelR);
    }
}
