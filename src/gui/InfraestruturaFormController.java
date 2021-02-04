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
import model.Entities.Infraestrutura;
import model.services.InfraestruturaService;
import model.exceptions.ValidationException;


public class InfraestruturaFormController implements Initializable {

	private Infraestrutura entity;
	private InfraestruturaService service;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtDescricao;
	
	@FXML
	private TextField txtQuantidade;
	
	@FXML
	private TextField txtValorUN;



	@FXML
	private Label labelErrorNome;
	
	@FXML
	private Label labelErrorQuantidade;
	
	@FXML
	private Label labelErrorValorUN;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	public void setInfraestruturaService(InfraestruturaService service) {
		this.service = service;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	public void setInfraestrutura(Infraestrutura entity) {
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

	private Infraestrutura getFormData() {
		Infraestrutura obj = new Infraestrutura();
		ValidationException exception = new ValidationException("Validation error");
		
			obj.setId(Utils.tryParseToInt(txtId.getText()));
	
		if (txtDescricao.getText() == null || txtDescricao.getText().trim().equals("")) {
			exception.addError("descricao", "Field can't be empty");
		}

		obj.setDescricao(txtDescricao.getText());

		if (txtQuantidade.getText() == null || txtQuantidade.getText().trim().equals("")) {
			exception.addError("quantidade", "Field can't be empty");
		}

		obj.setQuantidade(Utils.tryParseToInt(txtQuantidade.getText()));
		
		if (obj.getQuantidade() == null) {
			exception.addError("quantidade", "Campo numerico");
		}
		
		if (txtValorUN.getText() == null || txtValorUN.getText().trim().equals("")) {
			exception.addError("valorUn", "Field can't be empty");
		}

		obj.setValorUN(Utils.tryParseToDouble(txtValorUN.getText()));
		
		if (obj.getValorUN() == null) {
			exception.addError("valorUn", "Campo numerico");
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
		Constraints.setTextFieldMaxLength(txtDescricao, 30);
		Constraints.setTextFieldMaxLength(txtQuantidade, 8);
		Constraints.setTextFieldMaxLength(txtValorUN, 8);
		
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtDescricao.setText(entity.getDescricao());
		
		if (entity.getQuantidade() != null) {
			txtQuantidade.setText(String.valueOf(entity.getQuantidade()));
		}
		
		if (entity.getValorUN() != null) {
			txtValorUN.setText(String.valueOf(entity.getValorUN()));
		}
		
	}
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();

		if (fields.contains("descricao")) {
			labelErrorNome.setText(errors.get("descricao"));
		}
		
		if (fields.contains("quantidade")) {
			labelErrorQuantidade.setText(errors.get("quantidade"));
		}
		
		if (fields.contains("valorUn")) {
			labelErrorValorUN.setText(errors.get("valorUn"));
		}
		
	
	}
}