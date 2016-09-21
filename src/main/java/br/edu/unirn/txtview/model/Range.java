package br.edu.unirn.txtview.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * O intervalo que compreende os limites de um {@link Field}.
 * @author Reinaldo
 *
 */
@Embeddable
public class Range {
	
	@Column(name = "start_index", nullable = false) // "start" é palavra reservada no PostgreSQL.
	private int start;
	
	@Column(name = "end_index", nullable = false) // "end" é palavra reservada no PostgreSQL.
	private int end;
	
	public Range() {}
	
	public Range(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
	
	public int getSize() {
		return end - start + 1;
	}
}
