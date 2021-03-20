
package mz.co.mahs.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import mz.co.mahs.dao.DaoUtilizador;
import mz.co.mahs.models.Utilizador;

public class FXMLUtilizadorController implements Initializable, Crud {
	Alert alert = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	@FXML
	private AnchorPane rootUtilizador;

	@FXML
	private TextField txtNome, txtProcurar;

	@FXML
	private TextField txtApelido;

	@FXML
	private ComboBox<String> cboSexo;
	ObservableList<String> sexo = FXCollections.observableArrayList("M", "F");
	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TextField txtEndereco;

	@FXML
	private TableView<Utilizador> tblUtilizador;

	@FXML
	private TableColumn<Utilizador, Integer> colID;

	@FXML
	private TableColumn<Utilizador, String> colNome;

	@FXML
	private TableColumn<Utilizador, String> colEmail;

	@FXML
	private TableColumn<Utilizador, String> colEndereco;

	@FXML
	private TableColumn<Utilizador, String> ColTelefone;

	@FXML
	private TableColumn<Utilizador, String> colSexo;

	@FXML
	private TableColumn<Utilizador, String> ColUserName;

	@FXML
	private TableColumn<Utilizador, String> ColEstado;

	@FXML
	private TableColumn<Utilizador, String> colPerfil;

	@FXML
	private TextField txtuserName;

	@FXML
	private ComboBox<String> cboPerfil;
	ObservableList<String> perfil = FXCollections.observableArrayList("STANDARD", "ADMINISTRADOR", "GUEST");

	@FXML
	private ComboBox<String> cboEstado;
	ObservableList<String> estado = FXCollections.observableArrayList("INACTIVO", "ACTIVO");

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtID;

	@FXML
	private ImageView imagePhoto;

	@FXML
	private HBox hBoxButton;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnUpdate;

	@FXML
	private Button btnDelete;

	@FXML
	private void add(ActionEvent event) {
		acessoAdd();
		showInfo();
		btnAdd.setVisible(true);
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);

	}

	@FXML
	private void delete(ActionEvent event) {

		if (txtID.getText().isEmpty()) {
			alert.setTitle("");
			alert.setHeaderText("Validação");
			alert.setContentText("selecione a linha a apagar");
			alert.showAndWait();
		} else {
			alertConfirm.setTitle("Confirmação");
			alertConfirm.setHeaderText(" Dialogo de Confirmação");
			alertConfirm.setContentText("Eliminar o Registo?");

			Optional<ButtonType> result = alertConfirm.showAndWait();
			if (result.get() == ButtonType.OK) {
				// ... user chose OK
				acessoDelete();
				showInfo();
				limpar();
				btnAdd.setVisible(true);
				btnUpdate.setVisible(false);
				btnDelete.setVisible(false);

			}

			else {
			}
			// ... user chose CANCEL or closed the dialog
		}
	}

	@FXML
	private void handleMouseClickAction(MouseEvent event) {

		Utilizador utilizador = tblUtilizador.getSelectionModel().getSelectedItem();
		txtID.setText("" + utilizador.getIdUtilizador());
		txtNome.setText("" + utilizador.getNome());
		txtApelido.setText("" + utilizador.getApelido());
		txtEmail.setText("" + utilizador.getEmail());
		txtTelefone.setText("" + utilizador.getTelefone());
		txtEndereco.setText("" + utilizador.getEndereco());
		txtuserName.setText("" + utilizador.getUsername());
		btnAdd.setVisible(false);
		btnUpdate.setVisible(true);
		btnDelete.setVisible(true);
	}

	@FXML
	private void procurar(KeyEvent event) {
		List<Utilizador> list = DaoUtilizador.getUtilizadorList(txtProcurar.getText());
		final ObservableList<Utilizador> obserList = FXCollections.observableArrayList(list);
		colID.setCellValueFactory(new PropertyValueFactory<>("idUtilizador"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colEndereco.setCellValueFactory(new PropertyValueFactory<>("morada"));
		ColTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));
		ColUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
		ColEstado.setCellValueFactory(new PropertyValueFactory<>("status"));
		colPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil"));
		tblUtilizador.setItems(obserList);

	}

	@FXML
	private void update(ActionEvent event) {
		acessoUpdate();
		showInfo();
		btnAdd.setVisible(true);
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
	}

	/* When the the employee is selected */

	@FXML
	private void changedAction(ActionEvent event) {

	}

//=============================================================================================================
	@FXML
	private void updateUtilizador(ActionEvent event) {

		if (txtuserName.getText().isEmpty() && (txtPassword.getText().isEmpty())) {
			alert.setTitle("Informação");
			alert.setHeaderText("Validação");
			alert.setContentText("Seleciona a linha ");
			alert.showAndWait();
		} else {
			acessoUpdate();
			showInfo();
		}

	}

//------------------------Load----------------------------------------------
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cboPerfil.setItems(perfil);
		cboSexo.setItems(sexo);
		cboEstado.setItems(estado);
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		txtID.setVisible(false);
		showInfo();

	}

//--------------------------------------------------------------
	@Override
	public void acessoAdd() {
		Utilizador utilizador = new Utilizador();
		utilizador.setNome(txtNome.getText().toUpperCase());
		utilizador.setApelido(txtApelido.getText().toUpperCase());
		utilizador.setEmail(txtEmail.getText().toLowerCase());
		utilizador.setTelefone(txtTelefone.getText().toUpperCase());
		utilizador.setEndereco(txtEndereco.getText().toUpperCase());
		utilizador.setPerfil(cboPerfil.getValue().toUpperCase());
		utilizador.setUsername(txtuserName.getText().toLowerCase());
		utilizador.setPassword(txtPassword.getText());
		utilizador.setStatus(cboEstado.getValue().toUpperCase());
		utilizador.setGenero(cboSexo.getValue().toUpperCase());

		DaoUtilizador.addUtilizador(utilizador);
		limpar();
	}

	@Override
	public void acessoIsRecorded() {

	}

	@Override
	public void acessoUpdate() {
		Utilizador utilizador = new Utilizador();
		utilizador.setNome(txtNome.getText().toUpperCase());
		utilizador.setApelido(txtApelido.getText().toUpperCase());
		utilizador.setEmail(txtEmail.getText().toUpperCase());
		utilizador.setTelefone(txtTelefone.getText().toUpperCase());
		utilizador.setEndereco(txtEndereco.getText().toUpperCase());
		utilizador.setPerfil(cboPerfil.getValue());
		utilizador.setUsername(txtuserName.getText().toLowerCase());
		utilizador.setPassword(txtPassword.getText());
		utilizador.setGenero(cboSexo.getValue());
		utilizador.setStatus(cboEstado.getValue().toUpperCase());

		utilizador.setIdUtilizador(Integer.parseInt(txtID.getText()));
		DaoUtilizador.updateUtilizador(utilizador);
		limpar();
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		btnAdd.setVisible(true);
	}

	@Override
	public void limpar() {
		txtNome.setText("");
		txtID.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
		txtPassword.setText("");
		txtuserName.setText("");
		txtApelido.setText("");
	}

	@Override
	public void acessoDelete() {
		Utilizador u = new Utilizador();
		u.setIdUtilizador(Integer.parseInt(txtID.getText()));
		DaoUtilizador.deleteUtilizador(u);
		limpar();
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		btnAdd.setVisible(true);

	}

	@Override
	public void showInfo() {
		List<Utilizador> list = DaoUtilizador.getUtilizadorList(txtProcurar.getText());

		final ObservableList<Utilizador> obserList = FXCollections.observableArrayList(list);
		colID.setCellValueFactory(new PropertyValueFactory<>("idUtilizador"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colEndereco.setCellValueFactory(new PropertyValueFactory<>("morada"));
		ColTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));
		ColUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
		ColEstado.setCellValueFactory(new PropertyValueFactory<>("status"));
		colPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil"));
		tblUtilizador.setItems(obserList);

	}
}
