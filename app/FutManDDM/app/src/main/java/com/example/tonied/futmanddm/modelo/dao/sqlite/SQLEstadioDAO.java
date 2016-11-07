package com.example.tonied.futmanddm.modelo.dao.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.tonied.futmanddm.modelo.dao.core.EstadioDAO;
import com.example.tonied.futmanddm.modelo.entidade.Estadio;

public class SQLEstadioDAO implements EstadioDAO {

    private SQLiteDatabase db;

    public SQLEstadioDAO(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public void inserir(Estadio o) {
        Object[] valores = new Object[3];
        valores[0] = o.getEstrelas();
        valores[1] = o.getIngresso();
        valores[2] = o.getPublico();
        db.execSQL("insert into estadio (estrelas, ingresso, publico) values(?,?,?)", valores);
    }

    @Override
    public void editar(Estadio o) {
        Object[] valores = new Object[3];
        valores[0] = o.getEstrelas();
        valores[1] = o.getIngresso();
        valores[2] = o.getPublico();
        db.execSQL("update estadio estrelas = ?, ingresso = ?, publico ?, where estadioid = ?", valores);
    }

    @Override
    public Estadio pesquisar(int o) {
        String[] id = {Integer.toString(o)};
        Cursor cursor = db.rawQuery("select * from estadio where estadioid = ?", id);
        int index_estadioid = cursor.getColumnIndex("estadioid");
        int index_estrelas = cursor.getColumnIndex("estrelas");
        int index_ingresso = cursor.getColumnIndex("ingresso");
        int index_publico = cursor.getColumnIndex("publico");
        cursor.moveToFirst();
        Estadio e = new Estadio();
        e.setEstadioid(cursor.getInt(index_estadioid));
        e.setEstrelas(cursor.getInt(index_estrelas));
        e.setIngresso(cursor.getDouble(index_ingresso));
        e.setPublico(cursor.getInt(index_publico));
        return e;
    }

    @Override
    public List<Estadio> listar() {
        Cursor cursor = db.rawQuery("select * from estadio", null);
        int index_estadioid = cursor.getColumnIndex("estadioid");
        int index_estrelas = cursor.getColumnIndex("estrelas");
        int index_ingresso = cursor.getColumnIndex("ingresso");
        int index_publico = cursor.getColumnIndex("publico");
        List<Estadio> estadios = new ArrayList<>();
        while (!cursor.isAfterLast()){
            Estadio e = new Estadio();
            e.setEstadioid(cursor.getInt(index_estadioid));
            e.setEstrelas(cursor.getInt(index_estrelas));
            e.setIngresso(cursor.getDouble(index_ingresso));
            e.setPublico(cursor.getInt(index_publico));
            estadios.add(e);
        }
        return estadios;
    }

    @Override
    public void remover(int id) {
        Object[] valores = {id};
        db.execSQL("delete from estadio where estadioid = ?", valores);
    }
}
