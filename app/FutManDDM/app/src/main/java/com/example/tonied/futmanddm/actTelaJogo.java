package com.example.tonied.futmanddm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class actTelaJogo extends AppCompatActivity {

    private Button btOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_tela_jogo);
        btOk = (Button)findViewById(R.id.btOk);

        btOkClick();
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
