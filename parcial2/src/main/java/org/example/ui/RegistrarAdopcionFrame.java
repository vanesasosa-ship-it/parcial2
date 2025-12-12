package org.example.ui;

import org.example.DAO.AdopcionDAOH2Impl;
import org.example.DAO.AdoptanteDAOH2Impl;
import org.example.DAO.EmpleadoDAOHImpl;
import org.example.DAO.MascotaDAOH2Impl;
import org.example.DAO.interfaces.AdopcionDAO;
import org.example.DAO.interfaces.AdoptanteDAO;
import org.example.DAO.interfaces.EmpleadoDAO;
import org.example.DAO.interfaces.MascotaDAO;
import org.example.modelo.*;
import org.example.sesion.SesionIniciada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrarAdopcionFrame  extends JFrame {



    public RegistrarAdopcionFrame() {

        setTitle("Registrar adopcion");
        setSize(350, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelR = new JPanel(new BorderLayout());

        AdopcionIniciada sesionAdopcion = AdopcionIniciada.getInstancia();

        AdoptanteDAO adoptanteDAO = new AdoptanteDAOH2Impl();

        Adoptante adoptante = adoptanteDAO.buscarPorNombreA(sesionAdopcion.getAdoptanteNombre());

        MascotaDAO mascotaDAO = new MascotaDAOH2Impl();

        Mascota mascota = mascotaDAO.buscarRegistro(sesionAdopcion.getIdMascota());

        Empleado empleadoLogueado = SesionIniciada.getUsuarioActual();
        String infoHtml =
                "<html>" +
                        "-----------------------------------------------" +
                        "<br>          Ticket de adopcion           <br>" +
                        "-----------------------------------------------" +
                        "<br>Fecha y hora: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "<br><br>" +

                        "Datos de la mascota:<br><br>" +
                        "Nombre de la mascota: " + mascota.getNombre() + "<br>" +
                        "Especie: " + mascota.getEspecie() + "<br>" +
                        "Fecha de nacimiento: " + mascota.getFechaNacimiento() + "<br><br>" +


                        "Datos del adoptante:<br><br>" +
                        "Nombre del adoptante: " + adoptante.getNombre() + "<br>" +
                        "Dirección: " + adoptante.getDireccion() + "<br>" +
                        "Edad: " + adoptante.getEdad() + "<br><br>" +

                        "Empleado encargado:<br> " +
                        "Nombre: "+empleadoLogueado.getNombre()+"<br>" +
                        "Cargo: "+empleadoLogueado.getCargo()+"<br><br>" +

                        "<br>Si los datos son correctos<br> seleccione Registrar </html>";

        JLabel info = new JLabel(infoHtml);


        info.setFont(new Font("Arial", Font.PLAIN, 16));
        info.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton submitR = new JButton("Registrar");

        submitR.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {

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
            }
        });


        panelR.add(info, BorderLayout.NORTH);
        panelR.add(submitR, BorderLayout.SOUTH);
        add(panelR);
    }

}
