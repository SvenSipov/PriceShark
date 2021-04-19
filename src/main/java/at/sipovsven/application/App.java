package at.sipovsven.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {

	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/MainScene.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/icon-shark.ico")));
			primaryStage.setTitle("Preiskalkulator");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

				
				/*
				 * Listens for the primaryStage to close
				 * Shutting down embedded Derby db 
				 * 
				 * Note: Derby db always throws SQLNonTransientConnectionException per documentation
				 */
		         @Override
		         public void handle(WindowEvent event) {
		             Platform.runLater(new Runnable() {

		                 @Override
		                 public void run() {
		               
		                    	 try {
									DriverManager.getConnection("jdbc:derby:/home/test/databases/preiskalkulator;shutdown=true");
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    System.exit(0);
		                 }
		             });
		         }
		     });
		}

		 catch (Exception e) {
			e.printStackTrace();
		}
	}
}

	

