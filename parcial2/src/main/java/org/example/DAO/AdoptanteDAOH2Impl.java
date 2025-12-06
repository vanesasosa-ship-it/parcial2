package org.example.DAO;

import org.example.DAO.interfaces.AdoptanteDAO;
import org.example.modelo.Adoptante;
import org.example.modelo.Empleado;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class AdoptanteDAOH2Impl implements AdoptanteDAO {

    private static final String URL = "jdbc:h2:./data/base";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "ADOPTANTE";

    DAO dao = new DAO();

    public AdoptanteDAOH2Impl() {


    }


    @Override
    public List<Adoptante> listarAdoptantes() {
        return dao.listarDatos(
                TABLE_NAME,
                URL,
                USER,
                PASSWORD,
                rs -> {
                    try {
                        return new Adoptante(
                                rs.getString("nombre"),
                                rs.getString("direccion"),
                                rs.getInt("edad"),
                                rs.getInt("id")
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }



    @Override
    public Adoptante registrar(Adoptante adoptante, boolean iniciaP) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {


            String checkQuery = "SELECT id FROM " + TABLE_NAME + " WHERE NOMBRE = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setString(1, adoptante.getNombre());
            ResultSet rs = checkStmt.executeQuery();



            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                adoptante.setId(idGenerado);
                if(iniciaP){
                    System.out.println("Ya existe el adoptante");
                }


                return null;
            }else{
                String insertQuery = "INSERT INTO " + TABLE_NAME + "(nombre, direccion, edad) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(
                        insertQuery,
                        Statement.RETURN_GENERATED_KEYS
                );
                preparedStatement.setString(1, adoptante.getNombre());
                preparedStatement.setString(2, adoptante.getDireccion());
                preparedStatement.setInt(3, adoptante.getEdad());
                preparedStatement.executeUpdate();

                ResultSet rst = preparedStatement.getGeneratedKeys();

                if (rst.next()) {
                    int idGenerado = rst.getInt(1);
                    adoptante.setId(idGenerado);
                }

                rst.close();
                preparedStatement.close();
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        return adoptante;

    }

    public Adoptante buscarRegistro(int id) {
        return dao.buscarPorId(
                TABLE_NAME,
                id,
                URL,
                USER,
                PASSWORD,
                rs -> {
                    try {
                        return new Adoptante(
                                rs.getString("nombre"),
                                rs.getString("direccion"),
                                rs.getInt("edad"),
                                rs.getInt("id")
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );


    }

    @Override
    public Adoptante eliminarAdoptante(int id) {

        String selectSql = "SELECT * FROM ADOPTANTE WHERE id = ?";
        String deleteSql = "DELETE FROM ADOPTANTE WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {


            try (PreparedStatement psSelect = conn.prepareStatement(selectSql)) {
                psSelect.setInt(1, id);

                ResultSet rs = psSelect.executeQuery();

                if (!rs.next()) {
                    return null;
                }


                Adoptante adoptante = new Adoptante(
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getInt("edad"),
                        rs.getInt("id")
                );
                adoptante.setId(rs.getInt("id"));


                try (PreparedStatement psDelete = conn.prepareStatement(deleteSql)) {
                    psDelete.setInt(1, id);
                    psDelete.executeUpdate();
                }

                return adoptante;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
