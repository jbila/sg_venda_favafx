
package mz.co.mahs.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
 private  static final String USERNAME="root";
 private  static final  String PASSWORD="root";
 private  static final String CONN_STRING
            ="jdbc:mysql://127.0.0.1:3308/db_simafa?autoReconnect=true&useSSL=false";
 
   public static Connection connect(){
	   Connection conn=null;
   	try {
           conn=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
          // JOptionPane.showMessageDialog(null,"DATABASE CONNECTED!");
           return conn;
       } 
   	catch (SQLException ex) {
          
       	JOptionPane.showMessageDialog(null,ex);
       return null;
       }
   	
   	
   }
 //---------------------------------------------------------------------------------------
   public static void disconect(Connection conn){
   	{
   		try {
   			if(conn!=null)
				conn.close();
   			JOptionPane.showMessageDialog(null,"DATABASE  DISCONNECTED");
			} 
   		catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Houve um erro ao Tentar Fechar a Base de Dados" +e);
				
			}
   		finally{
   			
   		}
   		
   	}
   }

     
}
