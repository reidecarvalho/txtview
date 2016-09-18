package br.edu.unirn.txtview.dto;

import br.edu.unirn.txtview.model.Field;
import br.edu.unirn.txtview.model.Layout;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * DTO para a tabela de campos do {@link Layout}.
 * @author Reinaldo
 *
 */
public class FieldDTO {
	private IntegerProperty lineNumber = new SimpleIntegerProperty(0);
	private StringProperty name = new SimpleStringProperty("Nome da Coluna");
	private IntegerProperty size = new SimpleIntegerProperty(1);
	private IntegerProperty start = new SimpleIntegerProperty();
	private IntegerProperty end = new SimpleIntegerProperty();
	
	public FieldDTO() {}
	
	public FieldDTO(Field field) {
		setName(field.getName());
		setSize(field.getSize());
		setStart(field.getRange().getStart());
		setEnd(field.getRange().getEnd());
	}
	
	public int getLineNumber() {
		return lineNumber.get();
	}

	public void setLineNumber(int size) {
		this.lineNumber.set(size);
	}
	
	public IntegerProperty lineNumberProperty() {
		return lineNumber;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty nameProperty() {
		return name;
	}

	public int getSize() {
		return size.get();
	}

	public void setSize(int size) {
		this.size.set(size);
	}
	
	public IntegerProperty sizeProperty() {
		return size;
	}

	public int getStart() {
		return start.get();
	}

	public void setStart(int start) {
		this.start.set(start);
	}
	
	public IntegerProperty startProperty() {
		return start;
	}

	public int getEnd() {
		return end.get();
	}

	public void setEnd(int end) {
		this.end.set(end);
	}

	public IntegerProperty endProperty() {
		return end;
	}
}
