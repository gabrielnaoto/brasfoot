package br.udesc.ddm.trabalho2.modelo.dao.core;

import br.udesc.ddm.trabalho2.modelo.entidade.Estadio;

/**
 * Created by ignoi on 26/10/2016.
 */

public interface EstadioDAO {

    public void inserir(Estadio o);

    public void editar(Estadio o);

    public void pesquisar(Estadio o);

    public void listar(Estadio o);

    public void remover(int id);

}
