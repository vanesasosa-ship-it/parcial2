package org.example.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conejo extends Mascota{
    public Conejo(String nomMascota, int peso, LocalDate fechaNacimiento) {
        super(nomMascota,
                "Conejo",
                fechaNacimiento,
                peso,
                crearCuidados());
    }

    private static List<String> crearCuidados() {
        List<String> cuidados = new ArrayList<>();
        cuidados.add("bañar conejo");
        cuidados.add("alimentar conejo");
        return cuidados;
    }
}
