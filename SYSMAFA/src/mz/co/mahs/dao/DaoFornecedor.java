
package mz.co.mahs.dao;

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
import mz.co.mahs.models.Fornecedor;
import mz.co.mahs.models.Funcionario;
import mz.co.mahs.models.Utilizador;

/**
 * <h1>DaoFornecedor</h1>
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
public class DaoFornecedor {
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

	private static final String INSERT = "INSERT INTO tbl_fornecedor(nome,apelido,genero,email,telefone,endereco,idUtilizador,dataRegisto) VALUES(?,?,?,?,?,?,?,?)";
	private static final String LIST = "select * from vw_listFornecedor";
	private static final String DELETE = "DELETE FROM tbl_fornecedor WHERE idFornecedor=?";
	private static final String UPDATE = "UPDATE tbl_fornecedor SET nome=?,apelido=?,genero=?,email=?,telefone=?,endereco=?,idUtilizador=? WHERE idFornecedor=?";

	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	// private static CallableStatement cs=null;
	private static ResultSet rs = null;

	/**
	 * <h5>Esta funcao persiste um DaoFornecedor</h5>
	 * 
	 * @param DaoFornecedor
	 * @see DaoFornecedor
	 */
	public static boolean isRecorder(String telefone,String email) {
		boolean retorno = false;

		String sql = "SELECT telefone,email FROM tbl_fornecedor WHERE telefone ='" + telefone + "' OR '"+email+"'";

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			retorno = rs.next();

		} catch (SQLException e) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Verificar o Fornecedor " + e.getMessage());
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
	 * <h5>Esta funcao persiste um Fornecedor</h5>
	 * 
	 * @param fornecedor
	 * @see Fornecedor
	 */
	public static void addFornecedor(Fornecedor fornecedor) {

		try {
			LocalDate localDate = LocalDate.now();
			String dataRegisto = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);
			conn = Conexao.connect();

			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getApelido());
			stmt.setString(3, fornecedor.getGenero());
			stmt.setString(4, fornecedor.getEmail());
			stmt.setString(5, fornecedor.getTelefone());
			stmt.setString(6, fornecedor.getEndereco());
			stmt.setInt(7, fornecedor.getUtilizador().getIdUtilizador());
			stmt.setString(8, dataRegisto);
			stmt.executeUpdate();

			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText("Fornecedor Registado com exito ");
			alertInfo.showAndWait();
		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Registar Fornecedor " + ex.getMessage());
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
	 * <h5>Esta funcao elimina o Fornecedor</h5>
	 * 
	 * @param fornecedor
	 * @see Fornecedor
	 */
	public static void deleteFornecedor(Fornecedor fornecedor) {

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, fornecedor.getIdFornecedor());
			stmt.execute();
			alertInfo.setHeaderText("Informacao");
			alertInfo.setContentText("fornecedor Removida com exito ");
			alertInfo.showAndWait();

		} catch (SQLException e) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Excluir fornecedor " + e.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * <h5>Esta funcao actualiza Fornecedor cadastrados</h5>
	 * 
	 * @see Funcionario
	 * 
	 */
	public static void updateFornecedor(Fornecedor fornecedor) {

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getApelido());
			stmt.setString(3, fornecedor.getGenero());
			stmt.setString(4, fornecedor.getEmail());
			stmt.setString(5, fornecedor.getTelefone());
			stmt.setString(6, fornecedor.getEndereco());
			stmt.setInt(7, fornecedor.getUtilizador().getIdUtilizador());
			stmt.setInt(8, fornecedor.getIdFornecedor());
			stmt.executeUpdate();
			/*
			 * alertInfo.setHeaderText("Informação");
			 * alertInfo.setContentText("Fornecedor Actualizado  com exito ");
			 * alertInfo.showAndWait();
			 */
		}

		catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Actualizar Fornecedor " + ex.getMessage());
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
	 * <h5>Esta funcao lista todos Fornecedor cadastrados</h5>
	 * 
	 * @see Fornecedor
	 * @return retorno Fornecedores
	 * 
	 */
	public static List<Fornecedor> getAllFornecedor() {
		List<Fornecedor> fornecedores = new ArrayList<>();

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(LIST);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Fornecedor fornecedor = new Fornecedor();

				Utilizador utilizador = new Utilizador();
				utilizador.setUsername(rs.getString("utilizador"));

				fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setEmail(rs.getString("email"));
				fornecedor.setTelefone(rs.getString("telefone"));
				fornecedor.setEndereco(rs.getString("endereco"));
				fornecedor.setUtilizador(utilizador);

				fornecedores.add(fornecedor);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Listar Fornecedor" + ex.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return fornecedores;
	}

	/**
	 * <h5>Esta funcao procura Fornecedor cadastrados</h5>
	 * 
	 * @see Fornecedor
	 * @return retorno Fornecedores
	 * 
	 */
	public static List<Fornecedor> search(String nome) {
		List<Fornecedor> fornecedores = new ArrayList<>();

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement("SELECT * FROM vw_listFornecedor WHERE nome LIKE'%" + nome + "%'");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Fornecedor fornecedor = new Fornecedor();
				Utilizador utilizador = new Utilizador();
				utilizador.setUsername(rs.getString("utilizador"));
				fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setEmail(rs.getString("email"));
				fornecedor.setTelefone(rs.getString("telefone"));
				fornecedor.setEndereco(rs.getString("endereco"));
				fornecedor.setUtilizador(utilizador);

				fornecedores.add(fornecedor);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Listar Fornecedor" + ex.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return fornecedores;
	}

}
