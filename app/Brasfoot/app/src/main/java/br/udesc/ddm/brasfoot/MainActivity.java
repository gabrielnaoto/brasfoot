package br.udesc.ddm.brasfoot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import br.udesc.ddm.brasfoot.modelo.entidade.Time;
import br.udesc.ddm.brasfoot.util.ImportarArquivo;

public class MainActivity extends AppCompatActivity {

    private TextView texto;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
    }

    public void carregar(View v){
        try {
            ImportarArquivo i = new ImportarArquivo(getResources().openRawResource(R.raw.jogadores));
            List<Time> times = i.importar();
            for (int j = 0; j < times.size(); j++) {
                Toast.makeText(getApplicationContext(), times.get(j).getNome(), Toast.LENGTH_LONG).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
