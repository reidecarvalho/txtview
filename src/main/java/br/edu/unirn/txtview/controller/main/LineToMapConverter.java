package br.edu.unirn.txtview.controller.main;

import java.util.HashMap;
import java.util.Map;

import br.edu.unirn.txtview.exception.InvalidLineSizeException;
import br.edu.unirn.txtview.model.Field;
import br.edu.unirn.txtview.model.Layout;

/**
 * Converte uma linha para um mapa de campos, aplicando um {@link Layout} para realizar o parse.<br/>
 * O nome do campo é a chave. 
 * @author Reinaldo
 *
 */
public class LineToMapConverter {
	
	/**
	 * Aplica a conversão de uma linha para um mapa.
	 * @param line linha a ser realizado o parse.
	 * @param layout leiaute a ser aplicado na análise da linha.
	 * @return Um mapa de campos do leiaute, com os valores correspondentes na linha.
	 * @throws InvalidLineSizeException se o tamanho da linha for diferente do tamanho dos campos no leiaute.
	 */
	public Map<String, Object> toMap(String line, Layout layout) throws InvalidLineSizeException {
		if (line.length() != layout.getFieldsSize()) {
			throw new InvalidLineSizeException(line.length(), layout.getFieldsSize());
		}
		
		Map<String, Object> map = new HashMap<>();
		layout.getFields().forEach(f -> map.put(f.getName(), extractValue(line, f)));
		return map;
	}

	private String extractValue(String line, Field field) {
		int start = field.getRange().getStart() - 1;
		int end = field.getRange().getEnd();
		return line.substring(start, end);
	}
}
