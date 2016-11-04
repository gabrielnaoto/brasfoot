package com.example.tonied.futmanddm.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLFactoryDAO;
import com.example.tonied.futmanddm.modelo.entidade.Patrocinador;

/**
 * Created by ignoi on 02/11/2016.
 */

public abstract class FactoryDAO {

    protected SQLiteDatabase db;

    public FactoryDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public abstract EstadioDAO getEstadoDAO();

    public abstract JogadorDAO getJogadorDAO();

    public abstract PartidaDAO getPartidaDAO();

    public abstract PatrocinadorDAO getPatrocinadorDAO();

    public abstract TimeDAO getTimeDAO();

    public FactoryDAO getFactoryDAO (){
        return new SQLFactoryDAO(db);
    }

}
