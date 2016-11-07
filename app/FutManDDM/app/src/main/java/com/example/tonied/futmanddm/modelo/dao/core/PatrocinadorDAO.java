package com.example.tonied.futmanddm.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import com.example.tonied.futmanddm.modelo.entidade.Patrocinador;

/**
 * Created by ignoi on 26/10/2016.
 */

public interface PatrocinadorDAO {


    void inserir(Patrocinador o);

    void editar(Patrocinador o);

    Patrocinador pesquisar(int o);

    List<Patrocinador> listar();

    void remover(int id);


}