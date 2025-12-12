package org.example.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Adopcion {

   private int id;
   private String adoptanteNombre;
   private String mascotaNombre;
   private String empleadoNombre;
   private String fecha;
   private Adoptante adoptante;
   private Mascota mascota;
   private Empleado empleado;

    public Adopcion(String adoptanteNombre, String mascotaNombre, String empleadoNombre, String fecha, int id) {
        this.adoptanteNombre = adoptanteNombre;
        this.mascotaNombre = mascotaNombre;
        this.empleadoNombre = empleadoNombre;
        this.fecha = fecha;
        this.id = id;
    }


    public Adopcion(String adoptanteNombre, String mascotaNombre, Empleado empleado, String fecha) {
        this.adoptanteNombre = adoptanteNombre;
        this.mascotaNombre = mascotaNombre;
        this.empleado = empleado;
        this.fecha = fecha;

        this.empleadoNombre = (empleado != null ? empleado.getNombre() : null);
    }


    public Adopcion(Adoptante adoptante, Mascota mascota, Empleado empleado, String fecha) {
        this.adoptante = adoptante;
        this.mascota = mascota;
        this.empleado = empleado;
        this.fecha = fecha;
        this.adoptanteNombre = adoptante != null ? adoptante.getNombre() : null;
        this.mascotaNombre = mascota != null ? mascota.getNombre() : null;
        this.empleadoNombre = empleado != null ? empleado.getNombre() : null;
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
                "Especie: " + mascota.getEspecie() + "\n\n" +
                "Recomendaciones de cuidado: \n- " + String.join("\n- ", mascota.getCuidadosEspecificos()) + "\n\n" +



                "Empleado encargado: " +"\n" +
                "Nombre: " + empleado.getNombre() +"\n" +
                "Cargo: "+ empleado.getCargo()  + "\n\n" +

                "-----------------------------------------------\n" +
                "   ¡Gracias por adoptar una mascota!\n" +
                "-----------------------------------------------\n";
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Adoptante: " + adoptanteNombre +
                " | Mascota: " + mascotaNombre +
                " | Empleado: " + empleadoNombre +
                " | Fecha: " + fecha;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getEmpleadoNombre() {
        return empleadoNombre;
    }

    public void setEmpleadoNombre(String empleadoNombre) {
        this.empleadoNombre = empleadoNombre;
    }
}
