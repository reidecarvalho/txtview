package br.edu.unirn.txtview.controller.layout;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.collections.ListChangeListener.Change;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.inject.Inject;

import br.edu.unirn.txtview.controller.AbstractController;
import br.edu.unirn.txtview.dto.FieldDTO;
import br.edu.unirn.txtview.model.Field;
import br.edu.unirn.txtview.model.Layout;
import br.edu.unirn.txtview.model.Range;
import br.edu.unirn.txtview.service.LayoutService;
import br.edu.unirn.txtview.service.core.DadoInvalidoException;
import br.edu.unirn.txtview.view.Alerts;

public class LayoutController extends AbstractController {
	@FXML private TableView<FieldDTO> tblField;
	@FXML private TableColumn<FieldDTO, Number> colSize;
	@FXML private TextField txtName;
	@Inject private LayoutService layoutService;
	
	private Layout layout;

	@FXML
	private void initialize() {
		layout = new Layout();
		
		tblField.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tblField.getItems().addListener((Change<? extends FieldDTO> change) -> {
			computeRanges();
			reindex();
		});
	}
	
	@Override
	public void setParams(Object... params) {
		layout = (Layout) params[0];
		modelToView();
	}

	private void modelToView() {
		txtName.setText(layout.getName());
		layout.getFields().forEach(f -> addFieldToTable(new FieldDTO(f)));
	}
	
	@FXML
	private void computeRanges() {
		AtomicInteger start = new AtomicInteger(1);
		tblField.getItems().stream().forEach(field -> {
			field.setStart(start.get());
			field.setEnd(start.addAndGet(field.getSize()) - 1);
		});
	}
	
	private void reindex() {
		AtomicInteger index = new AtomicInteger(1);
		tblField.getItems().forEach(f -> f.setLineNumber(index.getAndIncrement()));
	}
	
	@FXML
	private void onEditCommitSize(CellEditEvent<FieldDTO, Number> event) {
		if (event.getRowValue() != null && event.getNewValue() != null) {
			event.getRowValue().setSize(event.getNewValue().intValue());
			computeRanges();			
		}
	}

	@FXML
	private void btnAddLine() {
		addFieldToTable(new FieldDTO());
	}
	
	private void addFieldToTable(FieldDTO dto) {
		tblField.getItems().add(dto);
	}
	
	@FXML
	private void btnDelLine() {
		FieldDTO line = tblField.getSelectionModel().getSelectedItem();
		if (line != null) {
			tblField.getItems().remove(line);
		}
	}
	
	@FXML
	private void btnSave() {
		viewToModel();
		
		try {
			if (layout.getId() == 0) {
				layoutService.insert(layout);
			} else {
				layoutService.update(layout);
			}
			closeView();
		}
		catch (DadoInvalidoException e) {
			Alerts.error(e.getMessage());
		}
	}

	private void viewToModel() {
		layout.setName(txtName.getText());
		
		layout.clearFields();
		tblField.getItems().forEach(dto -> {
			Field field = new Field();
			field.setName(dto.getName());
			field.setRange(new Range(dto.getStart(), dto.getEnd()));
			layout.addField(field);
		});
	}
	
	@FXML
	private void btnCancel() {
		closeView();
	}
}
