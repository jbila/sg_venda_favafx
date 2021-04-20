
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
import mz.co.mahs.dao.DaoFuncionario;
import mz.co.mahs.dao.DaoRelatorio;
import mz.co.mahs.dao.DaoUtilizador;
import mz.co.mahs.models.Funcionario;
import mz.co.mahs.models.Utilizador;

public class FXMLUtilizadorController implements Initializable, Crud {
	Alert alert = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	Alert alertWarning = new Alert(AlertType.WARNING);

	@FXML
	private AnchorPane rootUtilizador;

	@FXML
	private TextField txtNome, txtProcurar;
	@FXML
	private TableView<Utilizador> tblUtilizador;

	@FXML
	private TableColumn<Utilizador, Integer> colID;

	@FXML
	private TableColumn<Utilizador, Funcionario> colFuncionarioNome;

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
	private ComboBox<Funcionario> cboFuncionario;


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
	private Button btnDelete,btnRelatorio;
	/**Este evento chama a funcao que  adicionar um utilizador
	 * */
	@FXML
	private void add(ActionEvent event) {
		acessoAdd();
		showInfo();
		btnAdd.setVisible(true);
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);

	}
	/**Esta evento valida os campos primeiro
	 * depois chama a funcao que elimina o registo*/
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
		colFuncionarioNome.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
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
		cboPerfil.setValue("STANDARD");
		cboEstado.setItems(estado);
		cboEstado.setValue("INACTIVO");
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		txtID.setVisible(false);
		showInfo();
		fillFuncionario();
	}

//--------------------------------------------------------------
	@Override
	public void acessoAdd() {
		try {
		Utilizador utilizador = new Utilizador();
		/*----------------------------------------*/
		Funcionario funcionario=new Funcionario();
		Funcionario func=(Funcionario)cboFuncionario.getSelectionModel().getSelectedItem();
		final int idFuncionario = func.getIdFuncionario();
		funcionario.setIdFuncionario(idFuncionario);
		/*-----------------------------------------*/
		utilizador.setFuncionario(funcionario);
		utilizador.setPerfil(cboPerfil.getValue().toUpperCase());
		utilizador.setUsername(txtuserName.getText().toLowerCase());
		utilizador.setPassword(txtPassword.getText());
		utilizador.setStatus(cboEstado.getValue().toUpperCase());
		if(DaoUtilizador.isUserNameRecorded(idFuncionario)) {
			alertWarning.setHeaderText("Infomação");
			alertWarning.setHeaderText("Verificação de Dados");
			alertWarning.setContentText("Este utilizador já existe! ");
			alertWarning.showAndWait();	
		}
		else {
			DaoUtilizador.addUtilizador(utilizador);
			limpar();
		}
		
		}
		catch(NullPointerException ex) {
			alertWarning.setHeaderText("Infomação");
			alertWarning.setHeaderText("Verificação de Dados");
			alertWarning.setContentText("Selecione o Funcionario ");
			alertWarning.showAndWait();
		}
	}

	@Override
	public void acessoIsRecorded() {

	}

	@Override
	public void acessoUpdate() {
		try {
		Utilizador utilizador = new Utilizador();
		/*----------------------------------------*/
		Funcionario funcionario=new Funcionario();
		Funcionario func=(Funcionario)cboFuncionario.getSelectionModel().getSelectedItem();
		final int idFuncionario = func.getIdFuncionario();
		funcionario.setIdFuncionario(idFuncionario);
		/*-----------------------------------------*/
		utilizador.setFuncionario(funcionario);
		utilizador.setPerfil(cboPerfil.getValue());
		utilizador.setUsername(txtuserName.getText().toLowerCase());
		utilizador.setPassword(txtPassword.getText());
		utilizador.setStatus(cboEstado.getValue().toUpperCase());

		utilizador.setIdUtilizador(Integer.parseInt(txtID.getText()));
		DaoUtilizador.updateUtilizador(utilizador);
		limpar();
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		btnAdd.setVisible(true);
		}
		catch(NullPointerException ex) {
			alertWarning.setHeaderText("Infomação");
			alertWarning.setHeaderText("Verificação de Dados");
			alertWarning.setContentText("Selecione o Funcionario ");
			alertWarning.showAndWait();
		}
	}

	@Override
	public void limpar() {
		txtID.setText("");
		txtPassword.setText("");
		txtuserName.setText("");
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
		colFuncionarioNome.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
		ColUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
		ColEstado.setCellValueFactory(new PropertyValueFactory<>("status"));
		colPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil"));
		tblUtilizador.setItems(obserList);
	}
	/*-------------------------------------------------------*/
	private void fillFuncionario() {
		List<Funcionario> list = DaoFuncionario.getAllFuncionario();
		for (Funcionario items : list)
			cboFuncionario.getItems().add(items);
	}
	/*-------------------------------------------------------*/
	@FXML
	private void relatorio(ActionEvent event) {
		DaoRelatorio.utilizadorReport();
	}

}
