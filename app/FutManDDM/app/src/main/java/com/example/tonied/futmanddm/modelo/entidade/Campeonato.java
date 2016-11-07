package com.example.tonied.futmanddm.modelo.entidade;

/**
 * Created by ignoi on 06/11/2016.
 */

public class Campeonato {

    private Time t;
    private Patrocinador p;
    private Estadio e;

    public Campeonato() {
    }

    public Campeonato(Time t, Patrocinador p, Estadio e) {

        this.t = t;
        this.p = p;
        this.e = e;
    }

    public Time getT() {
        return t;
    }

    public void setT(Time t) {
        this.t = t;
    }

    public Patrocinador getP() {
        return p;
    }

    public void setP(Patrocinador p) {
        this.p = p;
    }

    public Estadio getE() {
        return e;
    }

    public void setE(Estadio e) {
        this.e = e;
    }
}
