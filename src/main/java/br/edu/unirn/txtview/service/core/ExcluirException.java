package br.edu.unirn.txtview.service.core;

/**
 * Exceção que representa um erro ou violação ao tentar excluir uam entidade na base de dados.
 * @author reinaldo
 *
 */
public class ExcluirException extends Exception {
	public ExcluirException(Exception e) {
		super(e);
	}
}
