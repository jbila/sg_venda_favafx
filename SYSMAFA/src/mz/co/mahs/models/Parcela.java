package mz.co.mahs.models;

public class Parcela {
	private int idParcela;
	private Pedido pedido;// vai conter o numero de venda
	private FormasDePagamento formasDepagamento;
	private double valorApagar;
	private String dataPrevista;
	private double juros;
	private String dataPagamento;
	private String estado;

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

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Parcela [idParcela=" + idParcela + ", pedido=" + pedido + ", formasDepagamento=" + formasDepagamento
				+ ", valorApagar=" + valorApagar + ", dataPrevista=" + dataPrevista + ", dataPagamento=" + dataPagamento
				+ ", estado=" + estado + "\n]";
	}

	

}
