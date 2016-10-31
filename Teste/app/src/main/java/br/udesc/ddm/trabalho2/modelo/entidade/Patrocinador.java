package br.udesc.ddm.trabalho2.modelo.entidade;

/**
 * Created by ignoi on 26/10/2016.
 */

public class Patrocinador {

    private int id;
    private int estrelas;
    private double valor;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Patrocinador(int id, int estrelas, double valor, String nome) {

        this.id = id;
        this.estrelas = estrelas;
        this.valor = valor;
        this.nome = nome;
    }
}
