package br.edu.unirn.txtview.service.impl;

import org.apache.commons.lang3.StringUtils;

import br.edu.unirn.txtview.model.Layout;
import br.edu.unirn.txtview.service.LayoutService;
import br.edu.unirn.txtview.service.core.AbstractServiceImpl;
import br.edu.unirn.txtview.service.core.DadoInvalidoException;

public class LayoutServiceImpl extends AbstractServiceImpl<Layout> implements LayoutService {
	
	@Override
	protected void validate(Layout l) throws DadoInvalidoException {
		if (l == null) {
			throw new DadoInvalidoException("Layout não informado");
		}
		if (StringUtils.isBlank(l.getName())) {
			throw new DadoInvalidoException("Nome do leiaute não informado");
		}
		if (l.getFields().isEmpty()) {
			throw new DadoInvalidoException("Nenhuma coluna foi informada");
		}
	}
}
