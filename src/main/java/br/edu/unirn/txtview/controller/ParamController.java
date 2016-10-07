package br.edu.unirn.txtview.controller;

/**
 * Controllers FXML que implementem essa interface podem receber parâmetros após serem inicializados.
 * @author Reinaldo
 *
 */
public interface ParamController {
	
	/**
	 * Permite configurar parâmetros.<br/>
	 * Observe que, utilizado junto com um Controller, ele será invocado somente após o método initialize.<br>
	 * Por isso, não utilize no método initialize qualquer operação que dependa de um parâmetro passado via essa interface.
	 * @param params lista de parâmetros que podem ser informados.
	 */
	void setParams(Object... params);
}
