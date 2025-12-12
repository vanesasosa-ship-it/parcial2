package org.example.DAO.interfaces;

import org.example.modelo.Adoptante;
import org.example.modelo.Mascota;

import java.util.List;

public interface AdoptanteDAO {

    public List<Adoptante> listarAdoptantes();
    public Adoptante registrar(Adoptante adoptante, boolean iniciaP);
    public Adoptante buscarRegistro(int id);
    public Adoptante eliminarAdoptante(int id);
    public Adoptante actualizarAdoptante(Adoptante adoptante);
    public Adoptante buscarPorNombreA(String nombre);
}

