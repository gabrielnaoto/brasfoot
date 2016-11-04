package com.example.tonied.futmanddm.modelo.entidade;

/**
 * Created by ignoi on 26/10/2016.
 */

public class Patrocinador {

    private int patrocinadorid;
    private int estrelas;
    private double valor;
    private String nome;

    public Patrocinador() {
    }

    public Patrocinador(int id, int estrelas, double valor, String nome) {

        this.patrocinadorid = id;
        this.estrelas = estrelas;
        this.valor = valor;
        this.nome = nome;
    }


    public int getPatrocinadorid() {
        return patrocinadorid;
    }

    public void setPatrocinadorid(int patrocinadorid) {
        this.patrocinadorid = patrocinadorid;
    }

    public int getId() {
        return patrocinadorid;
    }

    public void setId(int id) {
        this.patrocinadorid = id;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
