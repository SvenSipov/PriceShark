package at.sipovsven.controller;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.model.LabelModel;
import at.sipovsven.repository.LabelRepositoryJPA;
import at.sipovsven.service.NumberFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateLabelController {
	
	LabelRepositoryJPA labelRepo = new LabelRepositoryJPA();
	NumberFormat df = new NumberFormat();	
	
    @FXML
    private JFXButton createButton;

    @FXML
    private TextField labelNameTxtField;

    @FXML
    private TextField labelPriceTxtField;

   
    /**
     * Listener einbauen für if(labelCreator != open) ---> reload BuyPriceController, setTableItems
     * @param event
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     */
    @FXML
    void createLabel(ActionEvent event) throws NoSuchMethodException, SecurityException {
    	LabelModel label = new LabelModel();
    	label.setName(labelNameTxtField.getText());
    	label.setPrice(df.DecimalFormater(Double.valueOf(labelPriceTxtField.getText())));
    	labelRepo.addLabel(label);
    	
    	
    	labelNameTxtField.clear();
    	labelPriceTxtField.clear();
    
    	
    }

}

