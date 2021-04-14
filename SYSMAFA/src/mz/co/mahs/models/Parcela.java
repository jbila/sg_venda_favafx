package mz.co.mahs.models;

/**
 * <h1><b>Parcela</b></h1>
 * <p>
 * Esta classe representa o corpo de um parcela, ela é usada quando se faz <br>
 * uma venda ou pedido, e o cliente escolhe parcelar ovalor a pagar
 * </p>
 * <b>
 * <h3>@author JACINTO BILA TEL:848319153 Email: jacinto.billa@gmail.com</h3>
 */
public class Parcela {
	private int idParcela;
	private Pedido pedido;
	/** vai conter o numero de venda */
	private FormasDePagamento formasDepagamento;
	private double valorApagar;
	private String dataPrevista;
	private double juros;
	/** Aumenta o valor a pagar quando excede o dia do pagamento */
	private String dataPagamento;
	private String estado;

	/**
	 * <h2>Contrutor
	 * <h2>
	 */
	public Parcela() {
	}

	/**
	 * <h2>Modificadores de Acesso</h2> <br>
	 * <p>
	 * permite acaptura e insercao de dados no objecto
	 * </p>
	 */

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

	/**
	 * <h2>ToString</h2>
	 * <p>
	 * Pemite a impressao do objecto
	 * </p>
	 */
	@Override
	public String toString() {
		return "Parcela [idParcela=" + idParcela + ", pedido=" + pedido + ", formasDepagamento=" + formasDepagamento
				+ ", valorApagar=" + valorApagar + ", dataPrevista=" + dataPrevista + ", dataPagamento=" + dataPagamento
				+ ", estado=" + estado + "\n]";
	}

}
