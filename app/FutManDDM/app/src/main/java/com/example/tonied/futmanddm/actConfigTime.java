package com.example.tonied.futmanddm;


import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class actConfigTime extends AppCompatActivity {

    private static ImageView escudoCasa;
    private static ImageView escudoVisitante;
    private static TextView scoreCasa;
    private static TextView scoreVisitante;
    private static TextView infoTime;

    private int indiceTime;
    private int indicePatr;

    int[] times = {
            R.drawable.ebarcelona,
            R.drawable.erealm,
            R.drawable.eatlmadrid,
            R.drawable.ebayern,
            R.drawable.ejuventus
    };

    String[] nomeTime = {
            "Barcelona",
            "Real Madrid",
            "Atl. Madrid",
            "Bayern Munique",
            "Juventus"
    };

    int[] scores = {85,83,78,89,79};

    String[] valores = {"1kk","900k","800k","750k","600k","700k","750k","800k","450k", "800k", "350k"};
    String[] ingressos = {"80","85","70","65","60","50","50","45","50", "40", "35"};

    private TableLayout tabela;
    private int linhasSelect = 0;
    private int idLine01, idLine02;

    TableRow tr1;
    TableRow tr2;
    TextView tr1Score;
    TextView tr1Nome;
    TextView tr1Idade;
    TextView tr1Cartao;
    String j1Score;
    String j1Nome;
    String j1Idade;
    String j1Cartao;
    TextView tr2Score;
    TextView tr2Nome;
    TextView tr2Idade;
    TextView tr2Cartao;
    String j2Score;
    String j2Nome;
    String j2Idade;
    String j2Cartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_config_time);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Bundle dados = getIntent().getExtras();
        if(dados.isEmpty()){
            Toast.makeText(this, "Dados não carregados", Toast.LENGTH_SHORT).show();
            indicePatr = 0;
            indiceTime = 0;
        } else {
            indicePatr = dados.getInt("indicePatr");
            indiceTime = dados.getInt("indiceTime");
        }

        scoreCasa = (TextView)findViewById(R.id.scoreCasa);
        scoreCasa.setText("Score atual: "+scores[indiceTime]+" pontos");
        escudoCasa = (ImageView)findViewById(R.id.escudoCasa);
        escudoCasa.setImageResource(times[indiceTime]);

        scoreVisitante = (TextView)findViewById(R.id.scoreVisitante);
        scoreVisitante.setText("Score atual: "+scores[indiceTime+1]+" pontos");
        escudoVisitante = (ImageView)findViewById(R.id.escudoVisitante);
        escudoVisitante.setImageResource(times[indiceTime+1]);

        infoTime = (TextView)findViewById(R.id.infoTime);
        tabela = (TableLayout)findViewById(R.id.tabela);

        montaStrings(4,1,11,2);

        tabela.removeAllViews();
        int tabJog = 20;
        for(int i=0; i <= tabJog; i++) {
            String[] jogador = {
                    String.valueOf((int)(Math.random()*20) +70 ),
                    "Jogador "+i,
                    String.valueOf((int)(Math.random()*20) +20 ),
                    "-"};
            criaDadosTabela(i, jogador);
        }
    }

    public void montaStrings(int time, int casa, int pontos, int classif){
        String resultado;
        String local;
        String proximo = nomeTime[time];
        if(casa==0)
            local = "fora";
        else
            local = "casa";
        resultado = "Colocação: "+classif+" ("+pontos+" pts)\nPróximo jogo: "+proximo+" ("+local+")";
        infoTime.setText(resultado);
    }

    public void criaDadosTabela(int pos, String[] jogador){

        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(0,0));

        //VERIFICA SE SÃO LINHAS DE TÍTULO, desativando o onClick usado para substituição dos jogadores
        if (pos==0 || pos==13){
            //TextView TITULO
            TextView titulo = new TextView(this);
            titulo.setTextColor(Color.BLACK);
            TableRow.LayoutParams param01 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1);
            titulo.setLayoutParams(param01);
            String t1 = "";
            if(pos==0)
                t1 = "TITULARES";
            else
                t1 = "RESERVAS";
            titulo.setText(t1);
            titulo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            //divider
            View v = new View(this);
            v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
            v.setBackgroundColor(Color.DKGRAY);

            // add os TextViews na TableRow
            TableLayout.LayoutParams tableRowParams =
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT, 1);

            int leftMargin = 10;
            int topMargin = 5;
            int rightMargin = 10;
            int bottomMargin = 5;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            row.setBackgroundColor(Color.rgb(200,200,200));
            row.setLayoutParams(tableRowParams);
            row.addView(titulo);
            row.setId(100 + pos);

            // add TableRow na TableView
            tabela.addView(row);
            tabela.addView(v);
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //LINHA DE TITULOS
        } else if(pos == 1){
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

            //VERIFICA OS CLIQUES NAS LINHAS PARA SUBSTITUIÇÃO DOS JOGADORES TITULARES
            row.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    //NENHUM JOGADOR ESTÁ SELECIONADO, clicando no primeiro
                    if (linhasSelect == 0) {
                        linhasSelect = 1;
                        view.setBackgroundColor(Color.YELLOW);
                        idLine01 = view.getId();

                        tr1 = (TableRow) view;
                        tr1Score = (TextView) tr1.getChildAt(0);
                        tr1Nome = (TextView) tr1.getChildAt(1);
                        tr1Idade = (TextView) tr1.getChildAt(2);
                        tr1Cartao = (TextView) tr1.getChildAt(3);
                        j1Score = tr1Score.getText().toString();
                        j1Nome = tr1Nome.getText().toString();
                        j1Idade = tr1Idade.getText().toString();
                        j1Cartao = tr1Cartao.getText().toString();
                        //Toast.makeText(MainActivity.this, "Primeiro: "+j1Nome+" - "+j1Score, Toast.LENGTH_SHORT).show();

                        //PRIMEIRO JOGADOR JÁ ESTÁ SELECIONADO, verifica se está deselecionando ou trocando
                    } else if (linhasSelect == 1) {
                        idLine02 = view.getId();

                        tr2 = (TableRow) view;
                        tr2Score = (TextView) tr2.getChildAt(0);
                        tr2Nome = (TextView) tr2.getChildAt(1);
                        tr2Idade = (TextView) tr2.getChildAt(2);
                        tr2Cartao = (TextView) tr2.getChildAt(3);
                        j2Score = tr2Score.getText().toString();
                        j2Nome = tr2Nome.getText().toString();
                        j2Idade = tr2Idade.getText().toString();
                        j2Cartao = tr2Cartao.getText().toString();

                        if (idLine02 == idLine01) {
                            view.setBackgroundColor(Color.WHITE);
                            idLine01 = 0;
                            idLine02 = 0;
                            //ZERANDO linhasSelect para voltar ao passo 1
                            linhasSelect = 0;
                        } else {
                            //caso não seja o mesmo, FAZ A TROCA DAS LINHAS
                            linhasSelect = 2;
                            view.setBackgroundColor(Color.LTGRAY);

                            tr1Score.setText(j2Score);
                            tr1Nome.setText(j2Nome);
                            tr1Idade.setText(j2Idade);
                            tr1Cartao.setText(j2Cartao);

                            tr2Score.setText(j1Score);
                            tr2Nome.setText(j1Nome);
                            tr2Idade.setText(j1Idade);
                            tr2Cartao.setText(j1Cartao);
                            tr1.setBackgroundColor(Color.WHITE);
                            tr2.setBackgroundColor(Color.WHITE);
                            view.setBackgroundColor(Color.WHITE);
                            //Toast.makeText(MainActivity.this, "2 linhas OK", Toast.LENGTH_SHORT).show();

                            //LIBERA DA MEMORIA
                            tr1 = null;
                            tr2 = null;
                            linhasSelect = 0;
                        }
                    }

                }//end on click
            }); //end listener
        }//end if else
    }// end void cria jogador
}
