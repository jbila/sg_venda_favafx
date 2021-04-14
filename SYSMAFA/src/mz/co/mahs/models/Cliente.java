
package mz.co.mahs.models;

/**
 * <h1><b>Cliente</b></h1>
 * <p>
 * Esta classe representa o corpo de um Cliente, ela é usada quando se faz <br>
 * um registo,remocao,actualizacao, e listagem dascategorias
 * </p>
 * 
 * @see Pessoa <b>
 *      <h3>@author JACINTO BILA TEL:848319153 Email:
 *      jacinto.billa@gmail.com</h3>
 */
public class Cliente extends Pessoa {

	private int idCliente;
	private Utilizador utilizador;
	private String dataRegisto;
	private Distrito distrito;

	/**
	 * <h2>Contrutor vazio
	 * <h2>
	 */
	public Cliente() {
	}

	/**
	 * <h2>Modificadores de Acesso</h2> <br>
	 * <p>
	 * permite acaptura e insercao de dados no objecto
	 * </p>
	 */
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	/**
	 * <h2>ToString</h2>
	 * <p>
	 * Pemite a impressao do objecto
	 * </p>
	 */
	@Override
	public String toString() {
		return getNome();
	}

}
