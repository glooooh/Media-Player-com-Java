package application.br.ufrn.imd.visao;

import application.br.ufrn.imd.controle.UsuarioControle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaRegistro extends Application {

    private UsuarioControle controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        controller = new UsuarioControle();

        primaryStage.setTitle("Music Player");
        primaryStage.setResizable(false);

        VBox panel = new VBox(10);

        Label loginLabel = new Label("Login:");
        TextField loginCampo = new TextField();
        loginCampo.setMaxWidth(350);

        Label senhaLabel = new Label("Senha:");
        PasswordField senhaCampo = new PasswordField();
        senhaCampo.setMaxWidth(350);

        Label nomeLabel = new Label("Nome do Usuário:");
        TextField nomeCampo = new TextField();
        nomeCampo.setMaxWidth(350);

        String[] opcoes = {"VIP", "Comum"};
        ComboBox<String> tipoBox = new ComboBox<>();
        tipoBox.getItems().addAll(opcoes);
        tipoBox.setMaxWidth(100);

        Button registrarBtn = new Button("Registrar");
        registrarBtn.setStyle("-fx-text-fill: white; -fx-background-color: #090a09;");

        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-text-fill: white; -fx-background-color: #090a09;");

        registrarBtn.setOnAction(event -> {
            boolean sucesso = cadastrar(nomeCampo.getText(), loginCampo.getText(), senhaCampo.getText(), tipoBox.getSelectionModel().getSelectedItem());
            if (sucesso) {
                login(primaryStage);
            }
        });

        loginBtn.setOnAction(event -> login(primaryStage));

        panel.getChildren().addAll(loginLabel, loginCampo, senhaLabel, senhaCampo, nomeLabel, nomeCampo, tipoBox, registrarBtn, loginBtn);

        Scene scene = new Scene(panel, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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

    private void login(Stage primaryStage) {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.start(primaryStage);
    }

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
