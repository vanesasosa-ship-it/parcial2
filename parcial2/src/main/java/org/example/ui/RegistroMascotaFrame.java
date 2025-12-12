package org.example.ui;

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
    private JTextField fechaNacimientoField;
    private JTextField pesoField;
    private final MascotaDAO mascotaDAO;

    public RegistroMascotaFrame(boolean adopcion, MascotaDAO mascotaDAO){

        this.mascotaDAO = mascotaDAO;

        setTitle("Registrar una mascota");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelR = new JPanel();
        panelR.setLayout(new BoxLayout(panelR, BoxLayout.Y_AXIS));
        panelR.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelR.setBackground(new Color(245, 245, 245));


        JLabel titulo = new JLabel("Ingrese los datos de la mascota");
        titulo.setFont(new Font("Arial", Font.BOLD, 15));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        panelR.add(titulo);


        String[] opciones = {"Perro", "Gato", "Conejo"};

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





        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();

        JLabel fechaNacimientoLabel = new JLabel("Fecha de nacimiento:");
        fechaNacimientoField = new JTextField();

        JLabel pesoLabel = new JLabel("Peso:");
        pesoField = new JTextField();

        JPanel campos = new JPanel();
        campos.setLayout(new GridLayout(3, 3, 10, 10));
        campos.setOpaque(false);


        campos.add(nombreLabel);
        campos.add(nombreField);


        campos.add(fechaNacimientoLabel);
        campos.add(fechaNacimientoField);


        campos.add(pesoLabel);
        campos.add(pesoField);

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
                String fechaText = fechaNacimientoField.getText().trim();
                String pesoText = pesoField.getText();
                boolean adoptado = adopcion  ? true : false;

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


                Mascota mascota = SeleccionEspecie.seleccionEspecie(nombre, eleccion, fechaNacimiento, peso, adoptado);
                mascotaDAO.guardarMascota(mascota, false);

                if (adopcion) {

                    AdopcionIniciada sesionAdopcion = AdopcionIniciada.getInstancia();
                    Mascota mascotaEncontrada = mascotaDAO.buscarMascota2(nombre);

                    sesionAdopcion.setIdMascota(mascotaEncontrada.getId());

                    dispose();
                    new RegistrarAdopcionFrame().setVisible(true);

                } else {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Mascota registrada");
                }


            }
        });


    }
}
