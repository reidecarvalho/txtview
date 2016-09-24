package br.edu.unirn.txtview.tools;

import java.util.Optional;

public class AppInfo {
	
	public String getVersion() {
		return Optional.ofNullable(getClass().getPackage().getImplementationVersion()).orElse("DEV");
	}
}
