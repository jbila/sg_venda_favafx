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
import mz.co.mahs.models.Categoria;
import mz.co.mahs.models.Funcionario;
import mz.co.mahs.models.Utilizador;

public class DaoCategoria {

	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);

	private static final String INSERT = "INSERT INTO tbl_categoria(nome,descricao,idUtilizador,dataRegisto) values(?,?,?,?)";
	private static final String DELETE = "DELETE FROM tbl_categoria WHERE idCategoria=?";
	private static final String UPDATE = "UPDATE tbl_categoria SET nome=?,descricao=?,idUtilizador=? WHERE idCategoria=?";
	private static final String LIST = "select * from vw_listcategoria";

	private static Connection conn = null;
	private static ResultSet rs = null;
	// private static CallableStatement cs = null;
	private static PreparedStatement stmt;

	public static void add(Categoria categoria) {

		try {
			LocalDate localDate = LocalDate.now();
			String dataRegisto = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);

			conn = Conexao.connect();
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1, categoria.getNome());
			stmt.setString(2, categoria.getDescricao());
			stmt.setInt(3, categoria.getUtilizador().getIdUtilizador());
			stmt.setString(4, dataRegisto);
			stmt.executeUpdate();
			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText("Turma Criada  com êxito ");
			alertInfo.showAndWait();
		} catch (SQLException ex) {
			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText(" " + ex);
			alertInfo.showAndWait();
		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Erro fechar o  statement  " + e.getMessage());
				alertErro.showAndWait();
			}
		} // fim do try

	}

	// ========================Update=================================
	public static void update(Categoria categoria) {

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, categoria.getNome());
			stmt.setString(2, categoria.getDescricao());
			stmt.setInt(3, categoria.getUtilizador().getIdUtilizador());
			stmt.setInt(4, categoria.getIdCategoria());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText(" " + ex);
			alertInfo.showAndWait();
		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Erro fechar o  statement  " + e.getMessage());
				alertErro.showAndWait();

			}

		} // fim do try

	}
	// =======================Delete=================================

	public static void delete(Categoria categoria) {

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, categoria.getIdCategoria());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText(" " + ex);
			alertInfo.showAndWait();
		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Erro fechar o resultset ou statement  " + e.getMessage());
				alertErro.showAndWait();

			}

		} // fim do try

	}

//========================================================================================================    
	public static List<Categoria> getAllCategoria() {

		List<Categoria> categorias = new ArrayList<>();

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(LIST);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				Utilizador utilizador = new Utilizador();
				utilizador.setUsername(rs.getString("username"));
				categoria.setUtilizador(utilizador);
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setNome(rs.getString("nome"));
				categoria.setDescricao(rs.getString("descricao"));
				categoria.setUtilizador(utilizador);
				categorias.add(categoria);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Error ao listar a Turma " + ex.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Erro fechar o resultset ou statement  " + e.getMessage());
				alertErro.showAndWait();

			}

		} // fim do try

		return categorias;
	}
	//---------------------------------------------------------------------------------
	public static List<Categoria> search(String nome) {

		List<Categoria> categorias = new ArrayList<>();

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement("SELECT * FROM vw_listcategoria WHERE nome LIKE'%"+nome+"'");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				
				
				Utilizador utilizador = new Utilizador();
				utilizador.setUsername(rs.getString("username"));
				
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setNome(rs.getString("nome"));
				categoria.setDescricao(rs.getString("descricao"));
				categoria.setUtilizador(utilizador);
				categorias.add(categoria);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Error ao listar a Turma " + ex.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Erro fechar o resultset ou statement  " + e.getMessage());
				alertErro.showAndWait();

			}

		} // fim do try

		return categorias;
	}


// =============CONTROLO DE EXISTENCIA DA   CATEGORIA NA BASE DE DADOS==================

	public static boolean isRecorded(String nome) {
		boolean retorno = false;

		String sql = "SELECT nome FROM tbl_categoria WHERE nome ='" + nome + "'";

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			retorno = rs.next();

		} catch (SQLException e) {

			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao vertificar o Registo " + e);
			alertErro.showAndWait();

		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Erro fechar o resultset ou statement  " + e.getMessage());
				alertErro.showAndWait();

			}

		} // fim do try

		return retorno;
	}

}
