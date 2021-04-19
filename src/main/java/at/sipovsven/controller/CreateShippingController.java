package at.sipovsven.controller;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.model.Shipping;
import at.sipovsven.repository.ShippingRepositoryJPA;
import at.sipovsven.service.NumberFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateShippingController {

	ShippingRepositoryJPA shipRepo = new ShippingRepositoryJPA();
	NumberFormat df = new NumberFormat();

	@FXML
	private JFXButton createButton;

	@FXML
	private TextField shippingNameTxtField;

	@FXML
	private TextField shippingPriceTxtField;

	@FXML
	void createShipping(ActionEvent event) {
		Shipping ship = new Shipping();
		ship.setName(shippingNameTxtField.getText());
		ship.setPrice(df.DecimalFormater(Double.valueOf(shippingPriceTxtField.getText())));
		shipRepo.addShipping(ship);

		shippingNameTxtField.clear();
		shippingPriceTxtField.clear();
	}

}
