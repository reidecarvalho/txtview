package br.edu.unirn.txtview.controller.detail;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import org.apache.commons.lang3.Validate;

import br.edu.unirn.txtview.controller.AbstractController;

public class DetailController extends AbstractController {
	@FXML private TableView<Map> tblLine;
	
	private Map<String, Object> line;
	
	@FXML
	private void initialize() {
		createColumn("COLUNA", "column");
		createColumn("VALOR", "value");
	}
	
	@Override
	public void setParams(Object... params) {
		Validate.notEmpty(params);
		Validate.isAssignableFrom(Map.class, params[0].getClass());
		
		line = (Map<String, Object>) params[0];
		fillTable(line);
	}

	private void createColumn(String text, String key) {
		TableColumn<Map, String> column = new TableColumn<>(text);
		column.getStyleClass().add("columnsTblFile");
		column.setEditable(true);
		column.setCellValueFactory(new MapValueFactory<>(key));
		column.setCellFactory(TextFieldTableCell.forTableColumn());
		tblLine.getColumns().add(column);
	}
	
	private void fillTable(Map<String, Object> line) {
		line.entrySet().forEach(entry -> {
			Map<String, Object> item = new HashMap<>();
			item.put("column", entry.getKey());
			item.put("value", entry.getValue().toString());
			tblLine.getItems().add(item);
		});
	}
}
