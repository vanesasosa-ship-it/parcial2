package org.example.DAO.interfaces;

import org.example.modelo.Mascota;

import java.awt.*;
import java.util.List;

public interface MascotaDAO {


     List<Mascota> listarMascotas();

     public Mascota guardarMascota(Mascota mascota, boolean iniciaP);

    public Mascota buscarRegistro(int id);
    public Mascota eliminarMascota(int id);
    public Mascota buscarMascota(String nombre);
    public Mascota actualizarMascota(Mascota mascota);

}
