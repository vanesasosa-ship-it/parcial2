package org.example.modelo;

public class SesionIniciada {

    private static Empleado usuarioActual;
    private static String cargo;
    private static int id;

    public SesionIniciada() {
    }

    public static Empleado getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Empleado usuarioActual) {
        SesionIniciada.usuarioActual = usuarioActual;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SesionIniciada.id = id;
    }

    public static String getCargo() {
        return cargo;
    }

    public static void setCargo(String cargo) {
        SesionIniciada.cargo = cargo;
    }
}

