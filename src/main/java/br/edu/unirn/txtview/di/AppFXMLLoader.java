package br.edu.unirn.txtview.di;

import java.io.IOException;
import java.io.InputStream;

import br.edu.unirn.txtview.controller.ParamController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Loader para os arquivos fxml.
 * @author Reinaldo
 *
 */
public class AppFXMLLoader {
	private static final String VIEWS_PATH = "/views/";

	/**
	 * Carrega o FXML.
	 * @param fxml Nome do arquivo fxml (utilizar apenas o nome do arquivo; o path será resolvido).
	 * @param params Parâmetros para o Controller, via {@link ParamController#setParams(Object...)}.
	 * @return O root node na hierarquia do fxml.
	 */
	public static Parent load(String fxml, Object... params) {
		FXMLLoader loader = new FXMLLoader();
		
		// configura o controller de injeção
		loader.setControllerFactory(type -> AppInjector.getInstance(type));
		
		try(InputStream in = AppFXMLLoader.class.getResourceAsStream(VIEWS_PATH + fxml)) {
			Parent root = loader.load(in);
			applyParams(loader, params);
			return root;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void applyParams(FXMLLoader loader, Object... params) {
		boolean hasParams = params.length > 0;
		boolean isParamControllerType = loader.getController() instanceof ParamController;
		if (hasParams && isParamControllerType) {
			((ParamController) loader.getController()).setParams(params);
		}
	}
}
