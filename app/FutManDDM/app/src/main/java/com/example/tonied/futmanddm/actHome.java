package com.example.tonied.futmanddm;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class actHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_home);

        ActionBar ac = getSupportActionBar();
        ac.hide();

    }

    public void novo(View v){
        Toast.makeText(getApplicationContext(), "novo", Toast.LENGTH_SHORT).show();
        deleteDatabase("brasfoot");
        importar();

        //chama intent seltime
    }

    public void continuar(View v){
        Toast.makeText(getApplicationContext(), "continuar", Toast.LENGTH_SHORT).show();
        //chama intent manager
    }

    public void importar(){

    }
}
