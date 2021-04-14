package mz.co.mahs.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mz.co.mahs.conection.Conexao;
import mz.co.mahs.models.FormasDePagamento;

public class DaoFormasDePagamento {
	/**
	 * <h4>Alert</h4>
	 * <p>
	 * A classe <b>Alert</b> é do javafx equivalente ao JOPtionPane do swing<br>
	 * com ela pode se ter altertas tipos diferentes
	 * </p>
	 */
	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);
	private static final String LIST = "select * from tbl_formasDePagamento";

	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	// private static CallableStatement cs=null;
	private static ResultSet rs = null;
	/**
	 * <h5>Esta funcao lista FormasDePagamento</h5>
	 * @see FormasDePagamento
	 * @return FormasDePagamento- retorna uma lista de formas de pagamento
	 * 
	 */
	public static List<FormasDePagamento> getAll() {
		List<FormasDePagamento> formasDePagamentos = new ArrayList<>();

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(LIST);
			rs = stmt.executeQuery();

			while (rs.next()) {
				FormasDePagamento formasDePagamento = new FormasDePagamento();

				//Utilizador utilizador = new Utilizador();
				//utilizador.setUsername(rs.getString("utilizador"));
				formasDePagamento.setId(rs.getInt("id"));
				formasDePagamento.setNome(rs.getString("nome"));
				formasDePagamento.setDescricao(rs.getString("descricao"));
				formasDePagamentos.add(formasDePagamento);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Listar Formas de Pagamento" + ex.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return formasDePagamentos;
	}
//------------------------------------------------------------------------------

}
