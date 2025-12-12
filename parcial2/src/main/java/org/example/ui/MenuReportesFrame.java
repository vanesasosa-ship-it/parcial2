package org.example.ui;

import org.example.DAO.DAO;
import org.example.modelo.Adopcion;

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

        JPanel panelR = new JPanel();
        panelR.setLayout(new GridLayout(4, 5));



        JButton m = new JButton("Mascotas más adoptadas (Top 3)");
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

        JButton  e = new JButton("<html>Empleados con mayor cantidad<br> de adopciones registradas</html>");
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

        JButton a = new JButton("Adopciones por especie");
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

        JButton a2 = new JButton("Adopciones en un rango de fechas");
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




        panelR.add(m);
        panelR.add(e);
        panelR.add(a);
        panelR.add(a2);

        add(panelR);


    }
}
