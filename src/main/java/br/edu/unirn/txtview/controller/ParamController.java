package br.edu.unirn.txtview.controller;

/**
 * Controllers FXML que implementem essa interface podem receber parâmetros após serem inicializados.
 * @author Reinaldo
 *
 */
public interface ParamController {
	
	/**
	 * Permite configurar parâmetros.
	 * @param params lista de parâmetros que podem ser informados.
	 */
	void setParams(Object... params);
}
