package mz.co.mahs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mz.co.mahs.conection.Conexao;

public class DaoLogin {
	/**
	 * <h4>Alert</h4>
	 * <p>
	 * A classe <b>Alert</b> é do javafx equivalente ao JOPtionPane do swing<br>
	 * com ela pode se ter altertas tipos diferentes
	 * </p>
	 */
	public static int idUtilizador = 0;
	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);

	private static final String sql = "SELECT username, passwordd from utilizador where userName =? and passwordd =? and statuss='activo'";
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static ResultSet rs = null;

	public static boolean consultar(String user, String pass) throws SQLException {
		boolean autenticado = false;

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user);
			stmt.setString(2, pass);
			rs = stmt.executeQuery();

			if (rs.next()) {
				autenticado = true;

				return autenticado;

			} else {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Acesso Negado");
				alertErro.showAndWait();
				autenticado = false;
				return autenticado;
			}

		} catch (SQLException ex) {
			return false;
		}

		finally {
			rs.close();
			stmt.close();
		}
	}
//=============================================================================================
	public static int getIdUtilizador(String user) {

		int id = 0;

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement("select idUtilizador from utilizador where idUtilizador=?");
			stmt.setString(1, user);
			rs = stmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		} // end od finally
		return id;
	}
//=============================================================================================

}
