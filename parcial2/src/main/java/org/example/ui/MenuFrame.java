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
import org.example.sesion.SesionIniciada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

    public MenuFrame() {
        setTitle("Menu");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelR = new JPanel();
        panelR.setLayout(new GridLayout(10, 5));

        JButton tablaM = new JButton("Ver lista de mascotas");
        tablaM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MascotaDAO mascotaDAO = new MascotaDAOH2Impl();

                List<Mascota> mascotas = mascotaDAO.listarMascotas();

                String[] columnas = {"ID", "Nombre","Especie", "Fecha de nacimiento", "Peso", "adoptado", "cuidados"};

                TableFrame<Mascota> frame = new TableFrame<>(
                        "Listado de mascotas",
                        columnas,
                        mascotas,
                        mascota -> new Object[] {
                                mascota.getId(),
                                mascota.getNombre(),
                                mascota.getEspecie(),
                                mascota.getFechaNacimiento() != null ? mascota.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                                mascota.getPeso(),
                                mascota.getAdoptado(),
                                mascota.getCuidadosEspecificos()
                        }, id -> mascotaDAO.eliminarMascota(id),
                        id -> {
                            Mascota m = mascotaDAO.buscarRegistro(id);
                            if (m == null) {
                                JOptionPane.showMessageDialog(null, "No se encontró la mascota.");
                                return;
                            }

                            new EditorFrame<>(
                                    "Editar Mascota",
                                    m,
                                    new String[]{"Nombre", "Fecha Nacimiento", "Peso"},
                                    mas -> new Object[]{
                                            mas.getNombre(),
                                            mas.getFechaNacimiento() != null ? mas.getFechaNacimiento().toString() : "",
                                            mas.getPeso(),
                                            mas.getAdoptado(),
                                    },
                                    (mas, vals) -> {
                                        mas.setNombre((String) vals[0]);
                                        mas.setFechaNacimiento(vals[1].toString().isEmpty()
                                                ? null
                                                : LocalDate.parse(vals[1].toString()));
                                        mas.setPeso(Integer.parseInt((String) vals[2]));
                                    },
                                    mascotaEditada -> {
                                        mascotaDAO.actualizarMascota(mascotaEditada);

                                    }
                            );
                        }, null
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
                        },
                        id -> empleadoDAO.eliminarEmpleado(id),
                        id -> {
                            Empleado m = empleadoDAO.buscarEmpleado(id);
                            if (m == null) {
                                JOptionPane.showMessageDialog(null, "No se encontró el empleado.");
                                return;
                            }

                            new EditorFrame<>(
                                    "Editar Empleado",
                                    m,
                                    new String[]{"Nombre", "Cargo"},
                                    emp -> new Object[]{
                                            emp.getNombre(),
                                            emp.getCargo()
                                    },
                                    (emp, vals) -> {
                                        emp.setNombre((String) vals[0]);
                                        emp.setCargo((String) vals[1]);
                                    },
                                    empleadoEditado -> {
                                        empleadoDAO.actualizarEmpleado(empleadoEditado);

                                    }
                            );
                        }, null
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

                        }, id -> adoptanteDAO.eliminarAdoptante(id),
                        id->{
                            Adoptante m = adoptanteDAO.buscarRegistro(id);
                            if (m == null) {
                                JOptionPane.showMessageDialog(null, "No se encontró al adoptante.");
                                return;
                            }

                            new EditorFrame<>(
                                    "Editar Adoptante",
                                    m,
                                    new String[]{"Nombre", "Edad", "Direccion"},
                                    adop -> new Object[]{
                                            adop.getNombre(),
                                            adop.getEdad(),
                                            adop.getDireccion()
                                    },
                                    (adop, vals) -> {
                                        adop.setNombre((String) vals[0]);
                                        adop.setEdad(Integer.parseInt((String) vals[1]));
                                        adop.setDireccion((String) vals[2]);
                                    },
                                    adoptanteEditado -> {
                                        adoptanteDAO.actualizarAdoptante(adoptanteEditado);

                                    }
                            );
                        }, null
                );
            }
        });

        JButton tablaR = new JButton("Ver registro de adopciones");
        tablaR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                AdopcionDAO adopcionDAO = new AdopcionDAOH2Impl();

                List<Adopcion> adopciones = adopcionDAO.listarAdopciones();

                String[] columnas = {"ID", "Nombre del adoptante", "Nombre de la mascota", "Nombre empleado", "Fecha"};

                TableFrame<Adopcion> frame = new TableFrame<>(
                        "Historial de adopciones",
                        columnas,
                        adopciones,
                        adopcion -> new Object[] {
                                adopcion.getId(),
                                adopcion.getAdoptanteNombre(),
                                adopcion.getMascotaNombre(),
                                adopcion.getEmpleadoNombre(),
                                adopcion.getFecha()

                        }, id -> adopcionDAO.eliminarAdopcion(id),
                        id-> {
                            Adopcion m = adopcionDAO.buscarRegistro(id);
                            if (m == null) {
                                JOptionPane.showMessageDialog(null, "No se encontró el registro.");
                                return;
                            }

                            new EditorFrame<>(
                                    "Editar Adoptante",
                                    m,
                                    new String[]{"Nombre del Adoptante", "Nombre de la mascota", "fecha", "nombre del empleado"},
                                    adop -> new Object[]{
                                            adop.getAdoptanteNombre(),
                                            adop.getMascotaNombre(),
                                            adop.getFecha(),
                                            adop.getEmpleadoNombre(),
                                    },
                                    (adop, vals) -> {
                                        adop.setAdoptanteNombre((String) vals[0]);
                                        adop.setMascotaNombre((String) vals[1]);
                                        adop.setFecha((String) vals[2]);
                                        adop.setEmpleadoNombre((String) vals[3]);

                                    },
                                    adopcionEditada -> {
                                        adopcionDAO.actualizarAdopcion(adopcionEditada);

                                    }
                            );
                        }, id -> {


                    Adopcion adopcionCompleta = adopcionDAO.buscarConObjetos(id);

                    if (adopcionCompleta == null) {
                        JOptionPane.showMessageDialog(null, "No se encontró la adopción completa para imprimir.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String ticket = adopcionCompleta.generarTicket();

                    ImprimeTicketFrame ticketNuevo = new ImprimeTicketFrame(null, ticket);
                    ticketNuevo.setVisible(true);
                }
                );
            }
        });

        JButton tablaB = new JButton("Buscar un registro");
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
                new RegistroMascotaFrame(false, new MascotaDAOH2Impl()).setVisible(true);

              //  RegistroMascotaFrame registroMascota = new RegistroMascotaFrame(false);
             //  registroMascota.setVisible(true);
            }
        });

        JButton reportesB = new JButton("Reportes");
        reportesB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuReportesFrame().setVisible(true);
            }
        });



        JButton cerrarSesion = new JButton("Cerrar sesion");
        cerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SesionIniciada.cerrarSesion();
                JOptionPane.showMessageDialog(null, "Sesión cerrada correctamente.");

                dispose();
                LoginFrame login = new LoginFrame();
                login.setVisible(true);



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
        panelR.add(tablaB);
       // panelR.add(rAdoptante);
        panelR.add(rMascota);
        panelR.add(rAdopcion);
        panelR.add(reportesB);
        panelR.add(cerrarSesion);
        add(panelR);


    }
}
