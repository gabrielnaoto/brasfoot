package com.example.tonied.futmanddm.modelo.dao.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.tonied.futmanddm.modelo.dao.core.EstadioDAO;
import com.example.tonied.futmanddm.modelo.dao.core.JogadorDAO;
import com.example.tonied.futmanddm.modelo.dao.core.PatrocinadorDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Esquema;
import com.example.tonied.futmanddm.modelo.entidade.Jogador;
import com.example.tonied.futmanddm.modelo.entidade.Time;

public class SQLTimeDAO extends TimeDAO {

    private PatrocinadorDAO patrocinadorDAO;
    private EstadioDAO estadioDAO;
    private JogadorDAO jogadorDAO;

    public SQLTimeDAO(SQLiteDatabase db) {
        super(db);
        patrocinadorDAO = new SQLPatrocinadorDAO(db);
        estadioDAO = new SQLEstadioDAO(db);
        jogadorDAO = new SQLJogadorDAO(db);
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
        String[] id = {o + ""};
        Cursor cursor = db.rawQuery("select * from time where timeid = ?", id);
        int index_timeid = cursor.getColumnIndex("timeid");
        int index_nome = cursor.getColumnIndex("nome");
        int index_pontos = cursor.getColumnIndex("pontos");
        int index_esquema = cursor.getColumnIndex("esquema");
        int index_saldo = cursor.getColumnIndex("saldo");
        int index_patrocinadorid = cursor.getColumnIndex("patrocinadorid");
        int index_estadioid = cursor.getColumnIndex("estadioid");
        cursor.moveToFirst();
        Time t = new Time();
        t.setTimeid(cursor.getInt(index_timeid));
        t.setNome(cursor.getString(index_nome));
        t.setPontos(cursor.getInt(index_pontos));
        t.setEsquema(Esquema.values()[cursor.getInt(index_esquema)]);
        t.setSaldo(cursor.getInt(index_saldo));
        t.setPatrocinador(patrocinadorDAO.pesquisar(cursor.getInt(index_patrocinadorid)));
        t.setEstadio(estadioDAO.pesquisar(cursor.getInt(index_timeid)));
        Cursor cursorj = db.rawQuery("select * from jogador where timeid = ?", id);
        int index_jogadorid = cursorj.getColumnIndex("jogadorid");
        int index_nomej = cursorj.getColumnIndex("nome");
        int index_posicao = cursorj.getColumnIndex("posicao");
        int index_idade = cursorj.getColumnIndex("idade");
        int index_tecnica = cursorj.getColumnIndex("tecnica");
        int index_fisico = cursorj.getColumnIndex("fisico");
        int index_inteligencia = cursorj.getColumnIndex("inteligencia");
        int index_motivacao = cursorj.getColumnIndex("motivacao");
        int index_time = cursorj.getColumnIndex("time");
        int index_suspenso = cursorj.getColumnIndex("suspenso");
        int index_cartaoamarelo = cursorj.getColumnIndex("cartaoamarelo");
        cursorj.moveToFirst();
        List<Jogador> jogadores = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            Jogador j = new Jogador();
            j.setJogadorid(cursor.getInt(index_jogadorid));
            j.setNome(cursor.getString(index_nome));
            j.setPosicao(cursor.getString(index_posicao));
            j.setIdade(cursor.getInt(index_idade));
            j.setTecnica(cursor.getInt(index_tecnica));
            j.setFisico(cursor.getInt(index_fisico));
            j.setInteligentcia(cursor.getInt(index_inteligencia));
            j.setMotivacao(cursor.getInt(index_motivacao));
            j.setTime(t);
            j.setSuspenso(cursor.getInt(index_suspenso));
            j.setCartaoamarelo(cursor.getInt(index_cartaoamarelo));
            jogadores.add(j);
        }
        t.setJogadores(jogadores);
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
        int index_jogadores = cursor.getColumnIndex("jogadores");
        cursor.moveToFirst();
        List<Time> times = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            Time t = new Time();
            t.setTimeid(cursor.getInt(index_timeid));
            t.setNome(cursor.getString(index_nome));
            t.setPontos(cursor.getInt(index_pontos));
            t.setEsquema(Esquema.values()[cursor.getInt(index_esquema)]);
            t.setSaldo(cursor.getInt(index_saldo));
            t.setPatrocinador(patrocinadorDAO.pesquisar(cursor.getInt(index_patrocinadorid)));
            t.setEstadio(estadioDAO.pesquisar(cursor.getInt(index_timeid)));
            String[] id = {t.getTimeid() + ""};
            Cursor cursorj = db.rawQuery("select * from jogador where timeid = ?", id);
            int index_jogadorid = cursorj.getColumnIndex("jogadorid");
            int index_nomej = cursorj.getColumnIndex("nome");
            int index_posicao = cursorj.getColumnIndex("posicao");
            int index_idade = cursorj.getColumnIndex("idade");
            int index_tecnica = cursorj.getColumnIndex("tecnica");
            int index_fisico = cursorj.getColumnIndex("fisico");
            int index_inteligencia = cursorj.getColumnIndex("inteligencia");
            int index_motivacao = cursorj.getColumnIndex("motivacao");
            int index_time = cursorj.getColumnIndex("time");
            int index_suspenso = cursorj.getColumnIndex("suspenso");
            int index_cartaoamarelo = cursorj.getColumnIndex("cartaoamarelo");
            cursorj.moveToFirst();
            List<Jogador> jogadores = new ArrayList<>();
            while (!cursor.isAfterLast()) {
                Jogador j = new Jogador();
                j.setJogadorid(cursor.getInt(index_jogadorid));
                j.setNome(cursor.getString(index_nome));
                j.setPosicao(cursor.getString(index_posicao));
                j.setIdade(cursor.getInt(index_idade));
                j.setTecnica(cursor.getInt(index_tecnica));
                j.setFisico(cursor.getInt(index_fisico));
                j.setInteligentcia(cursor.getInt(index_inteligencia));
                j.setMotivacao(cursor.getInt(index_motivacao));
                j.setTime(t);
                j.setSuspenso(cursor.getInt(index_suspenso));
                j.setCartaoamarelo(cursor.getInt(index_cartaoamarelo));
                jogadores.add(j);
            }
            t.setJogadores(jogadores);
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