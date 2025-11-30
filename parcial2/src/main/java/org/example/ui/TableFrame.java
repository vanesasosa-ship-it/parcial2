package org.example.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Function;


public class TableFrame<T> extends JFrame {

    public TableFrame(String titulo, String[] columnas,
            List<T> data,
            Function<T, Object[]> rowMapper
    ) {

        setTitle(titulo);
        setSize(500, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
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
            setContentPane(panel);

            setLocationRelativeTo(null);
            setVisible(true);


        }
    }


}
