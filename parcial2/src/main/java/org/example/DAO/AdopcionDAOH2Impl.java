package org.example.DAO;

import org.example.DAO.interfaces.AdopcionDAO;
import org.example.modelo.Adopcion;
import org.example.modelo.Adoptante;
import org.example.modelo.Empleado;
import org.example.modelo.Mascota;

import java.sql.*;
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
    public Adopcion registrar(Adopcion adopcion) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "INSERT INTO " + TABLE_NAME + "(idAdoptante, idMascota, fecha) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, adopcion.getIdAdoptante());
            preparedStatement.setInt(2, adopcion.getIdMascota());
            preparedStatement.setString(3, adopcion.getFecha());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

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
                                rs.getInt("idAdoptante"),
                                rs.getInt("idMascota"),
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
                                 rs.getInt("idAdoptante"),
                                 rs.getInt("idMascota"),
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
                        rs.getInt("idAdoptante"),
                        rs.getInt("idMascota"),
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


}
