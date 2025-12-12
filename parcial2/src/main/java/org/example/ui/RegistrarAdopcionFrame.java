package org.example.ui;

import org.example.DAO.AdopcionDAOH2Impl;
import org.example.DAO.AdoptanteDAOH2Impl;
import org.example.DAO.MascotaDAOH2Impl;
import org.example.DAO.interfaces.AdopcionDAO;
import org.example.DAO.interfaces.AdoptanteDAO;
import org.example.DAO.interfaces.MascotaDAO;
import org.example.modelo.*;
import org.example.sesion.SesionIniciada;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrarAdopcionFrame extends JFrame {

    public RegistrarAdopcionFrame() {

        setTitle("Registrar adopción");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel panelR = new JPanel();
        panelR.setLayout(new BoxLayout(panelR, BoxLayout.Y_AXIS));
        panelR.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelR.setBackground(new Color(245, 245, 245));


        JLabel titulo = new JLabel("Ticket de adopción");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        panelR.add(titulo);


        AdopcionIniciada sesionAdopcion = AdopcionIniciada.getInstancia();
        AdoptanteDAO adoptanteDAO = new AdoptanteDAOH2Impl();
        Adoptante adoptante = adoptanteDAO.buscarPorNombreA(sesionAdopcion.getAdoptanteNombre());

        MascotaDAO mascotaDAO = new MascotaDAOH2Impl();
        Mascota mascota = mascotaDAO.buscarRegistro(sesionAdopcion.getIdMascota());

        Empleado empleadoLogueado = SesionIniciada.getUsuarioActual();


        String infoHtml =
                "<html><div style='text-align:center; width:100%;'>" +
                        "<b>Fecha y hora:</b> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "<br><br>" +

                        "<b>Datos de la mascota:</b><br>" +
                        "Nombre: " + mascota.getNombre() + "<br>" +
                        "Especie: " + mascota.getEspecie() + "<br>" +
                        "Nacimiento: " + mascota.getFechaNacimiento() + "<br><br>" +

                        "<b>Datos del adoptante:</b><br>" +
                        "Nombre: " + adoptante.getNombre() + "<br>" +
                        "Dirección: " + adoptante.getDireccion() + "<br>" +
                        "Edad: " + adoptante.getEdad() + "<br><br>" +

                        "<b>Empleado encargado:</b><br>" +
                        empleadoLogueado.getNombre() + " (" + empleadoLogueado.getCargo() + ")<br><br>" +

                        "Si los datos son correctos, seleccione Registrar.<br>" +
                        "</div></html>";


        JLabel info = new JLabel(infoHtml);
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));


        panelR.add(info);
        panelR.add(Box.createRigidArea(new Dimension(0, 20)));


        JButton submitR = new JButton("Registrar");
        submitR.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitR.addActionListener(e -> {
            Adopcion nuevaAdopcion = new Adopcion(
                    adoptante,
                    mascota,
                    empleadoLogueado,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            );

            AdopcionDAO dao = new AdopcionDAOH2Impl();
            dao.registrar(nuevaAdopcion);

            String textoParaPDF = nuevaAdopcion.generarTicket();

            dispose();

            ImprimeTicketFrame imprimeTicket =
                    new ImprimeTicketFrame(RegistrarAdopcionFrame.this, textoParaPDF);

            imprimeTicket.setVisible(true);
        });

        panelR.add(submitR);

        add(panelR);
    }
}
