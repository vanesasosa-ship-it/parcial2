package org.example.DAO.interfaces;

import org.example.modelo.Adopcion;
import org.example.modelo.Empleado;
import java.util.List;

public interface EmpleadoDAO {

    public List<Empleado> listarEmpleados();
    public Empleado registrarEmpleado(Empleado empleado, boolean iniciaP);
    public Empleado eliminarEmpleado(int id);
    public Empleado buscarEmpleado(int id);
    public Empleado login(String nombre, String password);
    public Empleado actualizarEmpleado(Empleado empleado);
    public Empleado buscarPorNombre(String nombre);
}
