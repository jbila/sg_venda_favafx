
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
import mz.co.mahs.dao.DaoRelatorio;
import mz.co.mahs.models.Categoria;
import mz.co.mahs.models.Utilizador;
/**
 * <h1>FXMLCategoriaController</h1>
 * <p>
 * Esta classe tem eventos que controla a entrada de dados,<b>validacoes de
 * campos</b> e <b>preenchimento de combobox como de tabelas</b><br>
 * ela comunica directamente com as classes que estao no pacote Dao e Model <br>
 * Possibilitando a troca de dados entre o utilizador e o sistema
 * </p>
 * <P>
 * Salientar que esta classe captura os dados das Telas que sao da extensao
 * <b>FXML do javaFX</b>
 * </P>
 * 
 * @author JACINTO BILA
 *         <h3>Contacto:848319153 Email:jacinto.billa@gmail.com
 *         </h3>
 * 
 */
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
	private Button btnDelete,btnRelatorio;
	/**Este evento ocorre quando uma linha da tabela for clicado
	 * o evento preenche o campos usando para a insercao de dados
	 * possibilitando o utilizador de 
	 * <li>Eliminar ou registo</li>
	 * <li>Actualizar</li>
	 * <p>Quando isso acontece o botao adicionar fica invesivel</p>
	 * */
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

	/**O evento faz a validacao dos campos e posterior chama a funcao
	 * que preenche o objecto para a 
	 * */
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
	/**Este evento faz busca*/
	@FXML
	private void procurar(KeyEvent event) {
		txtSearch.setOnKeyReleased(E -> {

		});

	}
	/**Este evento chama a funcao que faz a remocao da tupla selecionada*/
	@FXML
	private void delete(ActionEvent event) {
		acessoDelete();
		showInfo();
		
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		btnAdd.setVisible(true);
	}
	/**Este evento faz a busca na tabela*/
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
	/**Este evento chama a funcao que faz a actualizacao da tupla selecionada*/

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
	/**Este  metodo insere uma categoria no objecto*/
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
		//System.out.println(list);
		final ObservableList<Categoria> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colUtilizador.setCellValueFactory(new PropertyValueFactory<>("utilizador"));
		tblCategoria.setItems(obserList);

	}
	/*------------------------------------------------------------*/
	@FXML
	private void relatorio(ActionEvent event) {
		DaoRelatorio.categoriaReport();
	}
}
