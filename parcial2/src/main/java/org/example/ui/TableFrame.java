package org.example.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class TableFrame<T> extends JFrame {

    public TableFrame(String titulo, String[] columnas,
            List<T> data,
            Function<T, Object[]> rowMapper,
                      Consumer<Integer> deleteCallback,
                      Consumer<Integer> editCallback,
                      Consumer<Integer> imprimirCallback
    ) {

        setTitle(titulo);
        setSize(1000, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));

        DefaultTableModel tableModel = new DefaultTableModel(columnas, 0);


        if  (data == null || data.isEmpty()) {

            JOptionPane.showMessageDialog(null, "No hay datos en esta tabla");


        } else {
            for (T item : data) {
                tableModel.addRow(rowMapper.apply(item));
            }

            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);

            panel.add(scrollPane, BorderLayout.CENTER);

            JButton editar = new JButton("Editar seleccionado");
            editar.addActionListener(e -> {
                int fila = table.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione un registro");
                    return;
                }

                Object idObj = tableModel.getValueAt(fila, 0);

                if (idObj instanceof Integer id) {
                    if (editCallback != null) {
                        editCallback.accept(id);
                        dispose();
                    }
                }
            });


            JButton eliminar = new JButton("Eliminar seleccionado");

            eliminar.addActionListener(e -> {
                int fila = table.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione un registro");
                    return;
                }

                Object idObj = tableModel.getValueAt(fila, 0);

                if (idObj instanceof Integer) {
                    int id = (int) idObj;


                    if (deleteCallback != null) {
                        deleteCallback.accept(id);
                    }
                }


                tableModel.removeRow(fila);

                JOptionPane.showMessageDialog(null, "Registro eliminado");
                dispose();
            });

            JPanel southPanel = new JPanel();
            southPanel.add(editar);
            southPanel.add(eliminar);


            if (imprimirCallback != null) {
                JButton imprimir = new JButton("Imprimir seleccionado");
                imprimir.addActionListener(e -> {
                    int fila = table.getSelectedRow();

                    if (fila == -1) {
                        JOptionPane.showMessageDialog(null, "Seleccione un registro");
                        return;
                    }

                    Object idObj = tableModel.getValueAt(fila, 0);

                    if (idObj instanceof Integer id) {
                        imprimirCallback.accept(id);
                    }
                });

                southPanel.add(imprimir);
            }



            panel.add(southPanel, BorderLayout.SOUTH);

            setContentPane(panel);
            setLocationRelativeTo(null);
            setVisible(true);


        }
    }


}
