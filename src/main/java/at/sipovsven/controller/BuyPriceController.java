package at.sipovsven.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.model.Category;
import at.sipovsven.model.Content;
import at.sipovsven.model.LabelModel;
import at.sipovsven.model.Product;
import at.sipovsven.model.Pump;
import at.sipovsven.model.Shipping;
import at.sipovsven.model.Wrapping;
import at.sipovsven.repository.CategoryRepositoryJPA;
import at.sipovsven.repository.ContentRepositoryJPA;
import at.sipovsven.repository.LabelRepositoryJPA;
import at.sipovsven.repository.ProductRepositoryJPA;
import at.sipovsven.repository.PumpRepositoryJPA;
import at.sipovsven.repository.ShippingRepositoryJPA;
import at.sipovsven.repository.WrappingRepositoryJPA;
import at.sipovsven.service.IOService;
import at.sipovsven.service.NotificationService;
import at.sipovsven.service.NumberFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class BuyPriceController {

	@FXML
	private Pane pane;

	@FXML
	private Region region;

	@FXML
	private ImageView imgLogo;

	@FXML
	private JFXButton buyPriceButton1;

	@FXML
	private JFXButton exportButton;

	@FXML
	private JFXButton ghCalculationButton1;

	@FXML
	private Label foldingClaspLabel;

	@FXML
	private Label labelLabel;

	@FXML
	private Label transportLabel;

	@FXML
	private Label contentLabel;

	@FXML
	private JFXButton updateComponentButton;

	@FXML
	private TextField productNameTxtField;

	@FXML
	private ChoiceBox<Category> categoryChoice;

	@FXML
	private TextField bruttPriceTxtField;

	@FXML
	private JFXButton addButton;

	@FXML
	private TextField buyPriceField;

	@FXML
	private Button addLabelBtn;

	@FXML
	private Button addWrappingBtn;

	@FXML
	private Button addContentBtn;

	@FXML
	private Button addPumpBtn;

	@FXML
	private Button addShippingBtn;

	@FXML
	private Button addCategoryBtn;

	@FXML
	private JFXButton deleteCategoryBtn;

	@FXML
	private JFXButton deleteShippingBtn;

	@FXML
	private JFXButton deletePumpBtn;

	@FXML
	private JFXButton deleteLabelBtn;

	@FXML
	private JFXButton deleteWrappingBtn;

	@FXML
	private JFXButton deleteContentBtn;

	@FXML
	private JFXButton refreshBtn;

	@FXML
	private Label categoryLbl;

	@FXML
	private TextField searchBar;

	@FXML
	private ListView<String> componentListView;

	@FXML
	private TableView<LabelModel> labelView;

	@FXML
	private TableColumn<LabelModel, String> columnLabelName;

	@FXML
	private TableColumn<LabelModel, Double> columnLabelPrice;

	@FXML
	private TableView<Content> contentView;

	@FXML
	private TableColumn<Content, String> columnContentName;

	@FXML
	private TableColumn<Content, Double> columnContentPrice;

	@FXML
	private TableView<Product> productView;

	@FXML
	private TableColumn<Product, String> columnProductName;

	@FXML
	private TableColumn<Product, Double> columnProductSize;

	@FXML
	private TableColumn<Product, Double> columnProductBuyPrice;

	@FXML
	private TableView<Wrapping> wrappingView;

	@FXML
	private TableColumn<Wrapping, String> columnWrapName;

	@FXML
	private TableColumn<Wrapping, Double> columnWrapPrice;

	@FXML
	private TableView<Pump> pumpView;

	@FXML
	private TableColumn<Pump, String> columnPumpName;

	@FXML
	private TableColumn<Pump, Double> columnPumpPrice;

	@FXML
	private TableView<Shipping> shippingView;

	@FXML
	private TableColumn<Shipping, String> columnShippingName;

	@FXML
	private TableColumn<Shipping, Double> columnShippingPrice;

	@FXML
	private TableView<Category> categoryListView;

	@FXML
	private TableColumn<Category, String> columnCategoryName;

	@FXML
	private ChoiceBox<String> weightBox;

	@FXML
	private CheckBox grammChecker;

	@FXML
	private CheckBox mlChecker;

	ProductRepositoryJPA prodRepo = new ProductRepositoryJPA();
	ContentRepositoryJPA contentRepo = new ContentRepositoryJPA();
	LabelRepositoryJPA labelRepo = new LabelRepositoryJPA();
	WrappingRepositoryJPA wrapRepo = new WrappingRepositoryJPA();
	PumpRepositoryJPA pumpRepo = new PumpRepositoryJPA();
	ShippingRepositoryJPA shipRepo = new ShippingRepositoryJPA();
	CategoryRepositoryJPA cateRepo = new CategoryRepositoryJPA();

	NumberFormat df = new NumberFormat();
	SceneSwitcher ss = new SceneSwitcher();
	NotificationService notif = new NotificationService();
	IOService io = new IOService();

	double finalPrice;
	LabelModel label = new LabelModel();
	LabelModel zeroLabel = new LabelModel("Zero", 0);
	Content zeroContent = new Content("Zero", 0);
	Shipping zeroShipping = new Shipping("Zero", 0);
	Wrapping zeroWrap = new Wrapping("Zero", 0);
	Pump zeroPump = new Pump("Zero", 0);

	List<String> categoryL = new ArrayList<String>();
	List<String> weightL = new ArrayList<String>();
	private ObservableList<Product> productList = FXCollections.observableArrayList(prodRepo.getAllProducts());
	private ObservableList<Content> contentList = FXCollections.observableArrayList(contentRepo.getAllContent());
	private ObservableList<LabelModel> labelList = FXCollections.observableArrayList(labelRepo.getAllLabels());
	private ObservableList<Wrapping> wrapList = FXCollections.observableArrayList(wrapRepo.getAllWraps());
	private ObservableList<Pump> pumpList = FXCollections.observableArrayList(pumpRepo.getAllPumps());
	private ObservableList<Shipping> shipList = FXCollections.observableArrayList(shipRepo.getAllShippings());
	private ObservableList<Category> cateList = FXCollections.observableArrayList(cateRepo.getAllCategories());
	private ObservableList<String> componentList = FXCollections.observableArrayList();

	@FXML
	void initialize() {

		if (labelRepo.getAllLabels().isEmpty()) {
			labelRepo.addLabel(zeroLabel);
		}

		if (contentRepo.getAllContent().isEmpty()) {
			contentRepo.addContent(zeroContent);
		}

		if (shipRepo.getAllShippings().isEmpty()) {
			shipRepo.addShipping(zeroShipping);
		}

		if (wrapRepo.getAllWraps().isEmpty()) {
			wrapRepo.addWrap(zeroWrap);
		}

		if (pumpRepo.getAllPumps().isEmpty()) {
			pumpRepo.addPump(zeroPump);
		}

		ObservableList<String> weightList = FXCollections.observableArrayList("1","30", "50", "100", "200", "200", "300",
				"400", "500", "600", "700", "800", "900", "1000"

		);
		weightBox.setItems(weightList);
		weightBox.show();
		
		initTableViews();

		editableCells();

		listenForCellEdit();

	}

	private void initTableViews() {
		columnProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		columnProductSize.setCellValueFactory(new PropertyValueFactory<Product, Double>("weight"));
		columnProductBuyPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("purchasePrice"));

		productView.setItems(productList);

		columnContentName.setCellValueFactory(new PropertyValueFactory<Content, String>("Name"));
		columnContentPrice.setCellValueFactory(new PropertyValueFactory<Content, Double>("Price"));

		contentView.setItems(contentList);

		columnLabelName.setCellValueFactory(new PropertyValueFactory<LabelModel, String>("Name"));
		columnLabelPrice.setCellValueFactory(new PropertyValueFactory<LabelModel, Double>("Price"));

		labelView.setItems(labelList);

		columnWrapName.setCellValueFactory(new PropertyValueFactory<Wrapping, String>("name"));
		columnWrapPrice.setCellValueFactory(new PropertyValueFactory<Wrapping, Double>("Price"));

		wrappingView.setItems(wrapList);

		columnShippingName.setCellValueFactory(new PropertyValueFactory<Shipping, String>("name"));
		columnShippingPrice.setCellValueFactory(new PropertyValueFactory<Shipping, Double>("Price"));

		shippingView.setItems(shipList);

		columnPumpName.setCellValueFactory(new PropertyValueFactory<Pump, String>("name"));
		columnPumpPrice.setCellValueFactory(new PropertyValueFactory<Pump, Double>("Price"));

		pumpView.setItems(pumpList);

		columnCategoryName.setCellValueFactory(new PropertyValueFactory<Category, String>("Name"));
		categoryListView.setItems(cateList);
	}

	private void editableCells() {

		labelView.setEditable(true);
		labelView.getSelectionModel().setCellSelectionEnabled(true);
		wrappingView.setEditable(true);
		wrappingView.getSelectionModel().setCellSelectionEnabled(true);
		contentView.setEditable(true);
		contentView.getSelectionModel().setCellSelectionEnabled(true);
		pumpView.setEditable(true);
		pumpView.getSelectionModel().setCellSelectionEnabled(true);
		shippingView.setEditable(true);
		shippingView.getSelectionModel().setCellSelectionEnabled(true);
		categoryListView.setEditable(true);
		categoryListView.getSelectionModel().setCellSelectionEnabled(true);

		columnLabelName.setCellFactory(TextFieldTableCell.forTableColumn());
		columnWrapName.setCellFactory(TextFieldTableCell.forTableColumn());
		columnContentName.setCellFactory(TextFieldTableCell.forTableColumn());
		columnPumpName.setCellFactory(TextFieldTableCell.forTableColumn());
		columnShippingName.setCellFactory(TextFieldTableCell.forTableColumn());
		columnLabelPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		columnWrapPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		columnContentPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		columnPumpPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		columnShippingPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

	}

	private double setUpdatedPurchasePrice(Product p) {
		return p.getContent().getPrice() + p.getLabel().getPrice() + p.getShipping().getPrice() + p.getPump().getPrice()
				+ p.getWrapping().getPrice();
	};

	// updates Product on ComponentChange
	private void changeProducts(List<Product> productList) {
		for (Product p : productList) {
			p.setPurchasePrice(setUpdatedPurchasePrice(p));
			p = calculateCellOnUpdate(p, p.getPurchasePrice(), p.getSalonPrice(), p.getBruttPrice());
			prodRepo.updateProduct(p);
		}
	};

	/*
	 * Listeners for editing the components Cells
	 * 
	 * 
	 */
	private void listenForCellEdit() {

		columnLabelPrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<LabelModel, Double>>() {

			@Override
			public void handle(CellEditEvent<LabelModel, Double> t) {
				// TODO Auto-generated method stub

				LabelModel label = labelView.getSelectionModel().getSelectedItem();
				label.setPrice(t.getNewValue());
				labelRepo.updateLabel(label);

				List<Product> productList = prodRepo
						.getProductsByLabel(labelView.getSelectionModel().getSelectedItem());

				changeProducts(productList);

			}

		});
		columnLabelName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<LabelModel, String>>() {

			@Override
			public void handle(CellEditEvent<LabelModel, String> t) {

				LabelModel label = labelView.getSelectionModel().getSelectedItem();
				label.setName(t.getNewValue());
				labelRepo.updateLabel(label);

			}
		});

		columnContentPrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Content, Double>>() {

			@Override
			public void handle(CellEditEvent<Content, Double> t) {
				// TODO Auto-generated method stub

				Content content = contentView.getSelectionModel().getSelectedItem();
				content.setPrice(t.getNewValue());
				contentRepo.updateContent(content);

				List<Product> productList = prodRepo
						.getProductsByContent(contentView.getSelectionModel().getSelectedItem());

				changeProducts(productList);

			}
		});

		columnContentName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Content, String>>() {

			@Override
			public void handle(CellEditEvent<Content, String> t) {

				Content content = contentView.getSelectionModel().getSelectedItem();
				content.setName(t.getNewValue());
				contentRepo.updateContent(content);

			}
		});

		columnWrapPrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Wrapping, Double>>() {

			@Override
			public void handle(CellEditEvent<Wrapping, Double> t) {
				// TODO Auto-generated method stub

				Wrapping wrapping = wrappingView.getSelectionModel().getSelectedItem();
				wrapping.setPrice(t.getNewValue());
				wrapRepo.updateWrap(wrapping);

				List<Product> productList = prodRepo
						.getProductsByWrapping(wrappingView.getSelectionModel().getSelectedItem());

				changeProducts(productList);

			}
		});

		columnWrapName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Wrapping, String>>() {

			@Override
			public void handle(CellEditEvent<Wrapping, String> t) {

				Wrapping wrapping = wrappingView.getSelectionModel().getSelectedItem();
				wrapping.setName(t.getNewValue());
				wrapRepo.updateWrap(wrapping);

			}
		});

		columnPumpPrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pump, Double>>() {

			@Override
			public void handle(CellEditEvent<Pump, Double> t) {
				// TODO Auto-generated method stub

				Pump pump = pumpView.getSelectionModel().getSelectedItem();
				pump.setPrice(t.getNewValue());
				pumpRepo.updatePump(pump);

				List<Product> productList = prodRepo.getProductsByPump(pumpView.getSelectionModel().getSelectedItem());

				changeProducts(productList);

			}
		});

		columnPumpName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pump, String>>() {

			@Override
			public void handle(CellEditEvent<Pump, String> t) {

				Pump pump = pumpView.getSelectionModel().getSelectedItem();
				pump.setName(t.getNewValue());
				pumpRepo.updatePump(pump);
			}
		});

		columnShippingPrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Shipping, Double>>() {

			@Override
			public void handle(CellEditEvent<Shipping, Double> t) {
				// TODO Auto-generated method stub

				Shipping shipping = shippingView.getSelectionModel().getSelectedItem();
				shipping.setPrice(t.getNewValue());
				shipRepo.updateShipping(shipping);

				List<Product> productList = prodRepo
						.getProductsByShipping(shippingView.getSelectionModel().getSelectedItem());

				changeProducts(productList);

			}
		});

		columnShippingName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Shipping, String>>() {

			@Override
			public void handle(CellEditEvent<Shipping, String> t) {

				Shipping shipping = shippingView.getSelectionModel().getSelectedItem();
				shipping.setName(t.getNewValue());
				shipRepo.updateShipping(shipping);
			}
		});

		columnCategoryName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Category, String>>() {

			@Override
			public void handle(CellEditEvent<Category, String> t) {

				Category category = categoryListView.getSelectionModel().getSelectedItem();
				category.setName(t.getNewValue());
				cateRepo.updateCategory(category);
			}
		});

	}

	@FXML
	void clickOnTable(MouseEvent event) throws NullPointerException {

		componentList.clear();

		Product p = productView.getSelectionModel().getSelectedItem();
		
		componentList.add("Etikett:  ");
		componentList.add(p.getLabel().getName() + "     " + "Preis: " + String.valueOf(p.getLabel().getPrice()));

		componentList.add("");
		componentList.add("Inhalt:  ");
		componentList.add(p.getContent().getName() + "     " + "Preis: " + String.valueOf(p.getContent().getPrice()));

		componentList.add("");
		componentList.add("Transport:  ");
		componentList.add(p.getShipping().getName() + "     " + "Preis: " + String.valueOf(p.getShipping().getPrice()));

		componentList.add("");
		componentList.add("Pumpe/Verschluß:  ");
		componentList.add(p.getPump().getName() + "     " + "Preis: " + String.valueOf(p.getPump().getPrice()));

		componentList.add("");
		componentList.add("Verpackung:  ");
		componentList.add(p.getWrapping().getName() + "     " + "Preis: " + String.valueOf(p.getWrapping().getPrice()));

		componentList.add("");
		componentList.add("Einkaufspreis: " + String.valueOf(p.getPurchasePrice()));

		componentListView.setItems(componentList);
	}

	@FXML
	void update(ActionEvent event) {
		label = labelView.getSelectionModel().getSelectedItem();
		Content content = contentView.getSelectionModel().getSelectedItem();
		Wrapping wrap = wrappingView.getSelectionModel().getSelectedItem();
		Pump pump = pumpView.getSelectionModel().getSelectedItem();
		Shipping ship = shippingView.getSelectionModel().getSelectedItem();

		Product product = productView.getSelectionModel().getSelectedItem();

		product.setLabel(label);
		product.setContent(content);
		product.setWrapping(wrap);
		product.setPump(pump);
		product.setShipping(ship);

		product.setPurchasePrice(df.DecimalFormater(
				content.getPrice() + label.getPrice() + wrap.getPrice() + pump.getPrice() + ship.getPrice()));

		product = calculateCellOnUpdate(product, product.getPurchasePrice(), product.getSalonPrice(),
				product.getBruttPrice());

		prodRepo.updateProduct(product);

		productView.setItems(productList);
		productView.refresh();

	}

	private Product calculateCellOnUpdate(Product p, double purchasePrice, double salonPrice, double customerPrice) {

		if (purchasePrice == 0) {

			p.setFirstChargeEuro(salonPrice);
			p.setFirstChargePercent(0);

		} else {

			p.setFirstChargeEuro(df.DecimalFormater(salonPrice - purchasePrice));
			p.setFirstChargePercent(df.DecimalFormaterSingleDigit((p.getFirstChargeEuro() / purchasePrice) * 100));

		}

		return p;
	}

	public void refresh() {

		labelList = FXCollections.observableArrayList(labelRepo.getAllLabels());
		labelView.setItems(labelList);
		labelView.refresh();

		productList = FXCollections.observableArrayList(prodRepo.getAllProducts());
		contentList = FXCollections.observableArrayList(contentRepo.getAllContent());
		labelList = FXCollections.observableArrayList(labelRepo.getAllLabels());
		wrapList = FXCollections.observableArrayList(wrapRepo.getAllWraps());
		pumpList = FXCollections.observableArrayList(pumpRepo.getAllPumps());
		shipList = FXCollections.observableArrayList(shipRepo.getAllShippings());
		cateList = FXCollections.observableArrayList(cateRepo.getAllCategories());

		productView.setItems(productList);
		productView.refresh();

		contentView.setItems(contentList);

		labelView.setItems(labelList);
		wrappingView.setItems(wrapList);
		pumpView.setItems(pumpList);
		shippingView.setItems(shipList);
		categoryListView.setItems(cateList);

	}

	@FXML
	void checkGram(MouseEvent event) {

		mlChecker.setSelected(false);

	}

	@FXML
	void checkMil(ActionEvent event) {

		grammChecker.setSelected(false);

	}

	@FXML
	void exportIO(ActionEvent event) throws SQLException, IOException {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		try {
			fileChooser.setInitialFileName("Herbanima-Produkte.csv");
			fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("CSV", "csv"));
			io.exportProducts(fileChooser.showSaveDialog(stage).getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void openBuyPrice(ActionEvent event) {

	}

	@FXML
	void add(ActionEvent event) {
		LabelModel label = labelView.getSelectionModel().getSelectedItem();
		Content content = contentView.getSelectionModel().getSelectedItem();
		Wrapping wrap = wrappingView.getSelectionModel().getSelectedItem();
		Pump pump = pumpView.getSelectionModel().getSelectedItem();
		Shipping shipping = shippingView.getSelectionModel().getSelectedItem();
		String weight = weightBox.getSelectionModel().getSelectedItem();
		

		weight = checkWeightType(weight);

		Product product = new Product();

		product.setName(productNameTxtField.getText());
		product.setBruttPrice(Double.valueOf(bruttPriceTxtField.getText()));
		product.setCategory(categoryListView.getSelectionModel().getSelectedItem().getName());
		product.setLabel(label);
		product.setContent(content);
		product.setWrapping(wrap);
		product.setPump(pump);
		product.setShipping(shipping);

		product.setPurchasePrice(product.calculatePurchasePrice(label.getPrice(), shipping.getPrice(),
				content.getPrice(), pump.getPrice(), wrap.getPrice()));
		product.setFirstChargeEuro(0);
		product.setFirstChargeEuro(1);
		product.setSecondChargeEuro(1);
		product.setSecondChargePercent(0);
		product.setSalonPrice(10);
		product.setWeight(weight);
		prodRepo.addProduct(product);

		productNameTxtField.clear();
		bruttPriceTxtField.clear();

		productList.clear();
		productList.addAll(prodRepo.getAllProducts());
		productView.getItems();

	}

	private String checkWeightType(String weight) {
		if (grammChecker.isSelected()) {
			weight = weightBox.getSelectionModel().getSelectedItem() + "g";
		}

		if (mlChecker.isSelected()) {
			weight = weightBox.getSelectionModel().getSelectedItem() + "ml";
		}
		return weight;
	}

	@FXML
	void clickRefresh(ActionEvent event) {
		refresh();
	}

	@FXML
	void addLabel(ActionEvent event) throws Exception {

		Stage stage = new Stage();
		ss.openNewWindow(stage, "CreateLabel", "Etikett hinzufügen");
		refresh();
	}

	@FXML
	void addWrap(ActionEvent event) throws Exception {

		Stage stage = new Stage();
		ss.openNewWindow(stage, "CreateWrapping", "Verpackung hinzufügen");
		BuyPriceController.this.refresh();

	}

	@FXML
	void addCategory(ActionEvent event) throws Exception {

		Stage stage = new Stage();
		ss.openNewWindow(stage, "CreateCategory", "Kategorie hinzufügen");
		refresh();
	}

	@FXML
	void addContent(ActionEvent event) throws Exception {

		Stage stage = new Stage();
		ss.openNewWindow(stage, "CreateContent", "Inhalt hinzufügen");
		refresh();
	}

	@FXML
	void addPump(ActionEvent event) throws Exception {
		Stage stage = new Stage();
		ss.openNewWindow(stage, "CreatePump", "Pumpe hinzufügen");
		refresh();
	}

	@FXML
	void addShipping(ActionEvent event) throws Exception {
		Stage stage = new Stage();
		ss.openNewWindow(stage, "CreateShipping", "Transport hinzufügen");
		refresh();
	}

	@FXML
	void delete(ActionEvent event) {
		if (productView.getSelectionModel().getSelectedItem().getId() > 0) {
			prodRepo.removeProduct(productView.getSelectionModel().getSelectedItem().getId());
			refresh();
		} else {

			notif.showErrorMessageOnPane("Kein Produkt ausgewählt", "Bitte noch einmal", pane);
		}
	}

	@FXML
	void deleteCategory(ActionEvent event) {

		if (categoryListView.getSelectionModel().getSelectedItem() == null) {
			notif.showErrorMessageOnPane("Keine Kategorie ausgewählt", "Bitte Kategorie auswählen", pane);
		} else {
			Category category = categoryListView.getSelectionModel().getSelectedItem();
			cateRepo.removeCategory(category.getId());
		}
		refresh();
	}

	@FXML
	void deleteContent(ActionEvent event) {

		if (contentView.getSelectionModel().getSelectedItem() != null
				&& contentView.getSelectionModel().getSelectedItem().getId() != 1) {
			Content content = contentView.getSelectionModel().getSelectedItem();
			List<Product> list = prodRepo.getProductsByContent(content);

			for (Product a : list) {
				a.setContent(contentRepo.getContentByID(1));

				a.setPurchasePrice(a.calculatePurchasePrice(a.getLabel().getPrice(), a.getShipping().getPrice(),
						a.getContent().getPrice(), a.getPump().getPrice(), a.getWrapping().getPrice()));

				prodRepo.updateProduct(a);
			}

			contentRepo.removeContent(content.getId());
		}

		else {
			notif.showErrorMessageOnPane("Fehler bei Löschung des Inhalts", "Inhalt kann nicht gelöscht werden!", pane);
		}
		refresh();
	}

	@FXML
	void deleteLabel(ActionEvent event) {
		if (labelView.getSelectionModel().getSelectedItem() != null
				&& labelView.getSelectionModel().getSelectedItem().getId() != 1) {
			LabelModel label = labelView.getSelectionModel().getSelectedItem();
			List<Product> list = prodRepo.getProductsByLabel(label);

			for (Product a : list) {
				a.setLabel(labelRepo.getLabelByID(1));

				a.setPurchasePrice(a.calculatePurchasePrice(a.getLabel().getPrice(), a.getShipping().getPrice(),
						a.getContent().getPrice(), a.getPump().getPrice(), a.getWrapping().getPrice()));

				a = calculateCellOnUpdate(a, a.getPurchasePrice(), a.getSalonPrice(), a.getBruttPrice());
				prodRepo.updateProduct(a);
			}

			labelRepo.removeLabel(label.getId());
		}

		else {
			notif.showErrorMessageOnPane("Fehler bei Löschung der Etikette", "Etikette kann nicht gelöscht werden!",
					pane);
		}
		refresh();
	}

	@FXML
	void deletePump(ActionEvent event) {

		if (pumpView.getSelectionModel().getSelectedItem() != null
				&& pumpView.getSelectionModel().getSelectedItem().getId() != 1) {
			Pump pump = pumpView.getSelectionModel().getSelectedItem();
			List<Product> list = prodRepo.getProductsByPump(pump);

			for (Product a : list) {
				a.setPump(pumpRepo.getPumpByID(1));

				a.setPurchasePrice(a.calculatePurchasePrice(a.getLabel().getPrice(), a.getShipping().getPrice(),
						a.getContent().getPrice(), a.getPump().getPrice(), a.getWrapping().getPrice()));

				prodRepo.updateProduct(a);
			}

			pumpRepo.removePump(pump.getId());
		}

		else {
			notif.showErrorMessageOnPane("Fehler bei Löschung der Pumpe", "Pumpe kann nicht gelöscht werden!", pane);
		}
		refresh();
	}

	@FXML
	void deleteShipping(ActionEvent event) {

		if (shippingView.getSelectionModel().getSelectedItem() != null
				&& shippingView.getSelectionModel().getSelectedItem().getId() != 1) {
			Shipping ship = shippingView.getSelectionModel().getSelectedItem();
			List<Product> list = prodRepo.getProductsByShipping(ship);

			for (Product a : list) {
				a.setShipping(shipRepo.getShippingByID(1));

				a.setPurchasePrice(a.calculatePurchasePrice(a.getLabel().getPrice(), a.getShipping().getPrice(),
						a.getContent().getPrice(), a.getPump().getPrice(), a.getWrapping().getPrice()));

				prodRepo.updateProduct(a);
			}

			shipRepo.removeShipping(ship.getId());
		}

		else {
			Shipping ship = shippingView.getSelectionModel().getSelectedItem();
			notif.showErrorMessageOnPane("Fehler bei Löschung der Transportkosten",
					ship.getName() + " kann nicht gelöscht werden!", pane);
		}
		refresh();
	}

	@FXML
	void deleteWrapping(ActionEvent event) {

		if (wrappingView.getSelectionModel().getSelectedItem() != null
				&& wrappingView.getSelectionModel().getSelectedItem().getId() != 1) {
			Wrapping wrap = wrappingView.getSelectionModel().getSelectedItem();
			List<Product> list = prodRepo.getProductsByWrapping(wrap);

			for (Product a : list) {
				a.setWrapping(wrapRepo.getWrapByID(1));

				a.setPurchasePrice(a.calculatePurchasePrice(a.getLabel().getPrice(), a.getShipping().getPrice(),
						a.getContent().getPrice(), a.getPump().getPrice(), a.getWrapping().getPrice()));

				prodRepo.updateProduct(a);
			}

			wrapRepo.removeWrapping(wrap.getId());
		}

		else {
			notif.showErrorMessageOnPane("Fehler bei Löschung der Verpackung", "Verpackung kann nicht gelöscht werden!",
					pane);
		}
		refresh();
	}

	@FXML
	void requestFocus(MouseEvent event) {
		this.pane.requestFocus();
	}

	@FXML
	void searchProduct(KeyEvent event) {
		FilteredList<Product> flProduct = new FilteredList<Product>(productList, p -> true);//Pass the data to a filtered list
        
        

		 searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
	          
	                    flProduct.setPredicate(p -> p.getName().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
	                    }
		
				 
				 );
		 
		 productView.setItems(flProduct);
		
		 if(searchBar.getText().equals(null) || searchBar.getText() == "") {
			 
			 productView.setItems(productList);
			 
		 }
		
	}

	@FXML
	void openMenu(ActionEvent event) throws IOException {
		System.gc();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScene.fxml"));
		Stage stage = (Stage) ghCalculationButton1.getScene().getWindow();
		Scene scene = new Scene(loader.load());
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		stage.setScene(scene);

	}

}
