package mz.co.mahs.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mz.co.mahs.conection.Conexao;
import mz.co.mahs.models.Categoria;
import mz.co.mahs.models.Fornecedor;
import mz.co.mahs.models.FornecedorProducto;
import mz.co.mahs.models.Producto;
import mz.co.mahs.models.Utilizador;

public class DaoProducto {

	static Alert alertErro = new Alert(AlertType.ERROR);
	static Alert alertInfo = new Alert(AlertType.INFORMATION);
	private static final String INSERT = "INSERT INTO tbl_producto(nome,codigo,descricao,quantidade,precofinal,precoFornecedor,idCategoria,idUtilizador,idFornecedor,dataRegisto) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String LIST = "SELECT * FROM vw_listProducto";
	private static final String DELETE = "{CALL sp_Delete_Producto(?)}";
	private static final String UPDATE = "UPDATE tbl_producto SET nome=?,codigo=?,descricao=?,quantidade=?,precoFinal,precoFornecedor=?,validade=?,idCategoria=?,idFornecedor=? WHERE idProducto=?";

	private static Connection conn = null;
	private static CallableStatement cs = null;
	private static ResultSet rs = null;
	private static PreparedStatement stmt;

	public static boolean isRecorded(String codigo) {
		boolean retorno = false;
		final String sql = "SELECT codigo FROM tbl_producto WHERE nome ='" + codigo + "'";

		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			retorno = rs.next();

		} catch (SQLException e) {

			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Este Producto ja existe" + e);
			alertErro.showAndWait();

		}

		return retorno;
	}
// ---------------------------------------------------------------------------------------------------

	public static int add(Producto producto) {
		int lastId=0;
		try {
			LocalDate localDate = LocalDate.now();
			String dataRegisto = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);
			conn = Conexao.connect();
			//stmt = conn.prepareStatement(INSERT);
			final PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, producto.getNome());
			stmt.setString(2, producto.getCodigo());
			stmt.setString(3, producto.getDescricao());
			stmt.setInt(4, producto.getQuantidade());
			stmt.setDouble(5, producto.getPrecoFinal());
			stmt.setDouble(6, producto.getPrecoFornecedor());
			stmt.setInt(7, producto.getCategoria().getIdCategoria());
			stmt.setInt(8, producto.getUtilizador().getIdUtilizador());
			stmt.setInt(9, producto.getFornecedor().getIdFornecedor());
			stmt.setString(10, dataRegisto);
			stmt.executeUpdate();
			final ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
			   lastId = rs.getInt(1);   
			}
		} catch (SQLException ex) {
			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText(" " + ex);
			alertInfo.showAndWait();
			ex.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
return lastId;
	}

// ------------------------------------------------------------------------------------------------
	public static void delete(Producto producto) {

		try {
			conn = Conexao.connect();
			cs = conn.prepareCall(DELETE);
			cs.setInt(1, producto.getIdProducto());
			cs.execute();
			alertInfo.setHeaderText("Information");
			alertInfo.setContentText("producto Removida");
			alertInfo.showAndWait();
		} catch (SQLException e) {

			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao eliminar o  producto: " + e.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

//----------------------------------------------------------------------------------------------------
	public static void update(Producto producto) {
		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement(UPDATE);
			// nome=?,codigo=?,descricao=?,quantidade=?,precoFinal,precoFornecedor=?,validade=?,idCategoria=?,idFornecedor=?
			stmt.setString(1, producto.getNome());
			stmt.setString(2, producto.getCodigo());
			stmt.setString(3, producto.getDescricao());
			stmt.setInt(4, producto.getQuantidade());
			stmt.setDouble(5, producto.getPrecoFinal());
			stmt.setDouble(6, producto.getPrecoFornecedor());
			stmt.setString(7, producto.getValidade());
			stmt.setInt(8, producto.getCategoria().getIdCategoria());
			stmt.setInt(9, producto.getFornecedor().getIdFornecedor());
			stmt.setInt(10, producto.getIdProducto());
			stmt.executeUpdate();

			alertInfo.setHeaderText("Information");
			alertInfo.setContentText("Updated ");
			alertInfo.showAndWait();
		}

		catch (SQLException ex) {
			alertErro.setHeaderText("Error");
			alertErro.setContentText("Error Updating the the Product " + ex.getMessage());
			alertErro.showAndWait();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
//--------------------------------------------------------------------------------------------
	public static void updateQty(List<Producto> producto) {	
		try {
			
			conn = Conexao.connect();
			stmt = conn.prepareStatement("UPDATE tbl_producto SET quantidade=quantidade-? WHERE idProducto=?");
			for (Producto producto2 : producto) {
				stmt.setInt(1, producto2.getQuantidade());
				stmt.setInt(2, producto2.getIdProducto());
				stmt.executeUpdate();
			}
				
		} catch (SQLException ex) {
			alertInfo.setHeaderText("Informação");
			alertInfo.setContentText(" " + ex);
			alertInfo.showAndWait();
			ex.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	

//------------------------------------------------------------------------------------------------
/**Esta função lista a lista dos productos existentes na tabela
 * @return productos- esta e a lista de todos os productos existentes na tabela
 * 
 *  */
	public static List<Producto> getAll() {
		List<Producto> productos = new ArrayList<>();
		try {
			conn = Conexao.connect();
			stmt = conn.prepareCall(LIST);
			rs = stmt.executeQuery();
			while (rs.next()) {

				Producto producto = new Producto();// objecto principal

				Categoria categoria = new Categoria();
				categoria.setNome(rs.getString("categoria"));

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setNome(rs.getString("fornecedor"));
				Utilizador utilizador = new Utilizador(); // OBJECTO SECUNDARIO
				utilizador.setUsername(rs.getString("utilizador"));

				producto.setIdProducto(rs.getInt("idProducto"));
				producto.setCodigo(rs.getString("codigo"));
				producto.setNome(rs.getString("nome"));
				producto.setQuantidade(rs.getInt("quantidade"));
				producto.setPrecoFinal(Double.parseDouble(rs.getString("precoFinal")));
				producto.setPrecoFornecedor(Double.parseDouble(rs.getString("precoFornecedor")));
				producto.setValidade(rs.getString("validade"));
				producto.setDescricao(rs.getString("descricao"));
				producto.setDataRegisto(rs.getString("dataRegisto"));
				producto.setCategoria(categoria);
				producto.setFornecedor(fornecedor);
				producto.setUtilizador(utilizador);
				productos.add(producto);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao listar  Producto  " + ex.getMessage());
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

		return productos;
	}

//-------------------------------------------------------------------------------------------
	public static List<Producto> searchAll(String nome) {
		List<Producto> productos = new ArrayList<>();
		try {
			conn = Conexao.connect();
			stmt = conn.prepareCall("SELECT * FROM vw_listproducto WHERE nome LIKE '"+nome+"%' OR codigo LIKE'"+nome+"%' ");
			rs = stmt.executeQuery();
			while (rs.next()) {

				Producto producto = new Producto();// objecto principal

				Categoria categoria = new Categoria();
				categoria.setNome(rs.getString("categoria"));

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setNome(rs.getString("fornecedor"));
				Utilizador utilizador = new Utilizador(); // OBJECTO SECUNDARIO
				utilizador.setUsername(rs.getString("utilizador"));

				producto.setIdProducto(rs.getInt("idProducto"));
				producto.setCodigo(rs.getString("codigo"));
				producto.setNome(rs.getString("nome"));
				producto.setQuantidade(rs.getInt("quantidade"));
				producto.setPrecoFinal(Double.parseDouble(rs.getString("precoFinal")));
				producto.setPrecoFornecedor(Double.parseDouble(rs.getString("precoFornecedor")));
				producto.setValidade(rs.getString("validade"));
				producto.setDescricao(rs.getString("descricao"));
				producto.setDataRegisto(rs.getString("dataRegisto"));
				producto.setCategoria(categoria);
				producto.setFornecedor(fornecedor);
				producto.setUtilizador(utilizador);
				productos.add(producto);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao listar  Producto  " + ex.getMessage());
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

		return productos;
	}
//--------------------------------------------------------------------------------------------------
		public static List<Producto> searchAllPratileira(String nome) {
			List<Producto> productos = new ArrayList<>();
			try {
				conn = Conexao.connect();
				stmt = conn.prepareCall("SELECT * FROM vw_listproductopratileira WHERE nome LIKE '"+nome+"%' OR codigo LIKE'"+nome+"%' ");
				rs = stmt.executeQuery();
				while (rs.next()) {

					Producto producto = new Producto();// objecto principal

					Categoria categoria = new Categoria();
					categoria.setNome(rs.getString("categoria"));

					Fornecedor fornecedor = new Fornecedor();
					fornecedor.setNome(rs.getString("fornecedor"));
					Utilizador utilizador = new Utilizador(); // OBJECTO SECUNDARIO
					utilizador.setUsername(rs.getString("utilizador"));

					producto.setIdProducto(rs.getInt("idProducto"));
					producto.setCodigo(rs.getString("codigo"));
					producto.setNome(rs.getString("nome"));
					producto.setQuantidade(rs.getInt("quantidade"));
					producto.setPrecoFinal(Double.parseDouble(rs.getString("precoFinal")));
					producto.setPrecoFornecedor(Double.parseDouble(rs.getString("precoFornecedor")));
					producto.setValidade(rs.getString("validade"));
					producto.setDescricao(rs.getString("descricao"));
					producto.setDataRegisto(rs.getString("dataRegisto"));
					producto.setCategoria(categoria);
					producto.setFornecedor(fornecedor);
					producto.setUtilizador(utilizador);
					productos.add(producto);

				}

			} catch (SQLException ex) {
				alertErro.setHeaderText("Erro");
				alertErro.setContentText("Erro ao listar  Producto  " + ex.getMessage());
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

			return productos;
		}
	//--------------------------------------------------------------------------------------------------

	public static List<Producto> getAllPratileira() {
		List<Producto> productos = new ArrayList<>();
		try {
			conn = Conexao.connect();
			stmt = conn.prepareCall("SELECT * FROM vw_listproductopratileira");
			rs = stmt.executeQuery();
			while (rs.next()) {

				Producto producto = new Producto();// objecto principal

				Categoria categoria = new Categoria();
				categoria.setNome(rs.getString("categoria"));

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setNome(rs.getString("fornecedor"));
				Utilizador utilizador = new Utilizador(); // OBJECTO SECUNDARIO
				utilizador.setUsername(rs.getString("utilizador"));

				producto.setIdProducto(rs.getInt("idProducto"));
				producto.setCodigo(rs.getString("codigo"));
				producto.setNome(rs.getString("nome"));
				producto.setQuantidade(rs.getInt("quantidade"));
				producto.setPrecoFinal(Double.parseDouble(rs.getString("precoFinal")));
				producto.setPrecoFornecedor(Double.parseDouble(rs.getString("precoFornecedor")));
				producto.setValidade(rs.getString("validade"));
				producto.setDescricao(rs.getString("descricao"));
				producto.setDataRegisto(rs.getString("dataRegisto"));
				producto.setCategoria(categoria);
				producto.setFornecedor(fornecedor);
				producto.setUtilizador(utilizador);
				productos.add(producto);

			}

		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao listar  Producto  " + ex.getMessage());
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

		return productos;
	}

//----------------------------------------------------------------------------------------------------
	

	public static Integer ultimoIdProducto() {
		int id = 0;

		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement("SELECT MAX(idProducto) FROM tbl_producto");
			rs = stmt.executeQuery();
			if (rs.next()) {
				id = (rs.getInt(1));
			}
		} catch (SQLException ex) {
			alertErro.setHeaderText("Erro");
			alertErro.setContentText("Erro ao Carregar o Id do tbl_Producto  " + ex.getMessage());
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

		return id;
	}

//---------------------------------------------------------------------------------------------------
	public static void addForeignKeys(FornecedorProducto fornecedorProducto) {
		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement("INSERT INTO tbl_fornecedorProducto(idProduto,idFornecedor)value(?,?)");
			stmt.setInt(1, fornecedorProducto.getProducto().getIdProducto());
			stmt.setInt(2, fornecedorProducto.getFornecedor().getIdFornecedor());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException ex) {
			alertInfo.setHeaderText("Information");
			alertInfo.setContentText(" " + ex);
			alertInfo.showAndWait();
		}
	}
//----------------------------------------------------------------------------------------------------
	public static void updateForeignKeys(FornecedorProducto fornecedorProducto) {
		try {

			conn = Conexao.connect();
			stmt = conn.prepareStatement("UPDATE tbl_fornecedorProducto SET idProduto=?,idFornecedor=?)");
			stmt.setInt(1, fornecedorProducto.getProducto().getIdProducto());
			stmt.setInt(2, fornecedorProducto.getFornecedor().getIdFornecedor());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException ex) {
			alertInfo.setHeaderText("Information");
			alertInfo.setContentText(" " + ex);
			alertInfo.showAndWait();
		}
	}
//--------------------------------------------------------------------------------------------------------
}
