package mz.co.mahs.models;
/**
 * <h1><b>FormasDePagamento</b></h1>
 * <p>
 * Esta classe representa o corpo de um FormasDePagamento, ela é usada quando se faz
 * <br>
 * um registo,actualizacao, e listagem de FormasDePagamento, que por sua vez sera usada
 * no acto de Vendas 
 * </p>
 * 
 * @see Pessoa <b>
 *      <h3>@author JACINTO BILA TEL:848319153 Email:
 *      jacinto.billa@gmail.com</h3>
 */

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
