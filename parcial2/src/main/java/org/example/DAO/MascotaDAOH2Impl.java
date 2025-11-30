package org.example.DAO;

import org.example.DAO.interfaces.MascotaDAO;
import org.example.modelo.Adopcion;
import org.example.modelo.AdopcionIniciada;
import org.example.modelo.Adoptante;
import org.example.modelo.Mascota;
import org.example.ui.RegistrarAdopcionFrame;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAOH2Impl implements MascotaDAO {

    private static final String URL = "jdbc:h2:./data/base";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "MASCOTA";

    DAO dao = new DAO();

    public MascotaDAOH2Impl() {

    }


    public Mascota guardarMascota(Mascota mascota, boolean iniciaP) {


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            if (iniciaP) {

                String checkQuery = "SELECT id FROM " + TABLE_NAME + " WHERE nomMascota = ?";
                try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, mascota.getNomMascota());
                    try (ResultSet rs = checkStmt.executeQuery()) {
                        if (rs.next()) {

                            System.out.println("Ya existe la mascota en la tabla");

                        }
                    }
                }
            }else{
                String insertQuery = "INSERT INTO " + TABLE_NAME + " (nomMascota, especie, fechaNacimiento, peso) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, mascota.getNomMascota());
                    ps.setString(2, mascota.getEspecie());
                    ps.setDate(3, java.sql.Date.valueOf(mascota.getFechaNacimiento()));
                    ps.setInt(4, mascota.getPeso());

                    ps.executeUpdate();
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int idGenerado = generatedKeys.getInt(1);
                            mascota.setId(idGenerado);
                        }
                    }
            }


            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la mascota: " + e.getMessage());
            return null;
        }

        return mascota;
    }



    @Override
    public List<Mascota> listarMascotas() {
        return dao.listarDatos(
                TABLE_NAME,
                URL,
                USER,
                PASSWORD,
                rs -> {
                    try {
                        return new Mascota(
                                rs.getString("nomMascota"),
                                rs.getString("especie"),
                                rs.getDate("fechaNacimiento").toLocalDate(),
                                rs.getInt("peso"),
                                rs.getInt("id")
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }


    public Mascota buscarRegistro(int id) {
        return dao.buscarPorId(
                TABLE_NAME,
                id,
                URL,
                USER,
                PASSWORD,
                rs -> {
                    try {
                        return new Mascota(
                                rs.getString("nomMascota"),
                                rs.getString("especie"),
                                rs.getDate("fechaNacimiento").toLocalDate(),
                                rs.getInt("peso"),
                                rs.getInt("id")
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );


    }

}
