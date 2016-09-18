package br.edu.unirn.txtview.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Representa uma coluna de um {@link Layout}.
 * @author Reinaldo
 *
 */
@Entity
public class Field {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Embedded
	private Range range = new Range();

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
	
	public int getSize() {
		return range.getSize();
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

}
