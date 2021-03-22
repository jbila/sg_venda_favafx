package mz.co.mahs.models;

public class Naturalidade {
	private int idNaturalidade;
	private String nome;
	private Bairro bairro;
	
	public Naturalidade() {}

	public int getIdNaturalidade() {
		return idNaturalidade;
	}

	public void setIdNaturalidade(int idNaturalidade) {
		this.idNaturalidade = idNaturalidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
