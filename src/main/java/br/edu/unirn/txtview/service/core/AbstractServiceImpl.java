package br.edu.unirn.txtview.service.core;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transaction;

import com.google.inject.persist.Transactional;

import br.edu.unirn.txtview.dao.DAO;

/**
 * Fornece implementação padrão dos serviços referentes à interface {@link Service}.<br/>
 * Subclasses tem acesso ao {@link DAO} via {@link AbstractServiceImpl#getDAO()} e podem adicionar suas próprias operações.<br/>
 * Utilize a anotação {@link Transaction} sobre um novo serviço (método) para demarcá-lo numa transação. 
 * @author reinaldo
 *
 * @param <T> Tipo da entidade alvo das operações.
 */
public abstract class AbstractServiceImpl<T> implements Service<T> {
	
	@Inject
	private DAO<T> dao;
	
	/**
	 * Acesso ao {@link DAO} para operações na base de dados. 
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
	 * Subclasses podem sobrescrever esse método para implementar validações pré insert/update.
	 * @param entity a ser validada.
	 * @throws DadoInvalidoException caso alguma regra de negócio seja violada.
	 */
	protected void validate(T entity) throws DadoInvalidoException {}
}
