package br.edu.unirn.txtview.dao.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import net.jodah.typetools.TypeResolver;
import br.edu.unirn.txtview.dao.DAO;

/**
 * Fornece implementação padrão em JPA para a interface {@link DAO}.<br/>
 * Subclasses tem acesso ao {@link EntityManager} via {@link AbstractJpaDAO#getEM()} e podem adicionar suas próprias consultas. 
 * @author reinaldo
 *
 * @param <T> Tipo da entidade alvo das consultas.
 */
public abstract class AbstractJpaDAO<T> implements DAO<T> {
		
	@Inject
	private EntityManager em;
	
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public AbstractJpaDAO() {
		// obtém "T.class" em runtime
		Class<?>[] args = TypeResolver.resolveRawArguments(AbstractJpaDAO.class, getClass());
		entityClass = (Class<T>) args[0];
	}
	
	/**
	 * Acesso ao {@link EntityManager} para a realização de operações no banco de dados.
	 * @return o {@link EntityManager}.
	 */
	protected final EntityManager getEM() {
		return em;
	}
	
	@Override
	public void insert(T entity) {
		em.persist(entity);
	}
	
	@Override
	public void update(T entity) {
		em.merge(entity);
	}
	
	@Override
	public void delete(T entity) {
		em.remove(entity);
	}
	
	@Override
	public T find(Object pk) {
		return em.find(entityClass, pk);
	}
	
	@Override
	public List<T> findAll() {
		return em.createQuery("select x from " + entityClass.getSimpleName() + " x", entityClass).getResultList();
	} 
}
