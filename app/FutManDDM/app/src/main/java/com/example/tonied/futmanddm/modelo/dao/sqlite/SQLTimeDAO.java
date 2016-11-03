package br.udesc.ddm.brasfoot.modelo.dao.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ddm.brasfoot.modelo.dao.core.PatrocinadorDAO;
import br.udesc.ddm.brasfoot.modelo.dao.core.TimeDAO;
import br.udesc.ddm.brasfoot.modelo.entidade.Time;

public class SQLTimeDAO extends TimeDAO {

    public SQLTimeDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public void inserir(Time o) {
        Object[] valores = new Object[6];
        valores[0] = o.getNome();
        valores[1] = o.getPontos();
        valores[2] = o.getEsquema().getOrdem();
        valores[3] = o.getSaldo();
        valores[4] = o.getPatrocinador().getPatrocinadorid();
        valores[5] = o.getEstadio().getEstadioid();
        db.execSQL("insert into time(nome, pontos, esquema, saldo, patrocinadorid, estadioid) values(?,?,?,?,?,?)", valores);
        for (int i = 0; i < 18; i++) {
            Object jogadores = new Object[18];
            jogadores[0] = o.getJogadores.getJogadorid()[i];
            db.execSQL("insert into time(jogadores) values (?)", jogadores);
        }
    }

    @Override
    public void editar(Time o) {
        Object[] valores = new Object[7];
        valores[0] = o.getNome();
        valores[1] = o.getPontos();
        valores[2] = o.getEsquema().getOrdem();
        valores[3] = o.getSaldo();
        valores[4] = o.getPatrocinador().getPatrocinadorid();
        valores[5] = o.getEstadio().getEstadioid();
        db.execSQL("update time set nome = ?, pontos = ?, esquema = ?, saldo = ?, patrocinadorid = ?, estadioid = ? where timeid = ? ", valores);
        for (int i = 0; i < 18; i++) {
            Object jogadores = new Object[18];
            jogadores[0] = o.getJogadores.getJogadorid()[i];
            db.execSQL("update time jogadores = ? where timeid = ?", jogadores);
        }
    }

    @Override
    public Time pesquisar(int o) {
        String[] id = {Integer.toString(o)};
        Cursor cursor = db.rawQuery("select * from time where timeid = ?", null);
        int index_timeid = cursor.getColumnIndex("timeid");
        int index_nome = cursor.getColumnIndex("nome");
        int index_pontos = cursor.getColumnIndex("pontos");
        int index_esquema = cursor.getColumnIndex("esquema");
        int index_saldo = cursor.getColumnIndex("saldo");
        int index_patrocinadorid = cursor.getColumnIndex("patrocinadorid");
        int index_estadioid = cursor.getColumnIndex("estadioid");
        int index_jogadores = cursor.getColumnIndex("jogadores");
        cursor.moveToFirst();
        Time t = new Time();
        t.setTimeid(cursor.getInt(index_timeid));
        t.setNome(cursor.getString(index_nome));
        t.setPontos(cursor.getInt(index_pontos));
        t.setEsquema(cursor.getInt(index_esquema));
        t.setSaldo(cursor.getInt(index_saldo));
        t.setPatrociadorid(cursor.getInt(index_patrocinadorid));
        t.setEstadioid(cursor.getInt(index_timeid));
        for (int i = 0; i < 18; i++) {
            t.setJogadores(cursor.getColumnIndex("jogadores")[i]);
        }
        return t;
    }

    @Override
    public List<Time> listar() {
        String[] id = {Integer.toString(o)};
        Cursor cursor = db.rawQuery("select * from time where timeid = ?", null);
        int index_timeid = cursor.getColumnIndex("timeid");
        int index_nome = cursor.getColumnIndex("nome");
        int index_pontos = cursor.getColumnIndex("pontos");
        int index_esquema = cursor.getColumnIndex("esquema");
        int index_saldo = cursor.getColumnIndex("saldo");
        int index_patrocinadorid = cursor.getColumnIndex("patrocinadorid");
        int index_estadioid = cursor.getColumnIndex("estadioid");
        int index_jogadores = cursor.getColumnIndex("jogadores");
        cursor.moveToFirst();
        List<Time> times = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            t.setTimeid(cursor.getInt(index_timeid));
            t.setNome(cursor.getString(index_nome));
            t.setPontos(cursor.getInt(index_pontos));
            t.setEsquema(cursor.getInt(index_esquema));
            t.setSaldo(cursor.getInt(index_saldo));
            t.setPatrociadorid(cursor.getInt(index_patrocinadorid));
            t.setEstadioid(cursor.getInt(index_timeid));
            for (int i = 0; i < 18; i++) {
                t.setJogadores(cursor.getColumnIndex("jogadores")[i]);
            }
            times.add(t)
        }
        return times;
    }

    @Override
    public void remover(int id) {
        Object[] valores = {id};
        db.execSQL("delete from time where timeid = ?", valores);
    }
}