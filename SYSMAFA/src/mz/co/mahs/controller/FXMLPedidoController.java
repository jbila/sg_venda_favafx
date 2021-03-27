package mz.co.mahs.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mz.co.mahs.dao.DaoCliente;
import mz.co.mahs.dao.DaoFormasDePagamento;
import mz.co.mahs.dao.DaoItemsPedidos;
import mz.co.mahs.dao.DaoParcela;
import mz.co.mahs.dao.DaoPedido;
import mz.co.mahs.dao.DaoProducto;
import mz.co.mahs.models.Categoria;
import mz.co.mahs.models.Cliente;
import mz.co.mahs.models.FormasDePagamento;
import mz.co.mahs.models.ItemsPedidos;
import mz.co.mahs.models.Parcela;
import mz.co.mahs.models.Pedido;
import mz.co.mahs.models.Producto;
import mz.co.mahs.models.Utilizador;

public class FXMLPedidoController {
	/**
	 * Esta variavel armazena o codigo da venda ou pedido para posterior ser gravado
	 * na tabela items da venda ou items do pedido
	 */
	int idPedido = 0;
	public static String idCliente = "0", nomeCliente = "0";
	List<ItemsPedidos> data = new ArrayList<>();
	public static double total = 0, subTotal = 0, diminuir = 0;
	Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	Alert alertErro = new Alert(AlertType.ERROR);
	@FXML
	private AnchorPane anchorPaneVendas, rootFormasDePagamento;
	@FXML
	private Pane paneProducto;

	/**
	 * Esta tabela e usada para exibir informacao de producto para ser selecionada
	 * no acto de adicionar items
	 */
	@FXML
	private TableView<Producto> tblProducto;
	@FXML
	private TableColumn<Producto, Integer> colIdProducto;
	@FXML
	private TableColumn<Producto, String> colCodigoProducto;
	@FXML
	private TableColumn<Producto, String> colNomeProducto;
	@FXML
	private TableColumn<Producto, Categoria> colCategoria;
	@FXML
	private TableColumn<Producto, Integer> colQuantidade;
	@FXML
	private TableColumn<Producto, Double> colPrecoProducto;
	@FXML
	private TableColumn<Producto, String> colDescricao;
	/** ------------------------------------------------------ */
	@FXML
	private Pane paneCarrinha;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtPreco;
	@FXML
	private TextField txtQty;
	@FXML
	private TextField txtSutotal;
	@FXML
	private TextField txtTrocos, txtValorApagar, txtNumeroParcela, txtvalorApagar, txtValorTotal;
	@FXML
	private TextField txtProcurar;
	/** usada para pesquisar dentro da tabela */

	/** SEGUNDA TABELA items */
	@FXML
	public TableView<ItemsPedidos> tblItems = new TableView<ItemsPedidos>();
	@FXML
	public TableColumn<ItemsPedidos, Integer> colId;
	@FXML
	private TableColumn<ItemsPedidos, String> colItemDescricao;
	@FXML
	private TableColumn<ItemsPedidos, Producto> colNome;
	@FXML
	public TableColumn<ItemsPedidos, Double> colPreco;
	@FXML
	private TableColumn<ItemsPedidos, Double> colSubTotal;
	@FXML
	public TableColumn<ItemsPedidos, Integer> colQty;
	/**
	 * Esta tabela é usada para mostrar informacao de parcelamento da conta
	 */
	@FXML
	public TableView<Parcela> tblParcelamentos = new TableView<Parcela>();
	@FXML
	public TableColumn<Parcela, Integer> colCodigoVenda;
	@FXML
	private TableColumn<Parcela, String> colDataApagar;
	@FXML
	private TableColumn<Parcela, Double> colValor;
	@FXML
	private TableColumn<Parcela, String> colEstado;
	@FXML
	private TableColumn<Parcela, String> colDataPagamento;
	@FXML
	private ComboBox<Producto> cboProducto;
	List<Producto> listProducto = new ArrayList<>();
	@FXML
	private ComboBox<FormasDePagamento> cboFormaDePagamento, cboFormaDePagamento1;
	List<FormasDePagamento> listFormasDePagamento = new ArrayList<>();
	@FXML
	private ComboBox<String> cboTipo;
	ObservableList<String> tipo=FXCollections.observableArrayList("COMPLETA","PARCELADA");
	@FXML
	Button btnAdicionar, btnCancelar, btnConfirmarPagamento, btnVoltar;
	@FXML
	private Button btnRemover;
	@FXML
	public Label lblTotal = new Label();
	@FXML
	private Button btnGoPagamento;
	@FXML
	private Button btnCliente, btnCliente1, btnParcelar;
	@FXML
	private Pane topVendasPane;
	/** Estes campossao usados para exibir informacao do cliente */
	@FXML
	private ComboBox<Cliente> cboCliente, cboCliente1;
	List<Cliente> listCliente = new ArrayList<>();

	/**
	 * Este é o primeiro método que é carregado assim que o formulário de pedido ou
	 * venda é invocado ou em outras palavras é o primeiro evento que roda na
	 * aplicação ---------------------------------------------------------
	 */
	@FXML
	private void initialize() {
		rootFormasDePagamento.setVisible(false);
		tblProducto.setVisible(true);
		txtProcurar.setVisible(true);
		cboTipo.setItems(tipo);
		cboTipo.setValue("COMPLETA");

		showInfo();
		txtId.setVisible(false);// este e id de producto
		fillCliente();
		fillFormasDePagamento();
		total = 0;
		
		txtNumeroParcela.setVisible(false);
		btnParcelar.setVisible(false);
	}

	/**
	 * Esta metodo chama o formulario de formas de pagamento que inclui o total da
	 * venda,nome do cliente as formas de pagamento
	 */
	@FXML
	private void avancar(ActionEvent event) {
		if (tblItems.getItems().size() > 0) {
			rootFormasDePagamento.setVisible(true);
			tblProducto.setVisible(false);
			txtProcurar.setVisible(false);
			txtValorTotal.setText(lblTotal.getText());
		} else {
			alertInfo.setHeaderText("Informacao");
			alertInfo.setContentText("Adicione items na carrinha primeiro ");
			alertInfo.showAndWait();
		}

		// openFormasPagamento();
		/**
		 * Este metodo adiciona o pedido ou venda na tabela tbl_pedido e dentro dela é
		 * chamado metodo addItems, que adiciona os itens da venda ou pedido
		 */
	}

	@FXML
	private void confirmarPagamento(ActionEvent event) {
		addPedido();
		actualizarStock();
		calcularParcelaII();
		limpaCampos();
		showInfo();

	}
	/**
	 * Este metodo assegura a seleccao de VENDA  de forma que quando a selecao
	 * for parcelada o botao parcelar e o campo de numero de 
	 * fique habilitado
	 * parcelas
	 * 
	 */
	@FXML
	private void changeTipo(ActionEvent event) {
				cboTipo.setOnAction(ev-> {
			if ((cboTipo.getSelectionModel().getSelectedItem()).equalsIgnoreCase("PARCELADA")) {

				txtNumeroParcela.setVisible(true);
				btnParcelar.setVisible(true);
				
			} else {
			
				txtNumeroParcela.setVisible(false);
				btnParcelar.setVisible(false);
			}
		});

	}

	@FXML
	private void changed(ActionEvent event) {
				cboFormaDePagamento1.setOnAction(ev-> {
		});

	}

	/*------------------------------------*/
	@FXML
	private void parcelar(ActionEvent event) {
		tblParcelamentos.getItems().clear();
			calcularParcela();
	}

	/*------------------------------------*/
	@FXML
	private void voltar(ActionEvent event) {
		tblProducto.setVisible(true);
		txtProcurar.setVisible(true);
		tblProducto.setVisible(true);
		rootFormasDePagamento.setVisible(false);
	}

	/*------------------------------------*/
	@FXML
	private void calcularTrocos(KeyEvent event) {
		txtvalorApagar.setOnKeyReleased(e -> {
			double trocos = (Double.parseDouble(txtvalorApagar.getText())
					- (Double.parseDouble(txtValorTotal.getText())));
			txtTrocos.setText("" + trocos);
		});

	}

	@FXML
	public void handleMouseClickAction(MouseEvent event) {
		Producto producto = tblProducto.getSelectionModel().getSelectedItem();
		txtId.setText("" + producto.getIdProducto());
		txtNome.setText("" + producto.getNome());
		txtPreco.setText("" + producto.getPrecoFinal());
	}

	//
	@FXML
	private void addItems(ActionEvent event) {
		if (txtQty.getText().isEmpty() && txtSutotal.getText().isEmpty()) {
			alertInfo.setHeaderText("Informacao");
			alertInfo.setContentText("Adicione o iten e adicione a Qty ");
			alertInfo.showAndWait();
		} else {
			addRow();// este metodo adiciona uma linha na tabela de itens
			total = (total + subTotal);
			lblTotal.setText("" + total);
			txtQty.setText("0");
			txtNome.clear();
			txtSutotal.clear();
			txtPreco.clear();
		}
	}

	@FXML
	private void removeItems(ActionEvent event) {

		if (tblItems.getItems().size() > 0)
			deleteRow();

		else {

			alertInfo.setHeaderText("Informacao");
			alertInfo.setContentText("Tabela vazia ");
			alertInfo.showAndWait();
		}

	}

	@FXML
	private void productoList(ActionEvent event) {

	}

	/** Este evento ocorre quando a quantidade e intruduzida */
	@FXML
	private void calcularSubtotal(KeyEvent event) {
		if (txtQty.getText().equalsIgnoreCase("")) {
			txtSutotal.setText("0.0");
		} else {
			subTotal = (Integer.parseInt(txtQty.getText()) * (Double.parseDouble(txtPreco.getText())));
			txtSutotal.setText("" + subTotal);
		}

	}

	/** Este metodo adiciona producto que estao na base de dados */
	public void showInfo() {
		List<Producto> list = DaoProducto.getAll();
		colIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
		colCodigoProducto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		colNomeProducto.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		colPrecoProducto.setCellValueFactory(new PropertyValueFactory<>("precoFinal"));
		tblProducto.getItems().addAll(list);
	}

	// -------------------------------------------------------------------------------
	@FXML
	private void clientes(ActionEvent event) {
		openAllClients();

	}

	// --------------------------------------------------------------------------
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

	// -------------------------------------------------------------------------
	

	private void fillCliente() {
		listCliente = DaoCliente.getAllCliente();
		for (Cliente cliente : listCliente)
			cboCliente1.getItems().add(cliente);
	}

	// filFormasDePagamento
	private void fillFormasDePagamento() {
		listFormasDePagamento = DaoFormasDePagamento.getAll();
		for (FormasDePagamento formasDePagamento : listFormasDePagamento)
			cboFormaDePagamento1.getItems().add(formasDePagamento);
	}

	/** Este metodo adiciona itens na tabela */
	private void addRow() {

		ItemsPedidos items = new ItemsPedidos();
		Producto producto = new Producto();
		producto.setNome(txtNome.getText());
		producto.setIdProducto(Integer.parseInt(txtId.getText()));

		items.setProducto(producto);
		items.setPrecoUnitario(Double.parseDouble(txtPreco.getText()));
		items.setQuantidade(Integer.parseInt(txtQty.getText()));
		items.setSubTotal(Double.parseDouble(txtPreco.getText()) * Integer.parseInt(txtQty.getText()));
		items.setIdp(Integer.parseInt(txtId.getText()));
		data.add(items);
		int row = data.size();

		tblItems.requestFocus();
		tblItems.getSelectionModel().select(row);
		tblItems.getFocusModel().focus(row);

		colId.setCellValueFactory(new PropertyValueFactory<>("idp"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("producto"));
		colQty.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		colPreco.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
		colSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
		tblItems.getItems().add(items);
	}

	private void deleteRow() { 
		btnRemover.setOnAction(e -> {
			ItemsPedidos selectedItem = tblItems.getSelectionModel().getSelectedItem();
			double t=0.0;
			for (int i = 0; i < tblItems.getItems().size(); i++) {
				ItemsPedidos itemsPedido = new ItemsPedidos();
				itemsPedido.setSubTotal(colSubTotal.getCellData(i));
				t=colSubTotal.getCellData(i);
				//tblItems.getItems().remove(selectedItem);
				//tblItems.getItems().remove(i);
				//System.out.println(t);
				tblItems.getItems().remove(selectedItem);
		        System.out.println(t);

			}
				 
			   
			
			
			double temp=total-t;
			
			lblTotal.setText("" + temp);

		});
	}

	/**
	 * Este medido adicona o pedido ou a venda
	 */
	private void addPedido() {
		Pedido pedido = new Pedido();// OBJECTO PRINCIPAL
		Cliente cliente = new Cliente();// OBJECTO SECUNDARIO
		Cliente cli = (Cliente) cboCliente1.getSelectionModel().getSelectedItem();
		final int idCliente = cli.getIdCliente();
		cliente.setIdCliente(idCliente);

		Utilizador utilizador = new Utilizador();// OBJECTO SECUNDARIO
		utilizador.setIdUtilizador(ControllerLogin.idUsuario);

		FormasDePagamento formasDepagamento = new FormasDePagamento();// OBJECTO SECUNDARIO
		FormasDePagamento formasPagamento = (FormasDePagamento) cboFormaDePagamento1.getSelectionModel()
				.getSelectedItem();
		final int idFormasPagamento = formasPagamento.getId();
		formasDepagamento.setId(idFormasPagamento);

		pedido.setFormasDepagamento(formasDepagamento);
		pedido.setUtilizador(utilizador);
		pedido.setCliente(cliente);
		
		if(cboTipo.getSelectionModel().getSelectedItem().equals("COMPLETA"))
		pedido.setTipo("C");
		if(cboTipo.getSelectionModel().getSelectedItem().equals("PARCELADA"))
		pedido.setTipo("P");	
		
		pedido.setValorPedido(Double.parseDouble(lblTotal.getText()));
		/**--------------------------------------------------------*/
		if (cboTipo.getSelectionModel().getSelectedItem().equalsIgnoreCase("PARCELADA")) {
			pedido.setValorPago(Double.parseDouble(lblTotal.getText())/(Integer.parseInt(txtNumeroParcela.getText())));
		}
		if (cboTipo.getSelectionModel().getSelectedItem().equalsIgnoreCase("COMPLETA"))
		{
			pedido.setValorPago(Double.parseDouble(lblTotal.getText()));
		}		

		/**---------------------------------------------------------*/
		idPedido = DaoPedido.add(pedido);
		addItmes(idPedido);
		
		
	}
	/**ADICIONA PEDIDO PARCELADO*/
		
	/**
	 * Este medido adicona o items do pedido
	 */

	// addIttems na tabela
	public void addItmes(int idPedido) {
		List<ItemsPedidos> listItem = new ArrayList<>();
		tblItems.getSelectionModel().getSelectedItems();
		for (int i = 0; i < tblItems.getItems().size(); i++) {
			ItemsPedidos itemsPedido = new ItemsPedidos();
			Pedido pedido = new Pedido();
			pedido.setIdPedido(idPedido);
			Producto producto = new Producto();
			producto.setIdProducto(colId.getCellData(i));
			itemsPedido.setQuantidade(colQty.getCellData(i));
			itemsPedido.setPrecoUnitario(colPreco.getCellData(i));
			itemsPedido.setProducto(producto);
			itemsPedido.setPedido(pedido);
			listItem.add(itemsPedido);
		}
		DaoItemsPedidos.add(listItem);
		total = 0.0;
		lblTotal.setText(total + "0.0");
	}

	/** procurador */
	@FXML
	private void procurador(KeyEvent event) {
		List<Producto> list = DaoProducto.searchAll(txtProcurar.getText());
		final ObservableList<Producto> obserList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colQty.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		tblProducto.setItems(obserList);
	}

	/** Este metodo limpa campos de e esvazia a tabela de itens */
	public void limpaCampos() {
		txtId.clear();
		txtNome.clear();
		txtPreco.clear();
		txtSutotal.clear();
		txtTrocos.clear();
		subTotal = 0.0;
		total = 0.0;
		tblItems.getItems().clear();
		txtTrocos.clear();

	}

	/**
	 * Este metodo actualiza o stock em cada venda do producto levando consigo uma
	 * lista de productos que contem os ids de productos e a quantidade a se retirar
	 */
	public void actualizarStock() {
		List<Producto> idItems = new ArrayList<>();
		tblItems.getSelectionModel().getSelectedItems();
		for (int i = 0; i < tblItems.getItems().size(); i++) {

			Producto producto = new Producto();
			producto.setQuantidade(colQty.getCellData(i));
			producto.setIdProducto(colId.getCellData(i));

			idItems.add(producto);
		}
		DaoProducto.updateQty(idItems);
	}

//-----------------------------------------------------------------------
	private void calcularParcela() {	
		//tblParcelamentos.getItems().clear();
		List<Parcela> parcelas = new ArrayList<>();//esta lista contem as parcelas dos pedidos


		if (Double.parseDouble(txtValorTotal.getText()) > 1000)

		{
			if(Integer.parseInt(txtNumeroParcela.getText())>1) {
			
			for (int i = 0; i < Integer.parseInt(txtNumeroParcela.getText()); i++) {

				Parcela parcela = new Parcela();
				Pedido pedido = new Pedido();
				pedido.setIdPedido(idPedido);

				LocalDate localDate = LocalDate.now();
				String dataPrevista = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate.plusDays(30 * i));

				double divida = Double.parseDouble(txtValorTotal.getText())
						/ Integer.parseInt(txtNumeroParcela.getText());
				parcela.setDataPrevista(dataPrevista);
				parcela.setValorApagar(divida);
				parcela.setPedido(pedido);
				parcelas.add(parcela);
			}// fecha o for
			}//fecha a condicao de entrada de numeros menores que 1
			else
			{
				alertInfo.setHeaderText("Informacao");
				alertInfo.setContentText("O numero de Parcelas deve ser maior que 1");
				alertInfo.showAndWait();
			}

			colCodigoVenda.setCellValueFactory(new PropertyValueFactory<>("pedido"));
			colDataApagar.setCellValueFactory(new PropertyValueFactory<>("dataPrevista"));
			colValor.setCellValueFactory(new PropertyValueFactory<>("valorApagar"));
			colDataPagamento.setCellValueFactory(new PropertyValueFactory<>("dataPagamento"));
			colEstado.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
			tblParcelamentos.getItems().addAll(parcelas);
		} 
			else {
			alertInfo.setHeaderText("Informacao");
			alertInfo.setContentText("Lamentamos, so podes parcelar apartir de 1000 MT");
			alertInfo.showAndWait();
		}//fecha o else
	}// closes methods
private void calcularParcelaII() {
	List<Parcela> parcelas = new ArrayList<>();//esta lista contem as parcelas dos pedido
		
		for (int i = 0; i < Integer.parseInt(txtNumeroParcela.getText()); i++) {
			Parcela parcela = new Parcela();
			Pedido pedido = new Pedido();
			pedido.setIdPedido(idPedido);

			LocalDate localDate = LocalDate.now();
			String dataPrevista = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate.plusDays(30 * i));

			double divida = Double.parseDouble(txtValorTotal.getText())
					/ Integer.parseInt(txtNumeroParcela.getText());
			parcela.setDataPrevista(dataPrevista);
			parcela.setValorApagar(divida);
			parcela.setPedido(pedido);
			parcelas.add(parcela);
				

		}// fecha o for
			
		if(cboTipo.getSelectionModel().getSelectedItem().equals("PARCELADA"))
			DaoParcela.add(parcelas);
		tblParcelamentos.getItems().clear();
}// closes methods

	}//fecha a clase
