package mz.co.mahs.controller;
import java.util.List;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mz.co.mahs.dao.DaoCliente;
import mz.co.mahs.models.Cliente;
import mz.co.mahs.models.Distrito;
/**
 * <h1>FXMLAllClientsController</h1>
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

public class FXMLAllClientsController {
	FXMLFormasPagamentoController f=new FXMLFormasPagamentoController();
	@FXML
	private AnchorPane rootCliente;
	@FXML
	private TextField txtProcurar;

	@FXML
	private TableView<Cliente> tblCliente;

	@FXML
	private TableColumn<Cliente, Integer> colId;

	@FXML
	private TableColumn<Cliente, String> colNome;

	@FXML
	private TableColumn<Cliente, String> colApelido;

	@FXML
	private TableColumn<Cliente, String> colGenero;

	@FXML
	private TableColumn<Cliente, String> colEmail;

	@FXML
	private TableColumn<Cliente, String> colTelefone;

	@FXML
	private TableColumn<Cliente, String> colEndereco;
	@FXML
	private TableColumn<Cliente, Distrito> colDistritoUrbano;

	@FXML
	private TableColumn<Cliente, String> colDataRegisto;

	
	/**
	 * Este evento e o primeiro a ser chamado que chama a funcao 
	 * que popula os dados na tabela 
	 */
	@FXML
	private void initialize() {
		showInfo();
	}

	@FXML
	private void handleMouseClickAction(MouseEvent event) {
		Stage stage = (Stage) this.txtProcurar.getScene().getWindow();

		try {

			//Cliente cliente = tblCliente.getSelectionModel().getSelectedItem();
			//String id=("" + cliente.getIdCliente());
			//String nome=("" + cliente.getNome());
			//String telefone=("" + cliente.getTelefone());
			
			stage.close();
		} catch (java.lang.NullPointerException e) {
			JOptionPane.showMessageDialog(null, "EXCEPTION " + e.getMessage());
			//e.printStackTrace();
		}
	}
	/**Este evento chama a funcao que esta  no DaoCliente
	 * @see DaoCliente  que conte o metodo de busca
	 * 
	 * */
	@FXML
	 private void procurar(KeyEvent event) {
		  txtProcurar.setOnKeyReleased(e->{
			  
			  List<Cliente> listCliente = DaoCliente.search(txtProcurar.getText());
				final ObservableList<Cliente> obserList = FXCollections.observableArrayList(listCliente);
				colId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
				colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
				colApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));
				colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
				colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
				colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
				colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
				colDataRegisto.setCellValueFactory(new PropertyValueFactory<>("dataRegisto"));
				tblCliente.setItems(obserList); 
		  
		  });
		  }
	/**Esta Funcoa  prepara os dados para ser populados na tabela vindo de uma ListView
	 * que esta na classe @see DaoCliente*/
	public void showInfo() {
		List<Cliente> list = DaoCliente.getAllCliente();
		final ObservableList<Cliente> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));
		colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		colDistritoUrbano.setCellValueFactory(new PropertyValueFactory<>("distrito"));
		colDataRegisto.setCellValueFactory(new PropertyValueFactory<>("dataRegisto"));
		tblCliente.setItems(obserList);

	}

}
