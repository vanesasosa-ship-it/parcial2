package org.example.modelo;

public class Adopcion {
    
   private int id;
   private int idAdoptante;
   private int idMascota;
   private String fecha;

    public Adopcion(int idAdoptante, int idMascota, String fecha, int id) {
        this.idAdoptante = idAdoptante;
        this.idMascota = idMascota;
        this.fecha = fecha;
        this.id = id;
    }

    public Adopcion(int idAdoptante, int idMascota, String fecha) {
        this.idAdoptante = idAdoptante;
        this.idMascota = idMascota;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAdoptante() {
        return idAdoptante;
    }

    public void setIdAdoptante(int idAdoptante) {
        this.idAdoptante = idAdoptante;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
