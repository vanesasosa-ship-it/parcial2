package org.example.DAO;

import org.example.DAO.interfaces.MascotaDAO;
import org.example.modelo.*;
import org.example.ui.RegistrarAdopcionFrame;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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

                String checkQuery = "SELECT id FROM " + TABLE_NAME + " WHERE nombre = ?";
                try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, mascota.getNombre());
                    try (ResultSet rs = checkStmt.executeQuery()) {
                        if (rs.next()) {

                            System.out.println("Ya existe la mascota en la tabla");

                        }
                    }
                }
            }else{
                String insertQuery = "INSERT INTO " + TABLE_NAME + " (nombre, especie, fechaNacimiento, peso, cuidados) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, mascota.getNombre());
                    ps.setString(2, mascota.getEspecie());
                    ps.setDate(3, java.sql.Date.valueOf(mascota.getFechaNacimiento()));
                    ps.setInt(4, mascota.getPeso());
                    ps.setString(5, String.join(",", mascota.getCuidadosEspecificos()));

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
                                rs.getString("nombre"),
                                rs.getString("especie"),
                                rs.getDate("fechaNacimiento").toLocalDate(),
                                rs.getInt("peso"),
                                rs.getInt("id"),
                                Arrays.asList(rs.getString("cuidados").split(","))
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
                                rs.getString("nombre"),
                                rs.getString("especie"),
                                rs.getDate("fechaNacimiento").toLocalDate(),
                                rs.getInt("peso"),
                                rs.getInt("id"),
                                Arrays.asList(rs.getString("cuidados").split(","))
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );


    }

    @Override
    public Mascota eliminarMascota(int id) {

        String selectSql = "SELECT * FROM MASCOTA WHERE id = ?";
        String deleteSql = "DELETE FROM MASCOTA WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {


            try (PreparedStatement psSelect = conn.prepareStatement(selectSql)) {
                psSelect.setInt(1, id);

                ResultSet rs = psSelect.executeQuery();

                if (!rs.next()) {
                    return null;
                }


                Mascota mascota = new Mascota(
                        rs.getString("nombre"),
                        rs.getString("especie"),
                        rs.getDate("fechaNacimiento").toLocalDate(),
                        rs.getInt("peso"),
                        new ArrayList<>()
                );
                mascota.setId(rs.getInt("id"));


                try (PreparedStatement psDelete = conn.prepareStatement(deleteSql)) {
                    psDelete.setInt(1, id);
                    psDelete.executeUpdate();
                }

                return mascota;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

 @Override
    public Mascota buscarMascota(String nombre) {
     return dao.buscarPorNombre(
             TABLE_NAME,
             nombre,
             URL,
             USER,
             PASSWORD,
             rs -> {
                 try {
                     return new Mascota(
                             rs.getInt("id"),
                             rs.getString("nombre"),
                             rs.getString("especie"),
                             rs.getDate("fechaNacimiento").toLocalDate(),
                             rs.getInt("peso"),
                             Arrays.asList(rs.getString("cuidados").split(","))
                     );
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
             }
     );
 }

 @Override
    public Mascota actualizarMascota(Mascota mascota) {
        String sql = "UPDATE MASCOTA SET nombre=?, especie=?, fechaNacimiento=?, peso=?, cuidados=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEspecie());
            ps.setDate(3, java.sql.Date.valueOf(mascota.getFechaNacimiento()));
            ps.setInt(4, mascota.getPeso());
            ps.setString(5, String.join(",", mascota.getCuidadosEspecificos()));
            ps.setInt(6, mascota.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar mascota: " + e.getMessage());
        }
     return mascota;
 }

}
