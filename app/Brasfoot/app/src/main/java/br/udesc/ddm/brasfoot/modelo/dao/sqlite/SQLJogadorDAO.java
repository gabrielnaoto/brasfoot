package br.udesc.ddm.trabalho2.modelo.dao.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ddm.trabalho2.modelo.dao.core.JogadorDAO;
import br.udesc.ddm.trabalho2.modelo.entidade.Jogador;
import br.udesc.ddm.trabalho2.modelo.entidade.Patrocinador;

/**
 * Created by ignoi on 26/10/2016.
 */

public class SQLJogadorDAO implements JogadorDAO {

    private SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("brasfoot", null);

    @Override
    public void inserir(Jogador o) {
        Object[] valores = new Object[3];
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
        Object[] valores = new Object[3];
        valores[0] = o.getNome();

        db.execSQL("update jogador set nome = ?, posicao = ?, idade = ?, tecnica = ?, fisico = ?, inteligencia = ?,motivacao = ?, suspenso = ?, cartaoamarelo = ?, timeid = ?, where jogadorid = ?", valores);
    }

    @Override
    public Jogador pesquisar(int o) {
        String[] id = {Integer.toString(o)};
        Cursor cursor = db.rawQuery("select * from jogador where jogadorid = ?", id);
        int index_id = cursor.getColumnIndex("patrocinadorid");
        int index_nome = cursor.getColumnIndex("nome");
        int index_estrelas = cursor.getColumnIndex("estrelas");
        int index_valor = cursor.getColumnIndex("valor");
        cursor.moveToFirst();
        Patrocinador p = new Patrocinador();
        p.setId(cursor.getInt(index_id));
        p.setNome(cursor.getString(index_nome));
        p.setEstrelas(cursor.getInt(index_estrelas));
        p.setValor(cursor.getDouble(index_valor));

        return null;
    }

    @Override
    public List<Jogador> listar() {
        Cursor cursor = db.rawQuery("select * from patrocinador", null);
        int index_id = cursor.getColumnIndex("patrocinadorid");
        int index_nome = cursor.getColumnIndex("nome");
        int index_estrelas = cursor.getColumnIndex("estrelas");
        int index_valor = cursor.getColumnIndex("valor");
        cursor.moveToFirst();
        List<Patrocinador> patrocinadores = new ArrayList<>();
        while(!cursor.isAfterLast()){
            Patrocinador p = new Patrocinador();
            p.setId(cursor.getInt(index_id));
            p.setNome(cursor.getString(index_nome));
            p.setEstrelas(cursor.getInt(index_estrelas));
            p.setValor(cursor.getDouble(index_valor));
            patrocinadores.add(p);
        }

        return null;
    }

    @Override
    public void remover(int id) {
        Object[] valores = {id};
        db.execSQL("delete from patrocinador where patrocinadorid = ?", valores);

    }
}
