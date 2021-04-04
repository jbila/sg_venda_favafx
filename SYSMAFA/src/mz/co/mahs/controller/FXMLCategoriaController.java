
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import mz.co.mahs.dao.DaoCategoria;
import mz.co.mahs.models.Categoria;
import mz.co.mahs.models.Utilizador;

public class FXMLCategoriaController implements Initializable, Crud {
	Alert alert = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	Alert alterWarning = new Alert(AlertType.WARNING);

	@FXML
	private AnchorPane rootCategoria;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtDescricao;

	@FXML
	private TextField txtSearch;

	@FXML
	private TableView<Categoria> tblCategoria;

	@FXML
	private TableColumn<Categoria, Integer> colId;

	@FXML
	private TableColumn<Categoria, String> colNome;

	@FXML
	private TableColumn<Categoria, String> colDescricao;
	@FXML
	private TableColumn<Categoria, Utilizador> colUtilizador;

	@FXML
	private HBox hBoxButton;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnUpdate;

	@FXML
	private Button btnDelete;

	@FXML
	private void handleMouseClickAction() {
		Categoria categoria = tblCategoria.getSelectionModel().getSelectedItem();
		txtId.setText("" + categoria.getIdCategoria());
		txtNome.setText("" + categoria.getNome());
		txtDescricao.setText("" + categoria.getDescricao());
		btnAdd.setVisible(false);
		btnUpdate.setVisible(true);
		btnDelete.setVisible(true);
	}

	@FXML
	private void add(ActionEvent event) {
		if (!(txtNome.getText().isEmpty() && txtDescricao.getText().isEmpty())) {
			acessoAdd();
			showInfo();
			btnAdd.setVisible(true);
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);
		} else {
			alterWarning.setTitle("Aviso");
			alterWarning.setHeaderText("Validação de Dados");
			alterWarning.setContentText("Preencha os campos devidamente");
			alterWarning.showAndWait();
		}
	}

	@FXML
	private void procurar(KeyEvent event) {
		txtSearch.setOnKeyReleased(E -> {

		});

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
	private void search(KeyEvent event) {
		txtSearch.setOnKeyReleased(e -> {
			List<Categoria> listCategoria = DaoCategoria.search(txtSearch.getText());
			final ObservableList<Categoria> obserList = FXCollections.observableArrayList(listCategoria);
			colId.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
			colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
			colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
			colUtilizador.setCellValueFactory(new PropertyValueFactory<>("utilizador"));
			tblCategoria.setItems(obserList);

		});

	}

	@FXML
	private void update(ActionEvent event) {
		acessoUpdate();
		showInfo();
		btnAdd.setVisible(true);
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showInfo();
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);

	}

	@Override
	public void acessoAdd() {

		Categoria categoria = new Categoria();
		Utilizador utilizador = new Utilizador();
		utilizador.setIdUtilizador(ControllerLogin.idUsuario);

		categoria.setNome(txtNome.getText().toUpperCase());
		categoria.setDescricao(txtDescricao.getText().toUpperCase());
		categoria.setUtilizador(utilizador);
		DaoCategoria.add(categoria);
		limpar();

	}

	@Override
	public void acessoIsRecorded() {

	}

	@Override
	public void acessoUpdate() {
		Categoria categoria = new Categoria();
		Utilizador utilizador = new Utilizador();
		utilizador.setIdUtilizador(ControllerLogin.idUsuario);
		categoria.setNome(txtNome.getText().toUpperCase());
		categoria.setDescricao(txtDescricao.getText().toUpperCase());
		categoria.setUtilizador(utilizador);
		categoria.setIdCategoria(Integer.parseInt(txtId.getText()));
		DaoCategoria.update(categoria);
		limpar();

	}

	@Override
	public void acessoDelete() {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(Integer.parseInt(txtId.getText()));
		DaoCategoria.delete(categoria);
		limpar();

	}

	@Override
	public void limpar() {
		txtId.setText("");
		txtNome.setText("");
		txtDescricao.setText("");
	}

	@Override
	public void showInfo() {
		List<Categoria> list = DaoCategoria.getAllCategoria();
		System.out.println(list);
		final ObservableList<Categoria> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colUtilizador.setCellValueFactory(new PropertyValueFactory<>("utilizador"));
		tblCategoria.setItems(obserList);

	}
}
