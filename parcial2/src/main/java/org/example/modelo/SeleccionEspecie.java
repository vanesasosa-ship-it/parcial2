package org.example.modelo;

import java.time.LocalDate;

public class SeleccionEspecie {
    public static Mascota seleccionEspecie(String nombre, int opcion, LocalDate fechaNacimiento, int peso ) {

        switch (opcion) {
            case 0:
                return new Perro(nombre, peso, fechaNacimiento);

            case 1:
                return new Gato(nombre, peso, fechaNacimiento);

            case 2:
                return new Conejo(nombre, peso, fechaNacimiento);

            default:
                throw new IllegalArgumentException("Opción inválida");
        }
    }
}
