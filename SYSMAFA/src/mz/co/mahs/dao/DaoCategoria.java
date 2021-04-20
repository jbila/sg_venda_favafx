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
import mz.co.mahs.models.Utilizador;
/**
 * <h1>DaoCategoria</h1>
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
public class DaoCategoria {
	/**
	 * <h4>Alert</h4>
	 * <p>
	 * A classe <b>Alert</b> é do javafx equivalente ao JOPtionPane do swing<br>
	 * com ela pode se ter altertas tipos diferentes
	 * </p>
	 */

	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);
	/**Este comando sao usadas 
	 * para manipular as base de dado
	 * */

	private static final String INSERT = "INSERT INTO tbl_categoria(nome,descricao,idUtilizador,dataRegisto) values(?,?,?,?)";
	private static final String DELETE = "DELETE FROM tbl_categoria WHERE idCategoria=?";
	private static final String UPDATE = "UPDATE tbl_categoria SET nome=?,descricao=?,idUtilizador=? WHERE idCategoria=?";
	private static final String LIST = "select * from vw_listcategoria";

	private static Connection conn = null;
	private static ResultSet rs = null;
	// private static CallableStatement cs = null;
	private static PreparedStatement stmt;
	/**Este metodo insere um objecto  do tipo categoria na base de dado
	 * @param categoria- recebe um objecto
	 * @exception SQLException -esta funcao pode geral excecpoes desse tipo
	 * 
	 * */
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
			alertInfo.setTitle("Informação");
			alertInfo.setHeaderText("Validacao de campos");
			alertInfo.setContentText("Categoria adicionada  com êxito ");
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
	/**Esta funcao actualiza categoria
	 * @param categoria - recebe um objecto que contema os dados alterados
	 * @exception SQLException
	 * @see  Categoria
	 * */
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
	/**Esta funcao permite a remocao de uma tupla
	 * @param categoria-  vai conter o id para a remocao
	 * @see Categoria*/

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

	/**
	 * <h5>Esta funcao lista Categorias cadastradas</h5>
	 * @see Categoria
	 * @return categorias- retorna uma lista de categorias
	 * 
	 */
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
	/**
	 * <h5>Esta funcao procura Categorias cadastradas</h5>
	 * @see Categoria
	 * @return categorias- retorna uma lista de categorias
	 * @param nome- recebe nome da categoria
	 * 
	 */	public static List<Categoria> search(String nome) {

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
