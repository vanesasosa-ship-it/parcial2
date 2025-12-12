package org.example.modelo;

public class AdopcionIniciada {

    private static AdopcionIniciada instancia;
    private Integer idAdoptante;
    private Integer idMascota;
    private String adoptanteNombre;
    private String mascotaNombre;
    private String fecha;

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

    public String getMascotaNombre() {
        return mascotaNombre;
    }

    public void setMascotaNombre(String mascotaNombre) {
        this.mascotaNombre = mascotaNombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public void reset() {
        idAdoptante = null;
        idMascota = null;
        adoptanteNombre = null;
        mascotaNombre = null;
        fecha = null;
    }
}
