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
        setSize(350, campos.length * 60);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(campos.length, 2));


        Object[] valores = getterMapper.apply(objetoOriginal);

        fieldInputs = new JTextField[campos.length];


        for (int i = 0; i < campos.length; i++) {
            JLabel label = new JLabel(campos[i] + ":");
            JTextField input = new JTextField(valores[i].toString());
            fieldInputs[i] = input;

            panel.add(label);
            panel.add(input);
        }

        add(panel, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar cambios");
        add(btnGuardar, BorderLayout.SOUTH);

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

