package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Entities.Funcionarios;

public class CustoVariavelIndiretoController implements Initializable{
	@FXML
	private TableView<Funcionarios> tableViewFuncionarios;
	@FXML
	private TableColumn<Funcionarios, Integer> tableColumnId;
	@FXML
	private TableColumn<Funcionarios, String> tableColumnNome;
	@FXML
	private TableColumn<Funcionarios, String> tableColumnFuncao;
	@FXML
	private TableColumn<Funcionarios, Double> tableColumnSalario;
	@FXML
	private TableColumn<Funcionarios, Integer> tableColumnCargaHoraria;
	@FXML
	private Button btNew;
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
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
		
		Stage stage =(Stage) Main.getMainScene().getWindow();
		tableViewFuncionarios.prefHeightProperty().bind(stage.heightProperty());
	}

	
}
