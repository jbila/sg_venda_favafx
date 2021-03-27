
package mz.co.mahs.controller;


import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mz.co.mahs.conection.Conexao;



public class FXMLMenuController implements Initializable {
	Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	Alert alertErro = new Alert(AlertType.ERROR);
	  @FXML
	    private AnchorPane rootMenu;

	    @FXML
	    private   BorderPane menuPane;

	    @FXML
	    private VBox vBoxMenuItems;

	    @FXML
	    private Button btnDashBord;

	    @FXML
	    private Button btnProducto;

	    @FXML
	    private Button btnFornecedor;

	    @FXML
	    private Button btnCliente;

	    @FXML
	    private Button btnCategoria;

	    @FXML
	    private Button btnUtilizador;

	    @FXML
	    private Button btnVendas;
	    
	    @FXML
	    private Button btnPedidos;
	    @FXML
	    private Button btnProvinciaDistrito;
	    @FXML
	    private Pane topMenuPane,buttomMenuPane;
	    @FXML
	    private  Label lblUserName,lblType;

	    //-------------the load method-----------------------------------------------
	    public void initialize(URL url, ResourceBundle rb) {
	    	lblType.setText("Profile: "+ControllerLogin.perfil.toUpperCase());
	    	lblUserName.setText("User: "+ControllerLogin.username.toUpperCase());
	        
	    }    
	   //----------------------------------------------------------------------------
	   @FXML
	  private  void handleCategoria(ActionEvent event) {
	    	openCategoria();
	    }

	    @FXML
	    private  void handleCliente(ActionEvent event) {
	    	openCliente();
	    }

	    @FXML
	    private   void handleDashBoard(ActionEvent event) {
	    	
	    	openDashBord();

	    }

	    @FXML
	    private   void handleFornecedor(ActionEvent event) {
	    		openFornecedor();
	    }

	    @FXML
	    private   void handleProducto(ActionEvent event) {
	    	openProducto();
	    }

	   
	    @FXML
	    private    void handleVendas(ActionEvent event) {
	    	openVendas();
	    }
  
    
    @FXML
     private void handleUtilizador(ActionEvent event) {
         openUtilizador();
    }

    @FXML
    private void handlePedidos(ActionEvent event) {
        openPedidos();
   }
    @FXML
	  private  void handleProvinciaDistrito(ActionEvent event) {
    	openProvinciaDistrito();
	    }
  
    @FXML
    private void handleOut(ActionEvent event) {
		Connection conn = null;
    	alertConfirm.setTitle("Confirmacção");
    	alertConfirm.setHeaderText("Look, a Confirmation Dialog");
    	alertConfirm.setContentText("Tem certeza que quer sair da aplicacao?");

    	Optional<ButtonType> result = alertConfirm.showAndWait();
    	if (result.get() == ButtonType.OK){
    	    // ... user chose OK
    		Conexao.disconect(conn);
    		OpenLogin();
        	Stage stage = (Stage) this.rootMenu.getScene().getWindow();
            stage.close();
    	} else {
    	    // ... user chose CANCEL or closed the dialog
    	}
    		
    }
   
     
     private void openFornecedor(){
     Stage stage=new Stage();
       try {
           
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mz/co/mahs/views/FXMLFornecedor.fxml"));
        Parent rootFormando = (Parent) fxmlLoader.load();
        
		Scene scene = new Scene(rootFormando);
		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		menuPane.setCenter(rootFormando);
        //stage.show();
    } catch(Exception e) {
    	alertErro.setHeaderText("Erro");
    	alertErro.setContentText("Erro ao Carregar o Ficheiro "+e);
    	alertErro.showAndWait();
    }
    } 
    //--------------------------------------------------------------------------
      private void openProducto(){
    	  Stage stage=new Stage();
       try {
           
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mz/co/mahs/views/FXMLProducto.fxml"));
        Parent rootFormador = (Parent) fxmlLoader.load();
       
		Scene scene = new Scene(rootFormador);
		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		menuPane.setCenter(rootFormador);
		// stage.show();
    } catch(Exception e) {
    	alertErro.setHeaderText("Erro");
    	alertErro.setContentText("Erro ao Carregar o Ficheiro "+e);
    	alertErro.showAndWait();
    }
    }
    //--------------------------------------------------------------------------
       private void openUtilizador(){
    	   Stage stage=new Stage();
       try {
           
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mz/co/mahs/views/FXMLUtilizador.fxml"));
        Parent rootUtilizador = (Parent) fxmlLoader.load();
       
 
		Scene scene = new Scene(rootUtilizador);
		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setFullScreen(true);
		menuPane.setCenter(rootUtilizador);
		menuPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);/*
		THIS ALLOW TO OPEN THE FORM AND FULL THE CENTER OF THE BORDERPANE
		*/
		// stage.show();
    } catch(Exception e) {
    	alertErro.setHeaderText("Erro");
    	alertErro.setContentText("Erro ao Carregar o Ficheiro "+e);
    	alertErro.showAndWait();
    }
    }
   //---------------------------------------------------------------------------
        private void openPedidos(){
        	Stage stage=new Stage();
       try {
           
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mz/co/mahs/views/FXMLPedidos.fxml"));
        Parent rootEmpresa = (Parent) fxmlLoader.load();

		Scene scene = new Scene(rootEmpresa);
		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		menuPane.setCenter(rootEmpresa);
		menuPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	
		
		// stage.show();
    } catch(Exception e) {
    	alertErro.setHeaderText("Erro");
    	alertErro.setContentText("Erro ao Carregar o Ficheiro "+e);
    	alertErro.showAndWait();
    	e.printStackTrace();
    }
    } 
        //----------------------------------------------------------------------
         private void openCategoria(){
        	 Stage stage=new Stage();
       try {
           
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mz/co/mahs/views/FXMLCategoria.fxml"));
        Parent rootCurso = (Parent) fxmlLoader.load();
       
		Scene scene = new Scene(rootCurso);
		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		menuPane.setCenter(rootCurso);
		menuPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		// stage.show();
    } catch(Exception e) {
    	alertErro.setHeaderText("Erro");
    	alertErro.setContentText("Erro ao Carregar o Ficheiro "+e);
    	alertErro.showAndWait();
    }
    }
   
    //--------------------------------------------------------------------------
          private void openDashBord() {
        	  alertInfo.setTitle("Info");
        	  alertInfo.setHeaderText("Informação");
        	  alertInfo.setContentText("Wait please, i am still working on it,no pressure!");
        	  alertInfo.showAndWait();
          	} 
          
          private void OpenLogin(){
        	     Stage stage=new Stage();
        	       try {
        	    	   
        	           
						FXMLLoader fxmlLoader = new FXMLLoader(
								getClass().getResource("/mz/co/mahs/views/FXMLLogin.fxml"));
						Parent rootLogin = (Parent) fxmlLoader.load();
						Scene scene = new Scene(rootLogin);
						scene.getStylesheets()
								.add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
						stage.setScene(scene);
						stage.initStyle(StageStyle.UNDECORATED);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.show();
        	    } catch(Exception e) {
        	    	alertErro.setHeaderText("Erro");
        	    	alertErro.setContentText("Erro ao Carregar o Ficheiro "+e);
        	    	alertErro.showAndWait();
        	    }
        	    } 
        
 //--------------------------------------------------------------------------
          private void openCliente(){
        	  Stage stage=new Stage();
           try {
               
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mz/co/mahs/views/FXMLCliente.fxml"));
            Parent rootFormador = (Parent) fxmlLoader.load();
           
    		Scene scene = new Scene(rootFormador);
    		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
    		stage.setScene(scene);
    		stage.initStyle(StageStyle.UNDECORATED);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		menuPane.setCenter(rootFormador);
    		menuPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    		// stage.show();
        } catch(Exception e) {
        	alertErro.setHeaderText("Erro");
        	alertErro.setContentText("Erro ao Carregar o Ficheiro "+e);
        	alertErro.showAndWait();
        }
        }
          //--------------------------------------------------------------------------
          private void openVendas(){
        	  Stage stage=new Stage();
           try {
               
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mz/co/mahs/views/FXMLPedido.fxml"));//FXMLSituacao_
            Parent rootFormador = (Parent) fxmlLoader.load();
           
    		Scene scene = new Scene(rootFormador);
    		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
    		stage.setScene(scene);
    		stage.initStyle(StageStyle.UNDECORATED);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		menuPane.setCenter(rootFormador);
    		menuPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    		//stage.show();
        } catch(Exception e) {
        	alertErro.setHeaderText("Erro");
        	alertErro.setContentText("Erro ao Carregar o Ficheiro "+e);
        	alertErro.showAndWait();
        }
        }
          //--------------------------------------------------------------------------
          private void openProvinciaDistrito(){
        	  Stage stage=new Stage();
           try {
               
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mz/co/mahs/views/FXMLProvinciaDistrito.fxml"));//FXMLSituacao_
            Parent rootFormador = (Parent) fxmlLoader.load();
           
    		Scene scene = new Scene(rootFormador);
    		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
    		stage.setScene(scene);
    		stage.initStyle(StageStyle.UNDECORATED);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		menuPane.setCenter(rootFormador);
    		menuPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    		//stage.show();
        } catch(Exception e) {
        	alertErro.setHeaderText("Erro");
        	alertErro.setContentText("Erro ao Carregar o Ficheiro "+e);
        	alertErro.showAndWait();
        }
        }
}
