
package mz.co.mahs.models;

import java.time.LocalDate;
public class Utilizador extends Pessoa{
    private int idUtilizador;
    private String status;
    private String password;
    private String username;
    private String perfil;
    private LocalDate dataRegisto;
  //nome,genero,email,telefone,endereco
    
    
    public Utilizador() {}

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

@Override
public String toString() {
	
	return getUsername();
}

    
}
