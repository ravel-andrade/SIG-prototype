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
import model.Entities.Infraestrutura;
import model.services.InfraestruturaService;

public class InfraestruturaListController implements Initializable, DataChangeListener {

	private InfraestruturaService service;

	@FXML
	private TableView<Infraestrutura> tableViewInfraestrutura;
	
	@FXML
	private TableColumn<Infraestrutura, Integer> tableColumnId;


	@FXML
	private TableColumn<Infraestrutura, String> tableColumnDescricao;

	@FXML
	private TableColumn<Infraestrutura, Integer> tableColumnQuantidade;

	@FXML
	private TableColumn<Infraestrutura, Double> tableColumnValorUN;
	
	@FXML
	private TableColumn<Infraestrutura, Infraestrutura> tableColumnEDIT;
	
	@FXML
	private TableColumn<Infraestrutura, Infraestrutura> tableColumnREMOVE;

	@FXML
	private Button btNew;

	private ObservableList<Infraestrutura> obsList;

	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Infraestrutura obj = new Infraestrutura();
		createDialogForm(obj, "/gui/InfraestruturaForm.fxml", parentStage);
	}

	public void setInfraestruturaService(InfraestruturaService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tableColumnValorUN.setCellValueFactory(new PropertyValueFactory<>("valorUN"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewInfraestrutura.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Infraestrutura> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewInfraestrutura.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}

	private void createDialogForm(Infraestrutura obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			InfraestruturaFormController controller = loader.getController();
			controller.setInfraestrutura(obj);
			controller.setInfraestruturaService(new InfraestruturaService());
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
		tableColumnEDIT.setCellFactory(param -> new TableCell<Infraestrutura, Infraestrutura>() {
			private final Button button = new Button("Editar");

			@Override
			protected void updateItem(Infraestrutura obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/InfraestruturaForm.fxml", Utils.currentStage(event)));
			}
		});
	}
	
	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Infraestrutura, Infraestrutura>() {
			private final Button button = new Button("Remover");

			@Override
			protected void updateItem(Infraestrutura obj, boolean empty) {
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

	private void removeEntity(Infraestrutura obj) {
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
