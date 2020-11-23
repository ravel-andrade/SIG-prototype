package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
	public void onMenuItemFuncionarioCadastrarAction() {
		System.out.println("onMenuItemFuncionarioCadastrarAction");

	}

	@FXML
	public void onMenuItemFuncionarioListarAction() {
		System.out.println("onMenuItemFuncionarioListarAction");

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

	public void initialize(URL uri, ResourceBundle rb) {

	}

}
