
package mz.co.mahs.models;

import java.time.LocalDate;

/**
 * <h1><b>Utilizador</b></h1>
 * <p>
 * Esta classe representa o corpo de um Utilizador, ela é usada quando se faz
 * <br>
 * um registo,remocao,actualizacao, e listagem de Utilizador
 * </p>
 * 
 * @see Funcionario <b>
 *      <h3>@author JACINTO BILA TEL:848319153 Email:
 *      jacinto.billa@gmail.com</h3>
 */

public class Utilizador {
	private int idUtilizador;
	private String status;
	private String password;
	private String username;
	private String perfil;
	private Funcionario funcionario;
	private LocalDate dataRegisto;

	/**
	 * <h2>Contrutor vazio
	 * <h2>
	 */
	public Utilizador() {
	}

	/**
	 * <h2>Modificadores de Acesso</h2> <br>
	 * <p>
	 * permite acaptura e insercao de dados no objecto
	 * </p>
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public int getIdUtilizador() {
		return idUtilizador;
	}

	public void setIdUtilizador(int idUtilizador) {
		this.idUtilizador = idUtilizador;
	}

	public LocalDate getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(LocalDate dataRegisto) {
		this.dataRegisto = dataRegisto;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * <h2>ToString</h2>
	 * <p>
	 * Pemite a impressao do objecto
	 * </p>
	 */
	@Override
	public String toString() {

		return getUsername();
	}

}
