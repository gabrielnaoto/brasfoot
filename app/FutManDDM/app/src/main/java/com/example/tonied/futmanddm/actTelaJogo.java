package com.example.tonied.futmanddm;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class actTelaJogo extends AppCompatActivity {

    private Button btOk;
    private ImageView p1escCasa;
    private ImageView p2escCasa;
    private ImageView p3escCasa;
    private ImageView p4escCasa;
    private ImageView p1escVisit;
    private ImageView p2escVisit;
    private ImageView p3escVisit;
    private ImageView p4escVisit;
    private TextView p1plCasa;
    private TextView p2plCasa;
    private TextView p3plCasa;
    private TextView p4plCasa;
    private TextView p1plVisit;
    private TextView p2plVisit;
    private TextView p3plVisit;
    private TextView p4plVisit;
    private TextView rodada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_tela_jogo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        p1escCasa = (ImageView)findViewById(R.id.p1escCasa);
        p2escCasa = (ImageView)findViewById(R.id.p2escCasa);
        p3escCasa = (ImageView)findViewById(R.id.p3escCasa);
        p4escCasa = (ImageView)findViewById(R.id.p4escCasa);
        p1escVisit = (ImageView)findViewById(R.id.p1escVisit);
        p2escVisit = (ImageView)findViewById(R.id.p2escVisit);
        p3escVisit = (ImageView)findViewById(R.id.p3escVisit);
        p4escVisit = (ImageView)findViewById(R.id.p4escVisit);
        p1plCasa = (TextView)findViewById(R.id.p1plCasa);
        p2plCasa = (TextView)findViewById(R.id.p2plCasa);
        p3plCasa = (TextView)findViewById(R.id.p3plCasa);
        p4plCasa = (TextView)findViewById(R.id.p4plCasa);
        p1plVisit = (TextView)findViewById(R.id.p1plVisit);
        p2plVisit = (TextView)findViewById(R.id.p2plVisit);
        p3plVisit = (TextView)findViewById(R.id.p3plVisit);
        p4plVisit = (TextView)findViewById(R.id.p4plVisit);
        rodada = (TextView)findViewById(R.id.rodada);
        btOk = (Button)findViewById(R.id.btOk);

        btOkClick();
        cargaJogos(1);
    }

    private void cargaJogos(int r){
        rodada.setText("Resultados da "+r+"ª Rodada");
    }

    private void btOkClick() {
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
