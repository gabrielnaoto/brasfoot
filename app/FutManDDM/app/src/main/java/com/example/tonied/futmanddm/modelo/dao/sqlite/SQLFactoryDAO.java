package com.example.tonied.futmanddm.modelo.dao.sqlite;

import android.database.sqlite.SQLiteDatabase;

import com.example.tonied.futmanddm.modelo.dao.core.EstadioDAO;
import com.example.tonied.futmanddm.modelo.dao.core.FactoryDAO;
import com.example.tonied.futmanddm.modelo.dao.core.JogadorDAO;
import com.example.tonied.futmanddm.modelo.dao.core.PartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.core.PatrocinadorDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;

/**
 * Created by ignoi on 02/11/2016.
 */

public class SQLFactoryDAO implements FactoryDAO{

    private SQLiteDatabase db;

    public SQLFactoryDAO(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public EstadioDAO getEstadoDAO() {
        return new SQLEstadioDAO(db);
    }

    @Override
    public JogadorDAO getJogadorDAO() {
        return new SQLJogadorDAO(db);
    }

    @Override
    public PartidaDAO getPartidaDAO() {
        return new SQLPartidaDAO(db);
    }

    @Override
    public PatrocinadorDAO getPatrocinadorDAO() {
        return new SQLPatrocinadorDAO(db);
    }

    @Override
    public TimeDAO getTimeDAO() {
        return new SQLTimeDAO(db);
    }
}
