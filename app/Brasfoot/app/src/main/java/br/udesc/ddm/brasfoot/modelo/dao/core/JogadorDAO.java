package br.udesc.ddm.brasfoot.modelo.dao.core;

import java.util.List;

import br.udesc.ddm.brasfoot.modelo.entidade.Jogador;

/**
 * Created by ignoi on 26/10/2016.
 */

public interface JogadorDAO {

    public void inserir(Jogador o);

    public void editar(Jogador o);

    public Jogador pesquisar(int o);

    public List<Jogador> listar();

    public void remover(int id);
}
