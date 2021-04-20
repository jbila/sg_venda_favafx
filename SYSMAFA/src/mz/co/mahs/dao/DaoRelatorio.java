package mz.co.mahs.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import mz.co.mahs.conection.Conexao;
import mz.co.mahs.models.FormasDePagamento;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**<h1>DaoRelatorio</h1>
 * <p>Esta Classe é a fabrica de Relatórios<br>
 * Ela comunica com a base de dados<br>
 * e puxa o .jasper compilado da jrxml, para poder projectar os relatorios<br>
 * os relatorios estao na disco C<br></p>
 * @author JACINTO ANDRE BILA
 * @version 1.0
 *
 * usando versão  1.8_251 do jdk
 * */
public class DaoRelatorio {
	

//-----------------------------------Relatorio funcionario----------------------------------------------------------------
	/**Esta funcao retorna um relatorio de todos os
	 * funcionarios registados
	 * no sistema*/
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
		 * @paramp dataActaul
		 * @param dataActaulMais10Dias
		 
		 * */	
		public static void producto10ParaVencer(String hoje, String dezDias) {
				Connection conn = null;
				conn = Conexao.connect();

				int confirm = JOptionPane.showConfirmDialog(null, "Confirmar a emissão deste relatório?", "Atenção",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_NO_OPTION) {
					try {
						Map<String, Object> parametro = new HashMap<>();
						parametro.put("HOJE", hoje);
						parametro.put("DEZDIAS", dezDias);
						
						String caminho = "C:/Reports/sysmafa/Productos10ParaVencer.jasper";
						JasperPrint print = JasperFillManager.fillReport(caminho, parametro, conn);
						JasperViewer.viewReport(print, false);
						conn.close();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatório de Productos preste a vencer " + ex);

					} catch (java.lang.NoClassDefFoundError e) {
						JOptionPane.showMessageDialog(null, "ERRO " + e);

					}

				}

			}

/**Esta funcao retorna um PDF  de productos que estão no Stock
 * Mas que já venceram, isto é todos os productos que não tenha quantidade zero
 * @param DataActual, com a data actual vai fazer comparaçõoes com as validade dos productos
 * que estão na tabela stock
 * */
		public static void productoVencidos(String hoje) {
			Connection conn = null;
			conn = Conexao.connect();

			int confirm = JOptionPane.showConfirmDialog(null, "Confirmar a emissão deste relatório?", "Atenção",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_NO_OPTION) {
				try {
					Map<String, Object> parametro = new HashMap<>();
					parametro.put("DATA", hoje);
					String caminho = "C:/Reports/sysmafa/ProductosVencidos.jasper";
					JasperPrint print = JasperFillManager.fillReport(caminho, parametro, conn);
					JasperViewer.viewReport(print, false);
					conn.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatório de Productos Vencidos " + ex);

				} catch (java.lang.NoClassDefFoundError e) {
					JOptionPane.showMessageDialog(null, "ERRO " + e);

				}

			}

		}

// -----------------------------clientes---------------------------------------------------
		/**Retorna a lista de todos os cliente registados no sistema
		 * o documento produzido pode ser convertido em outras extensoes
		 * */
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
	/**Retorna a lista de todos os fornecedores registados no sistema
	 * o documento produzido pode ser convertido em outras extensoes
	 * */
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
	/**Retorna a lista de todos as vendas efectuadas no sistema
	 * o documento produzido pode ser convertido em outras extensoes
	 * @param dataInicio
	 * @param dataFim
	 * Isto é a funcao recebe duas datas para possibilitar ver as vendas de
	 * qualquer dia, por exemplo
	 * ver vendas de 01 a 30 de um determinado mes
	 * */
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
	/**Esta função exibe um PDF de vendas por tipo de pagamento de um dado dia
	 * isto é, com a função é possível ver qual é o total de valor pago por 
	 * @see FormasDePagamento
	 * num determinado dia
	 * @param formasPagamento(aforma que foi usada para efectuar o pagamento)
	 * @param startDate(Qual e a data que deseja ver s)
	 * @exception java.lang.NoClassDefFoundError*/
	
		
	public static void vendasFP(String formaDePagamento,String startDate ) {
		Connection conn = null;
		conn = Conexao.connect();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirma a emissao deste relatorio?", "Atencao",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			try {

				Map<String, Object> parametro = new HashMap<>();
				parametro.put("DATA", startDate);
				parametro.put("FP", formaDePagamento);
				


				String path = "C:/Reports/sysmafa/VendaPorFPData.jasper";
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
	public static void fatura(int numero,String cliente,String user) {
		Connection conn = null;
		conn = Conexao.connect();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirma a emissao deste relatorio?", "Atencao",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			try {

				Map<String, Object> parametro = new HashMap<>();
				parametro.put("NumeroVenda", numero);
				parametro.put("Cliente", cliente);
				parametro.put("User", user);

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
	/*------------------------------------------------------------*/
	public static void utilizadorReport() {
		Connection conn = null;
		conn = Conexao.connect();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirmar a emissão deste relatório?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			try {
				String caminho = "C:/Reports/sysmafa/Utilizador.jasper";
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
/*----------------------------------------------------------------------*/
	/**Esta Função permite a impressão de relatorio de todas as categorias
	 * @exception java.lang.NoClassDefFoundError e
	 * @exception FileNotFoundException
	 * */
	public static void categoriaReport() {
		Connection conn = null;
		conn = Conexao.connect();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirmar a emissão deste relatório?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			try {
				String path = "C:\\Reports\\sysmafa\\Categoria.jasper";
				JasperPrint print = JasperFillManager.fillReport(path, null, conn);
				JasperViewer.viewReport(print, false);
				conn.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatório de categoria " + ex);

			} 
			catch (java.lang.NoClassDefFoundError e) {
				JOptionPane.showMessageDialog(null, "ERRO " + e);

			}

			

		}

	}


}
