package at.sipovsven.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import at.sipovsven.model.Product;
import at.sipovsven.repository.ProductRepositoryJPA;

public class IOService {
	ProductRepositoryJPA prodRepo = new ProductRepositoryJPA();

	public void exportProducts(String csvFilePath) throws SQLException, IOException {

		
		List<Product> products = prodRepo.getAllProducts();

		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
		fileWriter.write(
				"id,Kategorie,Name,Gewicht,Einkaufspreis,Aufschlag %,Aufschlag EUR,EK-Salon,Aufschlag EUR,Aufschlag %,EVP exl, Mwst,EVP in Euro,content_id,label_id,pump_id,shipping_id,wrapping_id");

		String cateCompare = "h";

		for (Product p : products) {

			if (!p.getCategory().equals(cateCompare)) {

				fileWriter.newLine();

				fileWriter.write(p.getCategory());
			}

			String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", p.getId(), p.getCategory(),
					p.getName(),p.getWeight(), p.getPurchasePrice(), p.getFirstChargePercent(), p.getFirstChargeEuro(),
					p.getSalonPrice(), p.getSecondChargePercent(), p.getSecondChargeEuro(), p.getNetPrice(),
					p.getTaxEuro(), p.getBruttPrice(), 
					
					
					p.getContent().getId(),
					p.getLabel().getId(),
					p.getPump().getId(),
					p.getShipping().getId(),
					p.getWrapping().getId());

			fileWriter.newLine();
			fileWriter.write(line);
			cateCompare = p.getCategory();
		}

		fileWriter.close();

	}

}
