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
import mz.co.mahs.models.Funcao;
/**
 * <h1>DaoFuncao</h1>
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
public class DaoFuncao {
	/**
	 * <h4>Alert</h4>
	 * <p>
	 * A classe <b>Alert</b> é do javafx equivalente ao JOPtionPane do swing<br>
	 * com ela pode se ter altertas tipos diferentes
	 * </p>
	 */
	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);

	private static final String INSERT = "INSERT INTO tbl_funcao(nome,dataRegisto) values(?,?)";
	private static final String DELETE = "DELETE FROM tbl_funcao WHERE idFuncao=?";
	private static final String UPDATE = "UPDATE tbl_funcao SET nome=? WHERE idFuncao=?";
	private static final String LIST = "SELECT * from tbl_funcao";

	private static Connection conn = null;
	private static ResultSet rs = null;
	// private static CallableStatement cs = null;
	private static PreparedStatement stmt;
	/**
	 * <h5>Esta funcao persiste um Funcao</h5>
	 * @param funcao -recebe um objecto do tipo funcao
	 * @see Funcao
	 */
	public static void add(Funcao funcao) {

		try {
			LocalDate localDate = LocalDate.now();
			String dataRegisto = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);

			conn = Conexao.connect();
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1, funcao.getNome());
			stmt.setString(2, dataRegisto);
			stmt.executeUpdate();
			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText("Funcao adicionada  com êxito ");
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
				alertErro.setContentText("Erro fechar o  statement da Funcao " + e.getMessage());
				alertErro.showAndWait();
			}
		} // fim do try

	}

	/**
	 * <h5>Esta funcao actualiza funcao existente </h5>
	 * @see Funcao
	 * @param nome- recebe nome da funcao com os dados a serem alterados
	 * 
	 */	public static void update(Funcao funcao) {

		try {

		
			conn = Conexao.connect();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, funcao.getNome());
			stmt.setInt(2, funcao.getIdFuncao());
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
				alertErro.setContentText("Erro fechar o  statement Funcao " + e.getMessage());
				alertErro.showAndWait();

			}

		} // fim do try

	}
	/**
	 * <h5>Esta funcao remove uma Funcao cadastrada</h5>
	 * @see Funcao
	 * @param nome- recebe nome da funcao atraves do objecto
	 * 
	 */
	public static void delete(Funcao funcao) {

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, funcao.getIdFuncao());
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
	 * <h5>Esta funcao lista Funcoes cadastradas</h5>
	 * @see Funcao
	 * @return funcoes- retorna uma lista de funcoes
	 * 
	 */	public static List<Funcao> getAllFuncao() {

		List<Funcao> funcoes = new ArrayList<>();

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(LIST);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Funcao funcao=new Funcao();
				funcao.setIdFuncao(rs.getInt("idFuncao"));
				funcao.setNome(rs.getString("nome"));
				funcoes.add(funcao);
			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Error ao Funcao " + ex.getMessage());
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

		return funcoes;
	}

	/**
	 * <h5>Esta funcao procura Funcoes cadastradas</h5>
	 * @see Funcao
	 * @return funcoes- retorna uma lista de funcoes
	 * @param nome- recebe nome da funcao
	 * 
	 */	public static List<Funcao> search(String nome) {

		List<Funcao> funcoes = new ArrayList<>();

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement("SELECT * FROM tbl_funcao where nome like '"+nome+"'");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Funcao funcao=new Funcao();
				funcao.setIdFuncao(rs.getInt("idFuncao"));
				funcao.setNome(rs.getString("nome"));
				funcoes.add(funcao);
			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Error ao Funcao " + ex.getMessage());
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

		return funcoes;
		}



}
