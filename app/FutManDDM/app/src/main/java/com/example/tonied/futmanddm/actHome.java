package com.example.tonied.futmanddm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tonied.futmanddm.modelo.dao.core.FactoryDAO;
import com.example.tonied.futmanddm.modelo.dao.core.JogadorDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLJogadorDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLTimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Esquema;
import com.example.tonied.futmanddm.modelo.entidade.Estadio;
import com.example.tonied.futmanddm.modelo.entidade.Jogador;
import com.example.tonied.futmanddm.modelo.entidade.Patrocinador;
import com.example.tonied.futmanddm.modelo.entidade.Time;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class actHome extends AppCompatActivity {


    private Button b2;
    private TimeDAO timeDAO;
    private JogadorDAO jogadorDAO;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_home);

        ActionBar ac = getSupportActionBar();
        ac.hide();

    }

    public void novo(View v) throws IOException {
        Toast.makeText(getApplicationContext(), "novo", Toast.LENGTH_SHORT).show();
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
                "patrocinador integer)");

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

    public void continuar(View v) {
        Toast.makeText(getApplicationContext(), "continuar", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, actManager.class);
        startActivity(it);
        finish();
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
            t.setEsquema(Esquema.BALANCEADO);
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
