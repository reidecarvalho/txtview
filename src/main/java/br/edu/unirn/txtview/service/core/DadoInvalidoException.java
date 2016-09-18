package br.edu.unirn.txtview.service.core;

/**
 * Exce��o que representa um erro de viola��o de regras sobre os dados da entidade a ser inserida ou atualizada.
 * @author reinaldo
 *
 */
public class DadoInvalidoException extends Exception {
	public DadoInvalidoException(String msg) {
		super(msg);
	}
}
