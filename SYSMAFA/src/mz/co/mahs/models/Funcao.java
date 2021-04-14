package mz.co.mahs.models;

/**
 * <h1><b>Funcao</b></h1>
 * <p>
 * Esta classe representa o corpo da Funcao, ela é usada quando se faz
 * <br>
 * um registo,remocao,actualizacao, e listagem de Funcao
 * </p>
 * 
 * Ela é usada no acto do registo do Funcionario
 *      <h3>@author JACINTO BILA TEL:848319153 Email:
 *      jacinto.billa@gmail.com</h3>
 */
public class Funcao {
	private int idFuncao;
	private String nome;
	private String dataRegisto;

	/**
	 * <h2>Contrutor vazio
	 * <h2>
	 */
	public Funcao() {
	}

	/**
	 * <h2>Modificadores de Acesso</h2> <br>
	 * <p>
	 * permite acaptura e insercao de dados no objecto
	 * </p>
	 */
	public int getIdFuncao() {
		return idFuncao;
	}

	public void setIdFuncao(int idFuncao) {
		this.idFuncao = idFuncao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(String dataRegisto) {
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
