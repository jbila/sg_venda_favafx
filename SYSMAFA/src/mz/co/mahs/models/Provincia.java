package mz.co.mahs.models;

/**
 * <h1><b>Provincia</b></h1>
 * <p>
 * Esta classe representa o corpo de uma Provincia, ela é usada quando se faz
 * <br>
 * um registo,remocao,actualizacao, e listagem de Provincia
 * </p>
 * 
 * <h3>@author JACINTO BILA TEL:848319153 Email: jacinto.billa@gmail.com</h3>
 */
public class Provincia {
	private int idProvincia;
	private String nome;

	/**
	 * <h2>Contrutor vazio
	 * <h2>
	 */
	public Provincia() {
	}

	/**
	 * <h2>Modificadores de Acesso</h2> <br>
	 * <p>
	 * permite acaptura e insercao de dados no objecto
	 * </p>
	 */

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
