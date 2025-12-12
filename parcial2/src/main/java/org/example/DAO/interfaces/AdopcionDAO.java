package org.example.DAO.interfaces;

import org.example.modelo.Adopcion;

import java.util.List;

public interface AdopcionDAO {
    public Adopcion registrar(Adopcion adopcion);
    public List<Adopcion> listarAdopciones();
    public Adopcion buscarRegistro(int id);
    public Adopcion eliminarAdopcion(int id);
    public Adopcion actualizarAdopcion(Adopcion adopcion);
    public Adopcion buscarConObjetos(int id);
}
