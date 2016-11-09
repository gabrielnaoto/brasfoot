package com.example.tonied.futmanddm.modelo.entidade;

/**
 * Created by ignoi on 26/10/2016.
 */

public class Jogador {

    private int jogadorid;
    private String nome;
    private String posicao;
    private int idade;
    private int tecnica;
    private int fisico;
    private int inteligentcia;
    private int motivacao;
    private Time time;
    private int suspenso;
    private int cartaoamarelo;
    private boolean titular;

    public Jogador() {

    }



    public Jogador(int jogadorid, String nome, String posicao, int idade, int tecnica, int fisico, int inteligentcia, int motivacao, Time time, int suspenso, int cartaoamarelo) {
        this.jogadorid = jogadorid;
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
        this.tecnica = tecnica;
        this.fisico = fisico;
        this.inteligentcia = inteligentcia;
        this.motivacao = motivacao;
        this.time = time;
        this.suspenso = suspenso;
        this.cartaoamarelo = cartaoamarelo;
    }

    public double getValor() {
//        O preço dos jogadores varia em função de:
//        Idade: <21 anos: 2x; 22 a 26 anos: 1,8x; 27 a 30 anos: 1,5x; >31 anos: 1x
//        Nota: $1k por ponto
//        Posição: goleiro: 1x; defesa: 1,3x; meio campo: 1,7x; ataque: 2x


        double valor;

        float mi = 0;
        if (getIdade() < 21) {
            mi = 2;
        } else {
            if (getIdade() < 26) {
                mi = 1.8f;
            } else {
                if (getIdade() < 30) {
                    mi = 1.5f;
                } else {
                    mi = 1;
                }
            }
        }
        float mp = 0;
        if (getPosicao().equalsIgnoreCase("Goleiro")) {
            mp = 1;
        } else {
            if (getPosicao().equalsIgnoreCase("Meia")) {
                mp = 1.7f;
            } else {
                if (getPosicao().equalsIgnoreCase("Atacante")) {
                    mp = 2f;
                } else {
                    if (getPosicao().equalsIgnoreCase("Zagueiro")) {
                        mp = 1.3f;
                    }
                }
            }
        }
        long score = getScore() * 1000;

        valor = mi * score * mp;

        return valor;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getJogadorid() {
        return jogadorid;
    }

    public void setJogadorid(int jogadorid) {
        this.jogadorid = jogadorid;
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

    public int getSuspenso() {
        return suspenso;
    }

    public void setSuspenso(int suspenso) {
        this.suspenso = suspenso;
    }

    public int getCartaoamarelo() {
        return cartaoamarelo;
    }

    public void setCartaoamarelo(int cartaoamarelo) {
        this.cartaoamarelo = cartaoamarelo;
    }

    public boolean isTitular() {
        return titular;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }

    public int getScore() {
        return (int) (getInteligentcia() + getFisico() + getTecnica() + getMotivacao()) / 4;
    }

    public double getSalario() {
        return getValor() * 0.1;
    }

}
