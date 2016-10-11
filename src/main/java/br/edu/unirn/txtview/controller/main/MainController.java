package br.edu.unirn.txtview.controller.main;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import javax.inject.Inject;

import br.edu.unirn.txtview.controller.AbstractController;
import br.edu.unirn.txtview.model.Layout;
import br.edu.unirn.txtview.service.LayoutService;
import br.edu.unirn.txtview.service.core.ExcluirException;
import br.edu.unirn.txtview.view.Alerts;

public class MainController extends AbstractController {
	@FXML	private ComboBox<Layout> cmbLayout;
	@FXML	private ComboBox<String> cmbCharset;
	@FXML	private TextField txtFile;
	@FXML	private TableView<Map> tblFile;
	@Inject private TableFileController tableFileController;
	@Inject private LayoutService layoutService;

	@FXML
	private void initialize() {
		loadCmbLayout();
		configTableFile();
	}

	private void loadCmbLayout() {
		cmbLayout.getItems().clear();
		layoutService.findAll().forEach(l -> cmbLayout.getItems().add(l));
	}
	
	private void configTableFile() {
		tableFileController.setTblFile(tblFile);
		configTableContextMenu();
		tblFile.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	private void configTableContextMenu() {
		tblFile.setRowFactory(table -> {
			MenuItem detailItem = new MenuItem("Mostrar detalhes");
			detailItem.setOnAction(event -> showView("Detalhes da Linha", "Detail.fxml", tblFile.getSelectionModel().getSelectedItem()));
			
			MenuItem comparingItem = new MenuItem("Comparar as 2 linhas");
			comparingItem.setOnAction(event -> Alerts.info("Viva!"));
			
			// somente habilita o item quando houver exatamente 2 linhas selecionadas.
			comparingItem.disableProperty().bind(Bindings.notEqual(2, new IntegerBinding() {
				{
					super.bind(comparingItem.disableProperty());
				}
				
				@Override
				protected int computeValue() {
					return tblFile.getSelectionModel().getSelectedItems().size();
				}
			}));
			
			TableRow<Map> row = new TableRow<>();
			row.contextMenuProperty().bind(
						Bindings.when(row.emptyProperty())
						.then((ContextMenu) null)
						.otherwise(new ContextMenu(detailItem, comparingItem)));
			return row;
		});
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
	private void btnAddLayout() {
		showView("Manter Leiautes", "Layout.fxml");
		loadCmbLayout();
	}

	@FXML
	private void btnEditLayout() {
		Layout layout = cmbLayout.getValue();
		if (layout != null) {
			showView("Manter Leiautes", "Layout.fxml", layout);
			loadCmbLayout();
		} else {
			Alerts.warn("Selecione um Leiaute para alterar.");
		}
	}
	
	@FXML
	private void btnDelLayout() throws ExcluirException {
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
