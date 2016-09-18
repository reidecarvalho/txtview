package br.edu.unirn.txtview.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;

import br.edu.unirn.txtview.di.guice.AppModule;

/**
 * Classe para obter uma inst�ncia de uma classe de forma manual, quando necess�rio.
 * @author Reinaldo
 *
 */
public class AppInjector {
	private static final Injector INJECTOR = Guice.createInjector(new AppModule(), new JpaPersistModule("txtview"));
	
	private AppInjector() {}
	
	/**
	 * Obt�m uma inst�ncia de uma determinado tipo.
	 * @param type tipo a ser instanciado.
	 * @return a inst�ncia do tipo.
	 */
	public static <T> T getInstance(Class<T> type) {
		return INJECTOR.getInstance(type);
	}
}
