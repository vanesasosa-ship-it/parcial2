package org.example.ui;

import org.example.DAO.MascotaDAOH2Impl;
import org.example.DAO.interfaces.MascotaDAO;
import org.example.modelo.Mascota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketFrame extends JFrame {
    public TicketFrame()
    {
        setTitle("Informacion de adopcion");
        setSize(300, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelR = new JPanel();
        panelR.setLayout(new GridLayout(8, 1));

        JButton info = new JButton("Imprimir ticket");
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               //imprimir
            }
        });

        JLabel tituloLabel = new JLabel("<html> TICKET DE ADOPCIÓN <br> Fecha y hora:</html> " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                "<br>Datos de la Mascota:" +
                "</html>");
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panelR.add(tituloLabel);
        panelR.add(info);

       /* System.out.println("--------------------------------");
        System.out.println("      TICKET DE ADOPCIÓN");
        System.out.println("--------------------------------");

        System.out.println("\nFecha y hora: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n \nDatos del adoptante: \nNombre: "+ adoptante.getNombre() + "\nEdad: " + adoptante.getEdad()+"\nDirección: " + adoptante.getDireccion());

        System.out.println("\nDatos de la Mascota: \nEspecie: " + nuevaMascota.getNombreEspecie() + "\nNombre: " + nuevaMascota.getNomMascota() + "\nFecha de nacimiento: " + nuevaMascota.getFechaNacimiento() + "\nPeso: " + nuevaMascota.getPeso() + "Kg" + "\nRecomendacion de cuidado: "  + nuevaMascota.getCuidados() );

        System.out.println("\nEmpleado Encargado: \nNombre: " + nombre +"\nCargo: " + puesto);

        System.out.println("--------------------------------");
        System.out.println("¡Gracias por adoptar una mascota!");
        System.out.println("--------------------------------");*/
    }
}
