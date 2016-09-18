package br.edu.unirn.txtview;

import com.google.inject.persist.PersistService;

import br.edu.unirn.txtview.di.AppFXMLLoader;
import br.edu.unirn.txtview.di.AppInjector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
			primaryStage.setTitle("Txt View");
			primaryStage.getIcons().add(new Image("/images/app.png"));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() throws Exception {
		
		// para o serviço de persistência quando a aplicação encerrar
		AppInjector.getInstance(PersistService.class).stop();
	}
}
