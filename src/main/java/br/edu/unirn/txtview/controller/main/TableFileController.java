package br.edu.unirn.txtview.controller.main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.edu.unirn.txtview.exception.InvalidLineSizeException;
import br.edu.unirn.txtview.model.Field;
import br.edu.unirn.txtview.model.Layout;
import br.edu.unirn.txtview.view.Alerts;

/**
 * Controller auxiliar para gerenciar a tabela de arquivo.
 * @author Reinaldo
 *
 */
public class TableFileController {
	public static final String LINE_COLUMN_NAME = "LINHA";
	private static final char ERROR_FILL = '-';
	
	private TableView<Map> tblFile;
	
	@Inject 
	private LineToMapConverter lineToMapConverter;
	
	public void setTblFile(TableView<Map> tblFile) {
		this.tblFile = tblFile;
	}
	
	/**
	 * Carrega o arquivo na tabela, aplicando o leiaute para realizar o parse. 
	 * @param layout leiaute a ser aplicado às linhas do arquivo.
	 * @param file arquivo a ser carregado.
	 * @param charset encoding utilizado para carregar o arquivo.
	 * @throws IOException caso haja algum erro ao carregar o arquivo.
	 */
	public void applyLayoutOnFile(Layout layout, File file, String charset) throws IOException {
		try {
			clearTable();
			createColumns(layout);
			fillTable(layout, file, charset);
		}
		catch (Exception e) {
			Alerts.error(e.getMessage());
			clearTable();
		}
	}
	
	private void createColumns(Layout layout) {
		createLineColumn();
		layout.getFields().forEach(field -> tblFile.getColumns().add(createColumn(field)));
	}

	private void createLineColumn() {
		TableColumn<Map, String> lineColumn = new TableColumn<>(LINE_COLUMN_NAME);
		lineColumn.setCellValueFactory(new MapValueFactory<>(LINE_COLUMN_NAME));
		tblFile.getColumns().add(lineColumn);
	}
	
	private TableColumn<Map, String> createColumn(Field field) {
		String text = String.format("%s\n%d-%d", field.getName(), field.getRange().getStart(), field.getRange().getEnd()); 
		
		TableColumn<Map, String> column = new TableColumn<>(text);
		column.getStyleClass().add("columnsTblFile");
		column.setEditable(true);
		column.setCellValueFactory(new MapValueFactory<>(field.getName()));
		column.setCellFactory(TextFieldTableCell.forTableColumn());
		return column;
	}

	private void fillTable(Layout layout, File file, String charset) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.forName(charset));
		for (int i = 1; i <= lines.size(); i++) {
			try {
				String line = lines.get(i - 1);
				if (!line.startsWith("#")) {
					addLine(layout, i, line);
				}
			}
			catch (InvalidLineSizeException e) {
				boolean proceed = handleApplyLayoutError(e, i, layout);
				if (!proceed) {
					break;
				}
			}
		}
	}

	private void addLine(Layout layout, int i, String line) throws InvalidLineSizeException {
		Map<String, Object> lineMap = lineToMapConverter.toMap(line, layout);
		lineMap.put(LINE_COLUMN_NAME, i);
		tblFile.getItems().add(lineMap);
	}
	
	private boolean handleApplyLayoutError(Exception e, int index, Layout layout) {
		String msg = "Erro na linha " + index + " -> " + e.getMessage() + ".\nContinuar processando as outras linhas?";
		boolean proceed = Alerts.confirm(msg);
		if (proceed) {
			addErrorLine(index, layout);
		} else {
			clearTable();
		}
		return proceed;
	}

	private void addErrorLine(int index, Layout layout) {
		try {
			String errorLine = StringUtils.repeat(ERROR_FILL, layout.getFieldsSize());
			addLine(layout, index, errorLine);
		}
		catch (InvalidLineSizeException e) {
			// não ocorrerá porque a linha é criada no tamanho dos campos do layout
			throw new IllegalStateException(e);
		}
	}
	
	private void clearTable() {
		tblFile.getItems().clear();
		tblFile.getColumns().clear();
	}
}
