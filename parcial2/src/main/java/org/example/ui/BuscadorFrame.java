package org.example.ui;

import org.example.DAO.DAO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiConsumer;

public class BuscadorFrame extends JFrame {

    private JTextField nombreField;
    DAO dao = new DAO();

    public BuscadorFrame() {
        setTitle("Buscador");
        setSize(350, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelR = new JPanel();
        panelR.setLayout(new GridLayout(3, 1));

        JLabel tituloLabel = new JLabel("<html>Ingrese un nombre<br>puede ser de una mascota, adoptante o empleado</html>");
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelR.add(tituloLabel);

        JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nombreField = new JTextField();
        nombreField.setPreferredSize(new Dimension(250, 25));
        panelInput.add(nombreField);
        panelR.add(panelInput);



        JButton buscar = new JButton("Buscar");

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(buscar);
        panelR.add(panelBoton);

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = nombreField.getText();

                if (nombre == null || nombre.isBlank()) {
                    return;
                }

                List<Object[]> datos = dao.buscador(nombre);

                if (datos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
                    return;
                }


                List<Object[]> empleados = new ArrayList<>();
                List<Object[]> mascotas = new ArrayList<>();
                List<Object[]> adoptantes = new ArrayList<>();
                List<Object[]> adopciones = new ArrayList<>();

                for (Object[] fila : datos) {
                    String tipo = fila[0].toString();

                    switch (tipo) {
                        case "Empleado":
                            empleados.add(fila);
                            break;
                        case "Mascota":
                            mascotas.add(fila);
                            break;
                        case "Adoptante":
                            adoptantes.add(fila);
                            break;
                        case "Adopcion":
                            adopciones.add(fila);
                            break;
                    }
                }

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


                BiConsumer<String, List<Object[]>> agregarTabla = (titulo, lista) -> {
                    if (!lista.isEmpty()) {

                        String tipo = lista.get(0)[0].toString();
                        String[] columnas;

                        switch (tipo) {
                            case "Empleado":
                                columnas = new String[]{"Tipo", "ID", "Nombre", "Cargo", "-"};
                                break;
                            case "Mascota":

                                columnas = new String[]{"Tipo", "ID", "Nombre", "Especie", "Adoptado"};
                                break;
                            case "Adoptante":
                                columnas = new String[]{"Tipo", "ID", "Nombre", "Edad", "Direccion"};
                                break;
                            case "Adopcion":
                                columnas = new String[]{"Tipo", "ID", "Adoptante", "Mascota", "Fecha", "Empleado"};
                                break;
                            default:
                                columnas = new String[]{"Tipo", "Col1", "Col2", "Col3", "Col4"};
                        }

                        JLabel label = new JLabel(titulo);
                        label.setFont(new Font("Arial", Font.BOLD, 16));
                        panel.add(label);

                        Object[][] dataArray = lista.toArray(new Object[0][]);

                        JTable tabla = new JTable(dataArray, columnas);


                        tabla.removeColumn(tabla.getColumnModel().getColumn(0));

                        JScrollPane scroll = new JScrollPane(tabla);
                        scroll.setPreferredSize(new Dimension(600, 150));
                        panel.add(scroll);

                        panel.add(Box.createVerticalStrut(15));
                    }
                };


                agregarTabla.accept("Tabla empleados", empleados);
                agregarTabla.accept("Tabla mascotas", mascotas);
                agregarTabla.accept("Tabla adoptantes", adoptantes);
                agregarTabla.accept("Tabla adopciones", adopciones);

                JScrollPane scrollGeneral = new JScrollPane(panel);
                scrollGeneral.setPreferredSize(new Dimension(650, 500));

                JOptionPane.showMessageDialog(
                        null,
                        scrollGeneral,
                        "Resultados de búsqueda",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });


        add(panelR);
    }
}
