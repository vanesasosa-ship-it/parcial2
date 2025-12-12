package org.example.configDatabase;

import org.example.DAO.AdoptanteDAOH2Impl;
import org.example.DAO.DAO;
import org.example.DAO.EmpleadoDAOHImpl;
import org.example.modelo.Adoptante;
import org.example.modelo.Empleado;


public class ConfigDatabase {

    private static final String URL = "jdbc:h2:./data/base";
    private static final String USER = "sa";
    private static final String PASSWORD = "";



    public static void Precarga() {

        DAO dao = new DAO();

        dao.createTableDAO(
                "EMPLEADO",
                "CREATE TABLE EMPLEADO (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        " nombre VARCHAR(255), " +
                        " cargo VARCHAR(255), " +
                        " password VARCHAR(255))",
                URL, USER, PASSWORD
        );

        dao.createTableDAO(
                "MASCOTA",
                "CREATE TABLE MASCOTA (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nombre VARCHAR(255), " +
                        "especie VARCHAR(255), " +
                        "fechaNacimiento VARCHAR(255), " +
                        "peso VARCHAR(255), " +
                        "cuidados VARCHAR(500), " +
                        "adoptado BOOLEAN " +
                        ")",
                URL, USER, PASSWORD
        );

        dao.createTableDAO(
                "ADOPCION",
                "CREATE TABLE ADOPCION (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        " adoptanteNombre VARCHAR(255), " +
                        " mascotaNombre VARCHAR(255), " +
                        " empleadoNombre VARCHAR(255), " +
                        " fecha VARCHAR(255))",
                URL, USER, PASSWORD
        );

        dao.createTableDAO(
                "ADOPTANTE",
                "CREATE TABLE ADOPTANTE (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nombre VARCHAR(255), " +
                        "edad VARCHAR(255), " +
                        "direccion VARCHAR(255))",
                URL, USER, PASSWORD
        );



        EmpleadoDAOHImpl empleadoDAO = new EmpleadoDAOHImpl();

        Empleado nuevo = new Empleado(
                "ad",
                "administrador",
                "123",
                1
        );

        empleadoDAO.registrarEmpleado(nuevo , true);


        AdoptanteDAOH2Impl adoptanteDAO = new AdoptanteDAOH2Impl();

        Adoptante aNuevo = new Adoptante(
                "Romina",
                "Av la plata 345",
                27,
                1

        );

        adoptanteDAO.registrar(aNuevo, true);


    }


}
