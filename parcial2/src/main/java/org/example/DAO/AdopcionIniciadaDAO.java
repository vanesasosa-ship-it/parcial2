package org.example.DAO;

import org.example.modelo.Adoptante;
import org.example.modelo.Mascota;

public class AdopcionIniciadaDAO {

    private static Adoptante adoptanteR;
    private static Mascota mascotaR;


    public static void registroAdopcion(Adoptante adoptante, Mascota mascota) {
        adoptanteR = adoptante;
        mascotaR = mascota;
    }


    public static Adoptante getAdoptante() {
        return adoptanteR;
    }


    public static Mascota getMascota() {
        return mascotaR;
    }
}
