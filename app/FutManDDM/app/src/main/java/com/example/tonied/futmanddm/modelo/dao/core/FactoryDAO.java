package com.example.tonied.futmanddm.modelo.dao.core;

import android.database.sqlite.SQLiteDatabase;

import com.example.tonied.futmanddm.modelo.dao.sqlite.SQLFactoryDAO;
import com.example.tonied.futmanddm.modelo.entidade.Patrocinador;

/**
 * Created by ignoi on 02/11/2016.
 */

public interface FactoryDAO {

    EstadioDAO getEstadoDAO();

    JogadorDAO getJogadorDAO();

    PartidaDAO getPartidaDAO();

    PatrocinadorDAO getPatrocinadorDAO();

    TimeDAO getTimeDAO();

}
