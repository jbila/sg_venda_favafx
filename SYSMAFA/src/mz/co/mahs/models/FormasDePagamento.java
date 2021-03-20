package mz.co.mahs.models;

public class FormasDePagamento {
	private int id;
	private String nome;
	private String  descricao;
	
	public FormasDePagamento() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
	
		return getNome();
	}

}
