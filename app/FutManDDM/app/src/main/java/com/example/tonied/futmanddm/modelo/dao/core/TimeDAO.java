package com.example.tonied.futmanddm.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import com.example.tonied.futmanddm.modelo.entidade.Time;

/**
 * Created by ignoi on 26/10/2016.
 */

public interface TimeDAO {

    void inserir(Time o);

    void editar(Time o);

    Time pesquisar(int o);

    List<Time> listar();

    void remover(int id);
}
