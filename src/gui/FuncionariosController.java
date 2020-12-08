package gui;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Entities.Funcionarios;
import model.services.FuncionariosService;


public class FuncionariosController implements Initializable {

	private FuncionariosService service;

	@FXML
	private TableView<Funcionarios> tableViewFuncionarios;

	@FXML
	private TableColumn<Funcionarios, String> tableColumnNome;
	
	@FXML
	private TableColumn<Funcionarios, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Funcionarios, String> tableColumnFuncao;
	@FXML
	private TableColumn<Funcionarios, Double> tableColumnSalario;
	@FXML
	private TableColumn<Funcionarios, Integer> tableColumnCargaHoraria;
	
	@FXML
	private Button btNew;

	private ObservableList<Funcionarios> obsList;

	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/FuncionariosForm.fxml", parentStage);
	}

	public void setFuncionariosService(FuncionariosService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
		tableColumnSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
		tableColumnCargaHoraria.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewFuncionarios.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Funcionarios> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewFuncionarios.setItems(obsList);
	}
	
	
	private void createDialogForm(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Adicione os dados do funcionário");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
