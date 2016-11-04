package com.example.tonied.futmanddm;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> opcoes;
    ArrayAdapter<String> adaptador;
    ListView listaJogos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listaJogos = (ListView) findViewById(R.id.listaJogos);

        opcoes = new ArrayList<String>();

        opcoes.add("Selecionar time");
        opcoes.add("Gerenciar time");
        opcoes.add("Escalar time");
        opcoes.add("Teste Full");
        opcoes.add("Teste HOme");
        opcoes.add("Sair");

        adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, opcoes);
        listaJogos.setAdapter(adaptador);
        listaJogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: selTime();
                        break;
                    case 1: briefTime();
                        break;
                    case 2: configTime();
                        break;
                    case 3: teste();
                        break;
                    case 4: loco();
                        break;
                    case 5: finish();
                        break;
                }
            }
        });
    }

    private void selTime() {
        Intent it = new Intent(MainActivity.this, actSelTime.class);
        startActivity(it);
    }

    private void briefTime() {
        Intent it = new Intent(MainActivity.this, actManager.class);
        Bundle dados = new Bundle();
        //Data to bundle
        dados.putInt("indiceTime", 0);
        dados.putInt("indicePatr", 0);
        it.putExtras(dados);
        startActivity(it);
    }

    private void configTime() {
        Intent it = new Intent(MainActivity.this, actConfigTime.class);
        Bundle dados = new Bundle();
        //Data to bundle
        dados.putInt("indiceTime", 0);
        dados.putInt("indicePatr", 0);
        it.putExtras(dados);
        startActivity(it);
    }

    private void teste() {
        Intent it = new Intent(MainActivity.this, Teste.class);
        startActivity(it);
    }

    private void loco() {
        Intent it = new Intent(MainActivity.this, actHome.class);
        startActivity(it);
    }


}
