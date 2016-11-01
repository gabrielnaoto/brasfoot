package br.udesc.ddm.trabalho2.util;

import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.udesc.ddm.trabalho2.modelo.dao.core.JogadorDAO;
import br.udesc.ddm.trabalho2.modelo.dao.core.TimeDAO;
import br.udesc.ddm.trabalho2.modelo.dao.sqlite.SQLJogadorDAO;
import br.udesc.ddm.trabalho2.modelo.dao.sqlite.SQLTimeDAO;
import br.udesc.ddm.trabalho2.modelo.entidade.Jogador;
import br.udesc.ddm.trabalho2.modelo.entidade.Time;

/**
 * Created by ignoi on 30/10/2016.
 */

public class ImportarArquivo {

    private File arquivo;
    private BufferedReader leitor;
    private JogadorDAO jogadorDAO;
    private TimeDAO timeDAO;

    public ImportarArquivo(File arquivo) throws FileNotFoundException {
        this.arquivo = arquivo;
        this.leitor = new BufferedReader(new FileReader(arquivo));
        this.timeDAO = new SQLTimeDAO();
        this.jogadorDAO = new SQLJogadorDAO();
    }

    public void importar() throws IOException {
        String linha = "";
        for (int i = 0; i < 10; i++) {
            linha = leitor.readLine();
            Time t = new Time();
            t.setNome(linha);
            for (int j = 0; j < 18; j++) {
                linha = leitor.readLine();
                String[] valores = linha.split(",");
                Jogador jogador = new Jogador();
                jogador.setNome(valores[0]);
                jogador.setPosicao(valores[1]);
                jogador.setIdade(Integer.parseInt(valores[2]));
                jogador.setTecnica(Integer.parseInt(valores[3]));
                jogador.setFisico(Integer.parseInt(valores[4]));
                jogador.setInteligentcia(Integer.parseInt(valores[5]));
                jogador.setMotivacao(50);
                jogador = t.adiciongarJogador(jogador);
                jogadorDAO.inserir(jogador);
            }
            timeDAO.inserir(t);
        }
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }
}
