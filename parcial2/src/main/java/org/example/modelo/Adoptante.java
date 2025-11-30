package org.example.modelo;

public class Adoptante {

    private String nombre;
    private int edad;
    private String direccion;
    private int id;


    public Adoptante(String nombre,  String direccion, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }

    public Adoptante(String nombre, String direccion, int edad, int id) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.edad = edad;
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
