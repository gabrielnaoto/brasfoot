package com.example.tonied.futmanddm.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import com.example.tonied.futmanddm.modelo.entidade.Estadio;

/**
 * Created by ignoi on 26/10/2016.
 */

public interface EstadioDAO {


    void inserir(Estadio o);

    void editar(Estadio o);

    Estadio pesquisar(int o);

    List<Estadio> listar();

    void remover(int id);

}
