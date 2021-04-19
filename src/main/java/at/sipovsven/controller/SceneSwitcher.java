package at.sipovsven.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {

		

	public void openNewWindow(Stage stage,String fxmlFile, String title) throws Exception {
		// make Stage = new Stage for method to work

	
		
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
