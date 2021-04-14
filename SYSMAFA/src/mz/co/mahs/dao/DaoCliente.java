
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
import mz.co.mahs.models.Cliente;
import mz.co.mahs.models.Distrito;
import mz.co.mahs.models.Utilizador;

/**
 * <h1>DaoCliente</h1>
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
public class DaoCliente {
	/**
	 * <h4>Alert</h4>
	 * <p>
	 * A classe <b>Alert</b> é do javafx equivalente ao JOPtionPane do swing<br>
	 * com ela pode se ter altertas tipos diferentes
	 * </p>
	 */
	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);

	private static final String INSERT = "INSERT INTO tbl_cliente(nome,apelido,genero,email,telefone,endereco,idUtilizador,idDistrito,dataRegisto) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String LIST = "SELECT * FROM vw_listAllCliente";
	private static final String DELETE = "DELETE FROM tbl_cliente WHERE idCliente=?";
	private static final String UPDATE = "UPDATE tbl_cliente SET nome=?,apelido=?,genero=?,email=?,telefone=?,endereco=?,idUtilizador=?,idDistrito=? WHERE idCliente=? ";

	private static Connection conn = null;
	private static PreparedStatement stmt;
	private static ResultSet rs = null;

	public static boolean isRecorded(String telefone, String email) {
		boolean retorno = false;
		String sql = "SELECT telefone,email FROM formando WHERE telefone ='" + telefone + "'OR email= '" + email + "'";

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			retorno = rs.next();
		} catch (SQLException e) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Verifica Cliente  " + e.getMessage());
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

		return retorno;
	}

	/**
	 * <h5>Esta funcao persiste um Cliente</h5>
	 * @param cliente
	 * @see Cliente
	 */
	public static void addCliente(Cliente cliente) {

		try {
			LocalDate localDate = LocalDate.now();
			String dataRegisto = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);

			conn = Conexao.connect();
			stmt = conn.prepareStatement(INSERT);
			// nome,apelido,genero,email,telefone,endereco,idUtilizador,dataRegisto
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getApelido());
			stmt.setString(3, cliente.getGenero());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getTelefone());
			stmt.setString(6, cliente.getEndereco());
			stmt.setInt(7, cliente.getUtilizador().getIdUtilizador());
			stmt.setInt(8, cliente.getDistrito().getIdDistrito());
			stmt.setString(9, dataRegisto);
			stmt.executeUpdate();
			alertInfo.setHeaderText("Informacao");
			alertInfo.setContentText("Cliente Registado com Exito ");
			alertInfo.showAndWait();
		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Registar Cliente  " + ex.getMessage());
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
	 * <h5>Esta funcao elimina o Cliente</h5>
	 * @param cliente
	 * @see Cliente
	 */
	public static void deleteCliente(Cliente cliente) {
		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, cliente.getIdCliente());
			stmt.execute();
			alertInfo.setHeaderText("Informa��o");
			alertInfo.setContentText("Formando Removido com �xito ");
			alertInfo.showAndWait();
		} catch (SQLException e) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Remover Formando  " + e.getMessage());
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
	 * <h5>Esta funcao actualiza  Cliente</h5>
	 * @param cliente - recebe um objecto to tipo cliente
	 * @see Cliente
	 * 
	 */
	public static void updateCliente(Cliente cliente) {
		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getApelido());
			stmt.setString(3, cliente.getGenero());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getTelefone());
			stmt.setString(6, cliente.getEndereco());
			stmt.setInt(7, cliente.getUtilizador().getIdUtilizador());
			stmt.setInt(8, cliente.getDistrito().getIdDistrito());
			stmt.setInt(9, cliente.getIdCliente());
			stmt.executeUpdate();

			/*
			 * alertInfo.setHeaderText("Informacao");
			 * alertInfo.setContentText("Formando Actualizado com �xito ");
			 * alertInfo.showAndWait();
			 */
		}

		catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Actualizar Formando  " + ex.getMessage());
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
	 * <h5>Esta funcao procura Cliente cadastrados</h5>
	 * @see Cliente
	 * @return retorno clientes- lista de clientes
	 * 
	 */
	public static List<Cliente> getAllCliente() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(LIST);
			rs = stmt.executeQuery();

			while (rs.next()) {
				// nome,apelido,genero,email,telefone,endereco,idUtilizador
				Cliente cliente = new Cliente();
				Distrito distrito = new Distrito();
				distrito.setNome(rs.getString("Distrito"));
				Utilizador utilizador = new Utilizador();
				utilizador.setUsername(rs.getString("utilizador"));
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setApelido(rs.getString("apelido"));
				cliente.setGenero(rs.getString("genero"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setDistrito(distrito);
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setDataRegisto(rs.getString("dataRegisto"));
				cliente.setUtilizador(utilizador);
				clientes.add(cliente);
			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Listar Cliente  " + ex.getMessage());
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

		return clientes;
	}

	/**
	 * <h5>Esta funcao procura Clientes cadastrados</h5>
	 * @see Cliente
	 * @return clientes- retorna uma lista de clientes
	 * @param nome- rece nome do cliente
	 * 
	 */
	public static List<Cliente> search(String nome) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement("SELECT * FROM vw_listAllCliente WHERE nome LIKE '" + nome + "%' ");
			rs = stmt.executeQuery();

			while (rs.next()) {

				// nome,apelido,genero,email,telefone,endereco,idUtilizador
				Cliente cliente = new Cliente();
				Utilizador utilizador = new Utilizador();
				utilizador.setUsername(rs.getString("utilizador"));
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setApelido(rs.getString("apelido"));
				cliente.setGenero(rs.getString("genero"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setDataRegisto(rs.getString("dataRegisto"));
				cliente.setUtilizador(utilizador);
				clientes.add(cliente);
			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Listar Cliente  " + ex.getMessage());
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

		return clientes;
	}

}
