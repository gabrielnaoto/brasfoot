package br.udesc.ddm.trabalho2.modelo.dao.sqlite;

import br.udesc.ddm.trabalho2.modelo.dao.core.TimeDAO;
import.br.udesc.ddm.trabalho2.modelo.entidade.Time;

public class SQLTimeDAO implements TimeDAO {
    private SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("brasfoot", null);

    @Override
    public void inserir(Time o) {
        Object[] valores = new Object[7];
        valores[0] = o.getNome();
        valores[1] = o.getPontos();
        valores[2] = o.getEsquema().getOrdem();
        valores[3] = o.getSaldo();
        valores[4] = o.getPatrocinador().getPatrocinadorid();
        valores[5] = o.getEstadio().getEstadioid();
        db.execSQL("insert into time(nome, pontos, esquema, saldo, patrocinadorid, estadioid) values(?,?,?,?,?,?)", valores);
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
    }

    @Override
    public Time pesquisar(int o) {
        String[] id = {Integer.toString(o)};
        Cursor cursor = db.rawQuery();
        int index_timeid = cursor.getColumnIndex("timeid");
        int index_nome = cursor.getColumnIndex("nome");
        int index_pontos = cursor.getColumnIndex("pontos");
        int index_esquema = cursor.getColumnIndex("esquema");
        int index_saldo = cursor.getColumnIndex("saldo");
        int index_patrocinadorid = cursor.getColumnIndex("patrocinadorid");
        int index_estadioid = cursor.getColumnIndex("estadioid");
        cursos.moveToFirst();
        Time t = new Time();
        t.setTimeid(cursor.getInt(index_timeid));
        t.setNome(cursor.getInt(index_nome));
        t.setPontos(cursor.getInt(index_pontos));
        t.setEsquema(cursor.getInt(index_esquema));
        t.setSaldo(cursor.getInt(index_saldo));
        t.setPatrocinadorid(cursor.getInt(index_patrocinadorid));
        t.setEstadioid(cursor.getInt(index_estadioid));
        return t;
    }

    @Override
    public List<Time> listar() {
        Cursor cursor = db.rawQuery("select * from time", null);
        int index_timeid = cursor.getColumnIndex("timeid");
        int index_nome = cursor.getColumnIndex("nome");
        int index_pontos = cursor.getColumnIndex("pontos");
        int index_esquema = cursor.getColumnIndex("esquema");
        int index_saldo = cursor.getColumnIndex("saldo");
        int index_patrocinadorid = cursor.getColumnIndex("patrocinadorid");
        int index_estadioid = cursor.getColumnIndex("estadioid");
        cursor.moveToFirst();
        List<Time> times = new ArrayLis<>();
        while (!cursor.isAfterLast()) {
            t.setTimeid(cursor.getInt(index_timeid));
            t.setNome(cursor.getInt(index_nome));
            t.setPontos(cursor.getInt(index_pontos));
            t.setEsquema(cursor.getInt(index_esquema));
            t.setSaldo(cursor.getInt(index_saldo));
            t.setPatrocinadorid(cursor.getInt(index_patrocinadorid));
            t.setEstadioid(cursor.getInt(index_estadioid));
            times.add(t);
        }
        return times;
    }


    @Override
    public void remover(int id) {
        Object[] valores = {id};
        db.execSQL("delete from time where timeid = ?", valores);
    }
}