package com.example.tonied.futmanddm.modelo.dao.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.tonied.futmanddm.modelo.dao.core.JogadorDAO;
import com.example.tonied.futmanddm.modelo.dao.core.TimeDAO;
import com.example.tonied.futmanddm.modelo.entidade.Jogador;
import com.example.tonied.futmanddm.modelo.entidade.Patrocinador;

public class SQLJogadorDAO implements JogadorDAO {


    private SQLiteDatabase db;
    private TimeDAO timeDAO;

    public SQLJogadorDAO(SQLiteDatabase db) {
        timeDAO = new SQLTimeDAO(db);
        this.db = db;
    }


    @Override
    public void inserir(Jogador o) {
        Object[] valores = new Object[10];
        valores[0] = o.getNome();
        valores[1] = o.getPosicao();
        valores[2] = o.getIdade();
        valores[3] = o.getTecnica();
        valores[4] = o.getFisico();
        valores[5] = o.getInteligentcia();
        valores[6] = o.getMotivacao();
        valores[7] = o.getSuspenso();
        valores[8] = o.getCartaoamarelo();
        valores[9] = o.getTime().getTimeid();
        db.execSQL("insert into jogador(nome, posicao, idade, tecnica, fisico, inteligencia, motivacao,suspenso, cartaoamarelo, timeid) values(?,?,?,?,?,?,?,?,?,?)", valores);
    }

    @Override
    public void editar(Jogador o) {
        Object[] valores = new Object[10];
        valores[0] = o.getNome();
        valores[1] = o.getPosicao();
        valores[2] = o.getIdade();
        valores[3] = o.getTecnica();
        valores[4] = o.getFisico();
        valores[5] = o.getInteligentcia();
        valores[6] = o.getMotivacao();
        valores[7] = o.getSuspenso();
        valores[8] = o.getCartaoamarelo();
        valores[9] = o.getTime().getTimeid();
        db.execSQL("update jogador set nome = ?, posicao = ?, idade = ?, tecnica = ?, fisico = ?, inteligencia = ?,motivacao = ?, suspenso = ?, cartaoamarelo = ?, timeid = ? where jogadorid = ?", valores);
    }

    @Override
    public Jogador pesquisar(int o) {
        String[] id = {Integer.toString(o)};
        Cursor cursor = db.rawQuery("select * from jogador where jogadorid = ?", id);
        int index_jogadorid = cursor.getColumnIndex("jogadorid");
        int index_nome = cursor.getColumnIndex("nome");
        int index_posicao = cursor.getColumnIndex("posicao");
        int index_idade = cursor.getColumnIndex("idade");
        int index_tecnica = cursor.getColumnIndex("tecnica");
        int index_fisico = cursor.getColumnIndex("fisico");
        int index_inteligencia = cursor.getColumnIndex("inteligencia");
        int index_motivacao = cursor.getColumnIndex("motivacao");
        int index_time = cursor.getColumnIndex("time");
        int index_suspenso = cursor.getColumnIndex("suspenso");
        int index_cartaoamarelo = cursor.getColumnIndex("cartaoamarelo");
        cursor.moveToFirst();
        Jogador j = new Jogador();
        j.setJogadorid(cursor.getInt(index_jogadorid));
        j.setNome(cursor.getString(index_nome));
        j.setPosicao(cursor.getString(index_posicao));
        j.setIdade(cursor.getInt(index_idade));
        j.setTecnica(cursor.getInt(index_tecnica));
        j.setFisico(cursor.getInt(index_fisico));
        j.setInteligentcia(cursor.getInt(index_inteligencia));
        j.setMotivacao(cursor.getInt(index_motivacao));
        j.setTime(timeDAO.pesquisar(cursor.getInt(index_time)));
        j.setSuspenso(cursor.getInt(index_suspenso));
        j.setCartaoamarelo(cursor.getInt(index_cartaoamarelo));
        return j;
    }

    @Override
    public List<Jogador> listar() {
        Cursor cursor = db.rawQuery("select * from jogador", null);
        int index_jogadorid = cursor.getColumnIndex("jogadorid");
        int index_nome = cursor.getColumnIndex("nome");
        int index_posicao = cursor.getColumnIndex("posicao");
        int index_idade = cursor.getColumnIndex("idade");
        int index_tecnica = cursor.getColumnIndex("tecnica");
        int index_fisico = cursor.getColumnIndex("fisico");
        int index_inteligencia = cursor.getColumnIndex("inteligencia");
        int index_motivacao = cursor.getColumnIndex("motivacao");
        int index_time = cursor.getColumnIndex("time");
        int index_suspenso = cursor.getColumnIndex("suspenso");
        int index_cartaoamarelo = cursor.getColumnIndex("cartaoamarelo");
        cursor.moveToFirst();
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
//            j.setTime(timeDAO.pesquisar(cursor.getInt(index_time)));
            j.setSuspenso(cursor.getInt(index_suspenso));
            j.setCartaoamarelo(cursor.getInt(index_cartaoamarelo));
            jogadores.add(j);
        }
        return jogadores;
    }

    @Override
    public void remover(int id) {
        Object[] valores = {id};
        db.execSQL("delete from jogador where jogadorid = ?", valores);

    }
}
