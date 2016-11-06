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

public class actHome extends AppCompatActivity {

    private SQLiteDatabase db;
    private Button b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_home);

        ActionBar ac = getSupportActionBar();
        ac.hide();

    }

    public void novo(View v) {
        Toast.makeText(getApplicationContext(), "novo", Toast.LENGTH_SHORT).show();
        deleteDatabase("brasfoot");
        createDataBase();
        importar();


        startActivity(new Intent(this, actSelTime.class));
        finish();
        //chama intent seltime
    }

    private void createDataBase() {

        this.db = openOrCreateDatabase("brasfoot", MODE_PRIVATE, null);

        db.execSQL("create table if not exists patrocinador(\n" +
                "patrocinadorid integer primary key autoincrement,\n" +
                "nome varchar,\n" +
                "estrelas integer,\n" +
                "valor double)");

        db.execSQL("create table if not exists estadio(\n" +
                "estadioid integer primary key autoincrement,\n" +
                "estrelas integer,\n" +
                "ingresso double, \n" +
                "publico integer\n" +
                ")");

        db.execSQL("create table if not exists time(\n" +
                "nome varchar," +
                "timeid integer primary key autoincrement,\n" +
                "pontos integer,\n" +
                "esquema integer,\n" +
                "saldo double,\n" +
                "patrocinadorid integer not null,\n" +
                "estadioid integer not null\n" +
                ")");

        db.execSQL("create table if not exists jogador(\n" +
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

        db.execSQL("create table if not exists partida(\n" +
                "partidaid integer primary key autoincrement,\n" +
                "casaid integer,\n" +
                "visitanteid integer\n" +
                ");");

        db.execSQL("create table if not exists gols(\n" +
                "id integer primary key autoincrement,\n" +
                "gols integer not null,\n" +
                "timeid integer not null,\n" +
                "partidaid integer not null\n" +
                ")");
    }

    public void continuar(View v) {
        Toast.makeText(getApplicationContext(), "continuar", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, actManager.class);


        startActivity(it);
        finish();
    }

    public void importar() {
        //logica para ler os dados de um res.raw.jogadores.txt
        //boa sorte

    }
}
