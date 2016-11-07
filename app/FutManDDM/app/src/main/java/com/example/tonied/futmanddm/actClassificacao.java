package com.example.tonied.futmanddm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class actClassificacao extends AppCompatActivity {

    private Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_classificacao);

        btVoltar = (Button)findViewById(R.id.btVoltar);

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

}
