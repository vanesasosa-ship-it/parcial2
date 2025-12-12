package org.example.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gato extends Mascota{
    public Gato(String nomMascota, int peso, LocalDate fechaNacimiento, boolean adoptado) {
        super(nomMascota,
                "Gato",
                fechaNacimiento,
                peso,
                adoptado,
                crearCuidados());
    }

    private static List<String> crearCuidados() {
        List<String> cuidados = new ArrayList<>();
        cuidados.add("Limpieza del arenero");
        cuidados.add("cepillado del pelaje");
        cuidados.add("Lugares altos donde pueda trepar");
        return cuidados;
    }
}
