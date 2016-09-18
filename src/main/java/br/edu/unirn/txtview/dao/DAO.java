package br.edu.unirn.txtview.dao;

import java.util.List;

/**
 * Encapsula todas as opera��es comuns de acesso a dados �s entidades. 
 * @author reinaldo
 *
 * @param <T> Tipo da entidade alvo das consultas.
 */
public interface DAO<T> {
	
	/**
	 * Insere a entidade na base de dados.
	 * @param entity entidade a ser inserida. 
	 */
	void insert(T entity);
	
	/**
	 * Atualiza a entidade na base de dados.
	 * @param entity entidade a ser atualizada.
	 */
	void update(T entity);
	
	/**
	 * Exclui a entidade da base de dados.
	 * @param entity entidade a ser exclu�da.
	 */
	void delete(T entity);
	
	/**
	 * Consulta a entidade pela chave prim�ria.
	 * @param pk chave prim�ria.
	 * @return A entidade que possui a chave prim�ria informada, ou <code>null</code> caso n�o exista.
	 */
	T find(Object pk);
	
	/**
	 * Consulta todas as entidades.
	 * @return Todas as entidades, ou uma lista vazia caso n�o exista.
	 */
	List<T> findAll();
}
