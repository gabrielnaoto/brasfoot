package com.example.tonied.futmanddm;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLTimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Regras;
import com.example.tonied.futmanddm.modelo.entidade.Time;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class actClassificacao extends AppCompatActivity {
    private Button btVoltar;
    private TableLayout tabela;
    private ImageView escudo01;
    private ImageView escudo02;
    private ImageView escudo03;
    private ImageView escudo04;
    private ImageView escudo05;
    private ImageView escudo06;
    private ImageView escudo07;
    private ImageView escudo08;
    private TextView pontos01;
    private TextView pontos02;
    private TextView pontos03;
    private TextView pontos04;
    private TextView pontos05;
    private TextView pontos06;
    private TextView pontos07;
    private TextView pontos08;
    private TextView nome01;
    private TextView nome02;
    private TextView nome03;
    private TextView nome04;
    private TextView nome05;
    private TextView nome06;
    private TextView nome07;
    private TextView nome08;
    private Map<String, View> views;
    private TimeDAO timedao;
    private Time t;

    Time[] times = new Time[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_classificacao);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SQLiteDatabase db = openOrCreateDatabase("brasfoot", MODE_PRIVATE, null);
        timedao = new SQLTimeDAO(db);

        btVoltar = (Button) findViewById(R.id.btVoltar);
        tabela = (TableLayout) findViewById(R.id.tabela);
        escudo01 = (ImageView) findViewById(R.id.escudo01);
        escudo02 = (ImageView) findViewById(R.id.escudo02);
        escudo03 = (ImageView) findViewById(R.id.escudo03);
        escudo04 = (ImageView) findViewById(R.id.escudo04);
        escudo05 = (ImageView) findViewById(R.id.escudo05);
        escudo06 = (ImageView) findViewById(R.id.escudo06);
        escudo07 = (ImageView) findViewById(R.id.escudo07);
        escudo08 = (ImageView) findViewById(R.id.escudo08);
        pontos01 = (TextView) findViewById(R.id.pontos01);
        pontos02 = (TextView) findViewById(R.id.pontos02);
        pontos03 = (TextView) findViewById(R.id.pontos03);
        pontos04 = (TextView) findViewById(R.id.pontos04);
        pontos05 = (TextView) findViewById(R.id.pontos05);
        pontos06 = (TextView) findViewById(R.id.pontos06);
        pontos07 = (TextView) findViewById(R.id.pontos07);
        pontos08 = (TextView) findViewById(R.id.pontos08);
        nome01 = (TextView) findViewById(R.id.nome01);
        nome02 = (TextView) findViewById(R.id.nome02);
        nome03 = (TextView) findViewById(R.id.nome03);
        nome04 = (TextView) findViewById(R.id.nome04);
        nome05 = (TextView) findViewById(R.id.nome05);
        nome06 = (TextView) findViewById(R.id.nome06);
        nome07 = (TextView) findViewById(R.id.nome07);
        nome08 = (TextView) findViewById(R.id.nome08);

        List<Time> timess = timedao.listar();

        int ti = 0;
        for (Time t : timess) {
            times[ti++] = t;
        }

        criaDadosTabela();
        btVoltarClick();
    }

    private void btVoltarClick() {

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void criaDadosTabela() {
        escudo01.setImageResource(Regras.times[times[0].getTimeid()]);
        escudo02.setImageResource(Regras.times[times[1].getTimeid()]);
        escudo03.setImageResource(Regras.times[times[2].getTimeid()]);
        escudo04.setImageResource(Regras.times[times[3].getTimeid()]);
        escudo05.setImageResource(Regras.times[times[4].getTimeid()]);
        escudo06.setImageResource(Regras.times[times[5].getTimeid()]);
        escudo07.setImageResource(Regras.times[times[6].getTimeid()]);
        escudo08.setImageResource(Regras.times[times[7].getTimeid()]);
        pontos01.setText(times[0].getPontos() + "");
        pontos02.setText(times[1].getPontos() + "");
        pontos03.setText(times[2].getPontos() + "");
        pontos04.setText(times[3].getPontos() + "");
        pontos05.setText(times[4].getPontos() + "");
        pontos06.setText(times[5].getPontos() + "");
        pontos07.setText(times[6].getPontos() + "");
        pontos08.setText(times[7].getPontos() + "");
        nome01.setText(times[0].getNome());
        nome02.setText(times[1].getNome());
        nome03.setText(times[2].getNome());
        nome04.setText(times[3].getNome());
        nome05.setText(times[4].getNome());
        nome06.setText(times[5].getNome());
        nome07.setText(times[6].getNome());
        nome08.setText(times[7].getNome());
    }
}
