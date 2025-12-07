package org.example.modelo;

import java.time.LocalDate;
import java.util.List;

public class Mascota extends Especie {

    private String nomMascota;
    private String especie;
    private LocalDate fechaNacimiento;
    private int peso;
    private int id;

    public Mascota(String nomMascota, String especie, LocalDate fechaNacimiento, int peso, int id, List<String> cuidados) {
        super(cuidados);
        this.nomMascota = nomMascota;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.id = id;
    }

    public Mascota(String nomMascota, String especie, LocalDate fechaNacimiento, int peso, List<String> cuidados) {
        super(cuidados);
        this.nomMascota = nomMascota;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
    }

    public String getNomMascota() {
        return nomMascota;
    }

    public void setNomMascota(String nomMascota) {
        this.nomMascota = nomMascota;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
