package org.example.modelo;

import java.util.List;

public class Empleado {


    private String nombre;
    private String password;
    private String cargo;
    private int id;

    public Empleado(String nombre,  String cargo) {
        this.nombre = nombre;
        this.cargo = cargo;
    }
    public Empleado(String nombre,  String cargo, String password){
        this.nombre = nombre;
        this.password = password;
        this.cargo = cargo;

    }
    public Empleado(String nombre, String cargo, String password, int id){
        this.nombre = nombre;
        this.password = password;
        this.cargo = cargo;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
