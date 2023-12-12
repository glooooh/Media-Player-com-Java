package application.br.ufrn.imd.controle;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para a tela de login.
 */
public class LoginControle {
    private GerenciadorControle controller;

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    /**
     * Define o controlador principal.
     *
     * @param controller O controlador principal da aplicação.
     */
    public void setController(GerenciadorControle controller) {
        this.controller = controller;
    }

    /**
     * Inicializa o controlador.
     */
    @FXML
    public void initialize() {
        this.setController(new GerenciadorControle());
    }

    /**
     * Processa o login do usuário.
     */
    @FXML
    public void processarLogin() {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();

        if (usuario.isEmpty() || senha.isEmpty()) {
            exibirAlerta("Erro", "Por favor, preencha todos os campos!", Alert.AlertType.ERROR);
        } else if (controller.fazerLogin(usuario, senha)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../recursos/TelaMusicPlayer.fxml"));
            try {
                Parent root = loader.load();

                MusicPlayerControle musicPlayer_controle = loader.getController();
                musicPlayer_controle.setGerenciadorControle(this.controller);
                musicPlayer_controle.setNomeUsuario();

                Stage primaryStage = (Stage) campoUsuario.getScene().getWindow();
                primaryStage.setTitle("MusicPlayer");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            exibirAlerta("Erro", "Usuário ou senha incorretos!", Alert.AlertType.ERROR);
        }
    }

    /**
     * Processa o registro do usuário.
     *
     * @throws IOException Se ocorrer um erro de E/S durante o carregamento da tela
     *                     de registro.
     */
    @FXML
    public void processarRegistro() throws IOException {
        Stage registroStage = new Stage();

        Stage loginStage = (Stage) campoUsuario.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/application/br/ufrn/imd/recursos/TelaRegistro.fxml"));
        Parent root = loader.load();

        registroStage.setTitle("Tela de Registro");
        registroStage.setScene(new Scene(root, 600, 400));
        registroStage.show();

        RegistroControle registroControle = new RegistroControle();
        loader.setController(registroControle);

        loginStage.close();
    }

    /**
     * Exibe um alerta na interface gráfica.
     *
     * @param titulo   O título do alerta.
     * @param mensagem O conteúdo do alerta.
     * @param tipo     O tipo do alerta (INFORMATION, WARNING, ERROR, etc.).
     */
    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.show();
    }
}