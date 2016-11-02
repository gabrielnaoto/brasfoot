package br.udesc.ddm.brasfoot.modelo.dao.sqlite;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.udesc.ddm.brasfoot.modelo.dao.core.PartidaDAO;
import br.udesc.ddm.brasfoot.modelo.dao.core.TimeDAO;
import br.udesc.ddm.brasfoot.modelo.entidade.Partida;

/**
 * Created by ignoi on 26/10/2016.
 */

public class SQLPartidaDAO extends PartidaDAO {

    public SQLPartidaDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public void inserir(Partida o) {

    }

    @Override
    public void editar(Partida o) {

    }

    @Override
    public Partida pesquisar(int o) {
        return null;
    }

    @Override
    public List<Partida> listar() {
        return null;
    }

    @Override
    public void remover(int id) {

    }
}
