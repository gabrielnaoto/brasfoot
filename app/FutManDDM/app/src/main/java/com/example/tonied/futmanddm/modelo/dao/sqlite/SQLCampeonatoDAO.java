package com.example.tonied.futmanddm.modelo.dao.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tonied.futmanddm.modelo.dao.core.CampeonatoDAO;
import com.example.tonied.futmanddm.modelo.dao.core.EstadioDAO;
import com.example.tonied.futmanddm.modelo.dao.core.PatrocinadorDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Campeonato;

import java.util.ArrayList;
import java.util.List;

public class SQLCampeonatoDAO implements CampeonatoDAO {

    private TimeDAO tdao;
    private EstadioDAO edao;
    private PatrocinadorDAO pdao;
    private SQLiteDatabase db;

    public SQLCampeonatoDAO(SQLiteDatabase db) {
        tdao = new SQLTimeDAO(db);
        edao = new SQLEstadioDAO(db);
        pdao = new SQLPatrocinadorDAO(db);
        this.db = db;
    }

    @Override
    public void inserir(Campeonato o) {
        Object[] valores = new Object[4];
        valores[0] = o.getT().getTimeid();
        valores[1] = o.getE().getEstadioid();
        valores[2] = o.getP().getPatrocinadorid();
        valores[3] = o.getRodada();
        db.execSQL("insert into campeonato (time, estadio, patrocinador, rodada) values(?,?,?,?)", valores);
    }

    @Override
    public void editar(Campeonato o) {
        Object[] valores = new Object[4];
        valores[0] = o.getT().getTimeid();
//        valores[1] = o.getE().getEstadioid();
//        valores[2] = o.getP().getPatrocinadorid();
        valores[3] = o.getRodada();
        db.execSQL("update campeonato set time = ?, estadio = ?, patrocinador = ?, rodada =? ", valores);


    }

    @Override
    public Campeonato pesquisar() {
        Cursor cursor = db.rawQuery("select * from campeonato", null);
        int index_time = cursor.getColumnIndex("time");
        int index_estadio = cursor.getColumnIndex("estadio");
        int index_patrocinador = cursor.getColumnIndex("patrocinador");
        int index_rodada = cursor.getColumnIndex("rodada");
        cursor.moveToFirst();
        Campeonato e = new Campeonato();
        e.setT(tdao.pesquisar(cursor.getInt(index_time)));
//        e.setE(edao.pesquisar(cursor.getInt(index_estadio)));
//        e.setP(pdao.pesquisar(cursor.getInt(index_patrocinador)));
        e.setRodada(cursor.getInt(index_rodada));
        return e;
    }

    @Override
    public List<Campeonato> listar() {
        return null;
    }

    @Override
    public void remover(int id) {

    }
}
