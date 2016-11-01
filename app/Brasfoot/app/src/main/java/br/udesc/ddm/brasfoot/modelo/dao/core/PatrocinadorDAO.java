package br.udesc.ddm.brasfoot.modelo.dao.core;

import java.util.List;

import br.udesc.ddm.brasfoot.modelo.entidade.Patrocinador;

/**
 * Created by ignoi on 26/10/2016.
 */

public interface PatrocinadorDAO {

    public void inserir(Patrocinador o);

    public void editar(Patrocinador o);

    public Patrocinador pesquisar(int o);

    public List<Patrocinador> listar();

    public void remover(int id);
    
    
}