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
import mz.co.mahs.models.Distrito;
import mz.co.mahs.models.Funcao;
import mz.co.mahs.models.Funcionario;

/**
 * <h1>DaoFuncionario</h1>
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
public class DaoFuncionario {
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
	private static final String INSERT = "INSERT INTO tbl_Funcionario(nome,apelido,genero,email,telefone,endereco,nuit,numeroBi,idDistrito,idFuncao,salario,dataRegisto) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE = "DELETE FROM tbl_Funcionario WHERE idFuncionario=?";
	private static final String UPDATE = "UPDATE tbl_Funcionario SET nome=?,apelido=?,genero=?,email=?,telefone=?,endereco=?,nuit=?,numeroBi=?,idDistrito=?,idFuncao=?,salario=? WHERE idFuncionario=?";
	private static final String LIST = "SELECT * from vw_listFuncionario";

	private static Connection conn = null;
	private static ResultSet rs = null;
	// private static CallableStatement cs = null;
	private static PreparedStatement stmt;

	/**
	 * <h5>Esta funcao persiste um Funcionario</h5>
	 * 
	 * @param funcionario
	 * @see Funcionario
	 */
	public static void add(Funcionario funcionario) {

		try {
			LocalDate localDate = LocalDate.now();
			String dataRegisto = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);
			conn = Conexao.connect();
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getApelido());
			stmt.setString(3, funcionario.getGenero());
			stmt.setString(4, funcionario.getEmail());
			stmt.setString(5, funcionario.getTelefone());
			stmt.setString(6, funcionario.getEndereco());
			stmt.setInt(7, funcionario.getNuit());
			stmt.setString(8, funcionario.getNumeroBi());
			stmt.setInt(9, funcionario.getDistrito().getIdDistrito());
			stmt.setInt(10, funcionario.getFuncao().getIdFuncao());
			stmt.setDouble(11, funcionario.getSalario());
			stmt.setString(12, dataRegisto);
			stmt.executeUpdate();
			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText("Funcionario adicionado  com êxito ");
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

	/**
	 * <h5>Esta funcao elimina o Funcionario</h5>
	 * 
	 * @param funcionario
	 * @see Funcionario
	 */
	public static void update(Funcionario funcionario) {

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getApelido());
			stmt.setString(3, funcionario.getGenero());
			stmt.setString(4, funcionario.getEmail());
			stmt.setString(5, funcionario.getTelefone());
			stmt.setString(6, funcionario.getEndereco());
			stmt.setInt(7, funcionario.getNuit());
			stmt.setString(8, funcionario.getNumeroBi());
			stmt.setInt(9, funcionario.getDistrito().getIdDistrito());
			stmt.setInt(10, funcionario.getFuncao().getIdFuncao());
			stmt.setDouble(11, funcionario.getSalario());
			stmt.setInt(12, funcionario.getIdFuncionario());
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
	/**
	 * <h5>Esta funcao lista todos utilizadores cadastrados</h5>
	 * 
	 * @return retorno utilizadorList
	 * @param username
	 */
	public static void delete(Funcionario funcionario) {

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, funcionario.getIdFuncionario());
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
	 * <h5>Esta funcao lista todos Funcionarios cadastrados</h5>
	 * 
	 * @see Funcionario
	 * @return retorno funcionarios
	 * 
	 */
	public static List<Funcionario> getAllFuncionario() {

		List<Funcionario> funcionarios = new ArrayList<>();

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(LIST);
			rs = stmt.executeQuery();

			while (rs.next()) {
				// nome,apelido,genero,email,telefone,endereco,nuit,numeroBi,idDistrito,idFuncao,salario,dataRegisto
				Funcionario funcionario = new Funcionario();
				Funcao funcao = new Funcao();
				funcao.setNome(rs.getString("Funcao"));
				Distrito distrito = new Distrito();
				distrito.setNome(rs.getString("Distrito"));
				funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setApelido(rs.getString("apelido"));
				funcionario.setGenero(rs.getString("genero"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setNuit(rs.getInt("nuit"));
				funcionario.setNumeroBi(rs.getString("numeroBi"));
				funcionario.setSalario(rs.getDouble("salario"));
				funcionario.setDataRegisto(rs.getString("dataRegisto"));
				funcionario.setFuncao(funcao);
				funcionario.setDistrito(distrito);
				funcionarios.add(funcionario);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Error ao Funcionario " + ex.getMessage());
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

		return funcionarios;
	}

	/**
	 * <h5>Esta funcao pesquisa Funcionarios cadastrados</h5>
	 * 
	 * @param nome
	 * @return retorno funcionarios
	 * 
	 */
	public static List<Funcionario> search(String nome) {

		List<Funcionario> funcionarios = new ArrayList<>();

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement("SELECT * from vw_listFuncionario WHERE nome LIKE '"+nome+"%' ");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				Funcao funcao = new Funcao();
				funcao.setNome(rs.getString("Funcao"));
				Distrito distrito = new Distrito();
				distrito.setNome(rs.getString("Distrito"));
				funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
				funcionario.setNome(rs.getString("Funcionario"));
				funcionario.setApelido(rs.getString("apelido"));
				funcionario.setGenero(rs.getString("genero"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setNuit(rs.getInt("nuit"));
				funcionario.setNumeroBi(rs.getString("numeroBi"));
				funcionario.setSalario(rs.getDouble("salario"));
				funcionario.setDataRegisto(rs.getString("dataRegisto"));
				funcionario.setFuncao(funcao);
				funcionario.setDistrito(distrito);
				funcionarios.add(funcionario);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Error ao Funcionario " + ex.getMessage());
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

		return funcionarios;
	}

}
