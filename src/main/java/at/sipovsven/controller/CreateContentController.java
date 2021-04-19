package at.sipovsven.controller;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.model.Content;
import at.sipovsven.repository.ContentRepositoryJPA;
import at.sipovsven.service.NumberFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateContentController {

	ContentRepositoryJPA contentRepo = new ContentRepositoryJPA();
	NumberFormat df = new NumberFormat();

	@FXML
	private JFXButton createButton;

	@FXML
	private TextField contentNameTxtField;

	@FXML
	private TextField contentPriceTxtField;

	@FXML
	void createContent(ActionEvent event) {
		Content content = new Content();
		content.setName(contentNameTxtField.getText());
		content.setPrice(df.DecimalFormater(Double.valueOf(contentPriceTxtField.getText())));
		contentRepo.addContent(content);
    	
    	
		contentNameTxtField.clear();
		contentPriceTxtField.clear();
	}

}
