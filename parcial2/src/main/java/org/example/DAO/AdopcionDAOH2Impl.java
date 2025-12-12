package org.example.DAO;

import org.example.DAO.interfaces.AdopcionDAO;
import org.example.modelo.Adopcion;
import org.example.modelo.Adoptante;
import org.example.modelo.Empleado;
import org.example.modelo.Mascota;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import org.example.DAO.interfaces.EmpleadoDAO;
import org.example.DAO.interfaces.MascotaDAO;
import org.example.DAO.interfaces.AdoptanteDAO;
import java.util.List;

public class AdopcionDAOH2Impl implements AdopcionDAO {

    private static final String URL = "jdbc:h2:./data/base";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "ADOPCION";

    DAO dao = new DAO();

public AdopcionDAOH2Impl() {

}

    @Override
    public Adopcion registrar(Adopcion adopcion ) {
        int adopcionId = 0;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String insertQuery = "INSERT INTO " + TABLE_NAME +
                    " (adoptanteNombre, mascotaNombre, fecha, empleadoNombre) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, adopcion.getAdoptanteNombre());
            preparedStatement.setString(2, adopcion.getMascotaNombre());
            preparedStatement.setString(3, adopcion.getFecha());
            preparedStatement.setString(4, adopcion.getEmpleadoNombre());

            preparedStatement.executeUpdate();


            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    adopcionId = rs.getInt(1);
                }
            }

            preparedStatement.close();

            adopcion.setId(adopcionId);
            JOptionPane.showMessageDialog(null, "La adopción fue registrada exitosamente!");



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adopcion;
    }


    @Override
    public List<Adopcion> listarAdopciones() {
       return dao.listarDatos(
                TABLE_NAME,
                URL,
                USER,
                PASSWORD,
                rs -> {
                    try {
                        return new Adopcion(
                                rs.getString("adoptanteNombre"),
                                rs.getString("mascotaNombre"),
                                rs.getString("empleadoNombre"),
                                rs.getString("fecha"),
                                rs.getInt("id")
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

    }
     @Override
    public Adopcion buscarRegistro(int id) {
         return dao.buscarPorId(
                 TABLE_NAME,
                 id,
                 URL,
                 USER,
                 PASSWORD,
                 rs -> {
                     try {
                         return new Adopcion(
                                 rs.getString("adoptanteNombre"),
                                 rs.getString("mascotaNombre"),
                                 rs.getString("empleadoNombre"),
                                 rs.getString("fecha"),
                                 rs.getInt("id")
                         );
                     } catch (SQLException e) {
                         throw new RuntimeException(e);
                     }
                 }
         );


     }

    @Override
    public Adopcion eliminarAdopcion(int id) {

        String selectSql = "SELECT * FROM ADOPCION WHERE id = ?";
        String deleteSql = "DELETE FROM ADOPCION WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {


            try (PreparedStatement psSelect = conn.prepareStatement(selectSql)) {
                psSelect.setInt(1, id);

                ResultSet rs = psSelect.executeQuery();

                if (!rs.next()) {
                    return null;
                }


                Adopcion adopcion = new Adopcion(
                        rs.getString("adoptanteNombre"),
                        rs.getString("mascotaNombre"),
                        rs.getString("empleadoNombre"),
                        rs.getString("fecha"),
                        rs.getInt("id")
                );
                adopcion.setId(rs.getInt("id"));


                try (PreparedStatement psDelete = conn.prepareStatement(deleteSql)) {
                    psDelete.setInt(1, id);
                    psDelete.executeUpdate();
                }

                return adopcion;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Adopcion actualizarAdopcion(Adopcion adopcion) {

        String sql = "UPDATE ADOPCION SET adoptanteNombre=?, mascotaNombre=?, fecha=?, empleadoNombre=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, adopcion.getAdoptanteNombre());
            ps.setString(2, adopcion.getMascotaNombre());
            ps.setString(3, adopcion.getFecha());
            ps.setString(4, adopcion.getEmpleadoNombre());
            ps.setInt(5, adopcion.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar adopcion: " + e.getMessage());
        }
        return adopcion;
    }



    public Adopcion buscarConObjetos(int id) {
        String sql = "SELECT * FROM ADOPCION WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String adoptanteNombre = rs.getString("adoptanteNombre");
                String mascotaNombre = rs.getString("mascotaNombre");
                String empleadoNombre = rs.getString("empleadoNombre");
                String fecha = rs.getString("fecha");


                AdoptanteDAO adoptanteDAO = new AdoptanteDAOH2Impl();
                MascotaDAO mascotaDAO = new MascotaDAOH2Impl();
                EmpleadoDAO empleadoDAO = new EmpleadoDAOHImpl();


                Adoptante adoptante = adoptanteDAO.buscarPorNombreA(adoptanteNombre);
                Mascota mascota = mascotaDAO.buscarMascota2(mascotaNombre);
                Empleado empleado = empleadoDAO.buscarPorNombre(empleadoNombre);


                Adopcion adopcion = new Adopcion(adoptante, mascota, empleado, fecha);

                return adopcion;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
