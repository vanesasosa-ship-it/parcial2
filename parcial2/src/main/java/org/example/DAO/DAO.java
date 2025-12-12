package org.example.DAO;

import org.example.modelo.Adopcion;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DAO {

    private static final String URL = "jdbc:h2:./data/base";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

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

    public <T> List<T> buscarPorNombre(
            String TABLE_NAME,
            String nombre,
            String URL,
            String USER,
            String PASSWORD,
            Function<ResultSet, T> mapper
    ) {
        List<T> resultados = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {


            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet columnasRS = metaData.getColumns(null, null, TABLE_NAME.toUpperCase(), null);

            List<String> columnas = new ArrayList<>();
            while (columnasRS.next()) {
                columnas.add(columnasRS.getString("COLUMN_NAME"));
            }

            StringBuilder sql = new StringBuilder("SELECT * FROM " + TABLE_NAME + " WHERE ");
            for (int i = 0; i < columnas.size(); i++) {
                sql.append(columnas.get(i)).append(" LIKE ?");
                if (i < columnas.size() - 1) {
                    sql.append(" OR ");
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
                for (int i = 1; i <= columnas.size(); i++) {
                    ps.setString(i, "%" + nombre + "%");
                }

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    resultados.add(mapper.apply(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }



    public List<Object[]> buscador(String nombre) {

            List<Object[]> resultadoFinal = new ArrayList<>();


            resultadoFinal.addAll(
                    buscarPorNombre(
                            "EMPLEADO",
                            nombre,
                            URL, USER, PASSWORD,
                            rs -> {
                                try {
                                    return new Object[]{
                                            "Empleado",
                                            rs.getInt("id"),
                                            rs.getString("nombre"),
                                            rs.getString("cargo"),
                                            "-"
                                    };
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    )
            );



            resultadoFinal.addAll(
                    buscarPorNombre(
                            "MASCOTA",
                            nombre,
                            URL, USER, PASSWORD,
                            rs -> {
                                try {
                                    return new Object[]{
                                            "Mascota",
                                            rs.getInt("id"),
                                            rs.getString("nombre"),
                                            rs.getString("especie"),
                                            rs.getString("adoptado")
                                    };
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    )
            );



            resultadoFinal.addAll(
                    buscarPorNombre(
                            "ADOPTANTE",
                            nombre,
                            URL, USER, PASSWORD,
                            rs -> {
                                try {
                                    return new Object[]{
                                            "Adoptante",
                                            rs.getInt("id"),
                                            rs.getString("nombre"),
                                            rs.getString("edad"),
                                            rs.getString("direccion")
                                    };
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    )
            );

            resultadoFinal.addAll(
                    buscarPorNombre(
                            "ADOPCION",
                            nombre,
                            URL, USER, PASSWORD,
                            rs -> {
                                try {
                                    return new Object[]{
                                            "Adopcion",
                                            rs.getInt("id"),
                                            rs.getString("adoptanteNombre"),
                                            rs.getString("mascotaNombre"),
                                            rs.getString("fecha"),
                                            rs.getString("empleadoNombre")
                                    };
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    )
            );

            return resultadoFinal;
        }

    public <T> T buscarPorNombreGenerico(
            String tabla,
            String nombre,
            String columnaNombre,
            Function<ResultSet, T> mapper
    ) {
        String sql = "SELECT * FROM " + tabla + " WHERE " + columnaNombre + " = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapper.apply(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    public List<String> especiesMasAdoptadas() {
        String sql = "SELECT especie, COUNT(*) AS cantidad " +
                "FROM MASCOTA " +
                "WHERE adoptado = TRUE " +
                "GROUP BY especie " +
                "ORDER BY cantidad DESC " +
                "LIMIT 3";

        List<String> top3 = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String especie = rs.getString("especie");
                int cantidad = rs.getInt("cantidad");

                top3.add(especie + " (" + cantidad + ")");
            }

            if (top3.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay suficientes datos para realizar este reporte");
            }

            return top3;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Integer> obtenerAdopcionesPorEspecie() {
        String sql =
                "SELECT m.especie, COUNT(*) AS cantidad " +
                        "FROM ADOPCION a " +
                        "JOIN MASCOTA m ON a.mascotaNombre = m.nombre " +
                        "GROUP BY m.especie";

        Map<String, Integer> resultado = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String especie = rs.getString("especie");
                int cantidad = rs.getInt("cantidad");
                resultado.put(especie, cantidad);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public List<String> obtenerEmpleadosConMasAdopciones() {
        List<String> resultados = new ArrayList<>();

        String sql = "SELECT empleadoNombre, COUNT(*) AS cantidad " +
                "FROM ADOPCION " +
                "GROUP BY empleadoNombre " +
                "ORDER BY cantidad DESC";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                String empleado = rs.getString("empleadoNombre");
                int cantidad = rs.getInt("cantidad");
                resultados.add(empleado + " - " + cantidad + " adopciones");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }

    public List<Adopcion> obtenerAdopcionesPorRango(String fechaInicio, String fechaFin) {

        List<Adopcion> lista = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio, formatter);
        LocalDateTime fin = LocalDateTime.parse(fechaFin, formatter);

        String sql = "SELECT * FROM ADOPCION";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String fechaStr = rs.getString("fecha");
                LocalDateTime fecha = LocalDateTime.parse(fechaStr, formatter);

                if (!fecha.isBefore(inicio) && !fecha.isAfter(fin)) {
                    Adopcion adopcion = new Adopcion(
                            rs.getString("adoptanteNombre"),
                            rs.getString("mascotaNombre"),
                            rs.getString("empleadoNombre"),
                            fechaStr,
                            rs.getInt("id")
                    );

                    lista.add(adopcion);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
