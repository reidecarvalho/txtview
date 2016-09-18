package br.edu.unirn.txtview.service.core;

/**
 * Exceção que representa um erro de violação de regras sobre os dados da entidade a ser inserida ou atualizada.
 * @author reinaldo
 *
 */
public class DadoInvalidoException extends Exception {
	public DadoInvalidoException(String msg) {
		super(msg);
	}
}
