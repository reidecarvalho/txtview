package br.edu.unirn.txtview.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Exibe mensagens informativas ao usuário.
 * @author Reinaldo
 *
 */
public class Alerts {
	
	public static void info(String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(text);
		alert.showAndWait();
	}
	
	public static void warn(String text) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText(text);
		alert.showAndWait();
	}
	
	public static void error(String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(text);
		alert.showAndWait();
	}
	
	public static boolean confirm(String text) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(text);
		Optional<ButtonType> answer = alert.showAndWait();
		return answer.isPresent() && answer.get() == ButtonType.OK;
	}
}
