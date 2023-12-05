package application.br.ufrn.imd.controle;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroControle {

	@FXML
	private TextField campoUsuario;

	@FXML
	private TextField campoNome;

	@FXML
	private PasswordField campoSenha;

	@FXML
	private ChoiceBox<String> choiceBox;

	// Método para inicializar o controlador após o carregamento do arquivo FXML
	@FXML
	public void initialize() {
		// Aqui você pode adicionar opções à ChoiceBox
		if (choiceBox != null) {
			// Faça algo com o choiceBox
			choiceBox.getItems().addAll("Comum", "VIP");
		}
	}

	// Método para processar o login
	@FXML
	public void processarLogin() throws IOException {
		Stage loginStage = new Stage();

		Stage registroStage = (Stage) campoUsuario.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/br/ufrn/imd/recursos/TelaLogin.fxml"));
		Parent root = loader.load();

		loginStage.setTitle("Tela de Registro");
		loginStage.setScene(new Scene(root, 600, 400));
		loginStage.show();
		RegistroControle registroControle = new RegistroControle();
		loader.setController(registroControle);
		
		registroStage.close();
	}

	// Método para processar o registro
	@FXML
	public void processarRegistro() {
		
	}
}
