package application.br.ufrn.imd.visao;

import application.br.ufrn.imd.controle.UsuarioControle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Classe que representa a interface gráfica para o processo de registro de
 * usuário.
 */
public class TelaRegistro extends Application {

    private UsuarioControle controller;

    /**
     * Método principal que inicia a interface gráfica da tela de registro.
     *
     * @param primaryStage O palco principal (Stage) da aplicação.
     */
    @Override
    public void start(Stage primaryStage) {
        controller = new UsuarioControle();

        primaryStage.setTitle("Music Player");
        primaryStage.setResizable(false);

        VBox panel = new VBox(10);

        // Componentes da tela...

        Button registrarBtn = new Button("Registrar");
        registrarBtn.setStyle("-fx-text-fill: white; -fx-background-color: #090a09;");

        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-text-fill: white; -fx-background-color: #090a09;");

        registrarBtn.setOnAction(event -> {
            boolean sucesso = cadastrar(/* parâmetros */);
            if (sucesso) {
                login(primaryStage);
            }
        });

        loginBtn.setOnAction(event -> login(primaryStage));

        panel.getChildren().addAll(/* componentes da tela */, registrarBtn, loginBtn);

        Scene scene = new Scene(panel, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Método para realizar o processo de cadastro de um novo usuário.
     *
     * @param nome  Nome do usuário.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param tipo  Tipo de usuário (VIP ou Comum).
     * @return True se o cadastro foi bem-sucedido, False caso contrário.
     */
    private boolean cadastrar(String nome, String login, String senha, String tipo) {
        if (tipo.equals("VIP")) {
            tipo = "V";
        } else {
            tipo = "C";
        }

        if (controller.cadastrarUsuarioNoBanco(nome, login, senha, tipo)) {
            exibirAlerta("Sucesso", "Registro Efetuado com Sucesso", Alert.AlertType.INFORMATION);
            return true;
        } else {
            exibirAlerta("Falha", "Falha no Registro", Alert.AlertType.ERROR);
            return false;
        }
    }

    /**
     * Método para redirecionar para a tela de login.
     *
     * @param primaryStage O palco principal (Stage) da aplicação.
     */
    private void login(Stage primaryStage) {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.start(primaryStage);
    }

    /**
     * Método para exibir uma caixa de diálogo de alerta.
     *
     * @param titulo   Título do alerta.
     * @param mensagem Mensagem do alerta.
     * @param tipo     Tipo do alerta.
     */
    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Método principal que inicia a aplicação.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        launch(args);
    }
}