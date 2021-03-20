package mz.co.mahs.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import mz.co.mahs.dao.DaoPedido;
import mz.co.mahs.models.Cliente;
import mz.co.mahs.models.FormasDePagamento;
import mz.co.mahs.models.Pedido;
import mz.co.mahs.models.Utilizador;

public class FXMLPedidosController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<Pedido> tblPedidos=new TableView<>();

	@FXML
	private TableColumn<Pedido, Integer> colId;
	@FXML
	private TableColumn<Pedido, Cliente> colCliente;

	@FXML
	private TableColumn<Pedido, String> colTipo;

	@FXML
	private TableColumn<Pedido, Double> colTotal;

	@FXML
	private TableColumn<Pedido, Double> colPago;

	@FXML
	private TableColumn<Pedido, FormasDePagamento> colPagoVia;

	@FXML
	private TableColumn<Pedido, String> colData;

	@FXML
	private TableColumn<Pedido, Utilizador> colUser;

	@FXML
	private TextField txtProcurar;

	@FXML
	private TextField txtPedido;

	@FXML
	private void handleMouseClick(MouseEvent event) {

	}

	@FXML
	private void procurador(KeyEvent event) {

	}

	@FXML
	private void initialize() {
		// System.out.println("Nao"+DaoPedido.getAll()+"/n");
		showInfo();
	}

	private void showInfo() {
		List<Pedido> list = DaoPedido.getAll();
		final ObservableList<Pedido> obsList = FXCollections.observableArrayList(list);
		colId.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
		colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		colTotal.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colPago.setCellValueFactory(new PropertyValueFactory<>("valorPago"));
		colPagoVia.setCellValueFactory(new PropertyValueFactory<>("formasDepagamento"));
		colData.setCellValueFactory(new PropertyValueFactory<>("valorPedido"));
		colUser.setCellValueFactory(new PropertyValueFactory<>("utilizador"));
		 //tblPedidos.getItems().addAll(list);
		tblPedidos.setItems(obsList);

	}

}
