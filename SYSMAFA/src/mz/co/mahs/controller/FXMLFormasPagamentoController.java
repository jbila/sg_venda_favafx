package mz.co.mahs.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mz.co.mahs.dao.DaoCliente;
import mz.co.mahs.dao.DaoFormasDePagamento;
import mz.co.mahs.dao.DaoItemsPedidos;
import mz.co.mahs.dao.DaoPedido;
import mz.co.mahs.models.Cliente;
import mz.co.mahs.models.FormasDePagamento;
import mz.co.mahs.models.ItemsPedidos;
import mz.co.mahs.models.Pedido;
import mz.co.mahs.models.Producto;
import mz.co.mahs.models.Utilizador;

public class FXMLFormasPagamentoController {
	FXMLPedidoController f=new FXMLPedidoController();
	private int idPedido =0;
	Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	Alert alertErro = new Alert(AlertType.ERROR);

	   @FXML
	    private AnchorPane rootFormasDePagamento;

	    @FXML
	    private TextField txtvalorApagar;

	    @FXML
	    private Button btnConfirmarPagemento;

	    @FXML
	    private ComboBox<Cliente> cboCliente;
		List<Cliente> listCliente = new ArrayList<>();

	    @FXML
	    private ComboBox<FormasDePagamento> cboFormaDePagamento;
		List<FormasDePagamento> listFormasDePagamento = new ArrayList<>();

	    @FXML
	    private Button btnCliente;

	    @FXML
	    private TextField txtNomeCliente;

	    @FXML
	    private TextField txtTelefone;

	    @FXML
	    private TextField txtEmailCliente;

	    @FXML
	    private TextField txtValorTotal;

	    @FXML
	    private TextField txtTrocos;

	    @FXML
	    private Label lblNumero;

	    @FXML
	    private TextField txtNumeroParcela;

	    @FXML
	    private Button btnParcelar;

	    @FXML
	    private TableView<?> tblParcelamentos;

	    @FXML
	    private TableColumn<?, ?> colCodigoVenda;

	    @FXML
	    private TableColumn<?, ?> colDataApagar;

	    @FXML
	    private TableColumn<?, ?> colValor;

	    @FXML
	    private TableColumn<?, ?> colEstado;

	    @FXML
	    private TableColumn<?, ?> colDataPagamento;

	    
	    @FXML
		private void initialize() {
			
			fillCliente();
			fillFormasDePagamento();
			tblParcelamentos.setVisible(false);
			txtNumeroParcela.setVisible(false);
			btnParcelar.setVisible(false);
		}
	    @FXML
	   private  void calcularTrocos(KeyEvent event) {
	    	
	    	txtvalorApagar.setOnKeyReleased(e->{
	    		double trocos=(Double.parseDouble(txtvalorApagar.getText())-(Double.parseDouble(txtValorTotal.getText())));
	    		txtTrocos.setText(""+trocos);
	    	});   }

	    @FXML
	    private  void clientes(ActionEvent event) {
	    	openAllClients();
	    }

	    @FXML
	    private void confirmarPagamento(ActionEvent event) {
	    	/**Este Metodo adiciona o pedido ou a venda 
	    	 * dentro deste metodo chamamos o metodo adiconar itens da venda
	    	 * */
	    		addPedido();
	    		
	    		f.actualizarStock();
	    		//f.limpaCampos();
	    		//f.showInfo();
	    }
	    private void fillCliente() {
			listCliente = DaoCliente.getAllCliente();
			for (Cliente cliente : listCliente)
				cboCliente.getItems().add(cliente);
		}

		// filFormasDePagamento
		private void fillFormasDePagamento() {
			listFormasDePagamento = DaoFormasDePagamento.getAll();
			for (FormasDePagamento formasDePagamento : listFormasDePagamento)
				cboFormaDePagamento.getItems().add(formasDePagamento);
		}
		
		//=========================================================================
		private void openAllClients() {
			Stage stage = new Stage();
			try {

				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mz/co/mahs/views/FXMLAllClients.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
				stage.setScene(scene);
				stage.initStyle(StageStyle.DECORATED);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.centerOnScreen();
				stage.show();
			} catch (Exception e) {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Erro ao Carregar o Ficheiro " + e);
				alertErro.showAndWait();
			}
		}
		//===========================================================
		private void addPedido() {

			Pedido pedido = new Pedido();// OBJECTO PRINCIPAL
			Cliente cliente = new Cliente();// OBJECTO SECUNDARIO
			Cliente cli = (Cliente) cboCliente.getSelectionModel().getSelectedItem();
			final int idCliente = cli.getIdCliente();
			cliente.setIdCliente(idCliente);

			Utilizador utilizador = new Utilizador();// OBJECTO SECUNDARIO
			utilizador.setIdUtilizador(ControllerLogin.idUsuario);

			FormasDePagamento formasDepagamento = new FormasDePagamento();// OBJECTO SECUNDARIO
			FormasDePagamento formasPagamento = (FormasDePagamento) cboFormaDePagamento.getSelectionModel()
					.getSelectedItem();
			final int idFormasPagamento = formasPagamento.getId();
			formasDepagamento.setId(idFormasPagamento);

			pedido.setFormasDepagamento(formasDepagamento);
			pedido.setUtilizador(utilizador);
			pedido.setCliente(cliente);
			//pedido.setTipo(cbo);
			pedido.setValorPedido(Double.parseDouble(txtValorTotal.getText()));
			idPedido = DaoPedido.add(pedido);
			f.addItmes(idPedido);
			
			
		}
		
//==============================================================================
		public void addItmes() {
			List<ItemsPedidos> listItem = new ArrayList<>();
			f.tblItems.getSelectionModel().getSelectedItems();
			for (int i = 0; i < f.tblItems.getItems().size(); i++) {
				ItemsPedidos itemsPedido = new ItemsPedidos();
				Pedido pedido = new Pedido();
				pedido.setIdPedido(idPedido);
				Producto producto = new Producto();
				producto.setIdProducto(f.colId.getCellData(i));
				itemsPedido.setQuantidade(f.colQty.getCellData(i));
				itemsPedido.setPrecoUnitario(f.colPreco.getCellData(i));
				itemsPedido.setProducto(producto);
				itemsPedido.setPedido(pedido);
				listItem.add(itemsPedido);
			}

			DaoItemsPedidos.add(listItem);
			}
		
		
}
