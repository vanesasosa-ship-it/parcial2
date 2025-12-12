package org.example.ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class EditorFrame<T> extends JFrame {

    private final JTextField[] fieldInputs;

    public EditorFrame(
            String titulo,
            T objetoOriginal,
            String[] campos,
            Function<T, Object[]> getterMapper,
            BiConsumer<T, Object[]> setterMapper,
            Consumer<T> callbackGuardar
    ) {

        setTitle(titulo);
        int calculatedHeight = campos.length * 100;
        int finalHeight = Math.max(250, calculatedHeight);

        setSize(350, finalHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));


        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        panel.add(tituloLabel);


        JPanel camposPanel = new JPanel(new GridLayout(campos.length, 2, 10, 10));
        camposPanel.setOpaque(false);

        Object[] valores = getterMapper.apply(objetoOriginal);
        fieldInputs = new JTextField[campos.length];

        for (int i = 0; i < campos.length; i++) {

            JLabel label = new JLabel(campos[i] + ":");
            JTextField input = new JTextField(valores[i].toString());

            fieldInputs[i] = input;

            camposPanel.add(label);
            camposPanel.add(input);
        }

        panel.add(camposPanel);


        panel.add(Box.createRigidArea(new Dimension(0, 15)));


        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBoton.setOpaque(false);

        JButton btnGuardar = new JButton("Guardar cambios");
        panelBoton.add(btnGuardar);

        panel.add(panelBoton);

        add(panel);


        btnGuardar.addActionListener(e -> {

            Object[] nuevosValores = new Object[campos.length];

            for (int i = 0; i < fieldInputs.length; i++) {
                nuevosValores[i] = fieldInputs[i].getText();
            }

            setterMapper.accept(objetoOriginal, nuevosValores);
            callbackGuardar.accept(objetoOriginal);

            JOptionPane.showMessageDialog(this, "Registro actualizado.");
            dispose();
        });

        setVisible(true);
    }
}
