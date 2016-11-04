package com.example.tonied.futmanddm;


import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private TableLayout tabela;

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

        montaStrings(4,1,11,2);
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
}
