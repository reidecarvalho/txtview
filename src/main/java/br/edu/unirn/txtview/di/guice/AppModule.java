package br.edu.unirn.txtview.di.guice;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;

import br.edu.unirn.txtview.dao.DAO;
import br.edu.unirn.txtview.dao.jpa.LayoutJpaDAO;
import br.edu.unirn.txtview.model.Layout;
import br.edu.unirn.txtview.service.LayoutService;
import br.edu.unirn.txtview.service.core.Service;
import br.edu.unirn.txtview.service.impl.LayoutServiceImpl;

/**
 * Módulo para configuração no Guice das injeções de dependência.
 * @author reinaldo
 *
 */
public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<DAO<Layout>>(){}).to(new TypeLiteral<LayoutJpaDAO>(){});
		bind(LayoutService.class).to(LayoutServiceImpl.class);
		bind(new TypeLiteral<Service<Layout>>(){}).to(new TypeLiteral<LayoutServiceImpl>(){});
		
		// config log4j2 apache
		bindListener(Matchers.any(), new Log4JTypeListener());
	}
}
