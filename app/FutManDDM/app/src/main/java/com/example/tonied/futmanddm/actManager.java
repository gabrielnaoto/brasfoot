package com.example.tonied.futmanddm;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tonied.futmanddm.modelo.dao.core.CampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.core.PartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLCampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLPartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLTimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Campeonato;
import com.example.tonied.futmanddm.modelo.entidade.Partida;
import com.example.tonied.futmanddm.modelo.entidade.Regras;
import com.example.tonied.futmanddm.modelo.entidade.Time;

import java.util.List;

public class actManager extends AppCompatActivity {
    //informações cabeçalho
    private ImageView escudo;
    private TextView info00;
    private TextView info00n;   //score atual e novo
    private TextView info01;    //classificação + pontos
    private TextView info02;    //moral do time
    private TextView info03;    //caixa
    private TextView info04;    //despesas

    // ajustes do time - switches
    private Switch sw01;
    private Switch sw02;
    private Switch sw03;
    private Switch sw04;
    private ImageView hint01;
    private ImageView hint02;
    private ImageView hint03;
    private ImageView hint04;

    //últimas rodadas rodadas
    //cabeçalhos
    private TextView rod01;
    private TextView rod02;
    private TextView rod03;
    //escudos
    private ImageView ej01;
    private ImageView ej02;
    private ImageView ej03;
    //resultados
    private TextView res01;
    private TextView res02;
    private TextView res03;

    //tabela classificacao
    private ImageView ec01;
    private ImageView ec02;
    private ImageView ec03;
    private ImageView ec04;
    // times
    private TextView tc01;
    private TextView tc02;
    private TextView tc03;
    private TextView tc04;
    //pontos
    private TextView pc01;
    private TextView pc02;
    private TextView pc03;
    private TextView pc04;
    private Button btClassif;

    //proximo jogo
    private TextView tAdversario;
    private ImageView eAdversario;
    private Button btEscalar;


    private static int idCasa, idVisit;
    private static String localiz = "";
    int idScore;
    int atuScore;
    static int inc01;
    static int inc02;
    static int inc03;
    static int inc04;
    static int idClassif;
    static int advClassif;
    static int idPontos;
    static int idMoral;
    static double idCaixa;
    static double idDespesa;

    private TimeDAO tdao;
    private CampeonatoDAO cdao;
    private PartidaDAO pdao;

    private List<Time> times;
    private Time time;
    private Time adversario;

    private Campeonato campeonato;

    String[] nomeTime = {
            "Arsenal",
            "Atl. Madrid",
            "Barcelona",
            "Bayern Munique",
            "Juventus",
            "Manchester Utd",
            "Paris SG",
            "Real Madrid"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_manager);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        escudo = (ImageView) findViewById(R.id.escudo);
        eAdversario = (ImageView) findViewById(R.id.eAdversario);
        tAdversario = (TextView) findViewById(R.id.tAdversario);

        info00 = (TextView) findViewById(R.id.info00);
        info00n = (TextView) findViewById(R.id.info00n);
        info01 = (TextView) findViewById(R.id.info01);
        info02 = (TextView) findViewById(R.id.info02);
        info03 = (TextView) findViewById(R.id.info03);
        info04 = (TextView) findViewById(R.id.info04);

        hint01 = (ImageView) findViewById(R.id.hint01);
        hint02 = (ImageView) findViewById(R.id.hint02);
        hint03 = (ImageView) findViewById(R.id.hint03);
        hint04 = (ImageView) findViewById(R.id.hint04);
        sw01 = (Switch) findViewById(R.id.sw01);
        sw02 = (Switch) findViewById(R.id.sw02);
        sw03 = (Switch) findViewById(R.id.sw03);
        sw04 = (Switch) findViewById(R.id.sw04);

        rod01 = (TextView) findViewById(R.id.rod01);
        rod02 = (TextView) findViewById(R.id.rod02);
        rod03 = (TextView) findViewById(R.id.rod03);
        ej01 = (ImageView) findViewById(R.id.ej01);
        ej02 = (ImageView) findViewById(R.id.ej02);
        ej03 = (ImageView) findViewById(R.id.ej03);
        res01 = (TextView) findViewById(R.id.res01);
        res02 = (TextView) findViewById(R.id.res02);
        res03 = (TextView) findViewById(R.id.res03);

        ec01 = (ImageView) findViewById(R.id.ec01);
        ec02 = (ImageView) findViewById(R.id.ec02);
        ec03 = (ImageView) findViewById(R.id.ec03);
        ec04 = (ImageView) findViewById(R.id.ec04);
        tc01 = (TextView) findViewById(R.id.tc01);
        tc02 = (TextView) findViewById(R.id.tc02);
        tc03 = (TextView) findViewById(R.id.tc03);
        tc04 = (TextView) findViewById(R.id.tc04);
        pc01 = (TextView) findViewById(R.id.pc01);
        pc02 = (TextView) findViewById(R.id.pc02);
        pc03 = (TextView) findViewById(R.id.pc03);
        pc04 = (TextView) findViewById(R.id.pc04);

        btEscalar = (Button) findViewById(R.id.btEscalar);
        btClassif = (Button) findViewById(R.id.btClassif);

        SQLiteDatabase db = openOrCreateDatabase("brasfoot", MODE_PRIVATE, null);

        tdao = new SQLTimeDAO(db);
        cdao = new SQLCampeonatoDAO(db);
        pdao = new SQLPartidaDAO(db);

        times = tdao.listar();

        campeonato = cdao.pesquisar();
        time = campeonato.getT();
        adversario = tdao.pesquisar(Regras.getIndicesPorTime().get(Regras.getAdversario(time.getNome(), campeonato.getRodada())));

//        System.out.println("inicio---");
//        System.out.println(time.getNome() + " nome do time atual");
//        System.out.println(campeonato.getRodada() + " rodada atual");
//        System.out.println(Regras.getAdversario(time.getNome(), campeonato.getRodada()));
//        System.out.println(Regras.getIndicesPorTime().get(Regras.getAdversario(time.getNome(), campeonato.getRodada())));
//        System.out.println("fim------");

        buscaDadosExt();
        cargaInfos();
        cargaJogos(campeonato.getRodada());
        cargaClassif();
        btEscalarClick();
        visualizaTimeClick();
        btClassificacaoClick();
    }


    //==============================================================================================
    //=============== INICIO DOS METODOS - CARGA DAS INFORMACOES EXTERNAS ==========================
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void buscaDadosExt() {
        idClassif = 1;
        advClassif = 1;

        for (Time t : times) {
            if (t.getTimeid() == time.getTimeid()) {
                break;
            }
            idClassif++;
        }

        for (Time t : times) {
            if (t.getTimeid() == adversario.getTimeid()) {
                break;
            }
            advClassif++;

        }

        idPontos = time.getPontos();
        idCaixa = time.getSaldo();
        idScore = time.getAtributos();
        atuScore = idScore; //CAMPO UTILIZADO PARA SCORE PROJETADO
        //=============================================================
        // =========== FALTA DINAMIZAR ================================
        // ============================================================
        idDespesa = 0;
        idMoral = 70;
        inc01 = 0;
        inc02 = 0;
        inc03 = 0;
        inc04 = 0;
        //=============================================================
        //=============================================================
    }


    //===================== CARREGA AS INFORMAÇOES DO TIME =========================================
    public void cargaInfos() {
        escudo.setImageResource(Regras.times[time.getTimeid()]);
        info00.setText(idScore+"");
        atualizaScore();
        info01.setText(idClassif + "º (" + idPontos + " pontos)");
        info02.setText(idMoral + "%");
        info03.setText("R$ " + idCaixa + "0");
        info04.setText("R$ " + idDespesa + "0");
    }

    //============== ATUALIZA O SCORE PROJETADO COM BASE NOS INCREMENTOS ADQUIRIDOS ================
    public void atualizaScore() {
        info00n.setText(atuScore+inc01+inc02+inc03+inc04+"");
    }


    //================ ATUALIZA O PAINEL DO PROXIMO JOGO E JOGOS RECENTES ==========================
    public void cargaJogos(int rodAtual) {
        List<Partida> partidas = pdao.listarPorTime(time.getTimeid());
        eAdversario.setImageResource(Regras.times[adversario.getTimeid()]);
        if (Regras.isCasa(nomeTime[time.getTimeid()], campeonato.getRodada() )) {
            localiz = "Casa";
            idCasa = time.getTimeid();
            idVisit = adversario.getTimeid();
        } else {
            localiz = "Fora";
            idCasa = adversario.getTimeid();
            idVisit = time.getTimeid();
        }
        tAdversario.setText(localiz + " - " + advClassif + "º (" + adversario.getPontos() + " pts) - " + adversario.getAtributos());

        //==================== ATUALIZA OS ULTIMOS JOGOS DO TIME ===================================
        if (rodAtual == 1) {
            ej01.setImageResource(R.drawable.esemtime);
            ej02.setImageResource(R.drawable.esemtime);
            ej03.setImageResource(R.drawable.esemtime);
            rod01.setText("");
            rod02.setText("");
            rod03.setText("");
            res01.setText("  X  ");
            res02.setText("  X  ");
            res03.setText("  X  ");

        } else if (rodAtual == 2) {
            ej01.setImageResource(Regras.times[partidas.get(0).getCasa().getTimeid() == time.getTimeid() ? partidas.get(0).getVisitante().getTimeid() : partidas.get(0).getCasa().getTimeid()]);
            ej02.setImageResource(R.drawable.esemtime);
            ej03.setImageResource(R.drawable.esemtime);
            rod01.setText("Rodada 1 - " + (partidas.get(0).getCasa().getTimeid() == time.getTimeid() ? "casa" : "visitante"));
            rod02.setText("");
            rod03.setText("");
            res01.setText(partidas.get(0).getPlacar()[0] + " X " + partidas.get(0).getPlacar()[1]);
            res02.setText("  X  ");
            res03.setText("  X  ");

        } else if (rodAtual == 3) {
            ej01.setImageResource(Regras.times[partidas.get(0).getCasa().getTimeid() == time.getTimeid() ? partidas.get(0).getVisitante().getTimeid() : partidas.get(0).getCasa().getTimeid()]);
            ej02.setImageResource(Regras.times[partidas.get(1).getCasa().getTimeid() == time.getTimeid() ? partidas.get(1).getVisitante().getTimeid() : partidas.get(1).getCasa().getTimeid()]);
            ej03.setImageResource(R.drawable.esemtime);
            rod01.setText("Rodada 1 - " + (partidas.get(0).getCasa().getTimeid() == time.getTimeid() ? "casa" : "visitante"));
            rod02.setText("Rodada 2 - " + (partidas.get(1).getCasa().getTimeid() == time.getTimeid() ? "casa" : "visitante"));
            rod03.setText("");
            res01.setText(partidas.get(0).getPlacar()[0] + " X " + partidas.get(0).getPlacar()[1]);
            res02.setText(partidas.get(1).getPlacar()[0] + " X " + partidas.get(1).getPlacar()[1]);
            res03.setText("  X  ");

        } else if (rodAtual == 4) {
            ej01.setImageResource(Regras.times[partidas.get(0).getCasa().getTimeid() == time.getTimeid() ? partidas.get(0).getVisitante().getTimeid() : partidas.get(0).getCasa().getTimeid()]);
            ej02.setImageResource(Regras.times[partidas.get(1).getCasa().getTimeid() == time.getTimeid() ? partidas.get(1).getVisitante().getTimeid() : partidas.get(1).getCasa().getTimeid()]);
            ej03.setImageResource(Regras.times[partidas.get(2).getCasa().getTimeid() == time.getTimeid() ? partidas.get(2).getVisitante().getTimeid() : partidas.get(2).getCasa().getTimeid()]);
            rod01.setText("Rodada 1 - " + (partidas.get(0).getCasa().getTimeid() == time.getTimeid() ? "casa" : "visitante"));
            rod02.setText("Rodada 2 - " + (partidas.get(1).getCasa().getTimeid() == time.getTimeid() ? "casa" : "visitante"));
            rod03.setText("Rodada 3 - " + (partidas.get(2).getCasa().getTimeid() == time.getTimeid() ? "casa" : "visitante"));
            res01.setText(partidas.get(0).getPlacar()[0] + " X " + partidas.get(0).getPlacar()[1]);
            res02.setText(partidas.get(1).getPlacar()[0] + " X " + partidas.get(1).getPlacar()[1]);
            res03.setText(partidas.get(2).getPlacar()[0] + " X " + partidas.get(2).getPlacar()[1]);

        } else if (rodAtual >= 5) {
            ej01.setImageResource(Regras.times[partidas.get(rodAtual - 4).getCasa().getTimeid() == time.getTimeid() ? partidas.get(rodAtual - 4).getVisitante().getTimeid() : partidas.get(rodAtual - 4).getCasa().getTimeid()]);
            ej02.setImageResource(Regras.times[partidas.get(rodAtual - 3).getCasa().getTimeid() == time.getTimeid() ? partidas.get(rodAtual - 3).getVisitante().getTimeid() : partidas.get(rodAtual - 3).getCasa().getTimeid()]);
            ej03.setImageResource(Regras.times[partidas.get(rodAtual - 2).getCasa().getTimeid() == time.getTimeid() ? partidas.get(rodAtual - 2).getVisitante().getTimeid() : partidas.get(rodAtual - 2).getCasa().getTimeid()]);
            rod01.setText("Rodada " + (rodAtual - 3) + " - " +(partidas.get(0).getCasa().getTimeid() == time.getTimeid() ? "casa" : "visitante"));
            rod02.setText("Rodada " + (rodAtual - 2) + " - " +(partidas.get(0).getCasa().getTimeid() == time.getTimeid() ? "casa" : "visitante"));
            rod03.setText("Rodada " + (rodAtual - 1) + " - " +(partidas.get(0).getCasa().getTimeid() == time.getTimeid() ? "casa" : "visitante"));
            res01.setText(partidas.get(rodAtual - 4).getPlacar()[0] + " X " + partidas.get(rodAtual - 4).getPlacar()[1]);
            res02.setText(partidas.get(rodAtual - 3).getPlacar()[0] + " X " + partidas.get(rodAtual - 3).getPlacar()[1]);
            res03.setText(partidas.get(rodAtual - 2).getPlacar()[0] + " X " + partidas.get(rodAtual - 2).getPlacar()[1]);
        }
    }

    //==================== CARREGA OS DADOS DA CLASSIFICACAO =============================
    public void cargaClassif() {
//        System.out.println(times.get(0).getTimeid() + "");
//        System.out.println(times.get(1).getTimeid() + "");
//        System.out.println(times.get(2).getTimeid() + "");
//        System.out.println(times.get(3).getTimeid() + "");
        ec01.setImageResource(Regras.times[times.get(0).getTimeid()]); //imagem
        ec02.setImageResource(Regras.times[times.get(1).getTimeid()]);
        ec03.setImageResource(Regras.times[times.get(2).getTimeid()]);
        ec04.setImageResource(Regras.times[times.get(3).getTimeid()]);
        tc01.setText(times.get(0).getNome()); //nome
        tc02.setText(times.get(1).getNome());
        tc03.setText(times.get(2).getNome());
        tc04.setText(times.get(3).getNome());
        pc01.setText(times.get(0).getPontos() + ""); //pontos
        pc02.setText(times.get(1).getPontos() + "");
        pc03.setText(times.get(2).getPontos() + "");
        pc04.setText(times.get(3).getPontos() + "");
    }


    //=========================== INTERAÇÕES DOS SWITCHES E HELPS ==================================
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++ FALTANDO PERSISTIR NO BANCO AS ALTERACOES DOS INCREMENTOS ++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void onClickHint01(View v) {
        Toast.makeText(this, "Custo de R$ 25.000,00 por partida\nAumento de 2% no Score para a partida", Toast.LENGTH_SHORT).show();
    }

    public void onCLickHint02(View v) {
        Toast.makeText(this, "Custo de R$ 10.000,00 por partida\nAumento CUMULATIVO de 2% no Score por semana", Toast.LENGTH_SHORT).show();
    }

    public void onCLickHint03(View v) {
        Toast.makeText(this, "Custo de R$ 8.000,00 por partida\nAumento CUMULATIVO de 1% no Score por semana", Toast.LENGTH_SHORT).show();
    }

    public void onCLickHint04(View v) {
        Toast.makeText(this, "Custo de R$ 15.000,00 por partida\nGarante a recuperação plena do jogador, sem perda de score", Toast.LENGTH_SHORT).show();
    }

    public void onClickSw01(View v) {
        if (sw01.isChecked()) {
            idDespesa += 25000;
            inc01 = (int) (atuScore * 0.02);
        } else {
            idDespesa -= 25000;
            inc01 = 0;
        }
        info04.setText("R$ " + idDespesa + "0");
        atualizaScore();
    }

    public void onClickSw02(View v) {
        if (sw02.isChecked()) {
            idDespesa += 10000;
            inc02 = (int) (atuScore * 0.02);
        } else {
            idDespesa -= 10000;
            inc02 = 0;
        }
        info04.setText("R$ " + idDespesa + "0");
        atualizaScore();
    }

    public void onClickSw03(View v) {
        if (sw03.isChecked()) {
            idDespesa += 8000;
            inc03 = (int) (atuScore * 0.01);
        } else {
            idDespesa -= 8000;
            inc03 = 0;
        }
        info04.setText("R$ " + idDespesa + "0");
        atualizaScore();
    }

    public void onClickSw04(View v) {
        if (sw04.isChecked()) {
            idDespesa += 15000;
        } else {
            idDespesa -= 15000;
        }
        info04.setText("R$ " + idDespesa + "0");
        atualizaScore();
    }

    //=============================================================================================
    //========================= CHAMADA DAS TELAS SEGUINTES =======================================
    //=============================================================================================
    public void btEscalarClick() {
        btEscalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(actManager.this, actConfigTime.class);
                Bundle dados = new Bundle();
                //Data to bundle
                dados.putInt("indiceTime", time.getTimeid());
                dados.putInt("idCasa", idCasa );
                dados.putInt("idVisit", idVisit);
                it.putExtras(dados);
                startActivity(it);
            }
        });
    }

    public void visualizaTimeClick() {
        eAdversario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(actManager.this, actConsultaTime.class);
                Bundle dados = new Bundle();
                //Data to bundle
                dados.putInt("adverIdTime", adversario.getTimeid());
                dados.putInt("adverClassi", advClassif);
                dados.putInt("adverPontos", adversario.getPontos());
                dados.putString("adverProximo", Regras.getAdversario(nomeTime[adversario.getTimeid()], campeonato.getRodada() + 1) );
                it.putExtras(dados);
                startActivity(it);
            }
        });
    }

    public void btClassificacaoClick() {
        btClassif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(actManager.this, actClassificacao.class);
                Bundle dados = new Bundle();
                //Data to bundle

                it.putExtras(dados);
                startActivity(it);
            }
        });
    }
}
