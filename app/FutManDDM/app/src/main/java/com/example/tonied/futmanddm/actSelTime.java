package com.example.tonied.futmanddm;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;

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

    private int indiceTime = 0;
    private int indicePatr = 0;

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

    int[] patrocinadores = {
            R.drawable.p5nike,
            R.drawable.p5adidas,
            R.drawable.p4puma,
            R.drawable.p4reebok,
            R.drawable.p3mizuno,
            R.drawable.p3underarmour,
            R.drawable.p3umbro,
            R.drawable.p2lotto,
            R.drawable.p2olympikus,
            R.drawable.p1topper,
            R.drawable.p1penalty
        };

    String[] valores = {"1kk","900k","800k","750k","600k","700k","750k","800k","450k", "800k", "350k"};
    String[] ingressos = {"80","85","70","65","60","50","50","45","50", "40", "35"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_sel_time);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        texto01 = (TextView)findViewById(R.id.Info01);
        escudo = (ImageView)findViewById(R.id.escudo);
        timeLeft =(ImageButton)findViewById(R.id.timeLeft);
        timeRight =(ImageButton)findViewById(R.id.timeRight);

        patrocinio = (ImageView)findViewById(R.id.patrocinio);
        patrLeft =(ImageButton)findViewById(R.id.patrLeft);
        patrRight =(ImageButton)findViewById(R.id.patrRight);
        texto02 = (TextView)findViewById(R.id.Info02);

        escudo.setImageResource(times[indiceTime]);
        patrocinio.setImageResource(patrocinadores[indicePatr]);
        texto01.setText("Score de "+scores[indiceTime]+" pontos");
        texto02.setText("Investimento R$"+valores[indicePatr]+"\nIngressos R$ "+ingressos[indicePatr]);

        bTreinar = (ImageView)findViewById(R.id.bTreinar);

        timeLeftClick();
        timeRightClick();
        patrLeftClick();
        patrRightClick();
        bTreinarClick();
    }

    public void bTreinarClick(){
        bTreinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(actSelTime.this, actManager.class);
                Bundle dados = new Bundle();
                //Data to bundle
                dados.putInt("indiceTime", indiceTime);
                dados.putInt("indicePatr", indicePatr);
                it.putExtras(dados);
                startActivity(it);
            }
        });
    }


    public void timeLeftClick() {
        timeLeft.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( indiceTime==0){
                        indiceTime = times.length-1;
                    } else {
                        indiceTime =  indiceTime % times.length;
                        indiceTime--;
                    }
                    escudo.setImageResource(times[indiceTime]);
                    texto01.setText("Score de "+scores[indiceTime]+" pontos");
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
                indiceTime =  indiceTime % times.length;
                escudo.setImageResource(times[indiceTime]);
                texto01.setText("Score de "+scores[indiceTime]+" pontos");
                }
            }
        );
    }

    public void patrLeftClick() {
        patrLeft.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if (indicePatr==0){
                    indicePatr = patrocinadores.length-1;
                } else {
                    indicePatr =  indicePatr % patrocinadores.length;
                    indicePatr--;
                }
                patrocinio.setImageResource(patrocinadores[indicePatr]);
                texto02.setText("Investimento R$"+valores[indicePatr]+"\nIngressos R$ "+ingressos[0]);
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
                indicePatr =  indicePatr % patrocinadores.length;
                patrocinio.setImageResource(patrocinadores[indicePatr]);
                texto02.setText("Investimento R$"+valores[indicePatr]+"\nIngressos R$ "+ingressos[indicePatr]);
                }
            }
        );
    }
}
