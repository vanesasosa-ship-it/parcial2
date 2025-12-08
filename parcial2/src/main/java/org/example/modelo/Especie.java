package org.example.modelo;

import java.util.List;

public abstract class Especie {
 protected List<String> cuidadosEspecificos;
 public Especie(List<String> cuidadosEspecificos) {
     this.cuidadosEspecificos = cuidadosEspecificos;
 }

 public List<String> getCuidadosEspecificos() {
     return this.cuidadosEspecificos;
 }

    public void setCuidadosEspecificos(List<String> cuidadosEspecificos) {
        this.cuidadosEspecificos = cuidadosEspecificos;
    }
}
