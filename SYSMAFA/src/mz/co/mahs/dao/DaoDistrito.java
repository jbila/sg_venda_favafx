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
import mz.co.mahs.models.Provincia;

public class DaoDistrito {

	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);

	private static final String INSERT = "INSERT INTO tbl_distrito(nome,idProvincia,dataRegisto) values(?,?,?)";
	private static final String DELETE = "DELETE FROM tbl_distrito WHERE idDistrito=?";
	private static final String UPDATE = "UPDATE tbl_distrito SET nome=?, SET idDistrito=? WHERE iddistrito=?";
	private static final String LIST = "SELECT * from vw_listDistrito";

	private static Connection conn = null;
	private static ResultSet rs = null;
	// private static CallableStatement cs = null;
	private static PreparedStatement stmt;

	public static void add(Distrito distrito) {

		try {
			LocalDate localDate = LocalDate.now();
			String dataRegisto = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);

			conn = Conexao.connect();
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1, distrito.getNome());
			stmt.setInt(2, distrito.getProvincia().getIdProvincia());
			stmt.setString(3, dataRegisto);
			stmt.executeUpdate();
			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText("Distrito adicionado  com êxito ");
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
	public static void update(Distrito distrito) {

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(UPDATE);
			conn = Conexao.connect();
			stmt = conn.prepareStatement(INSERT);
			stmt.setString(1, distrito.getNome());
			stmt.setInt(2, distrito.getProvincia().getIdProvincia());
			stmt.setInt(3, distrito.getIdDistrito());
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

	public static void delete(Distrito distrito) {

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, distrito.getIdDistrito());
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
	public static List<Distrito> getAlldistrito() {

		List<Distrito> distritos = new ArrayList<>();

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(LIST);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Distrito distrito = new Distrito();
				Provincia provincia = new Provincia();
				provincia.setNome(rs.getString("Provincia"));
				distrito.setIdDistrito(rs.getInt("idDistrito"));
				distrito.setNome(rs.getString("Distrito"));
				distrito.setProvincia(provincia);
				distritos.add(distrito);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Error ao Distrito " + ex.getMessage());
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

		return distritos;
	}

	// ---------------------------------------------------------------------------------
	public static List<Distrito> search(Provincia nome) {

		List<Distrito> distritos = new ArrayList<>();

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement("SELECT * FROM vw_listdistrito WHERE provincia ='" + nome + "'");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Distrito distrito = new Distrito();
				Provincia provincia = new Provincia();
				provincia.setNome(rs.getString("Provincia"));
				distrito.setIdDistrito(rs.getInt("idDistrito"));
				distrito.setNome(rs.getString("Distrito"));
				distrito.setProvincia(provincia);
				distritos.add(distrito);


			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Error ao Distrito " + ex.getMessage());
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

		return distritos;
	}

}
