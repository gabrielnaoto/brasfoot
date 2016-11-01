package br.udesc.ddm.brasfoot.util;

import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.udesc.ddm.brasfoot.modelo.dao.core.JogadorDAO;
import br.udesc.ddm.brasfoot.modelo.dao.core.TimeDAO;
import br.udesc.ddm.brasfoot.modelo.dao.sqlite.SQLJogadorDAO;
import br.udesc.ddm.brasfoot.modelo.dao.sqlite.SQLTimeDAO;
import br.udesc.ddm.brasfoot.modelo.entidade.Jogador;
import br.udesc.ddm.brasfoot.modelo.entidade.Time;

/**
 * Created by ignoi on 30/10/2016.
 */

public class ImportarArquivo {

    private BufferedReader leitor;
    private JogadorDAO jogadorDAO;
    private TimeDAO timeDAO;

    public ImportarArquivo(InputStream arquivo) throws FileNotFoundException {
        this.leitor = new BufferedReader(new InputStreamReader(arquivo));
        this.timeDAO = new SQLTimeDAO();
        this.jogadorDAO = new SQLJogadorDAO();
    }

    public List<Time> importar() throws IOException {
        String linha = "";
        List<Time> times = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            linha = leitor.readLine();
            Time t = new Time();
            t.setNome(linha);
            for (int j = 0; j < 18; j++) {
                linha = leitor.readLine();
                String[] valores = linha.split(",");
                Jogador jogador = new Jogador();
                jogador.setNome(valores[1]);
                jogador.setPosicao(valores[2]);
                jogador.setIdade(Integer.parseInt(valores[3]));
                jogador.setTecnica(Integer.parseInt(valores[4]));
                jogador.setFisico(Integer.parseInt(valores[5]));
                jogador.setInteligentcia(Integer.parseInt(valores[6]));
                jogador.setMotivacao(50);
                jogador = t.adiciongarJogador(jogador);
                jogadorDAO.inserir(jogador);
            }
            timeDAO.inserir(t);
            times.add(t);
        }
        return times;
    }

}
