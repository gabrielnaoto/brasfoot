package br.udesc.ddm.brasfoot.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.udesc.ddm.brasfoot.modelo.entidade.Jogador;

/**
 * Created by ignoi on 26/10/2016.
 */

public abstract class JogadorDAO {

    protected SQLiteDatabase db;

    public JogadorDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public abstract void inserir(Jogador o);

    public abstract void editar(Jogador o);

    public abstract Jogador pesquisar(int o);

    public abstract List<Jogador> listar();

    public abstract void remover(int id);
}
