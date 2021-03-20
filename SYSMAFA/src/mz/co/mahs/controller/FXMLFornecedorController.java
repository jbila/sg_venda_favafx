
package mz.co.mahs.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import mz.co.mahs.dao.DaoFornecedor;
import mz.co.mahs.models.Fornecedor;
import mz.co.mahs.models.Utilizador;

public class FXMLFornecedorController implements Initializable,Crud {
	Alert alert = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	@FXML
	private AnchorPane rootFornecedor;
	@FXML
	private TextField txtNome,txtProcurar;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TextField txtEndereco;

	@FXML
	private TableView<Fornecedor> tblFornecedor;

	@FXML
	private TableColumn<Fornecedor, Integer> colId;

	@FXML
	private TableColumn<Fornecedor, String> colNome;

	@FXML
	private TableColumn<Fornecedor, String> colEmail;

	@FXML
	private TableColumn<Fornecedor, String> colEndereco;

	@FXML
	private TableColumn<Fornecedor, String> colTelefone;

	@FXML
	private TableColumn<Fornecedor, Utilizador> colUtilizador;

	@FXML
	private TextField txtId;

	@FXML
	private HBox hBoxButton;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnUpdate;

	@FXML
	private Button btnDelete;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showInfo();
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		txtId.setVisible(false);

	}
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
		acessoDelete();
		showInfo();
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		btnAdd.setVisible(true);
	}

	@FXML
	private void handleMouseClickAction(MouseEvent event) {
		Fornecedor fornecedor = tblFornecedor.getSelectionModel().getSelectedItem();
		txtId.setText("" + fornecedor.getIdFornecedor());
		txtNome.setText("" + fornecedor.getNome());
		//txtApelido.setText("" + fornecedor.getApelido());
		txtEmail.setText("" + fornecedor.getEmail());
		txtTelefone.setText("" + fornecedor.getTelefone());
		txtEndereco.setText("" + fornecedor.getEndereco());
		btnAdd.setVisible(false);
		btnUpdate.setVisible(true);
		btnDelete.setVisible(true);

	}

	@FXML
	private void update(ActionEvent event) {
		acessoUpdate();
		showInfo();
		btnAdd.setVisible(true);
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
	}

	@FXML
	private void changedActionTipo(ActionEvent event) {

	}
	  @FXML
	 private void procurar(KeyEvent event) {
		  txtProcurar.setOnKeyReleased(e->{  
			  List<Fornecedor> listFornecedor = DaoFornecedor.search(txtProcurar.getText());
				final ObservableList<Fornecedor> obserList = FXCollections.observableArrayList(listFornecedor);
				colId.setCellValueFactory(new PropertyValueFactory<>("idFornecedor"));
				colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
				colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
				colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
				colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
				colUtilizador.setCellValueFactory(new PropertyValueFactory<>("utilizador"));
				tblFornecedor.setItems(obserList);
		  });

	    }
	@Override
	public void acessoAdd() {
		//idFornecedor,nome,email,telefone,endereco,utilizador
		Fornecedor fornecedor=new Fornecedor();
		Utilizador utilizador=new Utilizador();
		utilizador.setIdUtilizador(ControllerLogin.idUsuario);
		fornecedor.setNome(txtNome.getText().toUpperCase());
		fornecedor.setEmail(txtEmail.getText().toLowerCase());
		fornecedor.setTelefone(txtTelefone.getText().toUpperCase());
		fornecedor.setEndereco(txtEndereco.getText().toUpperCase());
		fornecedor.setUtilizador(utilizador);
		DaoFornecedor.addFornecedor(fornecedor);
		limpar();
		
	}
	@Override
	public void acessoIsRecorded() {
		
	}
	@Override
	public void acessoUpdate() {
		Fornecedor fornecedor=new Fornecedor();
		Utilizador utilizador=new Utilizador();
		utilizador.setIdUtilizador(ControllerLogin.idUsuario);
		fornecedor.setNome(txtNome.getText().toUpperCase());
		fornecedor.setEmail(txtEmail.getText().toLowerCase());
		fornecedor.setTelefone(txtTelefone.getText().toUpperCase());
		fornecedor.setEndereco(txtEndereco.getText().toUpperCase());
		fornecedor.setUtilizador(utilizador);
		fornecedor.setIdFornecedor(Integer.parseInt(txtId.getText()));
		DaoFornecedor.updateFornecedor(fornecedor);
		limpar();
	}
	@Override
	public void acessoDelete() {
		Fornecedor fornecedor=new Fornecedor();
		fornecedor.setIdFornecedor(Integer.parseInt(txtId.getText()));
		DaoFornecedor.deleteFornecedor(fornecedor);
		limpar();
		
	}
	@Override
	public void limpar() {
		txtId.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtEndereco.setText("");
		
		
		
	}
	@Override
	public void showInfo() {
		// nome,genero,email,telefone,endereco

				List<Fornecedor> list = DaoFornecedor.getAllFornecedor();
				final ObservableList<Fornecedor> obserList = FXCollections.observableArrayList(list);
				colId.setCellValueFactory(new PropertyValueFactory<>("idFornecedor"));
				colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
				colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
				colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
				colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
				colUtilizador.setCellValueFactory(new PropertyValueFactory<>("utilizador"));
				tblFornecedor.setItems(obserList);
		
	}

	

}
