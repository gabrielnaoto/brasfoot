package br.udesc.ddm.trabalho2.modelo.entidade;

/**
 * Created by ignoi on 26/10/2016.
 */

public class Jogador {

    private int id;
    private String nome;
    private String posicao;
    private int idade;
    private int tecnica;
    private int fisico;
    private int inteligentcia;
    private int motivacao;
    private Time time;

    public Jogador() {

    }

    public Jogador(int id, String nome, int idade, int tecnica, int fisico, int inteligentcia, int motivacao) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.tecnica = tecnica;
        this.fisico = fisico;
        this.inteligentcia = inteligentcia;
        this.motivacao = motivacao;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
