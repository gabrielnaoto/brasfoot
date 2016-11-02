package br.udesc.ddm.brasfoot.modelo.dao.sqlite;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.udesc.ddm.brasfoot.modelo.dao.core.EstadioDAO;
import br.udesc.ddm.brasfoot.modelo.entidade.Estadio;

/**
 * Created by ignoi on 26/10/2016.
 */

public class SQLEstadioDAO extends EstadioDAO {

    public SQLEstadioDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public void inserir(Estadio o) {

    }

    @Override
    public void editar(Estadio o) {

    }

    @Override
    public Estadio pesquisar(int o) {
        return null;
    }

    @Override
    public List<Estadio> listar() {
        return null;
    }

    @Override
    public void remover(int id) {

    }
}
