package br.edu.unirn.txtview.controller.main;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import br.edu.unirn.txtview.controller.AbstractController;
import br.edu.unirn.txtview.model.Layout;
import br.edu.unirn.txtview.service.LayoutService;
import br.edu.unirn.txtview.service.core.ExcluirException;
import br.edu.unirn.txtview.view.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class MainController extends AbstractController {
	@FXML	private ComboBox<Layout> cmbLayout;
	@FXML	private ComboBox<String> cmbCharset;
	@FXML	private TextField txtFile;
	@FXML	private TableView<Map> tblFile;
	@Inject private TableFileController tableFileController;
	@Inject private LayoutService layoutService;

	@FXML
	private void initialize() {
		tableFileController.setTblFile(tblFile);
		loadCmbLayout();
	}

	private void loadCmbLayout() {
		cmbLayout.getItems().clear();
		layoutService.findAll().forEach(l -> cmbLayout.getItems().add(l));
	}

	@FXML
	private void btnApplyLayout() {
		Layout layout = cmbLayout.getValue();
		File file = (File) txtFile.getUserData();

		if (layout == null) {
			Alerts.warn("Nenhum leiaute de arquivo selecionado");
		} else if (file == null) {
			Alerts.warn("Nenhum arquivo carregado");
		} else {
			try {
				tableFileController.applyLayoutOnFile(layout, file, cmbCharset.getValue());
			}
			catch (IOException e) {
				Alerts.error("Não foi possível ler o arquivo: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void btnOpenFile() {
		File file = newFileChooser();
		if (file != null) {
			txtFile.setText(file.getName());
			txtFile.setTooltip(new Tooltip(file.getAbsolutePath()));
			txtFile.setUserData(file);
		}
	}

	@FXML
	private void btnAddLayout() throws IOException {
		showView("Manter Leiautes", "Layout.fxml");
		loadCmbLayout();
	}

	@FXML
	private void btnEditLayout() throws IOException {
		Layout layout = cmbLayout.getValue();
		if (layout != null) {
			showView("Manter Leiautes", "Layout.fxml", layout);
			loadCmbLayout();
		} else {
			Alerts.warn("Selecione um Leiaute para alterar.");
		}
	}
	
	@FXML
	private void btnDelLayout() throws IOException, ExcluirException {
		Layout layout = cmbLayout.getValue();
		if (layout != null) {
			String msg = "Confirma exclusão do Leiaute '" + layout.getName() + "'?";
			if (Alerts.confirm(msg)) {
				layoutService.delete(layout);
				cmbLayout.getItems().remove(layout);
				loadCmbLayout();
			}
		} else {
			Alerts.warn("Selecione um Leiaute para excluir.");
		}
	}
}
