package com.example.tonied.futmanddm;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tonied.futmanddm.modelo.dao.core.CampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.core.JogadorDAO;
import com.example.tonied.futmanddm.modelo.dao.core.PartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLCampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLPartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLTimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Campeonato;
import com.example.tonied.futmanddm.modelo.entidade.Esquema;
import com.example.tonied.futmanddm.modelo.entidade.Jogador;
import com.example.tonied.futmanddm.modelo.entidade.Regras;
import com.example.tonied.futmanddm.modelo.entidade.Time;

import java.util.List;

public class actConfigTime extends AppCompatActivity {

    private ImageView escudoCasa;
    private ImageView escudoVisitante;
    private TextView scoreCasa;
    private TextView scoreVisitante;
    private TextView infoTime;
    private TextView tFormacao;
    private SeekBar seekFormacao;
    private Button btVoltar;
    private Button btJogar;

    private static int indiceTime, idCasa, idVisit, adverClassif;

    int[] timesLogo = {
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

    private TableLayout tabela;
    private int linhasSelect = 0;
    private int idLine01, idLine02;

    TableRow tr1;
    TableRow tr2;
    TextView tr1Score;
    TextView tr1Nome;
    TextView tr1Posi;
    TextView tr1Idade;
    TextView tr1Cartao;
    String j1Score;
    String j1Nome;
    String j1Posi;
    String j1Idade;
    String j1Cartao;
    TextView tr2Score;
    TextView tr2Nome;
    TextView tr2Posi;
    TextView tr2Idade;
    TextView tr2Cartao;
    String j2Score;
    String j2Nome;
    String j2Posi;
    String j2Idade;
    String j2Cartao;

    private TimeDAO tdao;
    private CampeonatoDAO cdao;
    private PartidaDAO pdao;

    private List<Time> times;
    private Time time;
    private Time adversario;

    private double despesas;

    private Campeonato campeonato;
    private double recebimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_config_time);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        Bundle dados = getIntent().getExtras();
        indiceTime = dados.getInt("indiceTime");
        idCasa = dados.getInt("idCasa");
        idVisit = dados.getInt("idVisit");
        despesas = dados.getDouble("despesas");

        SQLiteDatabase db = openOrCreateDatabase("brasfoot", MODE_PRIVATE, null);
        tdao = new SQLTimeDAO(db);
        cdao = new SQLCampeonatoDAO(db);
        pdao = new SQLPartidaDAO(db);

        times = tdao.listar();
        campeonato = cdao.pesquisar();
        time = campeonato.getT();
        adversario = tdao.pesquisar(Regras.getIndicesPorTime().get(Regras.getAdversario(Regras.nomeTime[time.getTimeid()], campeonato.getRodada())));

        recebimentos();

        scoreCasa = (TextView)findViewById(R.id.scoreCasa);
        escudoCasa = (ImageView)findViewById(R.id.escudoCasa);
        scoreVisitante = (TextView)findViewById(R.id.scoreVisitante);
        escudoVisitante = (ImageView)findViewById(R.id.escudoVisitante);
        infoTime = (TextView)findViewById(R.id.infoTime);
        tFormacao = (TextView)findViewById(R.id.tFormacao);
        seekFormacao = (SeekBar)findViewById(R.id.seekFormacao);
        tabela = (TableLayout)findViewById(R.id.tabela);
        btVoltar = (Button)findViewById(R.id.btVoltar);
        btJogar = (Button)findViewById(R.id.btJogar);

        if(indiceTime == idCasa){
            //dados do time casa
            scoreCasa.setText("Score atual: "+time.getAtributos()+" pontos");
            escudoCasa.setImageResource(timesLogo[indiceTime]);
            //dados do time visitante
            scoreVisitante.setText("Score atual: "+adversario.getAtributos()+" pontos");
            escudoVisitante.setImageResource(timesLogo[adversario.getTimeid()]);
        } else {
            //dados do time casa
            scoreCasa.setText("Score atual: "+adversario.getAtributos()+" pontos");
            escudoCasa.setImageResource(timesLogo[adversario.getTimeid()]);
            //dados do time visitante
            scoreVisitante.setText("Score atual: "+time.getAtributos()+" pontos");
            escudoVisitante.setImageResource(timesLogo[indiceTime]);
        }

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //+++++++++++++++++ FALTA PERSISTIR A ALTERAÇÃO PARA INFLUENCIAR NO JOGO +++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //implementações da seekbar
        seekFormacao.setProgress(2);
        seekFormacao.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Esquema e = Esquema.values()[progress];
                tFormacao.setText(String.valueOf(e.getAtaque())+"/"+String.valueOf(e.getDefesa())+" - "+String.valueOf(e.getNome()) );
                time.setEsquema(e);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        tabela.removeAllViews();
        int tabJog = 20;
        int numJogador = 0;
        for(int i=0; i <= tabJog; i++) {
            if(i==0 || i==1 || i==13){
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
        adverClassif = classifTime(adversario.getTimeid());
        btJogarClick();
        btVoltarClick();
        visualizaTimeClick();
        montaStrings(adversario.getTimeid(), adversario.getPontos(), Regras.getAdversario(nomeTime[indiceTime], campeonato.getRodada()+1), adverClassif );
    }

    public int classifTime(int idTime){
        int classif = 0;
        for (Time t: times) {
            classif++;
            if(t.getTimeid() == idTime){
                break;
            }
        }
        return classif;
    }

    public void montaStrings(int time, int pontos, String proxAdversario, int adverClassif){
        String resultado;
        String proxLocal = "";

        if(Regras.isCasa(proxAdversario, campeonato.getRodada()+1))
            proxLocal = "fora";
        else
            proxLocal = "casa";

        resultado = "Colocação: "+adverClassif+" ("+pontos+" pts)\nPróximo jogo: "
                +proxAdversario+" ("+proxLocal+")";
        infoTime.setText(resultado);
    }

    private void btVoltarClick() {
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(actConfigTime.this, actManager.class));
                finish();
            }
        });
    }

    private void btJogarClick() {
        btJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(actConfigTime.this, actDelayJogo.class);
                Bundle dados = new Bundle();
                //Data to bundle
                dados.putInt("indiceTime", indiceTime);
                it.putExtras(dados);
                startActivity(it);
                time.setSaldo(time.getSaldo() - despesas + recebimento);
                tdao.editar(time);
                finish();
            }
        });
    }

    private void recebimentos() {
        double ingresso = campeonato.getIngresso();
        int classificacao = 1;
        for (Time t : tdao.listar()) {
            if (t.getTimeid() == campeonato.getT().getTimeid()) {
                break;
            }
            classificacao++;
        }
        int publico = Regras.getPublico(classificacao);
        recebimento = publico * ingresso;

    }

    public void visualizaTimeClick() {
        if (idCasa == indiceTime){
            escudoVisitante.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent it = new Intent(actConfigTime.this, actConsultaTime.class);
                Bundle dados = new Bundle();
                //Data to bundle
                    dados.putInt("adverIdTime", adversario.getTimeid());
                    dados.putInt("adverClassi", adverClassif);
                    dados.putInt("adverPontos", adversario.getPontos());
                    dados.putString("adverProximo", Regras.getAdversario(nomeTime[adversario.getTimeid()], campeonato.getRodada() + 1) );
                it.putExtras(dados);
                startActivity(it);
                }
            });
        }else{
            escudoCasa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent it = new Intent(actConfigTime.this, actConsultaTime.class);
                Bundle dados = new Bundle();
                    //Data to bundle
                    dados.putInt("adverIdTime", adversario.getTimeid());
                    dados.putInt("adverClassi", adverClassif);
                    dados.putInt("adverPontos", adversario.getPontos());
                    dados.putString("adverProximo", Regras.getAdversario(nomeTime[adversario.getTimeid()], campeonato.getRodada() + 1) );
                    it.putExtras(dados);
                startActivity(it);
                }
            });
        }

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
                        tr1Posi = (TextView) tr1.getChildAt(2);
                        tr1Idade = (TextView) tr1.getChildAt(3);
                        tr1Cartao = (TextView) tr1.getChildAt(4);
                        j1Score = tr1Score.getText().toString();
                        j1Nome = tr1Nome.getText().toString();
                        j1Posi = tr1Posi.getText().toString();
                        j1Idade = tr1Idade.getText().toString();
                        j1Cartao = tr1Cartao.getText().toString();

                        //PRIMEIRO JOGADOR JÁ ESTÁ SELECIONADO, verifica se está deselecionando ou trocando
                    } else if (linhasSelect == 1) {
                        idLine02 = view.getId();

                        tr2 = (TableRow) view;
                        tr2Score = (TextView) tr2.getChildAt(0);
                        tr2Nome = (TextView) tr2.getChildAt(1);
                        tr2Posi = (TextView) tr2.getChildAt(2);
                        tr2Idade = (TextView) tr2.getChildAt(3);
                        tr2Cartao = (TextView) tr2.getChildAt(4);
                        j2Score = tr2Score.getText().toString();
                        j2Nome = tr2Nome.getText().toString();
                        j2Posi = tr2Posi.getText().toString();
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
                            tr1Posi.setText(j2Posi);
                            tr1Idade.setText(j2Idade);
                            tr1Cartao.setText(j2Cartao);

                            tr2Score.setText(j1Score);
                            tr2Nome.setText(j1Nome);
                            tr2Posi.setText(j1Posi);
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
