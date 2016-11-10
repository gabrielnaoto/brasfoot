package com.example.tonied.futmanddm;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tonied.futmanddm.modelo.dao.core.CampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.core.PartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Campeonato;
import com.example.tonied.futmanddm.modelo.entidade.Regras;
import com.example.tonied.futmanddm.modelo.entidade.Time;

import java.util.List;

public class actFinal extends AppCompatActivity {

    private ImageView eCampeao;
    private ImageView ec02;
    private ImageView ec03;
    private TextView tc02;
    private TextView tc03;
    private TextView pc02;
    private TextView pc03;

    private TimeDAO tdao;
    private CampeonatoDAO cdao;
    private PartidaDAO pdao;

    private List<Time> times;
    private Time time;
    private Time adversario;

    private Campeonato campeonato;

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
}
