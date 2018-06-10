package application;

import common.utils.PathJoiner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import resources.Constants;

public class Main extends Application {

	private static final String WINDOW_TITLE = "ECG Simulator by Piotr Ławniczak";
	private static final String CSS_PATH = PathJoiner.join(Constants.RESOURCES_PATH, "application.css");
	private static final String FXML_PATH = PathJoiner.join(Constants.RESOURCES_PATH, "ApplicationView.fxml");

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle(WINDOW_TITLE);
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(FXML_PATH));
			BorderPane root = (BorderPane) loader.load();
			((ApplicationController) loader.getController()).setStage(primaryStage);
			Scene scene = new Scene(root, 1200, 600);
			scene.getStylesheets().add(getClass().getClassLoader().getResource(CSS_PATH).toExternalForm());
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
