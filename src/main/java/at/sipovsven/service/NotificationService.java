package at.sipovsven.service;

import java.io.IOException;

import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class NotificationService {

	/* org.controlsfx.control.Notifications;
	 * Which means to bypass the module error when constructing Stages
	 * Configure pom to 	
	 * <option>--add-exports</option>
	 * <option>javafx.base/com.sun.javafx.event=ALL-UNNAMED</option>
	 * as seen in current pom.xml
	 */
	

	
	public void showNotification(String title, String text, AnchorPane owner) throws IOException {
	Image img = new Image("/images/NotifCheckSign.png");
		Notifications.create()
		.title(title)
        .text(text)
        .graphic(new ImageView(img))
        .hideAfter(Duration.seconds(3))
        .owner(owner)
        .position(Pos.BOTTOM_LEFT)
        .darkStyle()
        .show();

	}
	
	
	public void showNotificationOnPane(String title, String text, Pane owner) throws IOException {
	Image img = new Image("/images/NotifCheckSign.png");
		Notifications.create()
		.title(title)
        .text(text)
        .graphic(new ImageView(img))
        .hideAfter(Duration.seconds(3))
        .owner(owner)
        .position(Pos.BOTTOM_LEFT)
        .darkStyle()
        .show();

	}
	
	public void showErrorMessage(String title, String text, AnchorPane owner) {
		Notifications.create()
		.title(title)
        .text(text)
        .hideAfter(Duration.seconds(3))
        .darkStyle()
        .owner(owner)
        .position(Pos.CENTER)
        .darkStyle()
        .showError();
		
	}
	public void showErrorMessageOnPane(String title, String text, Pane owner) {
		Notifications.create()
		.title(title)
        .text(text)
        .hideAfter(Duration.seconds(3))
        .darkStyle()
        .owner(owner)
        .position(Pos.CENTER)
        .darkStyle()
        .showError();
		
	}
}