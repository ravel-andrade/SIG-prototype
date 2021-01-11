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
import model.Entities.Funcionarios;
import model.services.FuncionariosService;
import model.exceptions.ValidationException;


public class FuncionariosFormController implements Initializable {

	private Funcionarios entity;
	private FuncionariosService service;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtFuncao;

	@FXML
	private TextField txtCargaHoraria;

	@FXML
	private TextField txtSalario;

	@FXML
	private Label labelErrorNome;
	
	@FXML
	private Label labelErrorFuncao;
	
	@FXML
	private Label labelErrorCargaHoraria;
	
	@FXML
	private Label labelErrorSalario;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	public void setFuncionariosService(FuncionariosService service) {
		this.service = service;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	public void setFuncionarios(Funcionarios entity) {
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
		}
		catch (ValidationException e) {
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

	private Funcionarios getFormData() {
		Funcionarios obj = new Funcionarios();
		ValidationException exception = new ValidationException("Validation error");
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));
				
		
		if (txtNome.getText() == null || txtNome.getText().trim().equals("")) {
			exception.addError("nome", "Field can't be empty");
		}

		obj.setNome(txtNome.getText());

		if (txtFuncao.getText() == null || txtFuncao.getText().trim().equals("")) {
			exception.addError("funcao", "Field can't be empty");
		}
		
		obj.setFuncao(txtFuncao.getText());
		
		if (txtCargaHoraria.getText() == null || txtCargaHoraria.getText().trim().equals("")) {
			exception.addError("cargaHoraria", "Field can't be empty");
		}

		obj.setCargaHoraria(Utils.tryParseToInt(txtCargaHoraria.getText()));
		
		if (txtSalario.getText() == null || txtSalario.getText().trim().equals("")) {
			exception.addError("salario", "Field can't be empty");
		}

		obj.setSalario(Utils.tryParseToDouble(txtSalario.getText()));

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
		Constraints.setTextFieldMaxLength(txtFuncao, 30);
		Constraints.setTextFieldMaxLength(txtCargaHoraria, 30);
		Constraints.setTextFieldMaxLength(txtSalario, 30);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getNome());
		txtFuncao.setText(entity.getFuncao());
		txtSalario.setText(String.valueOf(entity.getSalario()));
		txtCargaHoraria.setText(String.valueOf(entity.getCargaHoraria()));
	}
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();

		if (fields.contains("nome")) {
			labelErrorNome.setText(errors.get("nome"));
		}
		
		if (fields.contains("funcao")) {
			labelErrorFuncao.setText(errors.get("funcao"));
		}
		
		if (fields.contains("salario")) {
			labelErrorSalario.setText(errors.get("salario"));
		}
		
		if (fields.contains("cargaHoraria")) {
			labelErrorCargaHoraria.setText(errors.get("cargaHoraria"));
		}
	}
}