package at.sipovsven.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "wrapping")
public class Wrapping {

	private int id;
	private StringProperty name = new SimpleStringProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	private List<Product> product;

	public Wrapping() {

	}

	public Wrapping(String name, double price) {

		this.name = new SimpleStringProperty(name);
		this.price = new SimpleDoubleProperty(price);

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name().get();
	}

	public void setName(String name) {
		this.name().set(name);
	}

	public StringProperty name() {
		return name;
	}

	public double getPrice() {
		return price().get();
	}

	public void setPrice(double price) {
		this.price().set(price);
	}

	public DoubleProperty price() {
		return price;
	}

	@OneToMany(mappedBy = "wrapping", cascade = CascadeType.ALL)
	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
