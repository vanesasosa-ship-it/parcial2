package org.example.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gato extends Mascota{
    public Gato(String nomMascota, int peso, LocalDate fechaNacimiento) {
        super(nomMascota,
                "Gato",
                fechaNacimiento,
                peso,
                crearCuidados());
    }

    private static List<String> crearCuidados() {
        List<String> cuidados = new ArrayList<>();
        cuidados.add("bañar gato");
        cuidados.add("alimentar gato");
        return cuidados;
    }
}
