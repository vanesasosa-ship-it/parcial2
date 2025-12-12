package org.example.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;

public class GraficoPastelFrame extends JFrame {

    public GraficoPastelFrame(int cantP, int cantG, int cantC) {
        setTitle("Gráfico de Pastel");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Perros", cantP);
        dataset.setValue("Gatos", cantG);
        dataset.setValue("Conejos", cantC);

        JFreeChart chart = ChartFactory.createPieChart(
                "Mascotas Adoptadas",
                dataset,
                true, true, false
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }
    public GraficoPastelFrame() {
        this(0, 0, 0);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GraficoPastelFrame().setVisible(true);
        });
    }
}

