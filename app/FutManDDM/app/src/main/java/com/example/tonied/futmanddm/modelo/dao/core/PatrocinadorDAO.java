package br.udesc.ddm.brasfoot.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.udesc.ddm.brasfoot.modelo.entidade.Patrocinador;

/**
 * Created by ignoi on 26/10/2016.
 */

public abstract class PatrocinadorDAO {

    protected SQLiteDatabase db;

    public PatrocinadorDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public abstract void inserir(Patrocinador o);

    public abstract void editar(Patrocinador o);

    public abstract Patrocinador pesquisar(int o);

    public abstract List<Patrocinador> listar();

    public abstract void remover(int id);
    
    
}