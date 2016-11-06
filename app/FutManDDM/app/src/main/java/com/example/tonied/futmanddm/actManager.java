package com.example.tonied.futmanddm;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class actManager extends AppCompatActivity {
    //informações cabeçalho
    private ImageView escudo;
    private TextView info00;
    private TextView info00n;   //score atual e novo
    private TextView info01;    //classificação + pontos
    private TextView info02;    //moral do time
    private TextView info03;    //caixa
    private TextView info04;    //despesas

    // ajustes do time - switches
    private Switch sw01;
    private Switch sw02;
    private Switch sw03;
    private Switch sw04;
    private ImageView hint01;
    private ImageView hint02;
    private ImageView hint03;
    private ImageView hint04;

    //últimas rodadas rodadas
    //cabeçalhos
    private TextView rod01;
    private TextView rod02;
    private TextView rod03;
    //escudos
    private ImageView ej01;
    private ImageView ej02;
    private ImageView ej03;
    //resultados
    private TextView res01;
    private TextView res02;
    private TextView res03;

    //tabela classificacao
    private ImageView ec01;
    private ImageView ec02;
    private ImageView ec03;
    private ImageView ec04;
    // times
    private TextView tc01;
    private TextView tc02;
    private TextView tc03;
    private TextView tc04;
    //pontos
    private TextView pc01;
    private TextView pc02;
    private TextView pc03;
    private TextView pc04;
    private Button btClassif;

    //proximo jogo
    private TextView tAdversario;
    private ImageView eAdversario;
    private Button btEscalar;

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

    //informações EXTERNAS a activity
    int[] scores = {81,83,78,89,79,85,84,77};
    String[] stScores = {"85","83","78","89","79"};
    Double[] valPatr = {1000000.0,900000.0,800000.0,750000.0,600000.0,700000.0,750000.0,800000.0,450000.0, 800000.0, 350000.0};
    String[] tabClass = {"12","11","9","8","7"};


    int indiceTime;
    int indicePatr;
    String XatuScore = stScores[indiceTime];
    int idScore;
    int atuScore;
    static int inc01;
    static int inc02;
    static int inc03;
    static int inc04;
    static int idClassif;
    static int idPontos;
    static int idMoral;
    static double idCaixa;
    static double idDespesa;
    static int idAdver = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_manager);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        escudo = (ImageView)findViewById(R.id.escudo);
        eAdversario = (ImageView)findViewById(R.id.eAdversario);
        tAdversario = (TextView)findViewById(R.id.tAdversario);

        info00 = (TextView) findViewById(R.id.info00);
        info00n = (TextView) findViewById(R.id.info00n);
        info01 = (TextView) findViewById(R.id.info01);
        info02 = (TextView) findViewById(R.id.info02);
        info03 = (TextView) findViewById(R.id.info03);
        info04 = (TextView) findViewById(R.id.info04);

        hint01 = (ImageView) findViewById(R.id.hint01);
        hint02 = (ImageView) findViewById(R.id.hint02);
        hint03 = (ImageView) findViewById(R.id.hint03);
        hint04 = (ImageView) findViewById(R.id.hint04);
        sw01 = (Switch)findViewById(R.id.sw01);
        sw02 = (Switch)findViewById(R.id.sw02);
        sw03 = (Switch)findViewById(R.id.sw03);
        sw04 = (Switch)findViewById(R.id.sw04);

        rod01 = (TextView) findViewById(R.id.rod01);
        rod02 = (TextView) findViewById(R.id.rod02);
        rod03 = (TextView) findViewById(R.id.rod03);
        ej01 = (ImageView) findViewById(R.id.ej01);
        ej02 = (ImageView) findViewById(R.id.ej02);
        ej03 = (ImageView) findViewById(R.id.ej03);
        res01 = (TextView) findViewById(R.id.res01);
        res02 = (TextView) findViewById(R.id.res02);
        res03 = (TextView) findViewById(R.id.res03);

        ec01 = (ImageView)findViewById(R.id.ec01);
        ec02 = (ImageView)findViewById(R.id.ec02);
        ec03 = (ImageView)findViewById(R.id.ec03);
        ec04 = (ImageView)findViewById(R.id.ec04);
        tc01 = (TextView) findViewById(R.id.tc01);
        tc02 = (TextView) findViewById(R.id.tc02);
        tc03 = (TextView) findViewById(R.id.tc03);
        tc04 = (TextView) findViewById(R.id.tc04);
        pc01 = (TextView) findViewById(R.id.pc01);
        pc02 = (TextView) findViewById(R.id.pc02);
        pc03 = (TextView) findViewById(R.id.pc03);
        pc04 = (TextView) findViewById(R.id.pc04);

        btEscalar = (Button)findViewById(R.id.btEscalar);
        btClassif = (Button)findViewById(R.id.btClassif);

        indiceTime = 0;
        indicePatr = 0;
        idScore = scores[indiceTime];
        atuScore = idScore;

        buscaDadosExt();
        cargaInfos();
        cargaJogos(1);
        cargaClassif();
        btEscalarClick();
    }

    public void buscaDadosExt(){
        inc01 = 0;
        inc02 = 0;
        inc03 = 0;
        inc04 = 0;
        idClassif = 1;
        idPontos = 12;
        idMoral = 70;
        idCaixa = valPatr[indicePatr];
        idDespesa = 0;
        //id do adversario do proximo jogo
        idAdver = 4;
    }

    public void cargaJogos(int rodAtual){
        //sequencia dos 3 últimos adversários
        int[] advJogos = {1, 2, 3};

        //sequencia dos placares dos 3 ultimos jogos, pode carregar 0 se não houve
        String[] placarC = {"3", "2", "1"};
        String[] placarF = {"1", "0", "0"};
        //dados: colocacao - pontos
        String[] dadosAdver = {"2", "11"};
        //locais dos jogos - sequencia completa do time
        String[] locais = {"casa", "fora", "casa", "fora"};

        if (rodAtual==0){
            ej01.setImageResource(R.drawable.esemtime);
            ej02.setImageResource(R.drawable.esemtime);
            ej03.setImageResource(R.drawable.esemtime);
            rod01.setText("");
            rod02.setText("");
            rod03.setText("");
            res01.setText("  X  ");
            res02.setText("  X  ");
            res03.setText("  X  ");

        }else if (rodAtual==1){
            ej01.setImageResource(times[advJogos[0]]);
            ej02.setImageResource(R.drawable.esemtime);
            ej03.setImageResource(R.drawable.esemtime);
            rod01.setText("Rodada 1 - "+locais[1]);
            rod02.setText("");
            rod03.setText("");
            res01.setText(placarC[0]+" X "+placarF[0]);
            res02.setText("  X  ");
            res03.setText("  X  ");

        }else if (rodAtual==2){
            ej01.setImageResource(times[advJogos[0]]);
            ej02.setImageResource(times[advJogos[1]]);
            ej03.setImageResource(R.drawable.esemtime);
            rod01.setText("Rodada 1 - "+locais[0]);
            rod02.setText("Rodada 2 - "+locais[1]);
            rod03.setText("");
            res01.setText(placarC[0]+" X "+placarF[0]);
            res02.setText(placarC[1]+" X "+placarF[1]);
            res03.setText("  X  ");

        }else if (rodAtual==3){
            ej01.setImageResource(times[advJogos[0]]);
            ej02.setImageResource(times[advJogos[1]]);
            ej03.setImageResource(times[advJogos[2]]);
            rod01.setText("Rodada 1 - "+locais[0]);
            rod02.setText("Rodada 2 - "+locais[1]);
            rod03.setText("Rodada 3 - "+locais[2]);
            res01.setText(placarC[0]+" X "+placarF[0]);
            res02.setText(placarC[1]+" X "+placarF[1]);
            res03.setText(placarC[2]+" X "+placarF[2]);

        } else if (rodAtual>=4){
            ej01.setImageResource(times[advJogos[0]]);
            ej02.setImageResource(times[advJogos[1]]);
            ej03.setImageResource(times[advJogos[2]]);
            rod01.setText("Rodada "+(rodAtual-3)+locais[(rodAtual-4)]);
            rod02.setText("Rodada "+(rodAtual-2)+locais[(rodAtual-3)]);
            rod03.setText("Rodada "+(rodAtual-1)+locais[(rodAtual-2)]);
            res01.setText(placarC[0]+" X "+placarF[0]);
            res02.setText(placarC[1]+" X "+placarF[1]);
            res03.setText(placarC[2]+" X "+placarF[2]);
        }
        eAdversario.setImageResource(times[idAdver]);

        String localiz = "";
        if(locais[rodAtual].equalsIgnoreCase("casa"))
            localiz = "Casa";
        else
            localiz = "Fora";
        tAdversario.setText(localiz+" - "+dadosAdver[0]+"º ("+dadosAdver[1]+" pts) - "+scores[idAdver]);
    }

    public void cargaInfos(){
        escudo.setImageResource(times[indiceTime]);
        info00.setText(XatuScore);
        atualizaScore();
        info01.setText(idClassif+"º ("+idPontos+" pts)");
        info02.setText(idMoral+"%");
        info03.setText("R$ "+idCaixa+"0");
        info04.setText("R$ "+idDespesa+"0");
    }

    public void atualizaScore(){
        info00n.setText(XatuScore);//(atuScore+inc01+inc02+inc03+inc04));
    }

    public void cargaClassif(){
        ec01.setImageResource(times[indiceTime]);
        ec02.setImageResource(times[1]);
        ec03.setImageResource(times[2]);
        ec04.setImageResource(times[3]);
        tc01.setText(nomeTime[indiceTime]);
        tc02.setText(nomeTime[1]);
        tc03.setText(nomeTime[2]);
        tc04.setText(nomeTime[3]);
        pc01.setText(tabClass[0]);
        pc02.setText(tabClass[1]);
        pc03.setText(tabClass[2]);
        pc04.setText(tabClass[3]);
    }

    public void onClickHint01(View v){
        Toast.makeText(this, "Custo de R$ 25.000,00 por partida\nAumento de 2% no Score para a partida", Toast.LENGTH_SHORT).show();
    }

    public void onCLickHint02(View v){
        Toast.makeText(this, "Custo de R$ 10.000,00 por partida\nAumento CUMULATIVO de 2% no Score por semana", Toast.LENGTH_SHORT).show();
    }

    public void onCLickHint03(View v){
        Toast.makeText(this, "Custo de R$ 8.000,00 por partida\nAumento CUMULATIVO de 1% no Score por semana", Toast.LENGTH_SHORT).show();
    }

    public void onCLickHint04(View v){
        Toast.makeText(this, "Custo de R$ 15.000,00 por partida\nGarante a recuperação plena do jogador, sem perda de score", Toast.LENGTH_SHORT).show();
    }

    public void onClickSw01(View v){
        if (sw01.isChecked()){
            idDespesa+=25000;
            inc01 = (int) (atuScore * 0.02);
        } else {
            idDespesa-=25000;
            inc01 = 0;
        }
        info04.setText("R$ "+idDespesa+"0");
        atualizaScore();
    }
    public void onClickSw02(View v){
        if (sw02.isChecked()){
            idDespesa+=10000;
            inc02 = (int) (atuScore * 0.02);
        } else {
            idDespesa-=10000;
            inc02 = 0;
        }
        info04.setText("R$ "+idDespesa+"0");
        atualizaScore();
    }
    public void onClickSw03(View v){
        if (sw03.isChecked()){
            idDespesa+=8000;
            inc03 = (int) (atuScore * 0.01);
        } else {
            idDespesa-=8000;
            inc03 = 0;
        }
        info04.setText("R$ "+idDespesa+"0");
        atualizaScore();
    }
    public void onClickSw04(View v){
        if (sw04.isChecked()){
            idDespesa+=15000;
        } else {
            idDespesa-=15000;
        }
        info04.setText("R$ "+idDespesa+"0");
        atualizaScore();
    }

    public void btEscalarClick(){
        btEscalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(actManager.this, actConfigTime.class);
                Bundle dados = new Bundle();
                //Data to bundle
                dados.putInt("indiceTime", indiceTime);
                dados.putInt("indicePatr", indicePatr);
                it.putExtras(dados);
                startActivity(it);
            }
        });
    }
}
