package org.example.DAO.interfaces;

import org.example.modelo.Adoptante;

import java.util.List;

public interface AdoptanteDAO {

    public List<Adoptante> listarAdoptantes();
    public Adoptante registrar(Adoptante adoptante, boolean iniciaP);
    public Adoptante buscarRegistro(int id);
}

