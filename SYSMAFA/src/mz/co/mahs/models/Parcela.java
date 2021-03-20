package mz.co.mahs.models;

import java.util.Date;

public class Parcela {
	private int idParcela;
	private Pedido pedido;//vai conter o numero de venda 
	private FormasDePagamento formasDepagamento;
	private double valorApagar;
	private String dataPrevista;
	private double juros;
	private Date dataPagamento;
	public int getIdParcela() {
		return idParcela;
	}
	public void setIdParcela(int idParcela) {
		this.idParcela = idParcela;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public FormasDePagamento getFormasDepagamento() {
		return formasDepagamento;
	}
	public void setFormasDepagamento(FormasDePagamento formasDepagamento) {
		this.formasDepagamento = formasDepagamento;
	}
	public double getValorApagar() {
		return valorApagar;
	}
	public void setValorApagar(double valorApagar) {
		this.valorApagar = valorApagar;
	}
	public String getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public double getJuros() {
		return juros;
	}
	public void setJuros(double juros) {
		this.juros = juros;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	@Override
	public String toString() {
		
		return getPedido().getIdPedido()+"<>"+getValorApagar()+"<>"+getDataPrevista()+"\n";
	}

}
