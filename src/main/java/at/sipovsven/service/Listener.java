package at.sipovsven.service;

import at.sipovsven.model.Product;
import at.sipovsven.repository.ProductRepositoryJPA;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;

public class Listener implements EventHandler<CellEditEvent<Product, Double>> {

	TableColumn<Product, Double> columFirstChrgEuro;
	TableColumn<Product, Double>  columBuyPrice;
	TableColumn<Product, Double>  columFirstChrgPercent;
	TableColumn<Product, Double>  columSalonPrice;

	ProductRepositoryJPA productRepo = new ProductRepositoryJPA();

	@Override
	public void handle(CellEditEvent<Product, Double> event) {
	
		
		columFirstChrgEuro.setOnEditCommit(

				new EventHandler<TableColumn.CellEditEvent<Product, Double>>() {

					@Override
					public void handle(CellEditEvent<Product, Double> t) {
						Product d = ((Product) t.getTableView().getItems().get(t.getTablePosition().getRow()));

						double newValue = t.getNewValue();
						double purchasePrice = ((Product) columBuyPrice.getTableView().getSelectionModel()
								.getSelectedItem()).getPurchasePrice();

						double salonPrice = purchasePrice + newValue;
						double f = salonPrice / purchasePrice * 100;

						System.out.println("SALONPRICE: " + salonPrice);
						System.out.println("PURCHASEPRICE: " + purchasePrice);
						System.out.println("PERCENT CALCULATED: " + f);
						System.out.println(d.getFirstChargeEuro());

						((Product) columFirstChrgPercent.getTableView().getSelectionModel().getSelectedItem())
								.setFirstChargePercent(f);
						((Product) columSalonPrice.getTableView().getSelectionModel().getSelectedItem())
								.setSalonPrice(purchasePrice + newValue);

						d.setFirstChargePercent(
								((Product) columFirstChrgPercent.getTableView().getSelectionModel().getSelectedItem())
										.getFirstChargePercent());
						d.setFirstChargeEuro(newValue);
						d.setSalonPrice(((Product) columSalonPrice.getTableView().getSelectionModel().getSelectedItem())
								.getSalonPrice());

						((ProductRepositoryJPA) productRepo).updateProduct(d);

					}
				})

		;

	}

}
