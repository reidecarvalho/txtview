package br.edu.unirn.txtview.service.core;

import java.util.List;

import br.edu.unirn.txtview.dao.DAO;

/**
 * Encapsula todas as opera��es que correlacionam-se ao acesso de dados padr�o via {@link DAO}.<br/>
 * � somente nos servi�os que as transa��es s�o demarcadas.
 * @author reinaldo
 *
 * @param <T> Tipo da entidade alvo das opera��es.
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
	 * @param entity entidade a ser exclu�da.
	 * @throws ExcluirException caso a entidade n�o possa ser exclu�da.
	 */
	void delete(T entity) throws ExcluirException;
	
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
