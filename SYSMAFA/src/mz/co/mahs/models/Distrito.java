package mz.co.mahs.models;

public class Distrito {
	private int idDistrito;
	private String nome;
	private Provincia provincia;
	
	
	public Distrito() {}


	public int getIdDistrito() {
		return idDistrito;
	}


	public void setIdDistrito(int idDistrito) {
		this.idDistrito = idDistrito;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	@Override
	public String toString() {
		return getNome();
				}
	
	
	
	
}
