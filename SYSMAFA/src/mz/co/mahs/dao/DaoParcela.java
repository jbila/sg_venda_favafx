package mz.co.mahs.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mz.co.mahs.conection.Conexao;
import mz.co.mahs.models.ItemsPedidos;
import mz.co.mahs.models.Parcela;

public class DaoParcela {
	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);
	private static final String INSERT = "INSERT INTO tbl_parcela(idPedido,valor,dataPrevista) VALUES(?,?,?)";
	private static final String LIST = "select * from tbl_parcela";
	private static final String DELETE = "{CALL ps_Delete_Parcela(?)}";
	private static final String UPDATE = "UPDATE tbl_parcela  ";

	private static Connection conn = null;
	private static PreparedStatement stmt;
	private static CallableStatement cs = null;
	private static ResultSet rs = null;

	public static void add(List<Parcela> parcelas) {
		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(INSERT);
		for (Parcela itemsParcela : parcelas) {
			stmt.setInt(1, itemsParcela.getPedido().getIdPedido());
			stmt.setDouble(2, itemsParcela.getValorApagar());
			stmt.setString(3, itemsParcela.getDataPrevista());
			stmt.executeUpdate();
				}
		/*
			alertInfo.setHeaderText("Information");
			alertInfo.setContentText("Venda em Parcela Feita ");
			alertInfo.showAndWait();*/
			//
		} catch (SQLException ex) {
			alertInfo.setHeaderText("Information");
			alertInfo.setContentText(" " + ex);
			alertInfo.showAndWait();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
//-------------------------------------------------------------------


}
