package at.sipovsven.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTreeTableView;

import at.sipovsven.application.App;
import at.sipovsven.model.Product;
import at.sipovsven.repository.ContentRepositoryJPA;
import at.sipovsven.repository.ProductRepositoryJPA;
import at.sipovsven.service.IOService;
import at.sipovsven.service.Listener;
import at.sipovsven.service.NumberFormat;
import at.sipovsven.service.Sceneswitcher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class MainController<T, S> extends App {

	@FXML
	private Pane backGround;
	
    @FXML
    private AnchorPane anchorPane;

	@FXML
	private Region region;
	@FXML
	private ImageView imgLogo;

	@FXML
	private JFXButton buyPriceButton;

	@FXML
	private JFXButton exportButton;

	@FXML
	private JFXButton ghCalculationButton;

	@FXML
	private JFXButton updateButton;

	@FXML
	private JFXButton exitBtn;
	
    @FXML
    private TextField searchBar;

	@FXML
	private TableView<Product> tableview;

	@FXML
	private TableColumn<Product, String> columCategory;

	@FXML
	private TableColumn<Product, String> columProduct;

	@FXML
	private TableColumn<Product, String> columnWeight;

	@FXML
	private TableColumn<Product, Double> columBuyPrice;

	@FXML
	private TableColumn<Product, Double> columFirstChrgPercent;

	@FXML
	private TableColumn<Product, Double> columFirstChrgEuro;

	@FXML
	private TableColumn<Product, Double> columSalonPrice;

	@FXML
	private TableColumn<Product, Double> columScndChrgPercent;

	@FXML
	private TableColumn<Product, Double> columScdChrgEuro;

	@FXML
	private TableColumn<Product, Double> cloumnExldTax;

	@FXML
	private TableColumn<Product, Double> columnTax;

	@FXML
	private TableColumn<Product, Double> columnCustomerPrice;

	@FXML
	private JFXTreeTableView<Product> jfxView;

	ProductRepositoryJPA productRepo = new ProductRepositoryJPA();
	ContentRepositoryJPA contentRepo = new ContentRepositoryJPA();
	Sceneswitcher sceneswitcher = new Sceneswitcher();
	Boolean pressed = true;
	private ObservableList<Product> productList = FXCollections.observableArrayList();
	Stage stage = new Stage();
	NumberFormat df = new NumberFormat();
	Listener listener = new Listener();
	IOService io = new IOService();

	double decrease;

	double purchasePrice;

	double salonPrice;

	protected double newValue;

	protected double withoutTax;

	protected double taxEuro;

	protected double secondChargeEuro;

	protected double secondChargePercent;

	protected double customerPrice;

	protected double customerPrices;

	protected double firstChargeEuro;

	protected double firstChargePercent;

	protected double firstCharge;

	@FXML
	void initialize() {
		
		
		productList = FXCollections.observableArrayList(productRepo.getAllProducts());
		tableview.setItems(productList);

		
		tableview.setEditable(true);
		tableview.getSelectionModel().setCellSelectionEnabled(true);

		
		initTable();

		editableCells();
		
		listenForCellEdit();
		
		setStyleColumns();
		
	}

	private void setStyleColumns() {
		columProduct.setStyle("-fx-font-family: helvetica;-fx-font-size: 14px;-fx-font-weight: bold; -fx-text-fill: black");
		columSalonPrice.setStyle("-fx-font-family: helvetica;-fx-font-size: 16px;-fx-font-weight: bold; -fx-text-fill: #D80032");
		columBuyPrice.setStyle("-fx-font-family: helvetica;-fx-font-size: 16px;-fx-font-weight: bold; -fx-text-fill: #D80032");
		columnCustomerPrice.setStyle("-fx-font-family: helvetica;-fx-font-size: 16px;-fx-font-weight: bold; -fx-text-fill: #D80032");
	}

	private void initTable() {

		columProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		columBuyPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("purchasePrice"));
		columCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		columnWeight.setCellValueFactory(new PropertyValueFactory<Product, String>("weight"));
		columFirstChrgEuro.setCellValueFactory(new PropertyValueFactory<Product, Double>("firstChargeEuro"));
		columFirstChrgPercent.setCellValueFactory(new PropertyValueFactory<Product, Double>("firstChargePercent"));
		columSalonPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("salonPrice"));
		columScdChrgEuro.setCellValueFactory(new PropertyValueFactory<Product, Double>("secondChargeEuro"));
		columScndChrgPercent.setCellValueFactory(new PropertyValueFactory<Product, Double>("secondChargePercent"));
		cloumnExldTax.setCellValueFactory(new PropertyValueFactory<Product, Double>("netPrice"));
		columnTax.setCellValueFactory(new PropertyValueFactory<Product, Double>("taxEuro"));
		columnCustomerPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("bruttPrice"));
	}

	private void editableCells() {

		columProduct.setCellFactory(TextFieldTableCell.forTableColumn());
		columCategory.setCellFactory(TextFieldTableCell.forTableColumn());
		columFirstChrgEuro.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		columFirstChrgPercent.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		columSalonPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		columScdChrgEuro.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		columScndChrgPercent.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		cloumnExldTax.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		columnTax.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		columnCustomerPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

	}
	
	
	
	private void listenForCellEdit() {
		
		columProduct.setOnEditCommit(
				
				new EventHandler<TableColumn.CellEditEvent<Product, String>>(){
					
					@Override
					public void handle(CellEditEvent<Product, String> t) {
						
						Product p = ((Product) t.getTableView().getItems().get(t.getTablePosition().getRow()));
						String newName = t.getNewValue();
						p.setName(newName);
						
						productRepo.updateProduct(p);
					}
					
				}
				
				
				);
		
		
	columFirstChrgPercent.setOnEditCommit(
				
				new EventHandler<TableColumn.CellEditEvent<Product, Double>>(){
					
					@Override
					public void handle(CellEditEvent<Product, Double> t) {
						
						Product d = ((Product) t.getTableView().getItems().get(t.getTablePosition().getRow()));
						Double newValue = t.getNewValue();
						
						newValue = t.getNewValue();
						purchasePrice = d.getPurchasePrice();
						firstChargePercent = newValue;
						firstChargeEuro = (purchasePrice / 100) * firstChargePercent;
						salonPrice = purchasePrice + firstChargeEuro;
						
						d.setFirstChargePercent(df.DecimalFormater(firstChargePercent));
						d.setSalonPrice(df.DecimalFormater(salonPrice));
						d.setFirstChargeEuro(df.DecimalFormater(firstChargeEuro));

						productRepo.updateProduct(d);
					}
				});
		
		columnCustomerPrice.setOnEditCommit(

				new EventHandler<TableColumn.CellEditEvent<Product, Double>>() {
					@Override
					public void handle(CellEditEvent<Product, Double> t) {

						Product d = ((Product) t.getTableView().getItems().get(t.getTablePosition().getRow()));
						newValue = t.getNewValue();
						salonPrice = d.getSalonPrice();

						withoutTax = newValue / 100 * 83.5;
						taxEuro = newValue / 100 * 16.7;
						secondChargeEuro = withoutTax - salonPrice;
						secondChargePercent = df.DecimalFormater(secondChargeEuro / salonPrice * 100);

						d.setSecondChargeEuro(df.DecimalFormater(secondChargeEuro));
						d.setSecondChargePercent(df.DecimalFormaterSingleDigit(secondChargePercent));
						d.setBruttPrice(df.DecimalFormater(newValue));
						d.setNetPrice(df.DecimalFormaterSingleDigit(withoutTax));
						d.setTaxEuro(df.DecimalFormaterSingleDigit(taxEuro));

						productRepo.updateProduct(d);
					}
				});

		columScdChrgEuro.setOnEditCommit(

				new EventHandler<TableColumn.CellEditEvent<Product, Double>>() {
					@Override
					public void handle(CellEditEvent<Product, Double> t) {

						Product d = ((Product) t.getTableView().getItems().get(t.getTablePosition().getRow()));
						secondChargeEuro = t.getNewValue();
						salonPrice = d.getSalonPrice();

						withoutTax = df.DecimalFormater(salonPrice + secondChargeEuro);
						customerPrice = df.DecimalFormaterSingleDigit(withoutTax * 1.195);

						secondChargePercent = df.DecimalFormater(secondChargeEuro / salonPrice * 100);

						taxEuro = df.DecimalFormater(customerPrice - withoutTax);

						d.setBruttPrice(df.DecimalFormater(customerPrice));
						d.setSecondChargeEuro(df.DecimalFormater(secondChargeEuro));
						d.setSecondChargePercent(df.DecimalFormaterSingleDigit(secondChargePercent));
						d.setNetPrice(df.DecimalFormaterSingleDigit(withoutTax));
						d.setTaxEuro(df.DecimalFormaterSingleDigit(taxEuro));

						productRepo.updateProduct(d);

						tableview.refresh();

					}
				});
		
			columScndChrgPercent.setOnEditCommit(
				
				new EventHandler<TableColumn.CellEditEvent<Product, Double>>(){
					
					@Override
					public void handle(CellEditEvent<Product, Double> t) {
						
						
						
						Product d = ((Product) t.getTableView().getItems().get(t.getTablePosition().getRow()));
						Double newValue = t.getNewValue();
						
						newValue = t.getNewValue();
						salonPrice = d.getSalonPrice();
						secondChargePercent = newValue;
						secondChargeEuro = (salonPrice / 100) * secondChargePercent;
						customerPrice = (salonPrice + secondChargeEuro) * 1.2;
						
						withoutTax = customerPrice / 100 * 83.4;
						taxEuro = customerPrice / 100 * 16.7;
						
						d.setSecondChargePercent(df.DecimalFormater(secondChargePercent));
						d.setBruttPrice(df.DecimalFormaterSingleDigit(customerPrice));
						d.setSecondChargeEuro(df.DecimalFormater(secondChargeEuro));
						d.setNetPrice(df.DecimalFormaterSingleDigit(withoutTax));
						d.setTaxEuro(df.DecimalFormaterSingleDigit(taxEuro));
						
						productRepo.updateProduct(d);

						tableview.refresh();
					}
				});
			
			
		columSalonPrice.setOnEditCommit(

				new EventHandler<TableColumn.CellEditEvent<Product, Double>>() {
					@Override
					public void handle(CellEditEvent<Product, Double> t) {

						Product d = ((Product) t.getTableView().getItems().get(t.getTablePosition().getRow()));
						salonPrice = t.getNewValue();
						customerPrices = df.DecimalFormater(d.getBruttPrice());
						customerPrice = df.DecimalFormater(customerPrices);
						withoutTax = df.DecimalFormater(customerPrice * 0.835);

						
						secondChargeEuro = df.DecimalFormater(withoutTax - salonPrice);
						secondChargePercent = df.DecimalFormater((secondChargeEuro / salonPrice * 100));

						taxEuro = customerPrice / 100 * 16.7;
						firstChargeEuro = df.DecimalFormater(salonPrice - d.getPurchasePrice());
						firstChargePercent = df.DecimalFormater(firstChargeEuro / d.getPurchasePrice() * 100);



						d.setFirstChargeEuro(df.DecimalFormater(firstChargeEuro));
						d.setFirstChargePercent(df.DecimalFormaterSingleDigit(firstChargePercent));
						d.setSalonPrice(df.DecimalFormater(salonPrice));
						d.setBruttPrice(df.DecimalFormaterSingleDigit(customerPrice));
						d.setSecondChargeEuro(df.DecimalFormater(secondChargeEuro));
						d.setSecondChargePercent(df.DecimalFormaterSingleDigit((secondChargePercent)));
						d.setNetPrice(df.DecimalFormaterSingleDigit(withoutTax));
						d.setTaxEuro(df.DecimalFormaterSingleDigit(taxEuro));

						productRepo.updateProduct(d);

						tableview.refresh();

					}
				});

		columFirstChrgEuro.setOnEditCommit(

				new EventHandler<TableColumn.CellEditEvent<Product, Double>>() {

					@Override
					public void handle(CellEditEvent<Product, Double> t) {
						Product d = ((Product) t.getTableView().getItems().get(t.getTablePosition().getRow()));

						newValue = t.getNewValue();
						purchasePrice = d.getPurchasePrice();
						firstCharge = t.getNewValue();
						salonPrice = purchasePrice + newValue;
						firstChargePercent = firstCharge / purchasePrice * 100;

						columFirstChrgPercent.getTableView().getSelectionModel().getSelectedItem()
								.setFirstChargePercent(firstChargePercent);
						columSalonPrice.getTableView().getSelectionModel().getSelectedItem()
								.setSalonPrice(purchasePrice + newValue);

						d.setFirstChargePercent(df.DecimalFormaterSingleDigit(columFirstChrgPercent.getTableView()
								.getSelectionModel().getSelectedItem().getFirstChargePercent()));
						d.setFirstChargeEuro(df.DecimalFormater(newValue));
						d.setSalonPrice(df.DecimalFormater(
								columSalonPrice.getTableView().getSelectionModel().getSelectedItem().getSalonPrice()));

						productRepo.updateProduct(d);

					}
				})

		;
	}

	


	@FXML
	void openBuyPrice(ActionEvent event) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BuyPrice.fxml"));
		this.stage= (Stage) buyPriceButton.getScene().getWindow();
		Scene scene = new Scene(loader.load());
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		stage.setScene(scene);

		System.gc();
	}

	

	
	
    @FXML
    void searchProduct(KeyEvent event)  {

		
        FilteredList<Product> flProduct = new FilteredList<Product>(productList, p -> true);//Pass the data to a filtered list
        
        

		 searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
	          
	                    flProduct.setPredicate(p -> p.getName().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
	                    }
		
				 
				 );
		 
		 tableview.setItems(flProduct);
		
		 if(searchBar.getText().equals(null) || searchBar.getText() == "") {
			 
			 tableview.setItems(productList);
			 
		 }
		
    }

	
	@FXML
	void clearMouse(MouseEvent event) {
		this.backGround.requestFocus();
		this.backGround.toBack();

	}

	@FXML
	void exportIO(ActionEvent event) {
		try {
			Stage stage = new Stage();
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialFileName("Herbanima-Produkte.csv");
			fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(
		            "CSV", "csv"));
			io.exportProducts(fileChooser.showSaveDialog(stage).getAbsolutePath());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	

}