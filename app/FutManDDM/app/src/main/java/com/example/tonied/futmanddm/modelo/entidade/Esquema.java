package com.example.tonied.futmanddm.modelo.entidade;

/**
 * Created by ignoi on 30/10/2016.
 */

public enum Esquema {

    ATAQUE_TOTAL(0, 80, 20, "Ataque Total"),
    OFENSIVO(1, 65, 35, "Ofensivo"),
    BALANCEADO(2, 50, 50, "Balanceado"),
    DEFENSIVO(3, 35, 65, "Defensivo"),
    DEFENSA_TOTAL(4, 20, 80, "Defesa Total");

    private int ordem;
    private int ataque;
    private int defesa;
    private String nome;

    Esquema(int ordem, int ataque, int defesa, String nome) {
        this.ordem = ordem;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nome = nome;
    }

    public int getOrdem() {
        return ordem;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public String getNome() {
        return nome;
    }
}
