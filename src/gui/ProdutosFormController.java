package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Map;
import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Entities.Produtos;
import model.services.ProdutosService;
import model.exceptions.ValidationException;

public class ProdutosFormController implements Initializable {

	private Produtos entity;
	private ProdutosService service;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCustoVarDireto;

	@FXML
	private TextField txtCustoVarIndireto;

	@FXML
	private Label labelErrorNome;

	@FXML
	private Label labelErrorCustoVarIndreto;

	@FXML
	private Label labelErrorCustoVarDireto;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	public void setProdutosService(ProdutosService service) {
		this.service = service;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	public void setProdutos(Produtos entity) {
		this.entity = entity;
	}

	@FXML
	public void onBtSaveAction(ActionEvent event) {

		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}

		if (service == null) {
			throw new IllegalStateException("Service was null");
		}

		try {

			entity = getFormData();

			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		} catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		}

		catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private Produtos getFormData() {
		Produtos obj = new Produtos();
		ValidationException exception = new ValidationException("Validation error");

		obj.setId(Utils.tryParseToInt(txtId.getText()));

		if (txtNome.getText() == null || txtNome.getText().trim().equals("")) {
			exception.addError("nome", "Field can't be empty");
		} else {
			exception.clearError("nome");
			obj.setNome(txtNome.getText());
		}

		if (txtCustoVarDireto.getText() == null || txtCustoVarDireto.getText().trim().equals("")) {
			exception.addError("custovardireto", "Field can't be empty");
		} else {
			exception.clearError("custovardireto");
			try {
				obj.setVarDireto(Utils.tryParseToDouble(txtCustoVarDireto.getText()));
			} catch (Exception e) {
				exception.addError("custovardireto", "Campo numerico");
			}

		}

		if (txtCustoVarIndireto.getText() == null || txtCustoVarIndireto.getText().trim().equals("")) {

			exception.addError("custovarindireto", "Field can't be empty");
		} else {
			exception.clearError("custovarindireto");
			try {
				obj.setVarIndireto(Utils.tryParseToDouble(txtCustoVarIndireto.getText()));
			} catch (Exception e) {
				exception.addError("custovarindireto", "Campo numerico");
			}

		}

		if (exception.getErrors().size() > 0) {

			throw exception;
		}

		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtNome, 30);
		Constraints.setTextFieldMaxLength(txtCustoVarIndireto, 8);
		Constraints.setTextFieldMaxLength(txtCustoVarDireto, 8);

	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getNome());

		if (entity.getCustoVarDireto() != null) {
			txtCustoVarIndireto.setText(String.valueOf(entity.getCustoVarIndireto()));
		}

		if (entity.getCustoVarDireto() != null) {
			txtCustoVarDireto.setText(String.valueOf(entity.getCustoVarDireto()));
		}

	}

	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();

		if (fields.contains("nome")) {
			labelErrorNome.setText(errors.get("nome"));
		}

		if (fields.contains("custovardireto")) {
			labelErrorCustoVarDireto.setText(errors.get("custovardireto"));
		}

		if (fields.contains("custovarindireto")) {
			labelErrorCustoVarIndreto.setText(errors.get("custovarindireto"));
		}

	}
}