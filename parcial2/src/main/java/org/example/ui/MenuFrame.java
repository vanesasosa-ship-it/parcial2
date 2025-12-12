package org.example.ui;

import org.example.DAO.*;
import org.example.DAO.interfaces.*;
import org.example.modelo.*;
import org.example.sesion.SesionIniciada;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MenuFrame extends JFrame {

    public MenuFrame() {

        setTitle("Menú Principal");
        setSize(420, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel fondo = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panel.setMaximumSize(new Dimension(350, 600));

        JLabel titulo = new JLabel("Veterinaria Don Horacio");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Seleccione una opción");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(subtitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));


        Dimension buttonSize = new Dimension(260, 40);

        JButton tablaM = new JButton("Lista de Mascotas");
        JButton tablaE = new JButton("Lista de Empleados");
        JButton tablaR = new JButton("Registros de Adopción");
        JButton tablaB = new JButton("Buscar registro");
        JButton tablaA = new JButton("Lista de Adoptantes");
        JButton rMascota = new JButton("Registrar Mascota");
        JButton rAdopcion = new JButton("Registrar Adopción");
        JButton rAdoptante = new JButton("Registrar Adoptante");
        JButton reportesB = new JButton("Reportes");
        JButton cerrarSesion = new JButton("Cerrar Sesión");

        JButton[] botones = {tablaM, tablaE, tablaA, tablaR, tablaB, rMascota, rAdoptante, rAdopcion, reportesB, cerrarSesion};

        for (JButton b : botones) {
            b.setPreferredSize(buttonSize);
            b.setMaximumSize(buttonSize);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setFocusPainted(false);
            panel.add(b);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }


        tablaM.addActionListener(e -> {
            MascotaDAO mascotaDAO = new MascotaDAOH2Impl();
            List<Mascota> mascotas = mascotaDAO.listarMascotas();

            String[] columnas = {"ID", "Nombre", "Especie", "Nacimiento", "Peso", "Adoptado", "Cuidados"};

            new TableFrame<>(
                    "Listado de mascotas",
                    columnas,
                    mascotas,
                    m -> new Object[]{
                            m.getId(), m.getNombre(), m.getEspecie(),
                            m.getFechaNacimiento() != null ?
                                    m.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                            m.getPeso(), m.getAdoptado(),   String.join(", ", m.getCuidadosEspecificos())
                    },
                    mascotaDAO::eliminarMascota,
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
                                        mas.getPeso()
                                },
                                (mas, vals) -> {
                                    mas.setNombre((String) vals[0]);
                                    mas.setFechaNacimiento(((String) vals[1]).isEmpty() ? null : LocalDate.parse((String) vals[1]));
                                    mas.setPeso(Integer.parseInt((String) vals[2]));
                                },
                                mascotaDAO::actualizarMascota
                        );
                    },
                    null
            );
        });

        tablaE.addActionListener(e -> {
            EmpleadoDAO empleadoDAO = new EmpleadoDAOHImpl();
            List<Empleado> empleados = empleadoDAO.listarEmpleados();

            String[] columnas = {"ID", "Nombre", "Cargo"};

            new TableFrame<>(
                    "Listado de empleados",
                    columnas,
                    empleados,
                    emp -> new Object[]{emp.getId(), emp.getNombre(), emp.getCargo()},
                    empleadoDAO::eliminarEmpleado,
                    id -> {

                        Empleado emp = empleadoDAO.buscarEmpleado(id);
                        if (emp == null) {
                            JOptionPane.showMessageDialog(null, "No se encontró el empleado.");
                            return;
                        }

                        new EditorFrame<>(
                                "Editar Empleado",
                                emp,
                                new String[]{"Nombre", "Cargo"},
                                e2 -> new Object[]{e2.getNombre(), e2.getCargo()},
                                (e2, vals) -> {
                                    e2.setNombre((String) vals[0]);
                                    e2.setCargo((String) vals[1]);
                                },
                                empleadoDAO::actualizarEmpleado
                        );
                    },
                    null
            );
        });

        tablaA.addActionListener(e -> {
            AdoptanteDAO adoptanteDAO = new AdoptanteDAOH2Impl();
            List<Adoptante> adoptantes = adoptanteDAO.listarAdoptantes();

            String[] columnas = {"ID", "Nombre", "Edad", "Dirección"};

            new TableFrame<>(
                    "Listado de adoptantes",
                    columnas,
                    adoptantes,
                    a -> new Object[]{a.getId(), a.getNombre(), a.getEdad(), a.getDireccion()},
                    adoptanteDAO::eliminarAdoptante,
                    id -> {

                        Adoptante a = adoptanteDAO.buscarRegistro(id);
                        if (a == null) {
                            JOptionPane.showMessageDialog(null, "No se encontró al adoptante.");
                            return;
                        }

                        new EditorFrame<>(
                                "Editar Adoptante",
                                a,
                                new String[]{"Nombre", "Edad", "Dirección"},
                                ad -> new Object[]{ad.getNombre(), ad.getEdad(), ad.getDireccion()},
                                (ad, vals) -> {
                                    ad.setNombre((String) vals[0]);
                                    ad.setEdad(Integer.parseInt((String) vals[1]));
                                    ad.setDireccion((String) vals[2]);
                                },
                                adoptanteDAO::actualizarAdoptante
                        );
                    },
                    null
            );
        });

        tablaR.addActionListener(e -> {
            AdopcionDAO adopcionDAO = new AdopcionDAOH2Impl();
            List<Adopcion> adopciones = adopcionDAO.listarAdopciones();

            String[] columnas = {"ID", "Adoptante", "Mascota", "Empleado", "Fecha"};

            new TableFrame<>(
                    "Historial de adopciones",
                    columnas,
                    adopciones,
                    ad -> new Object[]{
                            ad.getId(),
                            ad.getAdoptanteNombre(),
                            ad.getMascotaNombre(),
                            ad.getEmpleadoNombre(),
                            ad.getFecha()
                    },
                    adopcionDAO::eliminarAdopcion,
                    id -> {

                        Adopcion ad = adopcionDAO.buscarRegistro(id);
                        if (ad == null) {
                            JOptionPane.showMessageDialog(null, "No se encontró la adopción.");
                            return;
                        }

                        new EditorFrame<>(
                                "Editar Adopción",
                                ad,
                                new String[]{"Adoptante", "Mascota", "Fecha", "Empleado"},
                                a -> new Object[]{
                                        a.getAdoptanteNombre(),
                                        a.getMascotaNombre(),
                                        a.getFecha(),
                                        a.getEmpleadoNombre()
                                },
                                (a, vals) -> {
                                    a.setAdoptanteNombre((String) vals[0]);
                                    a.setMascotaNombre((String) vals[1]);
                                    a.setFecha((String) vals[2]);
                                    a.setEmpleadoNombre((String) vals[3]);
                                },
                                adopcionDAO::actualizarAdopcion
                        );
                    },
                    id -> {
                        Adopcion completa = adopcionDAO.buscarConObjetos(id);
                        if (completa == null) {
                            JOptionPane.showMessageDialog(null, "No se pudo cargar la adopción.");
                            return;
                        }
                        String ticket = completa.generarTicket();
                        new ImprimeTicketFrame(null, ticket).setVisible(true);
                    }
            );
        });

        tablaB.addActionListener(e -> new BuscadorFrame().setVisible(true));
        rMascota.addActionListener(e -> new RegistroMascotaFrame(false, new MascotaDAOH2Impl()).setVisible(true));
        rAdopcion.addActionListener(e -> new RegistroAdoptanteFrame(true).setVisible(true));
        rAdoptante.addActionListener(e -> new RegistroAdoptanteFrame(false).setVisible(true));
        reportesB.addActionListener(e -> new MenuReportesFrame().setVisible(true));

        cerrarSesion.addActionListener(e -> {
            SesionIniciada.cerrarSesion();
            JOptionPane.showMessageDialog(null, "Sesión cerrada correctamente.");
            dispose();
            new LoginFrame().setVisible(true);
        });

        fondo.add(panel, gbc);
        add(fondo);
    }
}
