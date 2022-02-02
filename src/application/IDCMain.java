package application;

import application.control.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class IDCMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("IDCMain.fxml"));
//		Controller ctrl = loader.load();
		loader.load();
		Parent root = loader.getRoot();
		Scene scene = new Scene(root);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setMinHeight(520);
		primaryStage.setMinWidth(360);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
