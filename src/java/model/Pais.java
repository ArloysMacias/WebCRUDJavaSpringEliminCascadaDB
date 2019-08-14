/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author pc
 */
public class Pais {
    private int id;
    private String nombrePais="";

    public Pais() {
    }

    public Pais(String NombrePais) {
        this.nombrePais = NombrePais;
    }

    public Pais(int id, String NombrePais) {
        this.id = id;
        this.nombrePais = NombrePais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String NombrePais) {
        this.nombrePais = NombrePais;
    }
    
}
