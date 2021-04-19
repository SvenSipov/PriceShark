package at.sipovsven.service;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Sceneswitcher {

	public void openNewWindow(String fxmlFile, String title, Boolean b) throws Exception {
		// make Stage = new Stage for method to work

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/" + fxmlFile + ".fxml"));
		Stage stage = new Stage();
		Parent root = loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();

	}
	
	
	public void switchScene(String fxmlFile, String title, Stage stage) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/" + fxmlFile + ".fxml"));
		
		Parent root = loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
		
	}

}
