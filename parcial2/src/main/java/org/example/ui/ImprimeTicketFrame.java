package org.example.ui;

import javax.swing.*;
import java.awt.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.DAO.AdoptanteDAOH2Impl;
import org.example.DAO.MascotaDAOH2Impl;
import org.example.DAO.interfaces.AdoptanteDAO;
import org.example.DAO.interfaces.MascotaDAO;
import org.example.modelo.AdopcionIniciada;
import org.example.modelo.Adoptante;
import org.example.modelo.Empleado;
import org.example.modelo.Mascota;
import org.example.sesion.SesionIniciada;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ImprimeTicketFrame extends JDialog {

    public ImprimeTicketFrame(JFrame parent , String texto) {
        super(parent, "Imprimir", true);

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel mensaje = new JLabel("Desea imprimir este ticket?");
        mensaje.setHorizontalAlignment(SwingConstants.CENTER);
        add(mensaje, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        JButton btnCrearPDF = new JButton("Descargar PDF");
        JButton btnCerrar = new JButton("Cerrar");

        botones.add(btnCrearPDF);
        botones.add(btnCerrar);
        add(botones, BorderLayout.SOUTH);

        btnCrearPDF.addActionListener(e -> {
            CrearPDF(texto);
            dispose();
        });

        btnCerrar.addActionListener(e -> {
            cerrar();
            dispose();
        });
    }

    private static void CrearPDF(String texto) {

        String rutaDescargas = System.getProperty("user.home") + File.separator + "Downloads";
        String ruta = rutaDescargas + File.separator + "Ticket de adopción.pdf";

        Document documento = new Document();


        try {
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();

            documento.add(new Paragraph(texto));

            JOptionPane.showMessageDialog(null, "Revisa tu carpeta de descargas");


        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }

    private void cerrar() {
        dispose();

    }

}
