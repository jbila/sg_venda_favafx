package mz.co.mahs.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import mz.co.mahs.conection.Conexao;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class DaoRelatorio {

//-----------------------------------Relatorio funcionario----------------------------------------------------------------
	public static void funcionarioReport() {
		Connection conn = null;
		conn = Conexao.connect();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirmar a emissão deste relatório?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			try {
				String caminho = "C:/Reports/sysmafa/Funcionarios.jasper";
				JasperPrint print = JasperFillManager.fillReport(caminho, null, conn);
				JasperViewer.viewReport(print, false);
				conn.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatório de funcionario " + ex);

			} catch (java.lang.NoClassDefFoundError e) {
				JOptionPane.showMessageDialog(null, "ERRO " + e);

			}

		}

	}
	//-----------------------------------Relatorio de Productos que acabaram nas pratileras----------------------------------------------------------------
	/**Este metodo retorna um relatorio dos productos que acabaram na pratilera,
	 * isto é durante a venda dos productos, a quantidade vai baixando
	 * quando ela atinge zero o producto sai automaticamente da pratileira
	 * o relatorio tera o seguinte(nomeProducoto,quantidade=0,precoUnitario, descricao);
	 * */	
	public static void productoACabados() {
			Connection conn = null;
			conn = Conexao.connect();

			int confirm = JOptionPane.showConfirmDialog(null, "Confirmar a emissão deste relatório?", "Atenção",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_NO_OPTION) {
				try {
					String caminho = "C:/Reports/sysmafa/ProductosAcabados.jasper";
					JasperPrint print = JasperFillManager.fillReport(caminho, null, conn);
					JasperViewer.viewReport(print, false);
					conn.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatório de funcionario " + ex);

				} catch (java.lang.NoClassDefFoundError e) {
					JOptionPane.showMessageDialog(null, "ERRO " + e);

				}

			}

		}
	//-----------------------------------Relatorio de Productos que acabaram nas pratileras----------------------------------------------------------------
		/**Este metodo retorna um relatorio dos productos dos productos que já venceram
		 * isto é todos os productos que a sua validade é menor que a data de hoje serão 
		 * impressos, os mesmo, por mais que estejam na pratilera o sistema já não permite a venda do mesmo
		 * se tentar efectuar a venda de um producto vencido, receberá um  aviso, de verificação de validade
		 
		 * */	
		public static void productoAVencidos() {
				Connection conn = null;
				conn = Conexao.connect();

				int confirm = JOptionPane.showConfirmDialog(null, "Confirmar a emissão deste relatório?", "Atenção",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_NO_OPTION) {
					try {
						String caminho = "C:/Reports/sysmafa/ProductosVencidos.jasper";
						JasperPrint print = JasperFillManager.fillReport(caminho, null, conn);
						JasperViewer.viewReport(print, false);
						conn.close();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatório de funcionario " + ex);

					} catch (java.lang.NoClassDefFoundError e) {
						JOptionPane.showMessageDialog(null, "ERRO " + e);

					}

				}

			}


// -----------------------------clientes---------------------------------------------------
	public static void clienteReport() {
		Connection conn = null;
		conn = Conexao.connect();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirmar a emissão deste relatório?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			try {
				String path = "C:/Reports/sysmafa/clientes.jasper";
				JasperPrint print = JasperFillManager.fillReport(path, null, conn);
				JasperViewer.viewReport(print, false);
				conn.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatório de cliente " + ex);

			} catch (java.lang.NoClassDefFoundError e) {
				JOptionPane.showMessageDialog(null, "ERRO " + e);

			}

		}

	}

//------------------------------------fornecedor--------------------------------------------------------
	public static void fornecedorReport() {
		Connection conn = null;
		conn = Conexao.connect();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirmar a emissão deste relatório?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			try {
				String path = "C:/Reports/sysmafa/fornecedor.jasper";
				JasperPrint print = JasperFillManager.fillReport(path, null, conn);
				JasperViewer.viewReport(print, false);
				conn.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatório de fornecedor " + ex);

			} catch (java.lang.NoClassDefFoundError e) {
				JOptionPane.showMessageDialog(null, "ERRO " + e);

			}

		}

	}
//--------------------------------------------------------------------------------------------

	public static void vendasReport(String dInicio, String dFim) {
		Connection conn = null;
		conn = Conexao.connect();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirma a emissao deste relatorio?", "Atencao",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			try {

				Map<String, Object> parametro = new HashMap<>();
				parametro.put("DataInicio", dInicio);
				parametro.put("DataFim", dFim);

				String path = "C:/Reports/sysmafa/vendasCompletas.jasper";
				JasperPrint print = JasperFillManager.fillReport(path, parametro, conn);
				JasperViewer.viewReport(print, false);
				conn.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatorio de Venda " + ex);

			} catch (java.lang.NoClassDefFoundError e) {
				JOptionPane.showMessageDialog(null, "ERRO " + e);

			}

		}

	}

//-------------------------------------------------------------------------------------------------------------------------
	public static void vendasFP(String formaDePagamento) {
		Connection conn = null;
		conn = Conexao.connect();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirma a emissao deste relatorio?", "Atencao",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			try {

				Map<String, Object> parametro = new HashMap<>();
				parametro.put("FP", formaDePagamento);

				String path = "C:/Reports/sysmafa/VendasPorFP.jasper";
				JasperPrint print = JasperFillManager.fillReport(path, parametro, conn);
				JasperViewer.viewReport(print, false);
				conn.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatorio de Venda " + ex);

			} catch (java.lang.NoClassDefFoundError e) {
				JOptionPane.showMessageDialog(null, "ERRO " + e);

			}

		}

	}

//---------------------------------------------------------------------
	public static void fatura(int numero) {
		Connection conn = null;
		conn = Conexao.connect();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirma a emissao deste relatorio?", "Atencao",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			try {

				Map<String, Object> parametro = new HashMap<>();
				parametro.put("NumeroVenda", numero);

				String path = "C:/Reports/sysmafa/fatura.jasper";
				JasperPrint print = JasperFillManager.fillReport(path, parametro, conn);
				JasperViewer.viewReport(print, false);
				conn.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatorio de Venda " + ex);

			} catch (java.lang.NoClassDefFoundError e) {
				JOptionPane.showMessageDialog(null, "ERRO " + e);

			}

		}

	}

}
