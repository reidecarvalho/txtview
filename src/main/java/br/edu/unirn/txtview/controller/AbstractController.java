package br.edu.unirn.txtview.controller;

import java.io.File;
import java.io.IOException;

import br.edu.unirn.txtview.di.AppFXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Implementa��o base para Controllers FXML, fornecendo servi�os auxiliares.
 * @author Reinaldo
 *
 */
public abstract class AbstractController implements ParamController {
	@FXML	private Parent root;

	/**
	 * Permite que sublclasses mostre outra tela, modal.
	 * @param title t�tulo da tela.
	 * @param fxml nome do aquivo fxml.
	 * @param params lista de par�metros a serem passados para o controller.
	 * @throws IOException se o arquivo fxml n�o puder ser carregado.
	 */
	protected <C> void showView(String title, String fxml, Object... params) throws IOException {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(getStage()); // evita criar duas janelas na barra de tarefas
		stage.setTitle(title);
		stage.setScene(new Scene(AppFXMLLoader.load(fxml, params)));
		stage.showAndWait();
	}

	private Stage getStage() {
		if (root == null) {
			throw new IllegalStateException("Defina a propriedade fx:id=\"root\" para o root node no fxml");
		}
		return (Stage) root.getScene().getWindow();
	}

	/**
	 * Exibe um FileChooser para a sele��o de um arquivo pelo usu�rio.
	 * @return um {@link File} se o usu�rio escolheu um arquivo, ou <code>null</code> se n�o.
	 */
	protected File newFileChooser() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Selecione o arquivo");
		return fc.showOpenDialog(getStage());
	}

	/**
	 * Fecha a tela corrente.
	 */
	protected void closeView() {
		getStage().close();
	}

	/**
	 * Subclasses podem sobrescrever esse m�todo para receber par�metros informados. 
	 */
	@Override
	public void setParams(Object... params) {}
}
