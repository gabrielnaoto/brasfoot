package com.example.tonied.futmanddm;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tonied.futmanddm.modelo.dao.core.CampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.core.JogadorDAO;
import com.example.tonied.futmanddm.modelo.dao.core.PartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLJogadorDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLTimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Campeonato;
import com.example.tonied.futmanddm.modelo.entidade.Esquema;
import com.example.tonied.futmanddm.modelo.entidade.Estadio;
import com.example.tonied.futmanddm.modelo.entidade.Jogador;
import com.example.tonied.futmanddm.modelo.entidade.Patrocinador;
import com.example.tonied.futmanddm.modelo.entidade.Regras;
import com.example.tonied.futmanddm.modelo.entidade.Time;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class actFinal extends AppCompatActivity {

    private ImageView eCampeao;
    private ImageView ec02;
    private ImageView ec03;
    private TextView tc02;
    private TextView tc03;
    private TextView pc02;
    private TextView pc03;
    private Button btNovo;

    private TimeDAO tdao;
    private CampeonatoDAO cdao;
    private PartidaDAO pdao;

    private List<Time> times;
    private Time time;
    private Time adversario;

    private Campeonato campeonato;
    private Button b2;
    private TimeDAO timeDAO;
    private JogadorDAO jogadorDAO;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_final);
        eCampeao = (ImageView)findViewById(R.id.eCampeao);
        ec02 = (ImageView) findViewById(R.id.ec02);
        ec03 = (ImageView) findViewById(R.id.ec03);
        tc02 = (TextView) findViewById(R.id.tc02);
        tc03 = (TextView) findViewById(R.id.tc03);
        pc02 = (TextView) findViewById(R.id.pc02);
        pc03 = (TextView) findViewById(R.id.pc03);

        SQLiteDatabase db = openOrCreateDatabase("brasfoot", MODE_PRIVATE, null);

        timeDAO = new SQLTimeDAO(db);

        times = timeDAO.listar();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        cargaClassif();
    }

    //==================== CARREGA OS DADOS DA CLASSIFICACAO =============================
    public void cargaClassif() {
        eCampeao.setImageResource(Regras.times[times.get(0).getTimeid()]);
        ec02.setImageResource(Regras.times[times.get(1).getTimeid()]);
        ec03.setImageResource(Regras.times[times.get(2).getTimeid()]);
        tc02.setText(times.get(1).getNome());
        tc03.setText(times.get(2).getNome());
        pc02.setText(times.get(1).getPontos() + "");
        pc03.setText(times.get(2).getPontos() + "");
    }

    public void novo(View v) throws IOException {
        deleteDatabase("brasfoot");
        createDataBase();
        importar();

        startActivity(new Intent(this, actSelTime.class));
        finish();
    }

    private void createDataBase() {
        db = openOrCreateDatabase("brasfoot", MODE_PRIVATE, null);
        jogadorDAO = new SQLJogadorDAO(db);
        timeDAO = new SQLTimeDAO(db);

        db.execSQL("create table patrocinador(\n" +
                "patrocinadorid integer primary key autoincrement,\n" +
                "nome varchar,\n" +
                "estrelas integer,\n" +
                "valor double)");

        db.execSQL("create table campeonato(\n" +
                "id integer primary key autoincrement,\n" +
                "time integer,\n" +
                "estadio integer,\n" +
                "rodada integer,\n" +
                "patrocinador integer," +
                "ingresso integer)");

        db.execSQL("create table estadio(\n" +
                "estadioid integer primary key autoincrement,\n" +
                "estrelas integer,\n" +
                "ingresso double, \n" +
                "publico integer\n" +
                ")");

        db.execSQL("create table time(\n" +
                "nome varchar," +
                "timeid integer primary key,\n" +
                "pontos integer,\n" +
                "esquema integer,\n" +
                "saldo double,\n" +
                "patrocinadorid integer not null,\n" +
                "incrementos integer,\n" +
                "estadioid integer not null\n" +
                ")");

        db.execSQL("create table jogador(\n" +
                "jogadorid integer primary key autoincrement,\n" +
                "nome varchar,\n" +
                "posicao varchar,\n" +
                "idade integer,\n" +
                "tecnica integer,\n" +
                "fisico integer,\n" +
                "inteligencia integer,\n" +
                "motivacao integer,\n" +
                "suspenso integer,\n" +
                "cartaoamarelo integer,\n" +
                "timeid integer\n" +
                ")");


        db.execSQL("create table partida(\n" +
                "partidaid integer primary key autoincrement,\n" +
                "rodada integer,\n" +
                "golcasa integer,\n" +
                "golvizi integer,\n" +
                "casaid integer,\n" +
                "visitanteid integer\n" +
                ");");

    }


    public void importar() throws IOException {
        BufferedReader leitor;
        leitor = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.jogadores)));
        String linha = "";
        List<Time> times = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            linha = leitor.readLine();
            System.out.println("print " + linha);
            Time t = new Time();
            t.setTimeid(i);
            t.setNome(linha);
            t.setEsquema(Esquema.values()[(int)Math.floor(Math.random() * 5)]);
            System.out.println(t.getEsquema().getNome());
            t.setEstadio(new Estadio());
            t.setPatrocinador(new Patrocinador());
            t.setPontos(0);
            t.setSaldo(0);
            for (int j = 0; j < 18; j++) {
                System.out.println("print " + linha);
                linha = leitor.readLine();
                String[] valores = linha.split(",");
                Jogador jogador = new Jogador();
                jogador.setNome(valores[1]);
                jogador.setPosicao(valores[2]);
                jogador.setIdade(Integer.parseInt(valores[3]));
                jogador.setTecnica(Integer.parseInt(valores[4]));
                jogador.setFisico(Integer.parseInt(valores[5]));
                jogador.setInteligentcia(Integer.parseInt(valores[6]));
                jogador.setTitular(valores[0] == "1" ? true : false);
                jogador.setMotivacao(50);
                jogador.setTime(t);
                t.adiciongarJogador(jogador);
                jogadorDAO.inserir(jogador);
            }
            timeDAO.inserir(t);
            times.add(t);
        }
        leitor.readLine();
        for (int j = 0; j < 59; j++) {
            System.out.println("print " + linha);
            linha = leitor.readLine();
            String[] valores = linha.split(",");
            Jogador jogador = new Jogador();
            jogador.setNome(valores[1]);
            jogador.setPosicao(valores[2]);
            jogador.setIdade(Integer.parseInt(valores[3]));
            jogador.setTecnica(Integer.parseInt(valores[4]));
            jogador.setFisico(Integer.parseInt(valores[5]));
            jogador.setInteligentcia(Integer.parseInt(valores[6]));
            jogador.setMotivacao(50);
            jogador.setTitular(valores[0] == "1" ? true : false);
            jogador.setTime(new Time());
            jogadorDAO.inserir(jogador);
        }
    }

}
