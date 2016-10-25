package br.edu.unirn.txtview.controller.comparing;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import org.apache.commons.lang3.Validate;

import br.edu.unirn.txtview.controller.AbstractController;
import br.edu.unirn.txtview.controller.main.TableFileController;

public class ComparingController extends AbstractController {
	@FXML private TableView<Map> tblComparing;
	
	private Map<String, Object> line1;
	private Map<String, Object> line2;
	
	@Override
	public void setParams(Object... params) {
		Validate.notEmpty(params);
		Validate.isTrue(params.length == 2, "deve ser informado as duas linhas para comparação");
		Validate.isAssignableFrom(Map.class, params[0].getClass());
		Validate.isAssignableFrom(Map.class, params[1].getClass());
		
		line1 = (Map<String, Object>) params[0];
		line2 = (Map<String, Object>) params[1];
		
		createColumns();
		fillTable();
	}
	
	private void createColumns() {
		createColumn("COLUNA", "column");
		
		String text1 = "VALOR LINHA " + line1.get(TableFileController.LINE_COLUMN_NAME);
		createColumn(text1, "value1");
		
		String text2 = "VALOR LINHA " + line2.get(TableFileController.LINE_COLUMN_NAME);
		createColumn(text2, "value2");
	}

	private void createColumn(String text, String key) {
		TableColumn<Map, String> column = new TableColumn<>(text);
		column.getStyleClass().add("columnsTblFile");
		column.setEditable(true);
		column.setCellValueFactory(new MapValueFactory<>(key));
		column.setCellFactory(TextFieldTableCell.forTableColumn());
		tblComparing.getColumns().add(column);
	}
	
	private void fillTable() {
		Validate.isTrue(line1.size() == line2.size(), "as linhas devem ter número de colunas iguais");
		
		line1.keySet().forEach(key -> {
			if (!key.equals(TableFileController.LINE_COLUMN_NAME)) {
				Map<String, Object> item = new HashMap<>();
				item.put("column", key);
				item.put("value1", line1.get(key).toString());
				item.put("value2", line2.get(key).toString());
				tblComparing.getItems().add(item);
			}
		});
	}
}
