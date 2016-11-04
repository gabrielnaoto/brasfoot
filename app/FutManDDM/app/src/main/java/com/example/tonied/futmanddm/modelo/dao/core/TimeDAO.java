package com.example.tonied.futmanddm.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import com.example.tonied.futmanddm.modelo.entidade.Time;

/**
 * Created by ignoi on 26/10/2016.
 */

public abstract class TimeDAO {

    protected SQLiteDatabase db;

    public TimeDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public abstract void inserir(Time o);

    public abstract void editar(Time o);

    public abstract Time pesquisar(int o);

    public abstract List<Time> listar();

    public abstract void remover(int id);
}
