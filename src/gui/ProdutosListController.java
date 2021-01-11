package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Entities.Produtos;
import model.services.ProdutosService;

public class ProdutosListController implements Initializable, DataChangeListener {

	private ProdutosService service;

	@FXML
	private TableView<Produtos> tableViewProdutos;

	@FXML
	private TableColumn<Produtos, String> tableColumnNome;

	@FXML
	private TableColumn<Produtos, Integer> tableColumnId;

	@FXML
	private TableColumn<Produtos, Double> tableColumnCustoVarDireto;
	
	@FXML
	private TableColumn<Produtos, Double> tableColumnCustoVarIndireto;
	
	@FXML
	private TableColumn<Produtos, Produtos> tableColumnEDIT;
	
	@FXML
	private TableColumn<Produtos, Produtos> tableColumnREMOVE;

	@FXML
	private Button btNew;

	private ObservableList<Produtos> obsList;

	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Produtos obj = new Produtos();
		createDialogForm(obj, "/gui/ProdutosForm.fxml", parentStage);
	}

	public void setProdutosService(ProdutosService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		tableColumnCustoVarDireto.setCellValueFactory(new PropertyValueFactory<>("custoVarDireto"));
		tableColumnCustoVarIndireto.setCellValueFactory(new PropertyValueFactory<>("custoVarIndireto"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewProdutos.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Produtos> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewProdutos.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}

	private void createDialogForm(Produtos obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ProdutosFormController controller = loader.getController();
			controller.setProdutos(obj);
			controller.setProdutosService(new ProdutosService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Adicione os dados do funcionário");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();

	}

	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Produtos, Produtos>() {
			private final Button button = new Button("Editar");

			@Override
			protected void updateItem(Produtos obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/ProdutosForm.fxml", Utils.currentStage(event)));
			}
		});
	}
	
	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Produtos, Produtos>() {
			private final Button button = new Button("Remover");

			@Override
			protected void updateItem(Produtos obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}

	private void removeEntity(Produtos obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you sure to delete?");

		if (result.get() == ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Service was null");
			}
			try {
				service.remove(obj);
				updateTableView();
			}
			catch (DbIntegrityException e) {
				Alerts.showAlert("Error removing object", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}
}
