
package mz.co.mahs.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mz.co.mahs.conection.Conexao;

/**
 * <h1>ControllerLogin</h1>
 * <p>
 * Esta classe tem evento que controla a entrada de dados,<b>validacoes de
 * campos</b> e <b>preenchimento de combobox como de tabelas</b><br>
 * ela comunica directamente com as classes que estao no pacote Dao e Model <br>
 * Possibilitando a troca de dados entre o utilizador e o sistema
 * </p>
 * <P>
 * Salientar que esta classe captura os dados das Telas que sao da extensao
 * <b>FXML do javaFX</b>
 * </P>
 * 
 * @author JACINTO BILA
 *         <h3>Contacto:848319153 Email:jacinto.billa@gmail.com
 *         </h3>
 * 
 */
public class ControllerLogin implements Initializable {
	static Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertErro = new Alert(AlertType.ERROR);

	public static int idUsuario = 0;// Este idUsuario e usado para armazenar o id do utilizador vindo da base de
									// dado
	/**
	 * o status vai armazenar o estado do utilizador que esta alogar no sistema, se
	 * o estado estiver <b>ACTIVO</b> vai abrir o menu principal
	 */
	public String status = "";
	public static String perfil = "";// Armazena o Perfil vindo da base de dado
	public static String username = "";// Armazena o username
	public String password = "";// Armazena a Password

	@FXML
	private HBox hboximage;
	@FXML
	private ImageView imageviewkeylock;
	@FXML
	private Label lblUserName;
	@FXML
	private Label lblStatus;
	@FXML

	private TextField txtUserName;
	@FXML
	private Label lblPassword;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btnLogin;

	@FXML
	private Button btnCancel;
	@FXML
	private AnchorPane rootPane;

	/**
	 * Este evento chama o metodo que fecha a app
	 */
	@FXML
	private void handleBtnCancel(ActionEvent event) {
		cancel();
	}

	/**
	 * Esta evento verifica se os dados do utilizador que esta a logar sao validos ou
	 * nao fazendo comparacao com os dados existentes na base de dados se o perfil
	 * encontrado for <b>STANDARD</b> vai habilitar um perfil que responde ao esse
	 * utilizador caso nao vai abrir a conta principal que e do administrador
	 */
	@FXML
	private void handleActionLogin(ActionEvent event) throws IOException {
		Stage stage = (Stage) this.btnLogin.getScene().getWindow();
		// start
		username = txtUserName.getText();
		password = txtPassword.getText();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt;
		try {
			conn = Conexao.connect();
			stmt = conn.prepareStatement(
					"SELECT * FROM tbl_utilizador WHERE username='" + username + "' AND password='" + password + "' ");
			rs = stmt.executeQuery();

			if (rs.next()) {
				username = (rs.getString("username"));
				password = (rs.getString("password"));
				idUsuario = (rs.getInt("idUtilizador"));
				status = (rs.getString("status"));
				perfil = (rs.getString("perfil"));

				// when logged as Admin
				if (perfil.equalsIgnoreCase("ADMINISTRADOR") && (status.equalsIgnoreCase("Activo"))
						&& (txtUserName.getText().equalsIgnoreCase(username))
						&& (txtPassword.getText().equalsIgnoreCase(password))) {
					txtUserName.setText("Succesfully Logged");
					txtUserName.setText(null);
					txtPassword.setText(null);
					alertInfo.setHeaderText("Utilizador Info");
					alertInfo.setContentText(perfil + "<>" + username + "<>" + idUsuario);
					// alertInfo.showAndWait();
					login(); // CHAMA O FORMULARIO DE MENU
					stage.close();

				}
				// When is a Standard
				else if (perfil.equalsIgnoreCase("standard") && (status.equalsIgnoreCase("Activo"))) {

					// txtUserName.setForeground(Color.GREEN);
					txtUserName.setText("Succesfully Logged");
					txtUserName.setText(null);
					txtPassword.setText(null);
					alertInfo.setHeaderText("Utilizador Info");
					alertInfo.setContentText(perfil + "<>" + username + "<>" + idUsuario);
					// alertInfo.showAndWait();
					login(); // CHAMA O FORMULARIO DE MENU
					stage.close();// fecha a stage

				} else {
					txtUserName.setText("Utilizador Inactivo");
					// lblLogado.setForeground(Color.BLUE);
				}
				// ending of logged as Standard

			} else {

				txtUserName.setText("Palavra passe or username errada");
				// lblLogado.setForeground(Color.RED);

			}
			stmt.close();
			rs.close();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "um erro na" + ex);
		}

	}

//------------------------------------------------------------------------------
	// Esta funcao fecha aplicacao
	private void cancel() {
		System.exit(0);
	}

	// -----------------------------------------------------------------------------
	private void login() throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/mz/co/mahs/views/FXMLMenu.fxml"));//
		Scene scene = new Scene(root);
		stage.initStyle(StageStyle.UNIFIED);
		// stage.setFullScreen(true); //when i enable this the forms slow alot to save
		// when it opens forms in maximazed you have to resize after that you can do

		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Este evento e o primeiro a ser chamado, que inicializa as text-fields com
	 * dados do administrador
	 * <li>username:admin</li> *
	 * <li>pass:admin</li>
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtPassword.setText("admin");
		txtUserName.setText("admin");
	}

}
