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
import mz.co.mahs.models.Parcela;

public class DaoParcela {
	/**
	 * <h4>Alert</h4>
	 * <p>
	 * A classe <b>Alert</b> é do javafx equivalente ao JOPtionPane do swing<br>
	 * com ela pode se ter altertas tipos diferentes
	 * </p>
	 */
	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);
	/**
	 * <h4>Consultas SQL</h4>
	 * <p>
	 * As<b>Strings abaixo</b> sao usadas para interagir coma basse de dados no acto da remocao,adicionar...<br>
	 * listar e mais
	 * </p>
	 */
	private static final String INSERT = "INSERT INTO tbl_parcela(idPedido,valor,dataPrevista) VALUES(?,?,?)";
	//private static final String LIST = "select * from tbl_parcela";
	//private static final String DELETE = "{CALL ps_Delete_Parcela(?)}";
	private static final String UPDATE = "UPDATE tbl_parcela SET estado=?,dataPagamento=? WHERE idParcela=?";

	private static Connection conn = null;
	private static PreparedStatement stmt;
	//private static CallableStatement cs = null;
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
//--------------------------------------------------------------------------------------
	public static List<Parcela> getAll(int codigoPedido) {
		List<Parcela> parcelas = new ArrayList<>();
		try {
			final String SQL="SELECT PA.idParcela,PA.idPedido, PA.valor,PA.estado,PA.dataPagamento,PA.dataPrevista\r\n" + 
					" FROM tbl_parcela AS PA \r\n" + 
					"inner join tbl_pedido AS p\r\n" + 
					"on  PA.idPedido=P.idPedido WHERE PA.idPedido='"+codigoPedido+"'";
			conn = Conexao.connect();
			stmt = conn.prepareCall(SQL);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Parcela parcela = new Parcela();// objecto principal
				//Pedido pedido=new Pedido();
				//pedido.setIdPedido(rs.getInt("idPedido"));
				parcela.setIdParcela(rs.getInt("idParcela"));
				parcela.setValorApagar(rs.getDouble("valor"));
				parcela.setDataPagamento(rs.getString("dataPagamento"));
				parcela.setDataPrevista(rs.getString("dataPrevista"));
				parcela.setEstado(rs.getString("estado"));
				parcelas.add(parcela);
			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao listar  parcelas " + ex.getMessage());
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

		return parcelas;
	}
//-----------------------------------------------------------------------------------------
	public static void updateParcela(Parcela parcela) {

		try {
			LocalDate localDate = LocalDate.now();
			String dataPagamento = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);

			conn = Conexao.connect();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, parcela.getEstado());
			stmt.setString(2, dataPagamento);
			stmt.setInt(3, parcela.getIdParcela());
			stmt.executeUpdate();
			/*
			alertInfo.setHeaderText("Informacao");
			alertInfo.setContentText("Parcela  Pago com sucesso ");
			alertInfo.showAndWait();*/
		}

		catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Actualizar Parcela " + ex.getMessage());
			alertErro.showAndWait();
		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

//-------------------------------------------------------------------------------------------
		public static List<Parcela> getAll02() {
			List<Parcela> parcelas = new ArrayList<>();
			try {
				final String SQL="SELECT PA.idParcela,PA.idPedido, PA.valor,PA.estado,PA.dataPagamento,PA.dataPrevista\r\n" + 
						" FROM tbl_parcela AS PA \r\n" + 
						"inner join tbl_pedido AS p\r\n" + 
						"on  PA.idPedido=P.idPedido";
				conn = Conexao.connect();
				stmt = conn.prepareCall(SQL);
				rs = stmt.executeQuery();
				while (rs.next()) {
					Parcela parcela = new Parcela();// objecto principal
					//Pedido pedido=new Pedido();
					//pedido.setIdPedido(rs.getInt("idPedido"));
					parcela.setIdParcela(rs.getInt("idParcela"));
					parcela.setValorApagar(rs.getDouble("valor"));
					parcela.setDataPagamento(rs.getString("dataPagamento"));
					parcela.setDataPrevista(rs.getString("dataPrevista"));
					parcela.setEstado(rs.getString("estado"));
					parcelas.add(parcela);
				}

			} catch (SQLException ex) {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Erro ao listar  parcelas " + ex.getMessage());
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

			return parcelas;
		}
	//-----------------------------------------------------------------------------------------
		public static double getValor(int idParcela) {
			double valor=0;
			conn = Conexao.connect();
			try {
				stmt = conn.prepareStatement("SELECT valor FROM tbl_parcela WHERE idParcela='"+idParcela+"'");
			} catch (SQLException e) {
				e.printStackTrace();
			}				
			try {
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (rs.next()) {
					valor = (rs.getDouble("valor"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			return  valor;
			
		}// closes the method




}
