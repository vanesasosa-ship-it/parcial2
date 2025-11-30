package org.example.DAO;

import org.example.DAO.interfaces.AdopcionDAO;
import org.example.modelo.Adopcion;
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

}
