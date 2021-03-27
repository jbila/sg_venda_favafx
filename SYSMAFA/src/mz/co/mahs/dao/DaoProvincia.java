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
import mz.co.mahs.models.Provincia;

public class DaoProvincia {

	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);

	private static final String INSERT = "INSERT INTO tbl_provincia(nome,dataRegisto) values(?,?)";
	private static final String DELETE = "DELETE FROM tbl_provincia WHERE idProvincia=?";
	private static final String UPDATE = "UPDATE tbl_Provincia SET nome=? WHERE idProvincia=?";
	private static final String LIST = "select * from tbl_provincia;";

	private static Connection conn = null;
	private static ResultSet rs = null;
	// private static CallableStatement cs = null;
	private static PreparedStatement stmt;

	public static void add(Provincia provincia) {

		try {
			LocalDate localDate = LocalDate.now();
			String dataRegisto = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);

			conn = Conexao.connect();
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1, provincia.getNome());
			stmt.setString(2, dataRegisto);
			stmt.executeUpdate();
			
			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText("Provincia adicionada  com êxito ");
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
	public static void update(Provincia provincia) {

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, provincia.getNome());
			stmt.setInt(2, provincia.getIdProvincia());
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

	public static void delete(Provincia provincia) {

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, provincia.getIdProvincia());
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
	public static List<Provincia> getAllprovincia() {

		List<Provincia> provincias = new ArrayList<>();

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(LIST);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Provincia provincia = new Provincia();
				provincia.setIdProvincia(rs.getInt("idProvincia"));
				provincia.setNome(rs.getString("nome"));
				provincias.add(provincia);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Listar Provincia " + ex.getMessage());
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

		return provincias;
	}
	//---------------------------------------------------------------------------------
	public static List<Provincia> search(String nome) {

		List<Provincia> provincias = new ArrayList<>();

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement("SELECT * FROM vw_listprovincia WHERE nome LIKE'%"+nome+"'");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Provincia provincia = new Provincia();
				provincia.setNome(rs.getString("nome"));
				provincias.add(provincia);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Pesquisar Provincia " + ex.getMessage());
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

		return provincias;
	}


}
