package org.example.ui;

import org.example.DAO.AdopcionDAOH2Impl;
import org.example.DAO.AdoptanteDAOH2Impl;
import org.example.DAO.EmpleadoDAOHImpl;
import org.example.DAO.MascotaDAOH2Impl;
import org.example.DAO.interfaces.AdopcionDAO;
import org.example.DAO.interfaces.EmpleadoDAO;
import org.example.DAO.interfaces.MascotaDAO;
import org.example.DAO.interfaces.AdoptanteDAO;
import org.example.modelo.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

    public MenuFrame() {
        setTitle("Menu");
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelR = new JPanel();
        panelR.setLayout(new GridLayout(10, 6));

        JButton tablaM = new JButton("Ver lista de mascotas");
        tablaM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MascotaDAO mascotaDAO = new MascotaDAOH2Impl();

                List<Mascota> mascotas = mascotaDAO.listarMascotas();

                String[] columnas = {"ID", "Nombre","Especie", "Fecha de nacimiento", "Peso"};

                TableFrame<Mascota> frame = new TableFrame<>(
                        "Listado de mascotas",
                        columnas,
                        mascotas,
                        mascota -> new Object[] {
                                mascota.getId(),
                                mascota.getNomMascota(),
                                mascota.getEspecie(),
                                mascota.getFechaNacimiento() != null ? mascota.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                                mascota.getPeso()
                        }
                );
            }
        });

        JButton tablaE = new JButton("Ver lista de empleados");
        tablaE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EmpleadoDAO empleadoDAO = new EmpleadoDAOHImpl();
                List<Empleado> empleados = empleadoDAO.listarEmpleados();

                String[] columnas = {"ID", "Nombre", "Cargo"};

                TableFrame<Empleado> frame = new TableFrame<>(
                        "Listado de empleados",
                        columnas,
                        empleados,
                        empleado -> new Object[] {
                                empleado.getId(),
                                empleado.getNombre(),
                                empleado.getCargo()
                        }
                );


            }
        });

        JButton tablaA = new JButton("Ver lista de adoptantes");
        tablaA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AdoptanteDAO adoptanteDAO = new AdoptanteDAOH2Impl();

                List<Adoptante> adoptantes = adoptanteDAO.listarAdoptantes();

                String[] columnas = {"ID", "Nombre", "Edad", "Dirección"};

                TableFrame<Adoptante> frame = new TableFrame<>(
                        "Listado de adoptantes",
                        columnas,
                        adoptantes,
                        adoptante -> new Object[] {
                                adoptante.getId(),
                                adoptante.getNombre(),
                                adoptante.getEdad(),
                                adoptante.getDireccion(),

                        }
                );
            }
        });

        JButton tablaR = new JButton("Ver registro de adopciones");
        tablaR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                AdopcionDAO adopcionDAO = new AdopcionDAOH2Impl();

                List<Adopcion> adopciones = adopcionDAO.listarAdopciones();

                String[] columnas = {"ID", "IdAdoptante", "IdMascota", "Fecha"};

                TableFrame<Adopcion> frame = new TableFrame<>(
                        "Historial de adopciones",
                        columnas,
                        adopciones,
                        adopcion -> new Object[] {
                                adopcion.getId(),
                                adopcion.getIdAdoptante(),
                                adopcion.getIdMascota(),
                                adopcion.getFecha()

                        }
                );
            }
        });

        JButton tablaB = new JButton("Buscar un registro de adopcion");
        tablaB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BuscadorFrame buscadorFrame = new BuscadorFrame();
                buscadorFrame.setVisible(true);

            }
        });


        JButton rAdoptante = new JButton("Registrar adoptante");
        rAdoptante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Para registrar una adopcion primero debe registrar un adoptante y una mascota");

                RegistroAdoptanteFrame registroAdoptante = new RegistroAdoptanteFrame(false);
                registroAdoptante.setVisible(true);

            }
        });

        JButton rAdopcion = new JButton("Registrar adopción");
        rAdopcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RegistroAdoptanteFrame registroAdoptante = new RegistroAdoptanteFrame(true);
                registroAdoptante.setVisible(true);

            }
        });

        JButton rMascota = new JButton("Registrar mascota");
        rMascota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RegistroMascotaFrame registroMascota = new RegistroMascotaFrame(false);
               registroMascota.setVisible(true);
            }
        });



        JButton cerrarSesion = new JButton("Cerrar sesion");
        cerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cerrar sesion");
            }
        });




        JLabel tituloLabel = new JLabel("<html>Bienvenido/a a la veterinaria Don Horacio<br>por favor elija una opción</html>");
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panelR.add(tituloLabel);
        panelR.add(tablaE);
        panelR.add(tablaM);
        panelR.add(tablaA);
        panelR.add(tablaR);
       // panelR.add(tablaB);
       // panelR.add(rAdoptante);
        panelR.add(rMascota);
        panelR.add(rAdopcion);
        panelR.add(cerrarSesion);
        add(panelR);


    }
}
