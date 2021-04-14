package mz.co.mahs.models;

/**
 * <h1><b>Funcionario</b></h1>
 * <p>
 * Esta classe representa o corpo de um Funcionario, ela é usada quando se faz
 * <br>
 * um registo,remocao,actualizacao, e listagem de Funcionario
 * </p>
 * 
 * @see Pessoa <b>
 * @see Funcao
 *      <h3>@author JACINTO BILA TEL:848319153 Email:
 *      jacinto.billa@gmail.com</h3>
 */
public class Funcionario extends Pessoa {
	private int idFuncionario;
	private int nuit;
	private String numeroBi;
	private Distrito distrito;
	private Funcao funcao;
	private double salario;
	private String dataRegisto;

	/**
	 * <h2>Contrutor vazio
	 * <h2>
	 */
	public Funcionario() {
	}

	/**
	 * <h2>Modificadores de Acesso</h2> <br>
	 * <p>
	 * permite acaptura e insercao de dados no objecto
	 * </p>
	 */
	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public int getNuit() {
		return nuit;
	}

	public void setNuit(int nuit) {
		this.nuit = nuit;
	}

	public String getNumeroBi() {
		return numeroBi;
	}

	public void setNumeroBi(String numeroBi) {
		this.numeroBi = numeroBi;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
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
