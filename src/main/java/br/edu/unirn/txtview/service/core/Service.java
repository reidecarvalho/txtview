package br.edu.unirn.txtview.service.core;

import java.util.List;

import br.edu.unirn.txtview.dao.DAO;

/**
 * Encapsula todas as operações que correlacionam-se ao acesso de dados padrão via {@link DAO}.<br/>
 * É somente nos serviços que as transações são demarcadas.
 * @author reinaldo
 *
 * @param <T> Tipo da entidade alvo das operações.
 */
public interface Service<T> {
	
	/**
	 * Insere a entidade na base de dados.
	 * @param entity entidade a ser inserida. 
	 * @throws DadoInvalidoException caso alguma regra sobre os dados seja violada.
	 */
	void insert(T entity) throws DadoInvalidoException;
	
	/**
	 * Atualiza a entidade na base de dados.
	 * @param entity entidade a ser atualizada.
	 * @throws DadoInvalidoException caso alguma regra sobre os dados seja violada.
	 */
	void update(T entity) throws DadoInvalidoException;
	
	/**
	 * Exclui a entidade da base de dados.
	 * @param entity entidade a ser excluída.
	 * @throws ExcluirException caso a entidade não possa ser excluída.
	 */
	void delete(T entity) throws ExcluirException;
	
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
