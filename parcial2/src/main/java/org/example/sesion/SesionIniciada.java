package org.example.sesion;

import org.example.modelo.Empleado;

    public class SesionIniciada {

        private static Empleado usuarioActual;

        public static void iniciarSesion(Empleado empleado) {
            usuarioActual = empleado;
        }

        public static Empleado getUsuarioActual() {
            return usuarioActual;
        }

        public static void cerrarSesion() {
            usuarioActual = null;
        }
    }


