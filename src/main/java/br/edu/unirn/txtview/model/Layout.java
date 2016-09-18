package br.edu.unirn.txtview.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

/**
 * Representa o formato/protocolo sob o qual uma determina linha de texto deve ser interpretada.
 * @author Reinaldo
 *
 */
@Entity
public class Layout {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(cascade =  CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "layout_id")
	private List<Field> fields = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getFields() {
		return Collections.unmodifiableList(fields);
	}
	
	public void clearFields() {
		fields.clear();
	}
	
	public void addField(Field field) {
		fields.add(field);
	}

	public void setFields(List<Field> colunas) {
		this.fields = colunas;
	}
	
	/**
	 * Retorna o somatório dos tamanhos dos campos. É o tamanho total que uma linha deve ter.
	 * @return O somatório do tamanho dos campos.
	 */
	public int getFieldsSize() {
		return fields.stream().mapToInt(Field::getSize).sum();
	}
	
	@Override
	public String toString() {
		return name;
	}
}
