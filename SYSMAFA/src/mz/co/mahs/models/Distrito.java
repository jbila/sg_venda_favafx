package mz.co.mahs.models;

/**
 * <h1><b>Distrito</b></h1>
 * <p>
 * Esta classe representa o corpo de um Distrito, ela é usada quando se faz <br>
 * um registo,remocao,actualizacao, e listagem de Fornecedor
 * </p>
 * 
 * No registo do cliente sera selecionada o seu distrito de acordo com a Proncia
 * 
 * @see Provincia
 *      <h3>@author JACINTO BILA TEL:848319153 Email:
 *      jacinto.billa@gmail.com</h3>
 */
public class Distrito {
	private int idDistrito;
	private String nome;
	private Provincia provincia;

	/**
	 * <h2>Contrutor vazio
	 * <h2>
	 */
	public Distrito() {
	}

	/**
	 * <h2>Modificadores de Acesso</h2> <br>
	 * <p>
	 * permite acaptura e insercao de dados no objecto
	 * </p>
	 */
	public int getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(int idDistrito) {
		this.idDistrito = idDistrito;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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
