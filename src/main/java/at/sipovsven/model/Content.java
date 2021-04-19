package at.sipovsven.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity
@Table(name = "content")

public class Content implements Serializable  {
	private static final long serialVersionUID = 1L;

	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty name = new SimpleStringProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	
	   private List<Product> product;
	
	public Content(){
		
	}
	
	
	public Content(String name, double price){
		
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleDoubleProperty(price);
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return idProperty().get();
	}

	public void setId(int id) {
		this.idProperty().set(id);
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public IntegerProperty id() {
		return id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name().get();
	}

	public void setName(String name) {
		this.name().set(name);
	}

	public StringProperty name() {
		return name;
	}
	@Column(name = "price", nullable = false)
	public double getPrice() {
		return price().get();
	}

	public void setPrice(double price) {
		this.price().set(price);
	}

	public DoubleProperty price() {
		return price;
	}

	
	@OneToMany(mappedBy="content",cascade=CascadeType.ALL)
	 public List<Product> getProduct() {
		return product;
	}


	public void setProduct(List<Product> product) {
		this.product = product;
	}


	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
