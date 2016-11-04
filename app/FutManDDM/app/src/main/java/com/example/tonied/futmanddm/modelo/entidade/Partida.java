package com.example.tonied.futmanddm.modelo.entidade;

import java.util.Map;

public class Partida {

    private int partidaid;
    private Time casa;
    private Time visitante;
    private int[] placar;

    public Partida(Time casa, Time visitante) {
        this.casa = casa;
        this.visitante = visitante;
        placar = new int[2]; // [0]: casa | [1]: visitante;
    }

    public void iniciar() {
        int casaAtributos = casa.getAtributos();
        int visitanteAtributos = visitante.getAtributos();

        int[] atributos = {casaAtributos, visitanteAtributos};

        Esquema casaEsquema = casa.getEsquema();
        Esquema visitanteEsquema = visitante.getEsquema();

        Esquema[] esquemas = {casaEsquema, visitanteEsquema};

        placar = Regras.getGols(esquemas, atributos);
    }

    public int getPartidaid() {
        return partidaid;
    }

    public void setPartidaid(int partidaid) {
        this.partidaid = partidaid;
    }

    public int[] getPlacar() {
        return placar;
    }

    public Time getCasa() {
        return casa;
    }

    public Time getVisitante() {
        return visitante;
    }

    public void setCasa(Time casa) {
        this.casa = casa;
    }

    public void setVisitante(Time visitante) {
        this.visitante = visitante;
    }

    public void setPlacar(int[] placar) {
        this.placar = placar;
    }
}
