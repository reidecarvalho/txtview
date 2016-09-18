package br.edu.unirn.txtview.service.core;

/**
 * Exce��o que representa um erro ou viola��o ao tentar excluir uam entidade na base de dados.
 * @author reinaldo
 *
 */
public class ExcluirException extends Exception {
	public ExcluirException(Exception e) {
		super(e);
	}
}
