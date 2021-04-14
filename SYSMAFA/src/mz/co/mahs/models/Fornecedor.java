
package mz.co.mahs.models;

import java.time.LocalDate;

/**
 * <h1><b>Fornecedor</b></h1>
 * <p>
 * Esta classe representa o corpo de um Fornecedor, ela é usada quando se faz
 * <br>
 * um registo,remocao,actualizacao, e listagem de Fornecedor
 * </p>
 * 
 * @see Pessoa <b>
 *      <h3>@author JACINTO BILA TEL:848319153 Email:
 *      jacinto.billa@gmail.com</h3>
 */

public class Fornecedor extends Pessoa {
	private int idFornecedor;
	private Utilizador utilizador;
	private LocalDate dataRegisto;

	/**
	 * <h2>Contrutor vazio
	 * <h2>
	 */
	public Fornecedor() {
	}

	public Fornecedor(int idFornecedor, String nome, String apelido, String sexo, String email, String telefone,
			String endereco, Utilizador utilizador, LocalDate dataRegisto) {
		super(nome, apelido, sexo, email, telefone, endereco);
		this.idFornecedor = idFornecedor;
		this.utilizador = utilizador;
		this.dataRegisto = dataRegisto;
	}

	/**
	 * <h2>Modificadores de Acesso</h2> <br>
	 * <p>
	 * permite acaptura e insercao de dados no objecto
	 * </p>
	 */

	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public LocalDate getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(LocalDate dataRegisto) {
		this.dataRegisto = dataRegisto;
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
