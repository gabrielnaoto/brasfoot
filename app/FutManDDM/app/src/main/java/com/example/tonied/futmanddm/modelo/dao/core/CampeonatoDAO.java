package com.example.tonied.futmanddm.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import com.example.tonied.futmanddm.modelo.entidade.Campeonato;

import java.util.List;

/**
 * Created by ignoi on 06/11/2016.
 */

public interface CampeonatoDAO {

    void inserir(Campeonato o);

    void editar(Campeonato o);

    Campeonato pesquisar();

    List<Campeonato> listar();

    void remover(int id);

}
