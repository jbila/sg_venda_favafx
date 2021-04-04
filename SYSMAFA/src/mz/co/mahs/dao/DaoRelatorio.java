package mz.co.mahs.dao;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;

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

// -----------------------------clientes---------------------------------------------------
	public static void clienteReport() {
		Connection conn = null;
		conn = Conexao.connect();

		int confirm = JOptionPane.showConfirmDialog(null, "Confirmar a emissão deste relatório?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			try {
				String caminho = "C:/Reports/sysmafa/clientes.jasper";
				JasperPrint print = JasperFillManager.fillReport(caminho, null, conn);
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
				String caminho = "C:/Reports/sysmafa/fornecedor.jasper";
				JasperPrint print = JasperFillManager.fillReport(caminho, null, conn);
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
	@SuppressWarnings("rawtypes")
	public static void vendasReport(LocalDate data1, LocalDate data2){
		Connection conn=null;
		conn = Conexao.connect();
		
		
		int confirm= JOptionPane.showConfirmDialog(null, "Confirma a emissao deste relatorio?","Atencao",JOptionPane.YES_NO_OPTION);
			if(confirm==JOptionPane.YES_NO_OPTION){
				try{
					
					
					HashMap parametro=new HashMap<>();
					parametro.put("DataInicio",data1 );
					parametro.put("DataFim", data2);
					
					
					//String caminho="src/bila/co/mz/Reports/Vendas.jasper";
					String caminho01="C:/Reports/sysmafa/vendasCompletas.jasper";
					JasperPrint print=JasperFillManager.fillReport(caminho01,parametro,conn);
					JasperViewer.viewReport(print,false);
					conn.close();
					
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatorio de Venda "+ex);
					
				}
				catch(java.lang.NoClassDefFoundError e){
					JOptionPane.showMessageDialog(null, "ERRO "+e);
					

				}
				
			} 
					
		}
//-------------------------------------------------------------------------------------------------------------------------
	public static void vendasFP(String formaDePagamento){
		Connection conn=null;
		conn = Conexao.connect();
		
		
		int confirm= JOptionPane.showConfirmDialog(null, "Confirma a emissao deste relatorio?","Atencao",JOptionPane.YES_NO_OPTION);
			if(confirm==JOptionPane.YES_NO_OPTION){
				try{
					
					
					HashMap parametro=new HashMap<>();
					parametro.put("FP",formaDePagamento );
					
					
					//String caminho="src/bila/co/mz/Reports/Vendas.jasper";
					String caminho01="C:/Reports/sysmafa/VendasPorFP.jasper";
					JasperPrint print=JasperFillManager.fillReport(caminho01,parametro,conn);
					JasperViewer.viewReport(print,false);
					conn.close();
					
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatorio de Venda "+ex);
					
				}
				catch(java.lang.NoClassDefFoundError e){
					JOptionPane.showMessageDialog(null, "ERRO "+e);
					

				}
				
			} 
					
		}
//---------------------------------------------------------------------
	public static void fatura(int numero){
		Connection conn=null;
		conn = Conexao.connect();
		
		
		int confirm= JOptionPane.showConfirmDialog(null, "Confirma a emissao deste relatorio?","Atencao",JOptionPane.YES_NO_OPTION);
			if(confirm==JOptionPane.YES_NO_OPTION){
				try{
					
					
					HashMap parametro=new HashMap<>();
					parametro.put("Introduza o codigo da venda",numero );
					
					
					//String caminho="src/bila/co/mz/Reports/Vendas.jasper";
					String caminho01="C:/Reports/sysmafa/fatura.jasper";
					JasperPrint print=JasperFillManager.fillReport(caminho01,parametro,conn);
					JasperViewer.viewReport(print,false);
					conn.close();
					
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Erro ao emetir o Relatorio de Venda "+ex);
					
				}
				catch(java.lang.NoClassDefFoundError e){
					JOptionPane.showMessageDialog(null, "ERRO "+e);
					

				}
				
			} 
					
		}


}
