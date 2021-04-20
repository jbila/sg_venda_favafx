
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import mz.co.mahs.dao.DaoCliente;
import mz.co.mahs.dao.DaoDistrito;
import mz.co.mahs.dao.DaoProvincia;
import mz.co.mahs.dao.DaoRelatorio;
import mz.co.mahs.models.Cliente;
import mz.co.mahs.models.Distrito;
import mz.co.mahs.models.Provincia;
import mz.co.mahs.models.Utilizador;

public class FXMLClienteController implements Initializable, Crud {
	Alert alert = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	Alert alertWarning = new Alert(AlertType.WARNING);


	@FXML
	private AnchorPane rootPane;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtApelido;

	@FXML
	private TextField txtEmail, txtProcurar;

	@FXML
	private TableView<Cliente> tblCliente;

	@FXML
	private TableColumn<Cliente, Integer> colId;

	@FXML
	private TableColumn<Cliente, String> colNome;

	@FXML
	private TableColumn<Cliente, String> colApelido;

	@FXML
	private TableColumn<Cliente, String> colEmail;

	@FXML
	private TableColumn<Cliente, String> colTelefone;

	@FXML
	private TableColumn<Cliente, String> colSexo;

	@FXML
	private TableColumn<Cliente, String> colEndereco;//

	@FXML
	private TableColumn<Cliente, Distrito> colDistritoUrbano;
	@FXML
	private TableColumn<Cliente, Utilizador> colUtilizador;

	@FXML
	private TableColumn<Cliente, String> colDataRegisto;

	@FXML
	private TextField txtEndereco;

	@FXML
	private TextField txtTelefone;

	@FXML
	private ComboBox<String> cboSexo;
	ObservableList<String> sexo = FXCollections.observableArrayList("M", "F");

	@FXML
	private ComboBox<Provincia> cboProvincia;
	@FXML
	private ComboBox<Distrito> cboDistrito;
	@FXML
	private TextField txtID;// cboProvincia

	@FXML
	private HBox hBoxButton;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnUpdate;

	@FXML
	private Button btnDelete,btnRelatorio;
	/**Chama a funcao que faz a operacao de inserir os dados
	 * @see acessoAdd()
	 * @see showInfo()*/
	@FXML
	private void add(ActionEvent event) {
		acessoAdd();
		showInfo();
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

		Cliente cliente = tblCliente.getSelectionModel().getSelectedItem();
		txtID.setText("" + cliente.getIdCliente());
		txtNome.setText("" + cliente.getNome());
		txtApelido.setText("" + cliente.getApelido());
		txtEmail.setText("" + cliente.getEmail());
		txtTelefone.setText("" + cliente.getTelefone());
		txtEndereco.setText("" + cliente.getEndereco());
		btnAdd.setVisible(false);
		btnUpdate.setVisible(true);
		btnDelete.setVisible(true);
	}

	@FXML
	private void procurar(KeyEvent event) {
		txtProcurar.setOnKeyReleased(e -> {
			List<Cliente> listCliente = DaoCliente.search(txtProcurar.getText());
			final ObservableList<Cliente> obserList = FXCollections.observableArrayList(listCliente);
			colId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
			colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
			colApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));
			colSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));
			colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
			colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
			colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
			colDistritoUrbano.setCellValueFactory(new PropertyValueFactory<>("distrito"));
			colUtilizador.setCellValueFactory(new PropertyValueFactory<>("utilizador"));
			colDataRegisto.setCellValueFactory(new PropertyValueFactory<>("dataRegisto"));
			tblCliente.setItems(obserList);
		});
	}

	@FXML
	private void update(ActionEvent event) {
		acessoUpdate();
		showInfo();
		btnAdd.setVisible(true);
		//btnUpdate.setVisible(false);
		//btnDelete.setVisible(false);
		/**/
		btnUpdate.setDisable(true);
		btnDelete.setDisable(true);
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		cboSexo.setItems(sexo);
		cboSexo.setValue("M");
		showInfo();
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		txtID.setVisible(false);
		fillProvincia();
	

	}
	/**Estafuncao  e responsavel do preencher o objecto a
	 * a ser gravado
	 * @see Cliente 
	 * @see DaoCliente
	 * @exception NullPointerException
	 * 
	 * */
	@Override
	public void acessoAdd() {
		try {
		// nome,genero,email,telefone,endereco
		Cliente cliente = new Cliente();
		Utilizador utilizador = new Utilizador();
		Distrito distrito = new Distrito();
		Distrito dis = (Distrito) cboDistrito.getSelectionModel().getSelectedItem();
		final int idDistrito = dis.getIdDistrito();
		distrito.setIdDistrito(idDistrito);

		utilizador.setIdUtilizador(ControllerLogin.idUsuario);
		cliente.setNome(txtNome.getText().toUpperCase());
		cliente.setApelido(txtApelido.getText().toUpperCase());
		cliente.setEmail(txtEmail.getText().toLowerCase());
		cliente.setTelefone(txtTelefone.getText().toUpperCase());
		cliente.setEndereco(txtEndereco.getText().toUpperCase());
		cliente.setGenero(cboSexo.getValue().toLowerCase());
		cliente.setDistrito(distrito);
		cliente.setUtilizador(utilizador);
		if(DaoCliente.isRecorded(txtTelefone.getText(), txtEmail.getText())) {
			alertWarning.setTitle("Informacao");
			alertWarning.setHeaderText("Verificação de Dados");
			alertWarning.setContentText("Este Cliente já existe!");
			alertWarning.showAndWait();
		}
		
		else {
		DaoCliente.addCliente(cliente);
		limpar();
		}
		btnAdd.setVisible(true);
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		}
		catch(NullPointerException ex) {
			alertWarning.setTitle("Aviso");
			alertWarning.setHeaderText("Verificação de Dados");
			alertWarning.setContentText("Selecione a Provincia e o distrito");
			alertWarning.showAndWait();
			
		}
	}

	@Override
	public void acessoIsRecorded() {

	}
	/**<p>Funcao Responsavel por chamar e preencher objecto para a sua alteracao
	 * Para esta ser satisfeita, deve se selecionar o registo ou pesquisar o registo em causa
	 * para posterior editar se necessario</p>
	 * @see <h3>DaoCliente.updateCliente(cliente);<h3>

	 * */
	@Override
	public void acessoUpdate() {
		try {
		Cliente cliente = new Cliente();

		Utilizador utilizador = new Utilizador();

		Distrito distrito = new Distrito();
		Distrito dis = (Distrito) cboDistrito.getSelectionModel().getSelectedItem();
		final int idDistrito = dis.getIdDistrito();
		distrito.setIdDistrito(idDistrito);

		utilizador.setIdUtilizador(ControllerLogin.idUsuario);
		cliente.setNome(txtNome.getText().toUpperCase());
		cliente.setApelido(txtApelido.getText().toUpperCase());
		cliente.setEmail(txtEmail.getText().toLowerCase());
		cliente.setTelefone(txtTelefone.getText().toUpperCase());
		cliente.setEndereco(txtEndereco.getText().toUpperCase());
		cliente.setGenero(cboSexo.getValue().toUpperCase());
		cliente.setDistrito(distrito);
		cliente.setUtilizador(utilizador);
		cliente.setIdCliente(Integer.parseInt(txtID.getText()));
		DaoCliente.updateCliente(cliente);
		limpar();
		}
		catch(NullPointerException ex) {
			alertWarning.setTitle("Aviso");
			alertWarning.setHeaderText("Verificação de Dados");
			alertWarning.setContentText("Selecione a Provincia e o distrito");
			alertWarning.showAndWait();
			
		}

	}
	/**Esta funcao é responsável por eliminar o registo selecionado
	 * Nota,see o registo pretendido for vinculado numa venda, isto é apagar um cliente qque tenha
	 * vendas feitas, o sistem não lhe permitirá 
	 * completar o processo,
	 * @exception SecurityException
	 * @see  DaoCliente ver*/
	@Override
	public void acessoDelete() {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(Integer.parseInt(txtID.getText()));
		DaoCliente.deleteCliente(cliente);
		limpar();
	}
	/**<h1>Esta Funcao limpa os campos </h>*/
	@Override
	public void limpar() {
		// nome,genero,email,telefone,endereco
		txtNome.setText("");
		txtTelefone.setText("");
		txtEndereco.setText("");
		txtEmail.setText("");
		txtApelido.setText("");

	}
	/**<h2>Esta Funcao lista a informacao na tabela
	 * vindo duma lista
	 * @see DaoCliente.getAllCliente()</h2>
	 * 
	 * */
	@Override
	public void showInfo() {
		// nome,genero,email,telefone,endereco

		List<Cliente> list = DaoCliente.getAllCliente();
		final ObservableList<Cliente> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));
		colSexo.setCellValueFactory(new PropertyValueFactory<>("genero"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colDistritoUrbano.setCellValueFactory(new PropertyValueFactory<>("distrito"));
		colUtilizador.setCellValueFactory(new PropertyValueFactory<>("utilizador"));
		colDataRegisto.setCellValueFactory(new PropertyValueFactory<>("dataRegisto"));
		tblCliente.setItems(obserList);

	}

	private void fillProvincia() {
		List<Provincia> list = DaoProvincia.getAllprovincia();
		for (Provincia items : list)
			cboProvincia.getItems().add(items);
	}
	/**<h3>Esta funcao chama o relatorio do</h3>*/
	@FXML
	private void relatorio(ActionEvent event) {
		DaoRelatorio.clienteReport();
	}
}
