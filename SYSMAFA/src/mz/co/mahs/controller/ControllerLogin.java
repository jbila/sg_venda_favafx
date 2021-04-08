
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

public class ControllerLogin implements Initializable {
	static Alert alertInfo = new Alert(AlertType.INFORMATION);
	Alert alertErro = new Alert(AlertType.ERROR);

	public static int idUsuario = 0;// it will be used in other class
	public String status = "";
	public static String perfil = "";
	public static String username = "";
	public String password = "";

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

	@FXML
	private void handleBtnCancel(ActionEvent event) {
		cancel();
	}

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
		// data and stop the app, to solve it
		// wheen it opens forms in maximazed you have to resize after that you can do
		// all operations with no problem
		scene.getStylesheets().add(getClass().getResource("/mz/co/mahs/views/estilo.css").toExternalForm());
		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.show();
	}

	// -----------------------------------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtPassword.setText("admin");
		txtUserName.setText("admin");
	}

}
