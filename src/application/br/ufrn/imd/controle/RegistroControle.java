package application.br.ufrn.imd.controle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe responsável pelo controle da interface de registro.
 */
public class RegistroControle {

	@FXML
	private TextField campoUsuario;

	@FXML
	private TextField campoNome;

	@FXML
	private PasswordField campoSenha;

	@FXML
	private ChoiceBox<String> choiceBox;

	/**
	 * Inicializa a ChoiceBox com as opções "Comum" e "VIP".
	 */
	@FXML
	public void initialize() {
		if (choiceBox != null) {
			choiceBox.getItems().addAll("Comum", "VIP");
			choiceBox.setValue("Comum");
		}
	}

	/**
	 * Método para processar o login, direcionando para a tela de login.
	 *
	 * @throws IOException se houver um problema ao carregar a tela de login.
	 */
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

	/**
	 * Método para processar o registro do usuário.
	 */
	@FXML
	public void processarRegistro() {
		String nome = campoNome.getText();
		String login = campoUsuario.getText();
		String senha = campoSenha.getText();
		String tipo = choiceBox.getValue();

		if (nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
			exibirAlerta("Erro", "Por favor, preencha todos os campos!", Alert.AlertType.ERROR);
			return;
		}

		tipo = tipo.equals("VIP") ? "V" : "C";

		UsuarioControle usuarioControle = new UsuarioControle();
		boolean cadastroRealizado = usuarioControle.cadastrarUsuarioNoBanco(nome, login, senha, tipo);

		if (cadastroRealizado) {
			exibirAlerta("Sucesso", "Registro Efetuado com Sucesso", Alert.AlertType.INFORMATION);
		} else {
			exibirAlerta("Falha", "Falha no Registro", Alert.AlertType.ERROR);
		}
	}

	/**
	 * Método para exibir um alerta na interface.
	 *
	 * @param titulo   O título do alerta.
	 * @param mensagem A mensagem a ser exibida no alerta.
	 * @param tipo     O tipo de alerta (INFORMATION, ERROR, WARNING, etc.).
	 */
	private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
		Alert alerta = new Alert(tipo);
		alerta.setTitle(titulo);
		alerta.setContentText(mensagem);
		alerta.show();
	}
}