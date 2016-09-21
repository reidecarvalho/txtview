package br.edu.unirn.txtview.exception;

import br.edu.unirn.txtview.model.Layout;

/**
 * Sinaliza que o tamanho da linha é diferente do somatório do tamanho dos campos de um {@link Layout}.
 * @see Layout#getFieldsSize()
 * @author Reinaldo
 *
 */
public class InvalidLineSizeException extends Exception {
	public InvalidLineSizeException(int lineSize, int expected) {
		super("tamanho de linha inv�lido; foi " + lineSize + ", esperava " + expected);
	}
}
