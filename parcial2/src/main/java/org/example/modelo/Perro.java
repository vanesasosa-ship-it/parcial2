package org.example.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Perro extends Mascota {

    public Perro(String nomMascota, int peso, LocalDate fechaNacimiento, boolean adoptado) {
        super(nomMascota,
                "Perro",
                fechaNacimiento,
                peso,
                adoptado,
                crearCuidados());
    }

    private static List<String> crearCuidados() {
        List<String> cuidados = new ArrayList<>();
        cuidados.add("Paseos diarios");
        cuidados.add("vacunas al día");
        cuidados.add("baño regular y cepillado");
        return cuidados;
    }
}

