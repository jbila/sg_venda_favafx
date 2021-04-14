
package mz.co.mahs.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mz.co.mahs.conection.Conexao;
import mz.co.mahs.models.Funcionario;
import mz.co.mahs.models.Utilizador;

/**
 * <h1>DaoUtilizador</h1>
 * <p>
 * Esta classe tem metodos de persistencia de dados, ela comunica directamente
 * com a base <br>
 * coma base de dado fazendo as seguintes operacoes
 * </p>
 * <li>CREATE</li>
 * <li>DELETE</li>
 * <li>UPDATE</li>
 * <li>LIST</li>
 * <li>CHECKIFEXIST</li>
 * <h3>Esta classe recebe os objecto vindo das controladoras ou retorna para a
 * controladora desde objecto</h3>
 * <h4>@author JBILA Contacto:848319153 Email:jacinto.billa@gmail.com</h4>
 * 
 */

public class DaoUtilizador {
	/**
	 * <h4>Alert</h4>
	 * <p>
	 * A classe <b>Alert</b> é do javafx equivalente ao JOPtionPane do swing<br>
	 * com ela pode se ter altertas tipos diferentes
	 * </p>
	 */
	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);

	/** String que sao usadas para operacoes com base de dados */
	private static final String INSERT = "INSERT INTO tbl_utilizador(idFuncionario,status,perfil,username,password,dataRegisto) VALUES(?,?,?,?,?,?)";
	private static final String LIST = "select * from vw_listutilizador";
	private static final String DELETE = "{CALL sp_Delete_Utlizador(?)}";
	private static final String UPDATE = "UPDATE tbl_utilizador SET idFuncionario=?,status=?,perfil=?,username=?,password=? WHERE idUtilizador=? ";

	private static Connection conn = null;
	private static PreparedStatement stmt;
	/**
	 * <h3>CallableStatement</h3>
	 * <p>
	 * A classe CallableStatement tem um poder maior de processamento,pois a app não
	 * sofre <br>
	 * todos os procedimentos como as view são processadas no proprio servidor
	 * mysql<br>
	 * com CallableStatement deminui os comandos sql's na app.
	 * </p>
	 * 
	 */
	private static CallableStatement cs = null;
	private static ResultSet rs = null;

	/**
	 * <h5>Esta funcao verifica se o utilizador existe ou nao</h5>
	 * 
	 * @return retorno- true ou false
	 * @param idUtilizador que vem dentro do objecto
	 */
	public static boolean isUserNameRecorded(Utilizador idUtilizador) {

		String sql = "SELECT idFormando FROM Utilizador WHERE idFormando ='" + idUtilizador + "'";
		boolean retorno = false;
		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			retorno = rs.next();

		} catch (SQLException e) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Verificar Utilizador  " + e.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return retorno;
	}

	/**
	 * <h5>Esta funcao persiste um utilizador</h5>
	 * 
	 * @param u
	 * @see Utilizador
	 */
	public static void addUtilizador(Utilizador u) {

		try {
			LocalDate localDate = LocalDate.now();
			String dataRegisto = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);

			conn = Conexao.connect();
			stmt = conn.prepareStatement(INSERT);
			stmt.setInt(1, u.getFuncionario().getIdFuncionario());
			stmt.setString(2, u.getStatus());
			stmt.setString(3, u.getPerfil());
			stmt.setString(4, u.getUsername());
			stmt.setString(5, u.getPassword());
			stmt.setString(6, dataRegisto);
			stmt.executeUpdate();

			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText("Utilizador Registado com êxito ");
			alertInfo.showAndWait();
		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Registar Utilizador  " + ex.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * <h5>Esta funcao elimina o utilizador</h5>
	 * 
	 * @param u
	 */
	public static void deleteUtilizador(Utilizador u) {

		try {
			conn = Conexao.connect();
			cs = conn.prepareCall(DELETE);
			cs.setInt(1, u.getIdUtilizador());
			cs.execute();
			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText("Utilizador Removido com êxito ");
			alertInfo.showAndWait();
		} catch (SQLException e) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Apagar Utilizador  " + e.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * <h5>Esta funcao actualiza o utilizador</h5>
	 * 
	 * @param u
	 */
	public static void updateUtilizador(Utilizador u) {
		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(UPDATE);

			stmt.setInt(1, u.getFuncionario().getIdFuncionario());
			stmt.setString(2, u.getStatus());
			stmt.setString(3, u.getPerfil());
			stmt.setString(4, u.getUsername());
			stmt.setString(5, u.getPassword());
			stmt.setInt(6, u.getIdUtilizador());
			stmt.executeUpdate();
			/*
			 * alertInfo.setHeaderText("Informação");
			 * alertInfo.setContentText("Utilizador Actualizado com êxito ");
			 * alertInfo.showAndWait();
			 */
		}

		catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Actualizar Utilizador  " + ex.getMessage());
			alertErro.showAndWait();
		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * <h5>Esta funcao lista todos utilizadores cadastrados</h5>
	 * 
	 * @return retorno utilizadorList
	 * @param username
	 */
	public static List<Utilizador> getUtilizadorList(String username) {
		List<Utilizador> utilizadorList = new ArrayList<>();

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement("select * from vw_listutilizador WHERE username like'" + username + "%'");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Utilizador utilizador = new Utilizador();
				utilizador.setIdUtilizador(rs.getInt("idUtilizador"));
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(rs.getString("nome"));
				utilizador.setFuncionario(funcionario);
				utilizador.setUsername(rs.getString("username"));
				utilizador.setStatus(rs.getString("status"));
				utilizador.setPerfil(rs.getString("perfil"));
				utilizadorList.add(utilizador);

			}
		} // closes try

		catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Listar Utilizador  " + ex.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return utilizadorList;
	}

	/**
	 * <h5>Esta funcao lista todos utilizadores cadastrados</h5>
	 * 
	 * @return retorno utilizadorList
	 * @param nome - recebe o nome 
	 */
	public static List<Utilizador> search(String nome) {
		List<Utilizador> utilizadorList = new ArrayList<>();

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(LIST);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Utilizador utilizador = new Utilizador();
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(rs.getString("nome"));
				utilizador.setFuncionario(funcionario);
				utilizador.setIdUtilizador(rs.getInt("idUtilizador"));
				utilizador.setUsername(rs.getString("username"));
				utilizador.setStatus(rs.getString("status"));
				utilizador.setPerfil(rs.getString("perfil"));
				utilizadorList.add(utilizador);

			}
		} // closes try

		catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Listar Utilizador  " + ex.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return utilizadorList;
	}

}
