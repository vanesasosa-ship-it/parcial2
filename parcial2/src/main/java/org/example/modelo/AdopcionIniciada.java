package org.example.modelo;

public class AdopcionIniciada {

    private static AdopcionIniciada instancia;
    private Integer idAdoptante;
    private Integer idMascota;
    private String adoptanteNombre;
    private String mascotaNombre;

    private AdopcionIniciada() {}

    public static AdopcionIniciada getInstancia() {
        if (instancia == null) {
            instancia = new AdopcionIniciada();
        }
        return instancia;
    }

    public String getAdoptanteNombre() {
        return adoptanteNombre;
    }

    public void setAdoptanteNombre(String adoptanteNombre) {
        this.adoptanteNombre = adoptanteNombre;
    }


    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }


}
