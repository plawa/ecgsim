package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle(Messages.getString("WINDOW_TITLE")); //$NON-NLS-1$
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Messages.getString("FXML_PATH"))); //$NON-NLS-1$
			BorderPane root = (BorderPane) loader.load();
			((ApplicationController) loader.getController()).setStage(primaryStage);
			Scene scene = new Scene(root, 1200, 600);
			scene.getStylesheets().add(getClass().getResource(Messages.getString("CSS_PATH")).toExternalForm()); //$NON-NLS-1$
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
