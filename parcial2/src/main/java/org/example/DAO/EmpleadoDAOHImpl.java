package org.example.DAO;

import org.example.DAO.interfaces.EmpleadoDAO;
import org.example.modelo.Empleado;
import org.example.ui.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;

public class EmpleadoDAOHImpl  implements EmpleadoDAO {

    private static final String URL = "jdbc:h2:./data/base";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "EMPLEADO";

    DAO dao = new DAO();

    public EmpleadoDAOHImpl() {

    }


    public Empleado registrarEmpleado(Empleado empleado, boolean iniciaP) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {


            String checkQuery = "SELECT id FROM " + TABLE_NAME + " WHERE nombre = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setString(1, empleado.getNombre());
            ResultSet rs = checkStmt.executeQuery();


            if (rs.next()) {
                if(iniciaP){
                    System.out.println("Ya existe el usuario en la tabla");
                }else{
                    JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre");
                }


                return null;
            }else{
                String insertQuery = "INSERT INTO " + TABLE_NAME + "(nombre, cargo, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, empleado.getNombre());
                preparedStatement.setString(2, empleado.getCargo());
                preparedStatement.setString(3, empleado.getPassword());
                preparedStatement.executeUpdate();



            }


            return empleado;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Empleado> listarEmpleados() {
        return dao.listarDatos(
                TABLE_NAME,
                URL,
                USER,
                PASSWORD,
                rs -> {
                    try {
                        return new Empleado(
                                rs.getString("nombre"),
                                rs.getString("cargo"),
                                rs.getString("password"),
                                rs.getInt("id")
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public Empleado login(String nombre, String password) {

        String sql = "SELECT * FROM EMPLEADO WHERE nombre = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getString("nombre"),
                        rs.getString("cargo")
                );
                empleado.setId(rs.getInt("id"));

                return empleado;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Empleado eliminarEmpleado(int id) {

        String selectSql = "SELECT * FROM EMPLEADO WHERE id = ?";
        String deleteSql = "DELETE FROM EMPLEADO WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {


            try (PreparedStatement psSelect = conn.prepareStatement(selectSql)) {
                psSelect.setInt(1, id);

                ResultSet rs = psSelect.executeQuery();

                if (!rs.next()) {
                    return null;
                }


                Empleado empleado = new Empleado(
                        rs.getString("nombre"),
                        rs.getString("cargo")
                );
                empleado.setId(rs.getInt("id"));


                try (PreparedStatement psDelete = conn.prepareStatement(deleteSql)) {
                    psDelete.setInt(1, id);
                    psDelete.executeUpdate();
                }

                return empleado;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
