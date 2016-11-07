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
        Object[] valores = new Object[2];
        valores[0] = o.getCasa().getTimeid();
        valores[1] = o.getVisitante().getTimeid();
        db.execSQL("insert into partida(casa, visitante) values(?,?)", valores);
        for (int i = 0; i < 2; i++) {
            Object[] gols = new Object[3];
            gols[0] = o.getPlacar()[i];
            if (i == 0) {
                gols[1] = o.getCasa().getTimeid();
            } else {
                gols[1] = o.getVisitante().getTimeid();
            }
            gols[2] = o.getPartidaid();
            db.execSQL("insert into gols(gols, timeid, partidaid) values (?,?,?)", gols);
        }
    }



    @Override
    public Partida pesquisar(int o) {
        String[] id = {o + ""};
        Cursor cursor = db.rawQuery("select p.partidaid, g1.gols, g2.gols, p.casaid, p.visitanteid\n" +
                "from partida  as p inner join gols as g1 on p.partidaid = g1.partidaid and p.casaid = g1.timeid left join gols as g2 on p.partidaid = g2.partidaid and p.visitanteid = g2.timeid\n" +
                "where p.partidaid = ?", id);
        int index_casagols = cursor.getColumnIndex("casa_gols");
        int index_vizigols = cursor.getColumnIndex("vizi_gols");
        int index_partidaid = cursor.getColumnIndex("partidaid");
        int index_casaid = cursor.getColumnIndex("casaid");
        int index_visitanteid = cursor.getColumnIndex("visitanteid");
        cursor.moveToFirst();
        Partida p = new Partida(timeDAO.pesquisar(cursor.getInt(index_casaid)), timeDAO.pesquisar(cursor.getInt(index_visitanteid)));
        int[] placar = {cursor.getInt(index_casagols), cursor.getInt(index_vizigols)};
        p.setPlacar(placar);
        p.setPartidaid(cursor.getInt(index_partidaid));
        return p;
    }

    @Override
    public List<Partida> listar() {
        Cursor cursor = db.rawQuery("select p.partidaid, g1.gols, g2.gols, p.casaid, p.visitanteid\n" +
                "from partida  as p inner join gols as g1 on p.partidaid = g1.partidaid and p.casaid = g1.timeid left join gols as g2 on p.partidaid = g2.partidaid and p.visitanteid = g2.timeid\n", null);
        int index_casagols = cursor.getColumnIndex("casa_gols");
        int index_vizigols = cursor.getColumnIndex("vizi_gols");
        int index_partidaid = cursor.getColumnIndex("partidaid");
        int index_casaid = cursor.getColumnIndex("casaid");
        int index_visitanteid = cursor.getColumnIndex("visitanteid");
        cursor.moveToFirst();
        List<Partida> partidas = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            Partida p = new Partida(timeDAO.pesquisar(cursor.getInt(index_casaid)), timeDAO.pesquisar(cursor.getInt(index_visitanteid)));
            int[] placar = {cursor.getInt(index_casagols), cursor.getInt(index_vizigols)};
            p.setPlacar(placar);
            p.setPartidaid(cursor.getInt(index_partidaid));
            partidas.add(p);
        }
        return partidas;
    }


}
