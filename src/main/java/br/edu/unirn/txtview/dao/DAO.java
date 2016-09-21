package br.edu.unirn.txtview.dao;

import java.util.List;

/**
 * Encapsula todas as operações comuns de acesso a dados às entidades. 
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
	 * @param entity entidade a ser excluída.
	 */
	void delete(T entity);
	
	/**
	 * Consulta a entidade pela chave primária.
	 * @param pk chave primária.
	 * @return A entidade que possui a chave primária informada, ou <code>null</code> caso não exista.
	 */
	T find(Object pk);
	
	/**
	 * Consulta todas as entidades.
	 * @return Todas as entidades, ou uma lista vazia caso não exista.
	 */
	List<T> findAll();
}
