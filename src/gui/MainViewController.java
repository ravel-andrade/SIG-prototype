package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemFuncionarioCadastrar;
	@FXML
	private MenuItem menuItemFuncionarioListar;
	@FXML
	private MenuItem menuItemProdutosCadastrar;
	@FXML
	private MenuItem menuItemProdutosListar;
	@FXML
	private MenuItem menuItemInfraestruturaCadastrar;
	@FXML
	private MenuItem menuItemInfraestruturaListar;
	@FXML
	private MenuItem menuItemCustosFixo;
	@FXML
	private MenuItem menuItemCustosVariavelDireto;
	@FXML
	private MenuItem menuItemCustosVariavelIndireto;
	@FXML
	private MenuItem menuItemSobre;

	@FXML
	public void onMenuItemFuncionarioCadastrarAction() {
		System.out.println("onMenuItemFuncionarioCadastrarAction");

	}

	@FXML
	public void onMenuItemFuncionarioListarAction() {
		loadView("/gui/FuncionariosList.fxml");

	}

	public void onMenuItemProdutosCadastrarAction() {
		System.out.println("onMenuItemProdutosCadastrarAction");

	}

	@FXML
	public void onMenuItemProdutosListarAction() {
		System.out.println("onMenuItemProdutosListarAction");

	}

	@FXML
	public void onMenuItemInfraestruturaCadastrarAction() {
		System.out.println("onMenuItemInfraestruturaCadastrarAction");

	}

	@FXML
	public void onMenuItemInfraestruturaListarAction() {
		System.out.println("onMenuItemInfraestruturaListarAction");

	}

	@FXML
	public void onMenuItemCustosFixoAction() {
		System.out.println("onMenuItemCustosFixoAction");

	}

	@FXML
	public void onMenuItemCustosVariavelDiretoAction() {
		System.out.println("onMenuItemCustosVariavelDiretoAction");

	}
	
	@FXML
	public void onMenuItemCustosVariavelIndiretoAction() {
		System.out.println("onMenuItemCustosVariavelIndiretoAction");

	}
	
	@FXML
	public void onMenuItemSobreAction() {
		loadView("/gui/Sobre.fxml");

	}

	public void initialize(URL uri, ResourceBundle rb) {

	}
	
	private synchronized void loadView(String absoluteName) {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVBox = loader.load();
		Scene mainScene = Main.getMainScene();
		VBox mainVBox =(VBox)((ScrollPane) mainScene.getRoot()).getContent();
		
		Node mainMenu = mainVBox.getChildren().get(0);
		mainVBox.getChildren().clear();
		mainVBox.getChildren().add(mainMenu);
		mainVBox.getChildren().addAll(newVBox.getChildren());
		}
		catch(IOException e) {
			Alerts.showAlert("Erro de IO", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}
		
	}

}
