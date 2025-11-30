package org.example.modelo;

public class AdopcionIniciada {

    private static AdopcionIniciada instancia;

    private Integer idAdoptante;
    private Integer idMascota;
    private String fecha;

    private AdopcionIniciada() {}

    public static AdopcionIniciada getInstancia() {
        if (instancia == null) {
            instancia = new AdopcionIniciada();
        }
        return instancia;
    }

    public Integer getIdAdoptante() {
        return idAdoptante;
    }

    public void setIdAdoptante(Integer idAdoptante) {
        this.idAdoptante = idAdoptante;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void reset() {
        idAdoptante = null;
        idMascota = null;
        fecha = null;
    }
}
