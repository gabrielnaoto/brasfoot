package com.example.tonied.futmanddm.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import com.example.tonied.futmanddm.modelo.entidade.Jogador;

/**
 * Created by ignoi on 26/10/2016.
 */

public interface JogadorDAO {

    void inserir(Jogador o);

    void editar(Jogador o);

    Jogador pesquisar(int o);

    List<Jogador> listar();

    void remover(int id);
}
