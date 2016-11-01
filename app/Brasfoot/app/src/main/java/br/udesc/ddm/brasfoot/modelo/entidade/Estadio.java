package br.udesc.ddm.trabalho2.modelo.entidade;

import java.util.List;

/**
 * Created by ignoi on 26/10/2016.
 */

public class Estadio {

    private int estadioid;
    private int estrelas;
    private double ingresso;
    private int publico;

    public Estadio() {
    }

    public Estadio(int estadioid, int estrelas, double ingresso, int publico) {
        this.estadioid = estadioid;
        this.estrelas = estrelas;
        this.ingresso = ingresso;
        this.publico = publico;
    }

    public int getEstadioid() {
        return estadioid;
    }

    public void setEstadioid(int estadioid) {
        this.estadioid = estadioid;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public double getIngresso() {
        return ingresso;
    }

    public void setIngresso(double ingresso) {
        this.ingresso = ingresso;
    }

    public int getPublico() {
        return publico;
    }

    public void setPublico(int publico) {
        this.publico = publico;
    }

}
