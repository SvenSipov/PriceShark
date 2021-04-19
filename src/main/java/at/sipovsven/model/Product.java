package at.sipovsven.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import at.sipovsven.service.NumberFormat;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "products")
public class Product  extends RecursiveTreeObject<Product> implements Serializable {
	private static final long serialVersionUID = 1L;

	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty name = new SimpleStringProperty();
	private StringProperty category = new SimpleStringProperty();

	private StringProperty weight = new SimpleStringProperty();
	private DoubleProperty firstChargePercent = new SimpleDoubleProperty();
	private DoubleProperty firstChargeEuro = new SimpleDoubleProperty();
	private DoubleProperty secondChargePercent = new SimpleDoubleProperty();
	private DoubleProperty secondChargeEuro = new SimpleDoubleProperty();
	private DoubleProperty purchasePrice = new SimpleDoubleProperty();
	private DoubleProperty salonPrice = new SimpleDoubleProperty();
	private DoubleProperty taxEuro = new SimpleDoubleProperty();
	private DoubleProperty netPrice = new SimpleDoubleProperty();
	private DoubleProperty bruttPrice = new SimpleDoubleProperty();

	private Content content;
	private LabelModel label;
	private Wrapping wrapping;
	private Pump pump;
	private Shipping shipping;
	

	 NumberFormat df = new NumberFormat();

	public Product() {

	}

	public Product(String name, String category,String weight, double purchasePrice, double salonPrice, double taxEuro,
			double netPrice, double bruttPrice, double firstChargePercent, double firstChargeEuro,
			double secondChargePercent, double secondChargeEuro, Content content, LabelModel label,Pump pump,Shipping shipping) {

		this.name = new SimpleStringProperty(name);
		this.category = new SimpleStringProperty(category);
		this.weight = new SimpleStringProperty(weight);
		this.purchasePrice = new SimpleDoubleProperty(purchasePrice);
		this.salonPrice = new SimpleDoubleProperty(salonPrice);
		this.taxEuro = new SimpleDoubleProperty(taxEuro);
		this.netPrice = new SimpleDoubleProperty(netPrice);
		this.bruttPrice = new SimpleDoubleProperty(bruttPrice);
		this.firstChargePercent = new SimpleDoubleProperty(firstChargePercent);
		this.firstChargeEuro = new SimpleDoubleProperty(firstChargeEuro);
		this.secondChargePercent = new SimpleDoubleProperty(secondChargePercent);
		this.secondChargeEuro = new SimpleDoubleProperty(secondChargeEuro);
		this.content = content;
		this.label = label;
		this.pump = pump;
		this.shipping = shipping;
	

	}

	
	public double calculatePurchasePrice(double label, double shipping,double content,double pump,double wrapping) {
		
		return df.DecimalFormater(label+shipping+content+pump + wrapping) ;
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


	@Column(name = "name", nullable = false)
	public String getName() {
		return nameProperty().get();
	}

	public void setName(String name) {
		this.nameProperty().set(name);
	}

	public StringProperty nameProperty() {
		return name;
	}

	@Column(name = "category")
	public String getCategory() {
		return categoryProperty().get();
	}

	public void setCategory(String category) {
		this.categoryProperty().set(category);
	}

	public StringProperty categoryProperty() {
		return category;
	}
	
	@Column(name = "weight")
	public String getWeight() {
		return weightProperty().get();
	}

	public void setWeight(String weight) {
		this.weightProperty().set(weight);
	}

	public StringProperty weightProperty() {
		return weight;
	}

	@Column(name = "firstChargeEuro")
	public double getFirstChargeEuro() {
		return firstChargeEuroProperty().get();
	}

	public void setFirstChargeEuro(double firstChargeEuro) {
		this.firstChargeEuroProperty().set(firstChargeEuro);
	}

	public DoubleProperty firstChargeEuroProperty() {
		return firstChargeEuro;
	}

	@Column(name = "firstChargePercent")
	public double getFirstChargePercent() {
		return firstChargePercentProperty().get();
	}

	public void setFirstChargePercent(double firstChargePercent) {
		this.firstChargePercentProperty().set(firstChargePercent);
	}

	public DoubleProperty firstChargePercentProperty() {
		return firstChargePercent;
	}

	@Column(name = "secondChargePercent")
	public double getSecondChargePercent() {
		return secondChargePercentProperty().get();
	}

	public void setSecondChargePercent(double secondChargePercent) {
		this.secondChargePercentProperty().set(secondChargePercent);
	}

	public DoubleProperty secondChargePercentProperty() {
		return secondChargePercent;
	}

	@Column(name = "secondChargeEuro")
	public double getSecondChargeEuro() {
		return secondChargeEuroProperty().get();
	}

	public void setSecondChargeEuro(double secondChargeEuro) {
		this.secondChargeEuroProperty().set(secondChargeEuro);
	}

	public DoubleProperty secondChargeEuroProperty() {
		return secondChargeEuro;
	}

	@Column(name = "purchasePrice", nullable = false)
	public double getPurchasePrice() {
		return purchasePriceProperty().get();
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePriceProperty().set(purchasePrice);
	}

	public DoubleProperty purchasePriceProperty() {
		return purchasePrice;
	}

	@Column(name = "salonPrice")
	public double getSalonPrice() {
		return salonPriceProperty().get();
	}

	public void setSalonPrice(double salonPrice) {
		this.salonPriceProperty().set(salonPrice);
	}

	public DoubleProperty salonPriceProperty() {
		return salonPrice;
	}

	@Column(name = "taxEuro")
	public double getTaxEuro() {
		return taxEuroProperty().get();
	}

	public void setTaxEuro(double taxEuro) {
		this.taxEuroProperty().set(taxEuro);
	}

	public DoubleProperty taxEuroProperty() {
		return taxEuro;
	}

	@Column(name = "netPrice")
	public double getNetPrice() {
		return netPriceProperty().get();
	}

	public void setNetPrice(double netPrice) {
		this.netPriceProperty().set(netPrice);
	}

	public DoubleProperty netPriceProperty() {
		return netPrice;
	}

	@Column(name = "bruttPrice", nullable = false)
	public double getBruttPrice() {
		return bruttPrice().get();
	}

	public void setBruttPrice(double bruttPrice) {
		this.bruttPrice().set(bruttPrice);
	}

	public DoubleProperty bruttPrice() {
		return bruttPrice;
	}
	
	
	
	@ManyToOne()
    @JoinColumn(name="content_id")
	public Content getContent() {
		return content;
	}
	
	public void setContent(Content content) {
		this.content = content;
	}

	
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="label_id")
	public LabelModel getLabel() {
		return label;
	}


	public void setLabel(LabelModel label) {
		this.label = label;
	}
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="wrapping_id")
	public Wrapping getWrapping() {
		return wrapping;
	}	
	
	public void setWrapping(Wrapping wrapping) {
		this.wrapping = wrapping;
	}
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="pump_id")
	public Pump getPump() {
		return pump;
	}	
	
	public void setPump(Pump pump) {
		this.pump = pump;
	}
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="shipping_id")
	public Shipping getShipping() {
		return shipping;
	}	
	
	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setSecondChargeEuro(DoubleProperty secondChargeEuro) {
		this.secondChargeEuro = secondChargeEuro;
	}
}
