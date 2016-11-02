package br.udesc.ddm.brasfoot.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import br.udesc.ddm.brasfoot.modelo.dao.sqlite.SQLFactoryDAO;
import br.udesc.ddm.brasfoot.modelo.entidade.Patrocinador;

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
