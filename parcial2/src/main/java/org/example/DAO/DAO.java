package org.example.DAO;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Function;

public class DAO {


    public void createTableDAO(String TABLE_NAME, String createTableSQL, String URL,String USER, String PASSWORD){

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {


            String checkTable = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES " +
                    "WHERE TABLE_NAME = ?";

            try (PreparedStatement ps = connection.prepareStatement(checkTable)) {
                ps.setString(1, TABLE_NAME.toUpperCase());
                ResultSet rs = ps.executeQuery();
                boolean existeTabla = false;

                if (rs.next() && rs.getInt(1) > 0) {
                    existeTabla = true;
                }


                if (!existeTabla) {
                    System.out.println("Creando la tabla " + TABLE_NAME );


                    try (Statement stmt = connection.createStatement()) {
                        stmt.executeUpdate(createTableSQL);
                        System.out.println("Tabla creada correctamente.");
                    }

                } else {
                    System.out.println("La tabla " + TABLE_NAME + " ya existe.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  <T> List<T> listarDatos(String TABLE_NAME, String URL,String USER, String PASSWORD, Function<ResultSet, T> FILAS){

        List<T> lista = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);


            while (resultSet.next()) {
                lista.add(FILAS.apply(resultSet));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public <T> T buscarPorId(
            String TABLE_NAME,
            int id,
            String URL,
            String USER,
            String PASSWORD,
            Function<ResultSet, T> mapper
    ) {

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapper.apply(rs);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No existe un registro con ese id");
        }

        return null;
    }


}
