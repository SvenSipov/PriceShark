package at.sipovsven.controller;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.model.LabelModel;
import at.sipovsven.model.Wrapping;
import at.sipovsven.repository.WrappingRepositoryJPA;
import at.sipovsven.service.NumberFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateWrappingController {

    @FXML
    private JFXButton createButton;

    @FXML
    private TextField wrapNameTxtField;

    @FXML
    private TextField wrapPriceTxtField;
    
    
    
    WrappingRepositoryJPA wrapRepo = new WrappingRepositoryJPA();
    NumberFormat df = new NumberFormat();
    
    
    @FXML
    void createWrapping(ActionEvent event) {
    	Wrapping wrap = new Wrapping();
    	wrap.setName(wrapNameTxtField.getText());
    	wrap.setPrice(df.DecimalFormater(Double.valueOf(wrapPriceTxtField.getText())));
    	wrapRepo.addWrap(wrap);
    	
    	
    	wrapNameTxtField.clear();
    	wrapPriceTxtField.clear();

    	
    }

}
