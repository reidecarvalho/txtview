package br.edu.unirn.txtview.controller;

/**
 * Controllers FXML que implementem essa interface podem receber par�metros ap�s serem inicializados.
 * @author Reinaldo
 *
 */
public interface ParamController {
	
	/**
	 * Permite configurar par�metros.
	 * @param params lista de par�metros que podem ser informados.
	 */
	void setParams(Object... params);
}
