package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.FuncionariosService;
import model.services.InfraestruturaService;
import model.services.ProdutosService;

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
		loadView("/gui/FuncionariosList.fxml", (FuncionariosListController controller) -> {
			controller.setFuncionariosService(new FuncionariosService());
			controller.updateTableView();
		});
	}

	public void onMenuItemProdutosCadastrarAction() {
		System.out.println("onMenuItemProdutosCadastrarAction");

	}

	@FXML
	public void onMenuItemProdutosListarAction() {
		loadView("/gui/ProdutosList.fxml", (ProdutosListController controller) -> {
			controller.setProdutosService(new ProdutosService());
			controller.updateTableView();
		});

	}

	@FXML
	public void onMenuItemInfraestruturaCadastrarAction() {
		System.out.println("onMenuItemInfraestruturaCadastrarAction");

	}

	@FXML
	public void onMenuItemInfraestruturaListarAction() {
		loadView("/gui/InfraestruturaList.fxml", (InfraestruturaListController controller) -> {
			controller.setInfraestruturaService(new InfraestruturaService());
			controller.updateTableView();
		});


	}

	@FXML
	public void onMenuItemCustosFixoAction() {
		loadView("/gui/CustoFixo.fxml",x -> {});

	}

	@FXML
	public void onMenuItemCustosVariavelDiretoAction() {
		loadView("/gui/CustoVariavelDireto.fxml",x -> {});

	}
	
	@FXML
	public void onMenuItemCustosVariavelIndiretoAction() {
		loadView("/gui/CustoVariavelIndireto.fxml",x -> {});

	}
	
	@FXML
	public void onMenuItemSobreAction() {
		loadView("/gui/Sobre.fxml", x -> {});

	}

	public void initialize(URL uri, ResourceBundle rb) {

	}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());

			T controller = loader.getController();
			initializingAction.accept(controller);
			
			
		}catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar tela", e.getMessage(), AlertType.ERROR);
		}

	}

}