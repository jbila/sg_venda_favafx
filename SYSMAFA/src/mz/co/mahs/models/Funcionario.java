package mz.co.mahs.models;

public class Funcionario extends Pessoa {
	private int idFuncionario;
	private int nuit;
	private String numeroBi;
	private Distrito distrito;
	private Funcao funcao;
	private double salario;
	private String dataRegisto;

	// idFuncionario,nome,apelido,genero,email,telefone,endereco,nuit,numeroBi,distrito,funcao,salario
	public Funcionario() {
	}

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

	@Override
	public String toString() {

		return getNome();
	}

}
