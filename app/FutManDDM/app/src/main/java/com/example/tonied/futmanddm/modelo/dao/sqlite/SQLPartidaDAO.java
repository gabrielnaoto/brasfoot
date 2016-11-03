package br.udesc.ddm.brasfoot.modelo.dao.sqlite;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.udesc.ddm.brasfoot.modelo.dao.core.PartidaDAO;
import br.udesc.ddm.brasfoot.modelo.dao.core.TimeDAO;
import br.udesc.ddm.brasfoot.modelo.entidade.Partida;

public class SQLPartidaDAO extends PartidaDAO {

    public SQLPartidaDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public void inserir(Partida o) {
        Object valores = new Object[2];
        valores[0] = o.getCasa().getTimeid();
        valores[1] = o.getVisitante().getTimeid();
        db.execSQL("insert into time(casa, visitante) values(?,?)", valores);
        for (int i = 0; i < 2; i++) {
            Object gols = new Object[3];
            gols[0] = o.getPlacar()[i];
            if(i == 0) {
                gols[1] = o.getCasa().getTimeid();
            } else{
                gols[1] = o.getVisitante().getTimeid();
            }
            gols[2] = o.getPartidaid();
            db.execSQL("insert into gols(gols, timeid, partidaid) values (?,?,?)", gols);
        }
    }

    @Override
    public void editar(Partida o) {
        // não precisamos editar a partida
    }

    @Override
    public Partida pesquisar(int o) {
        Cursor cursor = db.rawQuery("select * from gols, partida where partidaid = ? and gols.partidaid = partida.partidaid");
        int index_gols = cursor.getColumnIndex("gols");
        int index_timeid = cursor.getColumnIndex("timeid");
        int index_partidaid = cursor.getColumnIndex("partidaid");
        int index_casaid = cursor.getColumnIndex("casaid");
        int index_visitanteid = cursor.getColumnIndex("visitanteid");
        cursor.moveToFirst();
        Partida p = new Partida();
        p.setGols(cursor.getInt(index_gols));
        p.setTimeid(cursor.getInt(index_timeid));
        p.setPartidaid(cursor.getInt(index_partidaid));
        p.setCasaid(cursor.getInt(index_casaid));
        p.setVisitanteid(cursor.getInt(index_visitanteid));
        return p;
    }

    @Override
    public List<Partida> listar() {
        Cursor cursor = db.rawQuery("select * from gols, partida where gols.partidaid = partida.partidaid");
        int index_gols = cursor.getColumnIndex("gols");
        int index_timeid = cursor.getColumnIndex("timeid");
        int index_partidaid = cursor.getColumnIndex("partidaid");
        int index_casaid = cursor.getColumnIndex("casaid");
        int index_visitanteid = cursor.getColumnIndex("visitanteid");
        cursor.moveToFirst();
        List<Partida> partidas = new ArrayList<>();
        while (!cursor.isAfterLast()){
            Partida p = new Partida();
            p.setGols(cursor.getInt(index_gols));
            p.setTimeid(cursor.getInt(index_timeid));
            p.setPartidaid(cursor.getInt(index_partidaid));
            p.setCasaid(cursor.getInt(index_casaid));
            p.setVisitanteid(cursor.getInt(index_visitanteid));
            partidas.add(p);
        }
        return partidas;
    }

    @Override
    public void remover(int id) {
        Object[] valores = {id};
        db.execSQL("delete from partida where partidaid = ?", valores);
    }
}
