package at.sipovsven.application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppMain {

	public static void main(String[] args) {
		
		App.launch(App.class, args);

	}
//	 class WindowButtons extends HBox {
//
//	        public WindowButtons() {
//	            Button closeBtn = new Button("X");
//
//	            closeBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//	                @Override
//	                public void handle(ActionEvent actionEvent) {
//	                    Platform.exit();
//	                }
//	            });
//
//	            this.getChildren().add(closeBtn);
//	        }
//	    }
//	
	}

