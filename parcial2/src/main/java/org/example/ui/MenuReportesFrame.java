package org.example.ui;

import org.example.DAO.DAO;
import org.example.DAO.MascotaDAOH2Impl;
import org.example.modelo.Adopcion;
import org.example.sesion.SesionIniciada;

import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MenuReportesFrame  extends JFrame {

    DAO dao = new DAO();

    public MenuReportesFrame() {
        setTitle("Reportes");
        setSize(300, 400);
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

        JLabel titulo = new JLabel("Reportes disponibles");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));

        panel.add(Box.createRigidArea(new Dimension(0, 20)));


        Dimension buttonSize = new Dimension(260, 40);

        JButton m = new JButton("Mascotas más adoptadas (Top 3)");
        JButton  e = new JButton("<html>Empleados con mayor cantidad<br> de adopciones registradas</html>");
        JButton a = new JButton("Adopciones por especie");
        JButton a2 = new JButton("Adopciones en un rango de fechas");

        JButton[] botones = {m, e, a, a2};
        for (JButton b : botones) {
            b.setPreferredSize(buttonSize);
            b.setMaximumSize(buttonSize);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setFocusPainted(false);
            panel.add(b);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }



        m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<String> top3 = dao.especiesMasAdoptadas();

                if (top3 != null && !top3.isEmpty()) {

                    String mensaje = String.join("\n", top3);

                    JOptionPane.showMessageDialog(
                            null,
                            mensaje,
                            "Top 3 especies más adoptadas",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });


        e.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<String> ranking = dao.obtenerEmpleadosConMasAdopciones();
                if (ranking != null && !ranking.isEmpty()) {
                    String mensaje = String.join("\n", ranking);
                    JOptionPane.showMessageDialog(
                            null,
                            mensaje,
                            "Empleados con mayor cantidad de adopciones registradas",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });


        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Map<String, Integer> datos = dao.obtenerAdopcionesPorEspecie();

                int perros = datos.getOrDefault("Perro", 0);
                int gatos = datos.getOrDefault("Gato", 0);
                int conejos = datos.getOrDefault("Conejo", 0);

                GraficoPastelFrame grafico = new GraficoPastelFrame(perros, gatos, conejos);
                grafico.setVisible(true);



            }
        });


        a2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fechaInicio = JOptionPane.showInputDialog(
                        null,
                        "Ingresar fecha de inicio (dd/MM/yyyy):",
                        "Fecha inicio",
                        JOptionPane.QUESTION_MESSAGE
                );


                if (fechaInicio == null) return;


                String fechaFin = JOptionPane.showInputDialog(
                        null,
                        "Ingresar fecha de fin (dd/MM/yyyy):",
                        "Fecha fin",
                        JOptionPane.QUESTION_MESSAGE
                );


                if (fechaFin == null) return;


                List<Adopcion> lista = dao.obtenerAdopcionesPorRango(fechaInicio + " 00:00", fechaFin + " 23:59");

                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "No se encontraron adopciones en el rango seleccionado.",
                            "Adopciones",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }

                StringBuilder sb = new StringBuilder();

                for (Adopcion a : lista) {
                    sb.append(a.toString()).append("\n");
                }

                JOptionPane.showMessageDialog(
                        null,
                        sb.toString(),
                        "Adopciones en un rango de fechas",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });



        fondo.add(panel, gbc);
        add(fondo);

    }
}
