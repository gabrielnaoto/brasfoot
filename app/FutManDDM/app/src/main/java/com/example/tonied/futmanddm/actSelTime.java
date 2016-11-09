package com.example.tonied.futmanddm;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tonied.futmanddm.modelo.dao.core.CampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLCampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLTimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Campeonato;
import com.example.tonied.futmanddm.modelo.entidade.Estadio;
import com.example.tonied.futmanddm.modelo.entidade.Patrocinador;
import com.example.tonied.futmanddm.modelo.entidade.Regras;
import com.example.tonied.futmanddm.modelo.entidade.Time;

import java.util.List;

public class actSelTime extends AppCompatActivity {
    private static ImageView escudo;
    private static ImageView patrocinio;
    private static ImageView bTreinar;

    private static ImageButton timeLeft;
    private static ImageButton timeRight;
    private static ImageButton patrLeft;
    private static ImageButton patrRight;

    private static TextView texto01;
    private static TextView texto02;

    private CampeonatoDAO cdao;
    private TimeDAO tdao;

    private int indiceTime = 0;
    private int indicePatr = 0;

    int[] scores = new int[8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_sel_time);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        texto01 = (TextView) findViewById(R.id.Info01);
        escudo = (ImageView) findViewById(R.id.escudo);
        timeLeft = (ImageButton) findViewById(R.id.timeLeft);
        timeRight = (ImageButton) findViewById(R.id.timeRight);

        patrocinio = (ImageView) findViewById(R.id.patrocinio);
        patrLeft = (ImageButton) findViewById(R.id.patrLeft);
        patrRight = (ImageButton) findViewById(R.id.patrRight);
        texto02 = (TextView) findViewById(R.id.Info02);

        escudo.setImageResource(Regras.times[indiceTime]);
        patrocinio.setImageResource(Regras.patrocinadores[indicePatr]);

        texto02.setText("Investimento R$" + Regras.valores[indicePatr] + "\nIngressos R$ " + Regras.ingressos[indicePatr]);

        bTreinar = (ImageView) findViewById(R.id.bTreinar);

        timeLeftClick();
        timeRightClick();
        patrLeftClick();
        patrRightClick();

        SQLiteDatabase db = openOrCreateDatabase("brasfoot", MODE_PRIVATE, null);

        cdao = new SQLCampeonatoDAO(db);
        tdao = new SQLTimeDAO(db);

        System.out.println("vai carregar");

        for (int i = 0; i < scores.length; i++) {
            scores[i] = tdao.pesquisar(i).getAtributos();
        }

        texto01.setText("Score de " + scores[indiceTime] + " pontos");

    }


    public void bTreinarClick(View v) {
        Intent it = new Intent(actSelTime.this, actManager.class);
        Bundle dados = new Bundle();
        dados.putInt("indiceTime", indiceTime);
        dados.putInt("indicePatr", indicePatr);
        it.putExtras(dados);
        startActivity(it);
        salvarCampeonato();
        finish();
    }


    private void salvarCampeonato() {

        Campeonato c = new Campeonato();
        Patrocinador p = new Patrocinador();
        p.setNome(Regras.n_patrocinadores[indicePatr]);
        p.setEstrelas(Regras.e_patrocinadores[indicePatr]);
        p.setValor(Regras.valPatr[indicePatr]);
        c.setP(p);
        Estadio e = new Estadio();
        e.setEstrelas(Regras.e_patrocinadores[indicePatr]);
        e.setIngresso(Regras.valPatr[indicePatr]);
        e.setPublico(6000);
        c.setE(e);
        Time t = new Time();
        t = tdao.pesquisar(indiceTime);
        t.setEstadio(e);
        t.setPatrocinador(p);
        c.setT(t);
        cdao.inserir(c);
    }


    public void timeLeftClick() {
        timeLeft.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (indiceTime == 0) {
                            indiceTime = Regras.times.length - 1;
                        } else {
                            indiceTime = indiceTime % Regras.times.length;
                            indiceTime--;
                        }
                        escudo.setImageResource(Regras.times[indiceTime]);
                        texto01.setText("Score de " + scores[indiceTime] + " pontos");
                    }
                }
        );
    }

    public void timeRightClick() {
        timeRight.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        indiceTime++;
                        indiceTime = indiceTime % Regras.times.length;
                        escudo.setImageResource(Regras.times[indiceTime]);
                        texto01.setText("Score de " + scores[indiceTime] + " pontos");
                    }
                }
        );
    }

    public void patrLeftClick() {
        patrLeft.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (indicePatr == 0) {
                            indicePatr = Regras.patrocinadores.length - 1;
                        } else {
                            indicePatr = indicePatr % Regras.patrocinadores.length;
                            indicePatr--;
                        }
                        patrocinio.setImageResource(Regras.patrocinadores[indicePatr]);
                        texto02.setText("Investimento R$" + Regras.valores[indicePatr] + "\nIngressos R$ " + Regras.ingressos[0]);
                    }
                }
        );
    }

    public void patrRightClick() {
        patrRight.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        indicePatr++;
                        indicePatr = indicePatr % Regras.patrocinadores.length;
                        patrocinio.setImageResource(Regras.patrocinadores[indicePatr]);
                        texto02.setText("Investimento R$" + Regras.valores[indicePatr] + "\nIngressos R$ " + Regras.ingressos[indicePatr]);
                    }
                }
        );
    }
}
