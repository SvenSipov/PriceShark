package at.sipovsven.service;

import at.sipovsven.model.Product;
import at.sipovsven.repository.ProductRepositoryJPA;


public class ProductService {

	ProductRepositoryJPA repo = new ProductRepositoryJPA();
	
	public void updateFirstCharge(Product product, Double percent) {
		
		product.setFirstChargePercent(percent);
		repo.updateProduct(product);
		
	}
}
