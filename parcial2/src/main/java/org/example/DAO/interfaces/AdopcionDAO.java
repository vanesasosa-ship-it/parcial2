package org.example.DAO.interfaces;

import org.example.modelo.Adopcion;
import org.example.modelo.Adoptante;

import java.util.List;

public interface AdopcionDAO {
    public Adopcion registrar(Adopcion adopcion);
    public List<Adopcion> listarAdopciones();
    public Adopcion buscarRegistro(int id);
}
