package com.example.tonied.futmanddm.modelo.dao.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.tonied.futmanddm.modelo.dao.core.PartidaDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Partida;

public class SQLPartidaDAO implements PartidaDAO {

    private TimeDAO timeDAO;
    private SQLiteDatabase db;

    public SQLPartidaDAO(SQLiteDatabase db) {
        timeDAO = new SQLTimeDAO(db);
        this.db = db;
    }

    @Override
    public void inserir(Partida o) {
        Object[] valores = new Object[5];
        valores[0] = o.getCasa().getTimeid();
        valores[1] = o.getVisitante().getTimeid();
        valores[2] = o.getRodada();
        valores[3] = o.getPlacar()[0];
        valores[4] = o.getPlacar()[1];
        db.execSQL("insert into partida(casaid, visitanteid, rodada, golcasa, golvizi) values(?,?, ?, ?, ?)", valores);
    }


    @Override
    public List<Partida> listarPorTime(int o) {
        String[] id = {o + "", o + ""};
        Cursor resultado = db.rawQuery("SELECT * FROM partida WHERE casaid = ? or visitanteid = ? order by rodada", id);
        int index_casagols = resultado.getColumnIndex("golcasa");
        int index_vizigols = resultado.getColumnIndex("golvizi");
        int index_partidaid = resultado.getColumnIndex("partidaid");
        int index_casaid = resultado.getColumnIndex("casaid");
        int index_visitanteid = resultado.getColumnIndex("visitanteid");
        int index_rodada = resultado.getColumnIndex("rodada");
        resultado.moveToFirst();
        List<Partida> partidas = new ArrayList<>();
        while (!resultado.isAfterLast()) {
            int indicec = resultado.getInt(index_casaid);
            int indicev = resultado.getInt(index_visitanteid);
            Partida p = new Partida(timeDAO.pesquisar(indicec), timeDAO.pesquisar(indicev));
            int[] placar = new int[2];
            placar[0] = resultado.getInt(index_casagols);
            placar[1] = resultado.getInt(index_vizigols);
            p.setPlacar(placar);
            p.setPartidaid(resultado.getInt(index_partidaid));
            p.setRodada(resultado.getInt(index_rodada));
            partidas.add(p);
            resultado.moveToNext();
        }
        return partidas;

    }

    @Override
    public List<Partida> listarPorRodada(int o) {
        String[] id = {o + ""};
        Cursor resultado = db.rawQuery("SELECT * FROM partida WHERE rodada = ?", id);
        int index_casagols = resultado.getColumnIndex("golcasa");
        int index_vizigols = resultado.getColumnIndex("golvizi");
        int index_partidaid = resultado.getColumnIndex("partidaid");
        int index_casaid = resultado.getColumnIndex("casaid");
        int index_visitanteid = resultado.getColumnIndex("visitanteid");
        int index_rodada = resultado.getColumnIndex("rodada");
        List<Partida> partidas = new ArrayList<>();
        resultado.moveToFirst();
        while (!resultado.isAfterLast()) {
            int indicec = resultado.getInt(index_casaid);
            int indicev = resultado.getInt(index_visitanteid);
            Partida p = new Partida(timeDAO.pesquisar(indicec), timeDAO.pesquisar(indicev));
            int[] placar = new int[2];
            placar[0] = resultado.getInt(index_casagols);
            placar[1] = resultado.getInt(index_vizigols);
            p.setPlacar(placar);
            p.setPartidaid(resultado.getInt(index_partidaid));
            p.setRodada(resultado.getInt(index_rodada));
            partidas.add(p);
            resultado.moveToNext();
        }
        return partidas;
    }


}
