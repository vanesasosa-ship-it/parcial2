package org.example.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conejo extends Mascota{
    public Conejo(String nomMascota, int peso, LocalDate fechaNacimiento, boolean adoptado) {
        super(nomMascota,
                "Conejo",
                fechaNacimiento,
                peso,
                adoptado,
                crearCuidados());
    }

    private static List<String> crearCuidados() {
        List<String> cuidados = new ArrayList<>();
        cuidados.add("Espacio amplio para moverse");
        cuidados.add("Limpieza de su zona");
        cuidados.add("Variedad de verduras y alfalfa como comida");
        cuidados.add("Espacio donde pueda hacer madriguera");
        return cuidados;
    }
}
