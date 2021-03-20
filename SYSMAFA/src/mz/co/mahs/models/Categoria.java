
package mz.co.mahs.models;

public class Categoria{

    private int idCategoria;
    private String nome;
    private String descricao;
    private Utilizador utilizador;
	
	
	
	

	public Categoria( String nome, String descricao) {	
		this.nome = nome;
		this.descricao = descricao;
		
	}

	public Categoria() {}
	
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

	@Override
	public String toString() {
		return getNome();
	}   
}
