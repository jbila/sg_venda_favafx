package mz.co.mahs.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mz.co.mahs.dao.DaoDistrito;
import mz.co.mahs.dao.DaoFuncao;
import mz.co.mahs.dao.DaoFuncionario;
import mz.co.mahs.dao.DaoProvincia;
import mz.co.mahs.dao.DaoRelatorio;
import mz.co.mahs.models.Distrito;
import mz.co.mahs.models.Funcao;
import mz.co.mahs.models.Funcionario;
import mz.co.mahs.models.Provincia;

public class FXMLFuncionarioController implements Crud {
	Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertWarning = new Alert(AlertType.WARNING);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);

	@FXML
	private AnchorPane rootFuncionario;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtApelido;
	@FXML
	private ComboBox<String> cboGenero;
	ObservableList<String> genero = FXCollections.observableArrayList("M", "F");
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtTelefone;
	@FXML
	private TextField txtEndereco;
	@FXML
	private TextField txtNuit;
	@FXML
	private ComboBox<Funcao> cboFuncao;
	@FXML
	private TextField txtNumeroBi;
	@FXML
	private ComboBox<Provincia> cboProvincia;
	@FXML
	private ComboBox<Distrito> cboDistrito;
	@FXML
	private TextField txtSalario;
	@FXML
	private TextField txtId;
	@FXML
	private TableView<Funcionario> tblFuncionario;
	@FXML
	private TableColumn<Funcionario, Integer> colId;
	@FXML
	private TableColumn<Funcionario, String> colNome;
	@FXML
	private TableColumn<Funcionario, String> colEmail;
	@FXML
	private TableColumn<Funcionario, String> colTelefone;
	@FXML
	private TableColumn<Funcionario, String> colEndereco;
	@FXML
	private TableColumn<Funcionario, Integer> colNuit;
	@FXML
	private TableColumn<Funcionario, Funcao> colFuncao;
	@FXML
	private TableColumn<Funcionario, Distrito> colDistrito;
	@FXML
	private TableColumn<Funcionario, Double> colSalario;
	@FXML
	private TableColumn<Funcionario, String> colBI;
	@FXML
	private TableColumn<Funcionario, String> colGenero;
	@FXML
	private Button tbnAdd, btnRelatorio;
	@FXML
	private Button btnUpdade;
	@FXML
	private Button btnApagar;
	@FXML
	private TextField txtProcurar;

	@FXML
	private void initialize() {
		cboGenero.setItems(genero);
		cboGenero.setValue("M");
		showInfo();

		txtId.setVisible(false);
		fillProvincia();
		fillFuncao();

	}

	// Event Listener on TableView[#tblFuncionario].onMouseClicked
	@FXML
	private void handleFuncionario(MouseEvent event) {
		Funcionario funcionario = tblFuncionario.getSelectionModel().getSelectedItem();
		txtId.setText("" + funcionario.getIdFuncionario());
		txtNome.setText("" + funcionario.getNome());
		txtApelido.setText("" + funcionario.getApelido());
		txtEmail.setText("" + funcionario.getEmail());
		txtTelefone.setText("" + funcionario.getTelefone());
		txtEndereco.setText("" + funcionario.getEndereco());
		txtNuit.setText("" + funcionario.getNuit());
		txtNumeroBi.setText("" + funcionario.getNumeroBi());
	}

	// Event Listener on Button[#tbnAdd].onAction
	@FXML
	public void add(ActionEvent event) {
		// if(!(txtNome.getText().isEmpty())&& txtEmail.getText().isEmpty() &&
		// txtTelefone.getText().isEmpty()) {
		acessoAdd();
		showInfo();
		// }
		/*
		 * else { txtNome.setStyle("-fx-text-fill: blue;");
		 * txtEmail.setStyle("-fx-text-fill: blue;");
		 * txtTelefone.setStyle("-fx-text-fill: blue;");
		 * alertWarning.setHeaderText("Aviso");
		 * alertWarning.setHeaderText("Verificação de Dados");
		 * alertWarning.setContentText("Campos Obrigatórios "+"\n");
		 * alertWarning.showAndWait(); }
		 */

	}

	// Event Listener on Button[#btnUpdade].onAction
	@FXML
	public void update(ActionEvent event) {
		// if(!(txtNome.getText().isEmpty())&& txtEmail.getText().isEmpty() &&
		// txtTelefone.getText().isEmpty()) {

		acessoUpdate();
		showInfo();
		// }
		/*
		 * else { txtNome.setStyle("-fx-text-fill: blue;");
		 * txtEmail.setStyle("-fx-text-fill: blue;");
		 * txtTelefone.setStyle("-fx-text-fill: blue;");
		 * 
		 * alertWarning.setHeaderText("Aviso");
		 * alertWarning.setHeaderText("Verificação de Dados");
		 * alertWarning.setContentText("Campos Obrigatórios "+"\n");
		 * alertWarning.showAndWait(); }
		 */
	}

	// Event Listener on Button[#txtApagar].onAction
	@FXML
	public void delete(ActionEvent event) {
		acessoDelete();
		showInfo();
	}

	/**Este evento é responsavel por fazer busca quando um 
	 * caracter é introduzido
	 * @see DaoFuncionario
	 * Dentro desse evento é chamado a função de busca que recebe como parametro 
	 * o nome que é introduzido, e retorna a tubla no nome em destaque
	 * */
	@FXML
	private void procurador(KeyEvent event) {
		List<Funcionario> list=DaoFuncionario.search(txtProcurar.getText());
		final ObservableList<Funcionario> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		colBI.setCellValueFactory(new PropertyValueFactory<>("numeroBi"));
		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colNuit.setCellValueFactory(new PropertyValueFactory<>("nuit"));
		colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
		colDistrito.setCellValueFactory(new PropertyValueFactory<>("distrito"));
		colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
		tblFuncionario.setItems(obserList);
		
	}

	@FXML
	private void actionProvincia(ActionEvent event) {
		cboDistrito.getItems().clear();
		Provincia nome = cboProvincia.getValue();
		List<Distrito> listDistrito = DaoDistrito.search(nome);
		for (Distrito distrito : listDistrito)
			cboDistrito.getItems().add(distrito);
	}

	@Override
	public void acessoAdd() {
		try {
			Funcionario funcionario = new Funcionario();
			/* objecto secundario Distrito */
			Distrito distrito = new Distrito();
			Distrito dis = (Distrito) cboDistrito.getSelectionModel().getSelectedItem();
			final int idDistrito = dis.getIdDistrito();
			distrito.setIdDistrito(idDistrito);
			/* objecto secundario Funcao */
			Funcao funcao = new Funcao();
			Funcao func = (Funcao) cboFuncao.getSelectionModel().getSelectedItem();
			final int idFuncao = func.getIdFuncao();
			funcao.setIdFuncao(idFuncao);

			funcionario.setNome(txtNome.getText().toUpperCase());
			funcionario.setApelido(txtApelido.getText().toUpperCase());
			funcionario.setEmail(txtEmail.getText().toLowerCase());
			funcionario.setTelefone(txtTelefone.getText().toUpperCase());
			funcionario.setEndereco(txtEndereco.getText().toUpperCase());
			funcionario.setGenero(cboGenero.getValue());
			funcionario.setNuit(Integer.parseInt(txtNuit.getText()));
			funcionario.setNumeroBi(txtNumeroBi.getText());
			funcionario.setSalario(Double.parseDouble(txtSalario.getText()));
			funcionario.setFuncao(funcao);
			funcionario.setDistrito(distrito);
			DaoFuncionario.add(funcionario);
			limpar();
		} catch (NullPointerException ex) {
			alertWarning.setHeaderText("Aviso");
			alertWarning.setHeaderText("Validação de Dados");
			alertWarning.setContentText("Selecione a Função, provincia e Distrito Urbano ");
			alertWarning.showAndWait();
		} catch (NumberFormatException e) {
			txtSalario.setStyle("-fx-text-fill: red;");
			txtNuit.setStyle("-fx-text-fill: red;");
			alertWarning.setHeaderText("Aviso");
			alertWarning.setHeaderText("Verificação de Dados");
			alertWarning.setContentText("O salario  e Nuit devem ser  NÚMEROS  " + "\n");
			alertWarning.showAndWait();
		}
	}

	@Override
	public void acessoIsRecorded() {

	}

	@Override
	public void acessoUpdate() {
		try {
			Funcionario funcionario = new Funcionario();
			/* objecto secundario Distrito */
			Distrito distrito = new Distrito();
			Distrito dis = (Distrito) cboDistrito.getSelectionModel().getSelectedItem();
			final int idDistrito = dis.getIdDistrito();
			distrito.setIdDistrito(idDistrito);
			/* objecto secundario Funcao */
			Funcao funcao = new Funcao();
			Funcao func = (Funcao) cboFuncao.getSelectionModel().getSelectedItem();
			final int idFuncao = func.getIdFuncao();
			funcao.setIdFuncao(idFuncao);
			funcionario.setNome(txtNome.getText().toUpperCase());
			funcionario.setApelido(txtApelido.getText().toUpperCase());
			funcionario.setEmail(txtEmail.getText().toLowerCase());
			funcionario.setTelefone(txtTelefone.getText());
			funcionario.setEndereco(txtEndereco.getText().toUpperCase());
			funcionario.setGenero(cboGenero.getValue());
			funcionario.setNuit(Integer.parseInt(txtNuit.getText()));
			funcionario.setNumeroBi(txtNumeroBi.getText());
			funcionario.setSalario(Double.parseDouble(txtSalario.getText()));
			funcionario.setFuncao(funcao);
			funcionario.setDistrito(distrito);
			funcionario.setIdFuncionario(Integer.parseInt(txtId.getText()));
			DaoFuncionario.update(funcionario);
			limpar();
		} catch (NullPointerException ex) {
			alertWarning.setHeaderText("Aviso");
			alertWarning.setHeaderText("Validação de Dados");
			alertWarning.setContentText("Selecione a Função, provincia e Distrito Urbano ");
			alertWarning.showAndWait();
		} catch (NumberFormatException e) {
			txtNuit.setStyle("-fx-text-fill: red;");
			txtSalario.setStyle("-fx-text-fill: red;");
			alertWarning.setHeaderText("Infomação");
			alertWarning.setHeaderText("Verificação de Dados");
			alertWarning.setContentText("O salario e Nuit devem ser  NÚMEROS " + "\n");
			alertWarning.showAndWait();
		}
	}

	@Override
	public void acessoDelete() {
		Funcionario funcionario = new Funcionario();
		funcionario.setIdFuncionario(Integer.parseInt(txtId.getText()));
		DaoFuncionario.delete(funcionario);
		limpar();

	}

	@Override
	public void limpar() {
		txtId.clear();
		txtNome.clear();
		txtApelido.clear();
		txtTelefone.clear();
		txtNuit.clear();
		txtNumeroBi.clear();
		txtEmail.clear();
		txtEndereco.clear();
		txtSalario.clear();
	}

	@Override
	public void showInfo() {
		List<Funcionario> list = DaoFuncionario.getAllFuncionario();
		final ObservableList<Funcionario> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		colBI.setCellValueFactory(new PropertyValueFactory<>("numeroBi"));
		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colNuit.setCellValueFactory(new PropertyValueFactory<>("nuit"));
		colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
		colDistrito.setCellValueFactory(new PropertyValueFactory<>("distrito"));
		colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
		tblFuncionario.setItems(obserList);

	}

	/*-------------------------------------------------------*/
	private void fillProvincia() {
		List<Provincia> list = DaoProvincia.getAllprovincia();
		for (Provincia items : list)
			cboProvincia.getItems().add(items);
	}

	/*-------------------------------------------------------*/
	private void fillFuncao() {
		List<Funcao> list = DaoFuncao.getAllFuncao();
		for (Funcao items : list)
			cboFuncao.getItems().add(items);
	}

	/*-------------------------------------------------------*/
	@FXML
	private void relatorio(ActionEvent event) {
		DaoRelatorio.funcionarioReport();
	}
}
