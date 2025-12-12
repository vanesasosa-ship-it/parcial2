package org.example.DAO.interfaces;

import org.example.modelo.Mascota;

import java.awt.*;
import java.util.List;
import java.util.Map;

public interface MascotaDAO {

     List<Mascota> listarMascotas();
     public Mascota guardarMascota(Mascota mascota, boolean iniciaP);
    public Mascota buscarRegistro(int id);
    public Mascota eliminarMascota(int id);
    public Mascota actualizarMascota(Mascota mascota);
    public Mascota buscarMascota2(String nombre);
}
