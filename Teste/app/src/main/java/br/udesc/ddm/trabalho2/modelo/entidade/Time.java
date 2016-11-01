package br.udesc.ddm.trabalho2.modelo.entidade;

import java.util.List;

/**
 * Created by ignoi on 26/10/2016.
 */

public class Time {

    private int timeid;
    private String nome;
    private int pontos;
    private double saldo;
    private List<Jogador> jogadores;
    private Esquema esquema;
    private Patrocinador patrocinador;
    private Estadio estadio;

    public int getAtributos() {
        int tecnica = 0;
        int fisico = 0;
        int inteligencia = 0;
        int motivacao = 0;
        for (Jogador jogador : jogadores) {
            tecnica += jogador.getTecnica();
            fisico += jogador.getFisico();
            inteligencia += jogador.getInteligentcia();
            motivacao += jogador.getMotivacao();
        }
        tecnica = tecnica / jogadores.size();
        fisico = fisico / jogadores.size();
        inteligencia = inteligencia / jogadores.size();
        motivacao = motivacao / jogadores.size();


        int atributos = (tecnica + fisico + inteligencia + motivacao) / 4;

        return atributos;
    }

    public int getTimeid() { return timeid; }

    public void setTimeid(int timeid) { this.timeid = timeid; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public Esquema getEsquema() {
        return esquema;
    }

    public Patrocinador getPatrocinador() {
        return patrocinador;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public void setEsquema(Esquema esquema) {
        this.esquema = esquema;
    }

    public void setPatrocinador(Patrocinador patrocinador) {
        this.patrocinador = patrocinador;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Jogador adiciongarJogador(Jogador j){
        jogadores.add(j);
        j.setTime(this);
        return j;
    }
}
