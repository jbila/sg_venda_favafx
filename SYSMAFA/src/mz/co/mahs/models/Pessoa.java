
package mz.co.mahs.models;

/**
 * <h1><b>Pessoa</b></h1>
 * <p>
 * Esta classe representa o corpo de uma Pessoa, ela é usada quando se faz <br>
 * <li>@see Funcionario</li>
 * <li>@see Cliente</li>
 * <li>@see Fornecedor</li>
 * </p>
 * <b><b>
 * <p>
 * <b>Esta classe nao e persistida na base de dado</b>
 * </p>
 * <h3>@author JACINTO BILA TEL:848319153 Email: jacinto.billa@gmail.com</h3>
 */
public abstract class Pessoa {
	/**
	 * <h3>Estas carecteristicas serao herdadas pelas classes filhas ou as
	 * subclasses</h3>
	 */
	private String nome;
	private String apelido;
	private String genero;
	private String email;
	private String telefone;
	private String endereco;

	public Pessoa() {
	}

	public Pessoa(String nome, String apelido, String genero, String email, String telefone, String endereco) {
		super();
		this.nome = nome;
		this.apelido = apelido;
		this.genero = genero;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	@Override
	public String toString() {

		return super.toString();
	}
}
