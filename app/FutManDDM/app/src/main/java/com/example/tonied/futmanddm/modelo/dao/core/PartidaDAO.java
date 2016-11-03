package br.udesc.ddm.brasfoot.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.udesc.ddm.brasfoot.modelo.entidade.Partida;

/**
 * Created by ignoi on 26/10/2016.
 */

public abstract class PartidaDAO {

    protected SQLiteDatabase db;

    public PartidaDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public abstract void inserir(Partida o);

    public abstract Partida pesquisar(int o);

    public abstract List<Partida> listar();

}
