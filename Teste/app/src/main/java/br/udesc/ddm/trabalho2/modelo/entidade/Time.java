package br.udesc.ddm.trabalho2.modelo.entidade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ignoi on 26/10/2016.
 */

public class Time {

    private List<Jogador> jogadores;
    private Esquema esquema;
    private Patrocinador patrocinador;

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

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public Esquema getEsquema() {
        return esquema;
    }

    public Patrocinador getPatrocinador() {
        return patrocinador;
    }
}
