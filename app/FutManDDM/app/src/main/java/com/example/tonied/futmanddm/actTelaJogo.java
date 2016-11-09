package com.example.tonied.futmanddm;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class actTelaJogo extends AppCompatActivity {

    private Button btOk;
    private ImageView p1escCasa;
    private ImageView p2escCasa;
    private ImageView p3escCasa;
    private ImageView p4escCasa;
    private ImageView p1escVisit;
    private ImageView p2escVisit;
    private ImageView p3escVisit;
    private ImageView p4escVisit;
    private TextView p1plCasa;
    private TextView p2plCasa;
    private TextView p3plCasa;
    private TextView p4plCasa;
    private TextView p1plVisit;
    private TextView p2plVisit;
    private TextView p3plVisit;
    private TextView p4plVisit;
    private TextView rodada;
    private CampeonatoDAO campeonatodao;
    private Campeonato c;
    private PartidaDAO partidadao;
    private Partida p;
    private TimeDAO timedao;
    private Time t;
    private int rodadap;
    private Map<String, View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_tela_jogo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SQLiteDatabase db = openOrCreateDatabase("brasfoot", MODE_PRIVATE, null);

        campeonatodao = new SQLCampeonatoDAO(db);

        partidadao = new SQLPartidaDAO(db);

        timedao = new SQLTimeDAO(db);

        p1escCasa = (ImageView) findViewById(R.id.p1escCasa);
        p2escCasa = (ImageView) findViewById(R.id.p2escCasa);
        p3escCasa = (ImageView) findViewById(R.id.p3escCasa);
        p4escCasa = (ImageView) findViewById(R.id.p4escCasa);
        p1plCasa = (TextView) findViewById(R.id.p1plCasa);
        p2plCasa = (TextView) findViewById(R.id.p2plCasa);
        p3plCasa = (TextView) findViewById(R.id.p3plCasa);
        p4plCasa = (TextView) findViewById(R.id.p4plCasa);

        p1escVisit = (ImageView) findViewById(R.id.p1escVisit);
        p2escVisit = (ImageView) findViewById(R.id.p2escVisit);
        p3escVisit = (ImageView) findViewById(R.id.p3escVisit);
        p4escVisit = (ImageView) findViewById(R.id.p4escVisit);
        p1plVisit = (TextView) findViewById(R.id.p1plVisit);
        p2plVisit = (TextView) findViewById(R.id.p2plVisit);
        p3plVisit = (TextView) findViewById(R.id.p3plVisit);
        p4plVisit = (TextView) findViewById(R.id.p4plVisit);

        views = new HashMap<>();
        views.put("1escCasa", p1escCasa);
        views.put("2escCasa", p2escCasa);
        views.put("3escCasa", p3escCasa);
        views.put("4escCasa", p4escCasa);
        views.put("1plCasa", p1plCasa);
        views.put("2plCasa", p2plCasa);
        views.put("3plCasa", p3plCasa);
        views.put("4plCasa", p4plCasa);

        views.put("1escVisit", p1escVisit);
        views.put("2escVisit", p2escVisit);
        views.put("3escVisit", p3escVisit);
        views.put("4escVisit", p4escVisit);
        views.put("1plVisit", p1plVisit);
        views.put("2plVisit", p2plVisit);
        views.put("3plVisit", p3plVisit);
        views.put("4plVisit", p4plVisit);

        rodada = (TextView) findViewById(R.id.rodada);
        btOk = (Button) findViewById(R.id.btOk);

        btOkClick();
        cargaJogos(1);
        carregaResultados();
    }

    public void carregaResultados() {
        c = campeonatodao.pesquisar();
        rodadap = c.getRodada();
        List<Partida> partidas = partidadao.listarPorRodada(rodadap);
        System.out.println("taggggg " + partidas.size());
        System.out.println(c.getRodada());
        for (int i = 0; i < 4; i++) {
            Partida atual = partidas.get(i);
            String chave = (i + 1) + "esc" + "Casa";
            ((ImageView) views.get(chave)).setImageResource(Regras.times[atual.getCasa().getTimeid()]);
            chave = (i + 1) + "pl" + "Casa";
            ((TextView) views.get(chave)).setText(atual.getPlacar()[0] + "");
            System.out.println(atual.getPlacar()[0] + " x " + atual.getPlacar()[1]);
            chave = (i + 1) + "esc" + "Visit";
            ((ImageView) views.get(chave)).setImageResource(Regras.times[atual.getVisitante().getTimeid()]);
            chave = (i + 1)  + "pl" + "Visit";
            ((TextView) views.get(chave)).setText(atual.getPlacar()[1] + "");
        }

        c.setRodada(c.getRodada() + 1);
        System.out.println(c.getRodada());
        campeonatodao.editar(c);
    }

    private void cargaJogos(int r) {
        rodada.setText("Resultados da " + r + "ª Rodada");
    }

    private void btOkClick() {
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(actTelaJogo.this, actManager.class));
                finish();
            }
        });
    }
}
