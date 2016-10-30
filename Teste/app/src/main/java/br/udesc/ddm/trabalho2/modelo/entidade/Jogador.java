package br.udesc.ddm.trabalho2.modelo.entidade;

/**
 * Created by ignoi on 26/10/2016.
 */

public class Jogador {

    private int id;
    private String nome;
    private int idade;
    private int tecnica;
    private int fisico;
    private int inteligentcia;
    private int motivacao;

    public Jogador(int id, String nome, int idade, int tecnica, int fisico, int inteligentcia, int motivacao) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.tecnica = tecnica;
        this.fisico = fisico;
        this.inteligentcia = inteligentcia;
        this.motivacao = motivacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getTecnica() {
        return tecnica;
    }

    public void setTecnica(int tecnica) {
        this.tecnica = tecnica;
    }

    public int getFisico() {
        return fisico;
    }

    public void setFisico(int fisico) {
        this.fisico = fisico;
    }

    public int getInteligentcia() {
        return inteligentcia;
    }

    public void setInteligentcia(int inteligentcia) {
        this.inteligentcia = inteligentcia;
    }

    public int getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(int motivacao) {
        this.motivacao = motivacao;
    }
}
