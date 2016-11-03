package com.example.tonied.futmanddm.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import com.example.tonied.futmanddm.modelo.entidade.Estadio;

/**
 * Created by ignoi on 26/10/2016.
 */

public abstract class EstadioDAO {

    protected SQLiteDatabase db;

    public EstadioDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public abstract void inserir(Estadio o);

    public abstract void editar(Estadio o);

    public abstract Estadio pesquisar(int o);

    public abstract List<Estadio> listar();

    public abstract void remover(int id);

}
