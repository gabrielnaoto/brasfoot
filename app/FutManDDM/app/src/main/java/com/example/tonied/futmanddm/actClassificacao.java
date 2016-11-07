package com.example.tonied.futmanddm;

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

import java.util.ArrayList;
import java.util.List;

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

    int[] times = {
            R.drawable.earsenal,
            R.drawable.eatlmadrid,
            R.drawable.ebarcelona,
            R.drawable.ebayern,
            R.drawable.ejuventus,
            R.drawable.emanunited,
            R.drawable.epsg,
            R.drawable.erealm
    };

    int[] pontos ={12, 11, 10, 9, 8, 7, 6, 5};

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
        setContentView(R.layout.activity_act_classificacao);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btVoltar = (Button)findViewById(R.id.btVoltar);
        tabela = (TableLayout)findViewById(R.id.tabela);
        escudo01 = (ImageView)findViewById(R.id.escudo01);
        escudo02 = (ImageView)findViewById(R.id.escudo02);
        escudo03 = (ImageView)findViewById(R.id.escudo03);
        escudo04 = (ImageView)findViewById(R.id.escudo04);
        escudo05 = (ImageView)findViewById(R.id.escudo05);
        escudo06 = (ImageView)findViewById(R.id.escudo06);
        escudo07 = (ImageView)findViewById(R.id.escudo07);
        escudo08 = (ImageView)findViewById(R.id.escudo08);
        pontos01 = (TextView)findViewById(R.id.pontos01);
        pontos02 = (TextView)findViewById(R.id.pontos02);
        pontos03 = (TextView)findViewById(R.id.pontos03);
        pontos04 = (TextView)findViewById(R.id.pontos04);
        pontos05 = (TextView)findViewById(R.id.pontos05);
        pontos06 = (TextView)findViewById(R.id.pontos06);
        pontos07 = (TextView)findViewById(R.id.pontos07);
        pontos08 = (TextView)findViewById(R.id.pontos08);
        nome01 = (TextView)findViewById(R.id.nome01);
        nome02 = (TextView)findViewById(R.id.nome02);
        nome03 = (TextView)findViewById(R.id.nome03);
        nome04 = (TextView)findViewById(R.id.nome04);
        nome05 = (TextView)findViewById(R.id.nome05);
        nome06 = (TextView)findViewById(R.id.nome06);
        nome07 = (TextView)findViewById(R.id.nome07);
        nome08 = (TextView)findViewById(R.id.nome08);

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
        escudo01.setImageResource(times[0]);
        escudo02.setImageResource(times[1]);
        escudo03.setImageResource(times[2]);
        escudo04.setImageResource(times[3]);
        escudo05.setImageResource(times[4]);
        escudo06.setImageResource(times[5]);
        escudo07.setImageResource(times[6]);
        escudo08.setImageResource(times[7]);
        pontos01.setText(pontos[0]+"");
        pontos02.setText(pontos[1]+"");
        pontos03.setText(pontos[2]+"");
        pontos04.setText(pontos[3]+"");
        pontos05.setText(pontos[4]+"");
        pontos06.setText(pontos[5]+"");
        pontos07.setText(pontos[6]+"");
        pontos08.setText(pontos[7]+"");
        nome01.setText(nomeTime[0]);
        nome02.setText(nomeTime[1]);
        nome03.setText(nomeTime[2]);
        nome04.setText(nomeTime[3]);
        nome05.setText(nomeTime[4]);
        nome06.setText(nomeTime[5]);
        nome07.setText(nomeTime[6]);
        nome08.setText(nomeTime[7]);
    }
}
