package com.example.tonied.futmanddm;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class actConsultaTime extends AppCompatActivity {

    private Button btVoltar;
    private ImageView escudo;
    private TextView score;
    private TextView infoTime;
    private TableLayout tabela;

    private int indiceTime;
    private int indicePatr;

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

    int[] scores = {81,83,78,89,79,85,84,77};
    int adverIdTime = 0;
    int adverClassi = 0;
    int adverPontos = 0;
    int adverProximo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_consulta_time);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Bundle dados = getIntent().getExtras();
        if (dados.isEmpty()) {
            Toast.makeText(this, "Dados não carregados", Toast.LENGTH_SHORT).show();
            adverIdTime = 0;
            adverClassi = 0;
            adverPontos = 0;
            adverProximo = 0;
        } else {
            adverIdTime = dados.getInt("adverIdTime");
            adverClassi = dados.getInt("adverClassi");
            adverPontos = dados.getInt("adverPontos");
            adverProximo = dados.getInt("adverProximo");
        }

        btVoltar = (Button)findViewById(R.id.btVoltar);
        escudo = (ImageView)findViewById(R.id.escudo);
        score = (TextView)findViewById(R.id.score);
        infoTime = (TextView)findViewById(R.id.infoTime);
        tabela = (TableLayout) findViewById(R.id.tabela);

        montaStrings(adverIdTime, adverClassi, adverPontos, adverProximo);

        tabela.removeAllViews();
        int tabJog = 18;
        for (int i = 0; i <= tabJog; i++) {
            String[] jogador = {
                    String.valueOf((int) (Math.random() * 20) + 70),
                    "Jogador " + i,
                    String.valueOf((int) (Math.random() * 20) + 20),
                    "-"};
            criaDadosTabela(i, jogador);
        }

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

    public void montaStrings(int adverIdTime, int adverClassi, int adverPontos, int adverProximo){
        escudo.setImageResource(times[adverIdTime]);
        score.setText(scores[adverIdTime]+"");
        String proximo = nomeTime[adverProximo];
        infoTime.setText("Colocação: "+adverClassi+" ("+adverPontos+" pts)\nPróximo jogo: "+proximo);
    }

    public void criaDadosTabela(int pos, String[] jogador){

        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(0,0));

       if(pos == 0){
            //Parametros de layout dos textviews
            TableRow.LayoutParams param01 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.4f);
            TableRow.LayoutParams param02 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams param03 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.4f);
            TableRow.LayoutParams param04 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.3f);

            //CARGA DOS TEXTOS
            String t1 = "Score";
            String t2 = "Nome";
            String t3 = "Idade";
            String t4 = "Cartão";

            //TextView SCORE
            TextView scoreJog = new TextView(this);
            scoreJog.setLayoutParams(param01);
            scoreJog.setText(t1);

            //TextView NOME
            TextView nomeJog = new TextView(this);
            nomeJog.setLayoutParams(param02);
            nomeJog.setText(t2);

            //TextView IDADE
            TextView ageJog = new TextView(this);
            ageJog.setLayoutParams(param03);
            ageJog.setText(t3);

            //TextView CARTAO
            TextView cartaoJog = new TextView(this);
            cartaoJog.setLayoutParams(param04);
            cartaoJog.setText(t4);

            //SETANDO AS CORES NOS TEXT VIEWS
            scoreJog.setTextColor(Color.BLACK);
            nomeJog.setTextColor(Color.BLACK);
            ageJog.setTextColor(Color.BLACK);
            cartaoJog.setTextColor(Color.BLACK);

            //divider
            View v = new View(this);
            v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
            v.setBackgroundColor(Color.DKGRAY);  //rgb(51, 51, 51));

            // add os TextViews na TableRow
            TableLayout.LayoutParams tableRowParams =
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT, 1);

            int leftMargin = 10;
            int topMargin = 5;
            int rightMargin = 10;
            int bottomMargin = 5;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            //SETA ALINHAMENTO DIFERENCIADO NO CABEÇALHO

            row.setBackgroundColor(Color.rgb(100,150,250));

            row.setLayoutParams(tableRowParams);
            row.addView(scoreJog);
            row.addView(nomeJog);
            row.addView(ageJog);
            row.addView(cartaoJog);
            row.setId(100 + pos);

            // add TableRow na TableView
            tabela.addView(row);
            tabela.addView(v);
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //LINHAS DOS JOGADORES
        } else {
            //Parametros de layout dos textviews
            TableRow.LayoutParams param01 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.4f);
            TableRow.LayoutParams param02 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams param03 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.4f);
            TableRow.LayoutParams param04 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.3f);

            //CARGA DOS TEXTOS
            String t1 = jogador[0];
            String t2 = jogador[1];
            String t3 = jogador[2];
            String t4 = jogador[3];

            //TextView SCORE
            TextView scoreJog = new TextView(this);
            scoreJog.setLayoutParams(param01);
            scoreJog.setText(t1);

            //TextView NOME
            TextView nomeJog = new TextView(this);
            nomeJog.setLayoutParams(param02);
            nomeJog.setText(t2);

            //TextView IDADE
            TextView ageJog = new TextView(this);
            ageJog.setLayoutParams(param03);
            ageJog.setText(t3);

            //TextView CARTAO
            TextView cartaoJog = new TextView(this);
            cartaoJog.setLayoutParams(param04);
            cartaoJog.setText(t4);

            //SETANDO AS CORES NOS TEXT VIEWS
            scoreJog.setTextColor(Color.BLACK);
            nomeJog.setTextColor(Color.BLACK);
            ageJog.setTextColor(Color.BLACK);
            cartaoJog.setTextColor(Color.BLACK);

            //divider
            View v = new View(this);
            v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
            v.setBackgroundColor(Color.DKGRAY);  //rgb(51, 51, 51));

            // add os TextViews na TableRow
            TableLayout.LayoutParams tableRowParams =
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT, 1);

            int leftMargin = 10;
            int topMargin = 5;
            int rightMargin = 10;
            int bottomMargin = 5;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            row.setBackgroundColor(Color.WHITE);

            row.setLayoutParams(tableRowParams);
            row.addView(scoreJog);
            row.addView(nomeJog);
            row.addView(ageJog);
            row.addView(cartaoJog);
            row.setId(100 + pos);

            // add TableRow na TableView
            tabela.addView(row);
            tabela.addView(v);
        }//end if else
    }// end void cria jogador
}
