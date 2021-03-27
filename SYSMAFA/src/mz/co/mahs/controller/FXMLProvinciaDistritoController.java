package mz.co.mahs.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mz.co.mahs.dao.DaoDistrito;
import mz.co.mahs.dao.DaoFuncao;
import mz.co.mahs.dao.DaoProvincia;
import mz.co.mahs.models.Distrito;
import mz.co.mahs.models.Funcao;
import mz.co.mahs.models.Provincia;

public class FXMLProvinciaDistritoController {
	@FXML
	private AnchorPane rootProvinciaDistrito;
	@FXML
	private AnchorPane rootDistrito, rootFuncao;
	/*------------------------------*/
	@FXML
	private TableView<Provincia> tblProvincia;
	@FXML
	private TableColumn<Provincia, Integer> colIDProvincia;
	@FXML
	private TableColumn<Provincia, String> colProvinciaNome;

	/*------------------------------*/
	@FXML
	private TableView<Distrito> tblDistritoUrbano;
	@FXML
	private TableColumn<Distrito, Integer> colId;
	@FXML
	private TableColumn<Distrito, Provincia> colIProv;
	@FXML
	private TableColumn<Distrito, String> colDistritoUrbano;
	/*------------------------------*/
	@FXML
	private TextField txtProvincia, txtNomeDistrito, txtIdProvincia, txtIdDistrito;
	@FXML
	private Button btnAddProvincia;
	@FXML
	private Button btnActualizarProvincia;
	@FXML
	private Button btnDelteProvincia;
	@FXML
	private Button btnAdicionarDistrito;
	@FXML
	private Button btnActualizarDistrito;
	@FXML
	private Button btnAgarDistrito;
	@FXML
	private ComboBox<Provincia> cboProvincia;
	/*-------------Funcao-----------------*/
	@FXML
	private TableView<Funcao> tblFuncao;
	@FXML
	private TableColumn<Funcao, Integer> colIdFuncao;
	@FXML
	private TableColumn<Funcao, String> colNomeFuncao;
	@FXML
	private Button btnApagarFuncao, btnActualizarFuncao, btnAdicionarFuncao;
	@FXML
	private TextField txtIdFuncao, txtNomeFuncao;

	@FXML
	private void initialize() {
		fillProvincia();
		showDistrito();
		showProvincia();
		showFuncao();
		
		txtIdDistrito.setVisible(false);
		txtIdFuncao.setVisible(false);
		txtIdProvincia.setVisible(false);
		/*------------Funcao------------*/
		btnApagarFuncao.setVisible(false);
		btnActualizarFuncao.setVisible(false);
		btnAdicionarFuncao.setVisible(true);
		/*------------provincia------------*/
		btnAddProvincia.setVisible(true);
		btnActualizarProvincia.setVisible(false);
		btnDelteProvincia.setVisible(false);
		/*------------Distrito---------------*/
		btnAdicionarDistrito.setVisible(true);
		btnActualizarDistrito.setVisible(false);
		btnAgarDistrito.setVisible(false);
	}

	// Event Listener on TableView[#tblProvincia].onMouseClicked
	@FXML
	public void handleProvincia(MouseEvent event) {
		Provincia provincia = tblProvincia.getSelectionModel().getSelectedItem();
		txtIdProvincia.setText("" + provincia.getIdProvincia());
		txtProvincia.setText("" + provincia.getNome());
		btnAddProvincia.setVisible(false);
		btnActualizarProvincia.setVisible(true);
		btnDelteProvincia.setVisible(true);
	}

	// Event Listener on TableView[#tblDistritoUrbano].onMouseClicked
	@FXML
	public void handleDistrito(MouseEvent event) {
		Distrito distrito = tblDistritoUrbano.getSelectionModel().getSelectedItem();
		txtIdDistrito.setText("" + distrito.getIdDistrito());
		txtNomeDistrito.setText("" + distrito.getNome());
		
		btnAdicionarDistrito.setVisible(false);
		btnActualizarDistrito.setVisible(true);
		btnAgarDistrito.setVisible(true);
	}

	@FXML
	public void handleFuncao(MouseEvent event) {
		Funcao funcao = tblFuncao.getSelectionModel().getSelectedItem();
		txtIdFuncao.setText("" + funcao.getIdFuncao());
		txtNomeFuncao.setText("" + funcao.getNome());
		btnApagarFuncao.setVisible(true);
		btnActualizarFuncao.setVisible(true);
		btnAdicionarFuncao.setVisible(false);
	}

	// Event Listener on Button[#btnAddProvincia].onAction
	@FXML
	public void addProvincia(ActionEvent event) {
		addProvincia();
		showProvincia();
	}

	// Event Listener on Button[#btnActualizarProvincia].onAction
	@FXML
	public void updateProvincia(ActionEvent event) {
		updateProvincia();
		showProvincia();
		
		btnAddProvincia.setVisible(true);
		btnActualizarProvincia.setVisible(false);
		btnDelteProvincia.setVisible(false);
	}

	// Event Listener on Button[#btnDelteProvincia].onAction
	@FXML
	public void deleteProvincia(ActionEvent event) {
		deleteProvincia();
		showProvincia();
		
		btnAddProvincia.setVisible(true);
		btnActualizarProvincia.setVisible(false);
		btnDelteProvincia.setVisible(false);
	}
//Funcao

	@FXML
	public void addFuncao(ActionEvent event) {
		addFuncao();
		showFuncao();
		
	}

	// Event Listener on Button[#btnActualizarProvincia].onAction
	@FXML
	public void updateFuncao(ActionEvent event) {
		updateFuncao();
		showFuncao();
		btnApagarFuncao.setVisible(false);
		btnActualizarFuncao.setVisible(false);
		btnAdicionarFuncao.setVisible(true);
		
		
	}

	// Event Listener on Button[#btnDelteProvincia].onAction
	@FXML
	public void deleteFuncao(ActionEvent event) {
		deleteFuncao();
		showFuncao();
		btnApagarFuncao.setVisible(false);
		btnActualizarFuncao.setVisible(false);
		btnAdicionarFuncao.setVisible(true);
	}

	// Event Listener on Button[#btnAdicionarDistrito].onAction
	@FXML
	public void addDistrito(ActionEvent event) {
		addDistrito();
		showDistrito();
	}

	// Event Listener on Button[#btnActualizarDistrito].onAction
	@FXML
	public void updateDistrito(ActionEvent event) {
		updateDistrito();
		showDistrito();
		btnAdicionarDistrito.setVisible(true);
		btnActualizarDistrito.setVisible(false);
		btnAgarDistrito.setVisible(false);
	}

	// Event Listener on Button[#btnAgarDistrito].onAction
	@FXML
	public void deleteDistrito(ActionEvent event) {
		deleteDistrito();
		showDistrito();
		btnAdicionarDistrito.setVisible(true);
		btnActualizarDistrito.setVisible(false);
		btnAgarDistrito.setVisible(false);
		
	}

	private void addProvincia() {
		Provincia provincia = new Provincia();
		provincia.setNome(txtProvincia.getText().toUpperCase());
		DaoProvincia.add(provincia);
		
	}

	private void updateProvincia() {
		Provincia provincia = new Provincia();
		provincia.setNome(txtProvincia.getText());
		provincia.setIdProvincia(Integer.parseInt(txtIdProvincia.getText().toUpperCase()));
		DaoProvincia.update(provincia);
		limparProvincia();

	}

	private void deleteProvincia() {
		Provincia provincia = new Provincia();
		provincia.setIdProvincia(Integer.parseInt(txtIdProvincia.getText()));
		DaoProvincia.delete(provincia);
		limparProvincia();
	}

	private void addDistrito() {

		Distrito distrito = new Distrito();
		Provincia provincia = new Provincia();
		Provincia pro = (Provincia) cboProvincia.getSelectionModel().getSelectedItem();
		final int idPro = pro.getIdProvincia();
		provincia.setIdProvincia(idPro);
		distrito.setNome(txtNomeDistrito.getText().toUpperCase());
		distrito.setProvincia(provincia);
		DaoDistrito.add(distrito);
		limparDistrito();

	}

	private void updateDistrito() {

		Distrito distrito = new Distrito();
		Provincia provincia = new Provincia();
		Provincia pro = (Provincia) cboProvincia.getSelectionModel().getSelectedItem();
		final int idPro = pro.getIdProvincia();
		provincia.setIdProvincia(idPro);
		distrito.setNome(txtNomeDistrito.getText().toUpperCase());
		distrito.setProvincia(provincia);
		distrito.setIdDistrito(Integer.parseInt(txtIdDistrito.getText()));
		DaoDistrito.update(distrito);
		limparDistrito();

	}

	private void deleteDistrito() {
		Distrito distrito = new Distrito();
		distrito.setIdDistrito(Integer.parseInt(txtIdDistrito.getText()));
		DaoDistrito.delete(distrito);
		limparDistrito();

	}

	// funcao
	private void addFuncao() {
		Funcao funcao=new Funcao();
		funcao.setNome(txtNomeFuncao.getText().toUpperCase());
		DaoFuncao.add(funcao);
		limparFuncao();
	}

	private void updateFuncao() {
		Funcao funcao=new Funcao();
		funcao.setNome(txtNomeFuncao.getText().toUpperCase());
		funcao.setIdFuncao(Integer.parseInt(txtIdFuncao.getText()));
		DaoFuncao.update(funcao);
		limparFuncao();
	}

	private void deleteFuncao() {
		Funcao funcao=new Funcao();
		funcao.setIdFuncao(Integer.parseInt(txtIdFuncao.getText()));
		DaoFuncao.delete(funcao);
		limparFuncao();
	}

	private void fillProvincia() {
		List<Provincia> list = DaoProvincia.getAllprovincia();
		for (Provincia items : list)
			cboProvincia.getItems().add(items);
	}

	private void showProvincia() {
		List<Provincia> list = DaoProvincia.getAllprovincia();
		final ObservableList<Provincia> obserList = FXCollections.observableArrayList(list);
		colIDProvincia.setCellValueFactory(new PropertyValueFactory<>("idProvincia"));
		colProvinciaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tblProvincia.setItems(obserList);

	}

	private void showDistrito() {

		List<Distrito> list = DaoDistrito.getAlldistrito();
		final ObservableList<Distrito> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idDistrito"));
		colDistritoUrbano.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colIProv.setCellValueFactory(new PropertyValueFactory<>("provincia"));
		tblDistritoUrbano.setItems(obserList);

	}

	private void showFuncao() {
		List<Funcao> list = DaoFuncao.getAllFuncao();
		final ObservableList<Funcao> obserList = FXCollections.observableArrayList(list);
		colIdFuncao.setCellValueFactory(new PropertyValueFactory<>("idFuncao"));
		colNomeFuncao.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tblFuncao.setItems(obserList);

	}
	
	private void limparFuncao() {
		txtIdFuncao.clear();
		txtNomeFuncao.clear();
	}
	private void  limparProvincia() {
		txtIdProvincia.clear();
		txtProvincia.clear();
	}
	private void  limparDistrito() {
		txtIdDistrito.clear();
		txtNomeDistrito.clear();
		//cboProvincia.getItems().clear();
	}
	
}
