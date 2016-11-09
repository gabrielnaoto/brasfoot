package com.example.tonied.futmanddm;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tonied.futmanddm.modelo.dao.core.CampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.core.PartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLCampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLPartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLTimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Campeonato;
import com.example.tonied.futmanddm.modelo.entidade.Esquema;
import com.example.tonied.futmanddm.modelo.entidade.Partida;
import com.example.tonied.futmanddm.modelo.entidade.Regras;
import com.example.tonied.futmanddm.modelo.entidade.Time;

public class actDelayJogo extends AppCompatActivity implements Runnable {
    
    private CampeonatoDAO campeonatodao;
    private Campeonato c;
    private PartidaDAO partidadao;
    private Partida p;
    private TimeDAO timedao;
    private Time t;
    private int rodada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_delay_jogo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Handler handler = new Handler();
        handler.postDelayed(this, 5000);
        results();
    }

    public void run(){
        startActivity(new Intent(this, actTelaJogo.class));
        finish();
    }

    public void results(){
        SQLiteDatabase db = openOrCreateDatabase("brasfoot", MODE_PRIVATE, null);
        timedao = new SQLTimeDAO(db);
        partidadao = new SQLPartidaDAO(db);
        campeonatodao = new SQLCampeonatoDAO(db);
        c = campeonatodao.pesquisar();
        rodada = c.getRodada();
        String[][] calendario = Regras.getCalendario(rodada);
        for (int i = 0; i < 4; i++) {
            Time[] times = new Time[2];
            Esquema[] esquemas = new Esquema[2];
            int[] atributos = new int[2];
            for (int j = 0; j < 2; j++) {
                t = timedao.pesquisar(Regras.getIndicesPorTime().get(calendario[i][j]));
                esquemas[j] = t.getEsquema();
                atributos[j] = t.getAtributos();
                times[j] = t;
            }
            int[] placar = Regras.getGols(esquemas, atributos);
            p = new Partida();
            p.setRodada(rodada);
            p.setCasa(times[0]);
            p.setVisitante(times[1]);
            p.setPlacar(placar);
            partidadao.inserir(p);

            System.out.println("partida inserida");

            if (p.getPlacar()[0] > p.getPlacar()[1]) {
                p.getCasa().ganhar();
                timedao.editar(p.getCasa());
                System.out.println("pontos casa adicionados");
                System.out.println(timedao.pesquisar(p.getCasa().getTimeid()).getPontos());
            } else {
                if (p.getPlacar()[1] > p.getPlacar()[0]) {
                    p.getVisitante().ganhar();
                    timedao.editar(p.getVisitante());
                    System.out.println("pontos visitante adicionados");
                    System.out.println(timedao.pesquisar(p.getVisitante().getTimeid()).getPontos());
                } else {
                    p.getCasa().empatar();
                    p.getVisitante().empatar();
                    timedao.editar(p.getCasa());
                    timedao.editar(p.getVisitante());
                    System.out.println("ponto empate adicionado");
                    System.out.println(timedao.pesquisar(p.getCasa().getTimeid()).getPontos());
                    System.out.println(timedao.pesquisar(p.getVisitante().getTimeid()).getPontos());
                }
            }

        }


    }
}
