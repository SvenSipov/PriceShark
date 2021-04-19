package at.sipovsven.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.model.Category;
import at.sipovsven.repository.CategoryRepositoryJPA;
import at.sipovsven.service.NotificationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CreateCategoryController {
	CategoryRepositoryJPA cateRepo = new CategoryRepositoryJPA();
	
    @FXML
    private Pane pane;
	@FXML
	private JFXButton createButton;

	@FXML
	private TextField categoryNameTxtField;

	NotificationService notif = new NotificationService();
	
	
	
	@FXML
	void createCategory(ActionEvent event) throws IOException {
		Category category = new Category();
		category.setName(categoryNameTxtField.getText());
		cateRepo.addCategory(category);
		
		notif.showNotificationOnPane("Kategorie hinzugefügt", "Kategorie: " + category.getName() + " erfolgreich hinzugefügt!", pane);
		
		categoryNameTxtField.clear();
	}

}
