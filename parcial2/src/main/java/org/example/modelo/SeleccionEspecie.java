package org.example.modelo;

import java.time.LocalDate;

public class SeleccionEspecie {
    public static Mascota seleccionEspecie(String nombre, int opcion, LocalDate fechaNacimiento, int peso, boolean adoptado ) {

        switch (opcion) {
            case 0:
                return new Perro(nombre, peso, fechaNacimiento, adoptado);

            case 1:
                return new Gato(nombre, peso, fechaNacimiento, adoptado);

            case 2:
                return new Conejo(nombre, peso, fechaNacimiento, adoptado);

            default:
                throw new IllegalArgumentException("Opción inválida");
        }
    }
}
