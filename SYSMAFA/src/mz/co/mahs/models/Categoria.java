
package mz.co.mahs.models;

/**
 * <h1><b>Categoria</b></h1>
 * <p>
 * Esta classe representa o corpo de uma Categoria, ela é usada quando se faz
 * <br>
 * um registo,remocao,actualizacao, e listagem de categorias
 * </p>
 * <b>
 * <h3>@author JACINTO BILA TEL:848319153 Email: jacinto.billa@gmail.com</h3>
 */
public class Categoria {

	private int idCategoria;
	private String nome;
	private String descricao;
	private Utilizador utilizador;

	/**
	 * <h2>Contrutor inicializado
	 * <h2>
	 */
	public Categoria(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	/**
	 * <h2>Contrutor vazio
	 * <h2>
	 */
	public Categoria() {
	}

	/**
	 * <h2>Modificadores de Acesso</h2> <br>
	 * <p>
	 * permite acaptura e insercao de dados no objecto
	 * </p>
	 */

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
