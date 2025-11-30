package org.example.DAO.interfaces;

import org.example.modelo.Empleado;

import java.sql.*;
import java.util.List;

public interface EmpleadoDAO {


    //agregar la construccion de la tabla empleado aca


    public List<Empleado> listarEmpleados();
    public Empleado registrarEmpleado(Empleado empleado, boolean iniciaP);

    public Empleado login(String nombre, String password);

}
