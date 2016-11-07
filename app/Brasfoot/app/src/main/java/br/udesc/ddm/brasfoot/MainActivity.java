package br.udesc.ddm.brasfoot;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.udesc.ddm.brasfoot.modelo.dao.core.JogadorDAO;
import br.udesc.ddm.brasfoot.modelo.dao.core.TimeDAO;
import br.udesc.ddm.brasfoot.modelo.dao.sqlite.SQLJogadorDAO;
import br.udesc.ddm.brasfoot.modelo.dao.sqlite.SQLTimeDAO;
import br.udesc.ddm.brasfoot.modelo.entidade.Esquema;
import br.udesc.ddm.brasfoot.modelo.entidade.Estadio;
import br.udesc.ddm.brasfoot.modelo.entidade.Jogador;
import br.udesc.ddm.brasfoot.modelo.entidade.Patrocinador;
import br.udesc.ddm.brasfoot.modelo.entidade.Time;

public class MainActivity extends AppCompatActivity {

    private TextView texto;
    private Button button;
    private BufferedReader leitor;
    private JogadorDAO jogadorDAO;
    private TimeDAO timeDAO;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deleteDatabase("brasfoot");

        this.db = openOrCreateDatabase("brasfoot", MODE_PRIVATE, null);



//        this.texto = (TextView) findViewById(R.id.textView);
        this.button = (Button) findViewById(R.id.button);
        this.leitor = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.jogadores)));
        this.timeDAO = new SQLTimeDAO(db);
        this.jogadorDAO = new SQLJogadorDAO(db);


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

    public void carregar(View v) {

        try {
            importar();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Cursor cursor = db.rawQuery("SELECT * FROM jogador", null);

        int nome = cursor.getColumnIndex("nome");
        int estrelas = cursor.getColumnIndex("estrelas");
        int valor = cursor.getColumnIndex("valor");

        cursor.moveToFirst();
        String conteudo = "";
        while(!cursor.isAfterLast()) {
            conteudo += cursor.getString(nome) + "\n";
            cursor.moveToNext();
        }
        texto.setText(conteudo);
    }

    public List<Time> importar() throws IOException, NullPointerException{

    }


}
