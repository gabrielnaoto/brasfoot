package br.udesc.ddm.brasfoot.modelo.dao.sqlite;

import android.database.sqlite.SQLiteDatabase;

import br.udesc.ddm.brasfoot.modelo.dao.core.EstadioDAO;
import br.udesc.ddm.brasfoot.modelo.dao.core.FactoryDAO;
import br.udesc.ddm.brasfoot.modelo.dao.core.JogadorDAO;
import br.udesc.ddm.brasfoot.modelo.dao.core.PartidaDAO;
import br.udesc.ddm.brasfoot.modelo.dao.core.PatrocinadorDAO;
import br.udesc.ddm.brasfoot.modelo.dao.core.TimeDAO;

/**
 * Created by ignoi on 02/11/2016.
 */

public class SQLFactoryDAO extends FactoryDAO{

    public SQLFactoryDAO(SQLiteDatabase db) {
        super(db);
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
