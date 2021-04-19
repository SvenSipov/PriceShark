package at.sipovsven.controller;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.model.Pump;
import at.sipovsven.repository.PumpRepositoryJPA;
import at.sipovsven.service.NumberFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreatePumpController {

	PumpRepositoryJPA pumpRepo = new PumpRepositoryJPA();
	NumberFormat df = new NumberFormat();

	@FXML
	private JFXButton createButton;

	@FXML
	private TextField pumpNameTxtField;

	@FXML
	private TextField pumpPriceTxtField;

	@FXML
	void createPump(ActionEvent event) {
		Pump pump = new Pump();
		pump.setName(pumpNameTxtField.getText());
		pump.setPrice(df.DecimalFormater(Double.valueOf(pumpPriceTxtField.getText())));
		pumpRepo.addPump(pump);

		pumpNameTxtField.clear();
		pumpPriceTxtField.clear();
	}

}
