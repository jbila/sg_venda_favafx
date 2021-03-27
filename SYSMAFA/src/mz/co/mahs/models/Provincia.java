package mz.co.mahs.models;

public class Provincia {
	private int idProvincia;
	private String nome;
	
	
	public Provincia() {}


	public int getIdProvincia() {
		return idProvincia;
	}


	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Override
	public String toString() {
		return getNome();
	}
	

}
