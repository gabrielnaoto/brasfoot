package br.udesc.ddm.brasfoot.modelo.dao.core;

import java.util.List;

import br.udesc.ddm.brasfoot.modelo.entidade.Time;

/**
 * Created by ignoi on 26/10/2016.
 */

public interface TimeDAO {

    public void inserir(Time o);

    public void editar(Time o);

    public Time pesquisar(int o);

    public List<Time> listar();

    public void remover(int id);
}
