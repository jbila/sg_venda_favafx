package mz.co.mahs.models;

import java.util.List;

public class Pedido {
	private int  idPedido;
	private Cliente cliente;
	private Utilizador utilizador;
	private  FormasDePagamento formasDepagamento;
	private String tipo;//completa=C/parcelada=P
	private double valorPedido;
	private double valorPago;/**Este atributo vai controlar se o cliente pagou todo o valor ou nao
	se tiver pago uma parte a venda fica Inactiva, caso contrario é activa
	isso possibita que o cliente pode vir novamente com o numero da venda para concluir o pagamento
	duma outra forma
	*/
	private List<ItemsPedidos> items;
	private String dataRegisto;// VAI ARMAZENAR INFOMAÇÃO PARA VER A DATA QUE FOI FEITA A VENDA E A RESPECTIVA HORA

	public Pedido() {
		
	}
	
	public Pedido(int idPedido, Cliente cliente, Utilizador utilizador, String dataRegisto) {
		super();
		this.idPedido = idPedido;
		this.cliente = cliente;
		this.utilizador = utilizador;
		this.dataRegisto = dataRegisto;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public String getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(String dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	public FormasDePagamento getFormasDepagamento() {
		return formasDepagamento;
	}

	public void setFormasDepagamento(FormasDePagamento formasDepagamento) {
		this.formasDepagamento = formasDepagamento;
	}

	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}

	

	public List<ItemsPedidos> getItems() {
		return items;
	}

	public void setItems(List<ItemsPedidos> items) {
		this.items = items;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
/*
	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", cliente=" + cliente + ", utilizador=" + utilizador.getUsername()
				+ ", formasDepagamento=" + formasDepagamento + ", tipo=" + tipo + ", valorPedido=" + valorPedido
				+ ", valorPago=" + valorPago + ", items=" + items + ", dataRegisto=" + dataRegisto + "\n]";
	}
*/


	
	
	
	
}
