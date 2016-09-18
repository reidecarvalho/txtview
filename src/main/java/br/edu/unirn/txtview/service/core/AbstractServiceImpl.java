package br.edu.unirn.txtview.service.core;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transaction;

import com.google.inject.persist.Transactional;

import br.edu.unirn.txtview.dao.DAO;

/**
 * Fornece implementa��o padr�o dos servi�os referentes � interface {@link Service}.<br/>
 * Subclasses tem acesso ao {@link DAO} via {@link AbstractServiceImpl#getDAO()} e podem adicionar suas pr�prias opera��es.<br/>
 * Utilize a anota��o {@link Transaction} sobre um novo servi�o (m�todo) para demarc�-lo numa transa��o. 
 * @author reinaldo
 *
 * @param <T> Tipo da entidade alvo das opera��es.
 */
public abstract class AbstractServiceImpl<T> implements Service<T> {
	
	@Inject
	private DAO<T> dao;
	
	/**
	 * Acesso ao {@link DAO} para opera��es na base de dados. 
	 * @return o {@link DAO};
	 */
	protected final DAO<T> getDAO() {
		return dao;
	}

	@Transactional
	@Override
	public void insert(T entity) throws DadoInvalidoException {
		validate(entity);
		dao.insert(entity);
	}
	
	@Transactional
	@Override
	public void update(T entity) throws DadoInvalidoException {
		validate(entity);
		dao.update(entity);
	}

	@Transactional
	@Override
	public void delete(T entity) throws ExcluirException {
		dao.delete(entity);
	}

	@Transactional
	@Override
	public T find(Object pk) {
		return dao.find(pk);
	}

	@Transactional
	@Override
	public List<T> findAll() {
		return dao.findAll();
	}
	
	/**
	 * Subclasses podem sobrescrever esse m�todo para implementar valida��es pr� insert/update.
	 * @param entity a ser validada.
	 * @throws DadoInvalidoException caso alguma regra de neg�cio seja violada.
	 */
	protected void validate(T entity) throws DadoInvalidoException {}
}
