
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
import mz.co.mahs.models.Utilizador;

public class DaoCliente {
	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);

	private static final String INSERT = "INSERT INTO tbl_cliente(nome,apelido,genero,email,telefone,endereco,idUtilizador,dataRegisto) VALUES(?,?,?,?,?,?,?,?)";
	private static final String LIST = "SELECT * FROM vw_listAllCliente";
	private static final String DELETE = "DELETE FROM tbl_cliente WHERE idCliente=?";
	private static final String UPDATE = "UPDATE tbl_cliente SET nome=?,apelido=?,genero=?,email=?,telefone=?,endereco=?,idUtilizador=? WHERE idCliente=? ";

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return retorno;
	}
	// --------------------------------------------------------------------------

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
			stmt.setString(8, dataRegisto);
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

	// --------------------------------------------------------------------------
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

//------------------------------------------------------------------------------
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
			stmt.setInt(8, cliente.getIdCliente());
			stmt.executeUpdate();
			
			/*
			alertInfo.setHeaderText("Informa��o");
			alertInfo.setContentText("Formando Actualizado com �xito ");
			alertInfo.showAndWait();*/
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

//------------------------------------------------------------------------------
	public static List<Cliente> getAllCliente() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(LIST);
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
//------------------------------------------------------------------------------
	public static List<Cliente> search(String nome) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement("SELECT * FROM vw_listAllCliente WHERE nome LIKE '"+nome+"%' ");
			rs = stmt.executeQuery();

			while (rs.next()) {
				
				// nome,apelido,genero,email,telefone,endereco,idUtilizador
				Cliente cliente = new Cliente();
				Utilizador utilizador = new Utilizador();
				utilizador.setNome(rs.getString("utilizador"));
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
