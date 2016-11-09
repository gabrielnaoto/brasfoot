package com.example.tonied.futmanddm;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.tonied.futmanddm.modelo.dao.core.CampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.core.PartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLCampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLPartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLTimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Campeonato;
import com.example.tonied.futmanddm.modelo.entidade.Regras;
import com.example.tonied.futmanddm.modelo.entidade.Time;

import java.util.List;

public class actConsultaTime extends AppCompatActivity {

    private Button btVoltar;
    private ImageView escudo;
    private TextView score;
    private TextView infoTime;
    private TableLayout tabela;

    private int indiceTime;
    private int indicePatr;

    int[] timesEscudo = {
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

    private TimeDAO tdao;
    private CampeonatoDAO cdao;
    private PartidaDAO pdao;

    private List<Time> times;
    private Time time;
    private Time adversario;
    private Campeonato campeonato;

    int adverIdTime = 0;
    int adverClassi = 0;
    int adverPontos = 0;
    String adverProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_consulta_time);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Bundle dados = getIntent().getExtras();
            adverIdTime = dados.getInt("adverIdTime");
            adverClassi = dados.getInt("adverClassi");
            adverPontos = dados.getInt("adverPontos");
            adverProximo = dados.getString("adverProximo");

        SQLiteDatabase db = openOrCreateDatabase("brasfoot", MODE_PRIVATE, null);
        tdao = new SQLTimeDAO(db);
        cdao = new SQLCampeonatoDAO(db);
        pdao = new SQLPartidaDAO(db);

        times = tdao.listar();
        campeonato = cdao.pesquisar();
        time = campeonato.getT();
        adversario = tdao.pesquisar(Regras.getIndicesPorTime().get(Regras.getAdversario(time.getNome(), campeonato.getRodada())));

        btVoltar = (Button)findViewById(R.id.btVoltar);
        escudo = (ImageView)findViewById(R.id.escudo);
        score = (TextView)findViewById(R.id.score);
        infoTime = (TextView)findViewById(R.id.infoTime);
        tabela = (TableLayout) findViewById(R.id.tabela);

        tabela.removeAllViews();
        int tabJog = 18;
        int numJogador = 0;
        for(int i=0; i <= tabJog; i++) {
            if(i==0){
                criaDadosTabela(i, null);
            } else {
                String[] jogador = {
                        String.valueOf(
                                (int)
                                        (  (time.getJogadores().get(numJogador).getFisico()
                                                +time.getJogadores().get(numJogador).getInteligentcia()
                                                +time.getJogadores().get(numJogador).getTecnica()
                                                //+time.getJogadores().get(numJogador).getMotivacao()
                                        )
                                                /3)
                        ),
                        time.getJogadores().get(numJogador).getNome(),
                        time.getJogadores().get(numJogador).getPosicao().substring(0,1),
                        String.valueOf(time.getJogadores().get(numJogador).getIdade()),
                        String.valueOf(time.getJogadores().get(numJogador).getCartaoamarelo())
                };
                criaDadosTabela(i, jogador);
                numJogador++;
            }
        }

        btVoltarClick();
        montaStrings(adverIdTime, adverClassi, adverPontos, adverProximo);
    }

    private void btVoltarClick() {
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void montaStrings(int adverIdTime, int adverClassi, int adverPontos, String adverProximo){
        escudo.setImageResource(timesEscudo[adverIdTime]);
        score.setText(adversario.getAtributos()+"");;
        infoTime.setText("Colocação: "+adverClassi+" ("+adverPontos+" pts)\nPróximo jogo: "+adverProximo);
    }

    public void criaDadosTabela(int pos, String[] jogador){
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(0,0));

        if(pos == 0){
            //Parametros de layout dos textviews
            TableRow.LayoutParams param01 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.4f);
            TableRow.LayoutParams param02 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams param05 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
            TableRow.LayoutParams param03 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.4f);
            TableRow.LayoutParams param04 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.3f);

            //CARGA DOS TEXTOS
            String t1 = "Score";
            String t2 = "Nome";
            String t5 = "Pos";
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

            //TextView POSICAO
            TextView posiJog = new TextView(this);
            posiJog.setLayoutParams(param05);
            posiJog.setText(t5);

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
            posiJog.setTextColor(Color.BLACK);
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
            row.addView(posiJog);
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
            TableRow.LayoutParams param05 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
            TableRow.LayoutParams param03 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.4f);
            TableRow.LayoutParams param04 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.3f);

            //CARGA DOS TEXTOS
            String t1 = jogador[0];
            String t2 = jogador[1];
            String t5 = jogador[2];
            String t3 = jogador[3];
            String t4 = jogador[4];


            //TextView SCORE
            TextView scoreJog = new TextView(this);
            scoreJog.setLayoutParams(param01);
            scoreJog.setText(t1);

            //TextView NOME
            TextView nomeJog = new TextView(this);
            nomeJog.setLayoutParams(param02);
            nomeJog.setText(t2);

            //TextView POSICAO
            TextView posiJog = new TextView(this);
            posiJog.setLayoutParams(param05);
            posiJog.setText(t5);

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
            posiJog.setTextColor(Color.BLACK);
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
            row.addView(posiJog);
            row.addView(ageJog);
            row.addView(cartaoJog);
            row.setId(100 + pos);

            // add TableRow na TableView
            tabela.addView(row);
            tabela.addView(v);
        }//end if else
    }// end void cria jogador
}
