package org.example.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuReportesFrame  extends JFrame {
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


            }
        });

        JButton  e = new JButton("<html>Empleados con mayor cantidad<br> de adopciones registradas</html>");
        e.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton a = new JButton("Adopciones por especie");
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        JButton a2 = new JButton("Adopciones en un rango de fechas");
        a2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });



        panelR.add(m);
        panelR.add(e);
        panelR.add(a);
        panelR.add(a2);

        add(panelR);


    }
}
