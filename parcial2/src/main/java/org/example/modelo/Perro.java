package org.example.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Perro extends Mascota {

    public Perro(String nomMascota, int peso, LocalDate fechaNacimiento) {
        super(nomMascota,
                "Perro",
                fechaNacimiento,
                peso,
                crearCuidados());
    }

    private static List<String> crearCuidados() {
        List<String> cuidados = new ArrayList<>();
        cuidados.add("bañar");
        cuidados.add("alimentar");
        return cuidados;
    }
}

//Mascota masc = new Mascota(nomMascota, peso, fecha, new Perro()