package br.edu.unirn.txtview;

import java.awt.SplashScreen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import br.edu.unirn.txtview.di.AppFXMLLoader;
import br.edu.unirn.txtview.di.AppInjector;
import br.edu.unirn.txtview.tools.AppInfo;

import com.google.inject.persist.PersistService;

/**
 * Ponto de início da aplicação.
 * @author Reinaldo
 *
 */
public class Main extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		// inicia o serviço de persistência
		AppInjector.getInstance(PersistService.class).start();
		
		try {
			Scene scene = new Scene(AppFXMLLoader.load("Main.fxml"));
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.setTitle("TXT View - v" + AppInjector.getInstance(AppInfo.class).getVersion());
			primaryStage.getIcons().add(new Image("/images/app.png"));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (SplashScreen.getSplashScreen() != null) {
				SplashScreen.getSplashScreen().close();				
			}
		}
	}
	
	@Override
	public void stop() throws Exception {
		
		// para o serviço de persistência quando a aplicação encerrar
		AppInjector.getInstance(PersistService.class).stop();
	}
}
