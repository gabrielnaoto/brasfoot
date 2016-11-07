package com.example.tonied.futmanddm.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import com.example.tonied.futmanddm.modelo.entidade.Partida;

/**
 * Created by ignoi on 26/10/2016.
 */

public interface PartidaDAO {


    void inserir(Partida o);

    Partida pesquisar(int o);

    List<Partida> listar();

}
