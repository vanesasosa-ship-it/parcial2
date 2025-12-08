package org.example.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Adopcion {
    
   private int id;
   private int idAdoptante;
   private int idMascota;
   private String fecha;

   private Adoptante adoptante;
   private Mascota mascota;
   private Empleado empleado;

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

    public Adopcion(Adoptante adoptante, Mascota mascota, Empleado empleado, String fecha) {
        this.adoptante = adoptante;
        this.mascota = mascota;
        this.empleado = empleado;
        this.fecha = fecha;
        this.idAdoptante = adoptante.getId();
        this.idMascota = mascota.getId();
    }

    public String generarTicket() {
        return  "-----------------------------------------------\n" +
                "          Ticket de adopción\n" +
                "-----------------------------------------------\n" +
                "Fecha y hora: " +  LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n\n" +

                "Datos del Adoptante:\n" +
                "Nombre: " + adoptante.getNombre() + "\n" +
                "Edad: " + adoptante.getEdad() + "\n" +
                "Dirección: " + adoptante.getDireccion() + "\n\n" +


                "Datos de la Mascota:\n" +
                "Nombre: " + mascota.getNombre() + "\n" +
                "Fecha de nacimiento: " + mascota.getFechaNacimiento() + "\n" +
                "Peso: " + mascota.getPeso() + "\n" +
                "Especie: " + mascota.getEspecie() + "\n" +
                "Recomendaciones de cuidado: " + mascota.getCuidadosEspecificos() + "\n\n" +



                "Empleado encargado: " +"\n" +
                "Nombre: " + empleado.getNombre() +"\n" +
                "Cargo: "+ empleado.getCargo()  + "\n\n" +

                "-----------------------------------------------\n" +
                "   ¡Gracias por adoptar una mascota!\n" +
                "-----------------------------------------------\n";
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
