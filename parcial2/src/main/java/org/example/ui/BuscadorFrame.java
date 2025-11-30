package org.example.ui;
import org.example.DAO.AdopcionDAOH2Impl;
import org.example.DAO.interfaces.AdopcionDAO;
import org.example.modelo.Adopcion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscadorFrame extends JFrame {

    private JTextField idField;


    public BuscadorFrame() {
        setTitle("Buscador");
        setSize(350, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelR = new JPanel();
        panelR.setLayout(new GridLayout(2, 3));

        JLabel idLabel = new JLabel("Ingrese el id del registro:");
        idField = new JTextField();

        panelR.add(idLabel);
        panelR.add(idField);

        JButton buscar = new JButton("Buscar");
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(idField.getText());

                AdopcionDAO adopcionDAO = new AdopcionDAOH2Impl();

                Adopcion adopcion = adopcionDAO.buscarRegistro(id);

               if (adopcion != null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "ID: " + adopcion.getId() +
                                    "\nID Adoptante: " + adopcion.getIdAdoptante() +
                                    "\nID Mascota: " + adopcion.getIdMascota() +
                                    "\nFecha: " + adopcion.getFecha()
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "No se encontró un registro con ID: " + id
                    );
                }
            }



        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(buscar);

        panelR.add(panelBoton);
        add(panelR);
    }



}
