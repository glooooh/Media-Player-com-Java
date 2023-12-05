package application.br.ufrn.imd.visao;

import application.br.ufrn.imd.controle.GerenciadorControle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaLogin extends Application {

    private GerenciadorControle controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new GerenciadorControle();

        primaryStage.setTitle("Music Player");
        primaryStage.setResizable(false);

        VBox root = new VBox(10);
        root.setPrefSize(800, 400);

        Label loginLabel = new Label("Login:");
        TextField loginCampo = new TextField();
        loginCampo.setMaxWidth(350);

        Label senhaLabel = new Label("Senha:");
        PasswordField senhaCampo = new PasswordField();
        senhaCampo.setMaxWidth(350);

        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-text-fill: white; -fx-background-color: #090a09;");

        Button cadastrarBtn = new Button("Cadastre-se!");
        cadastrarBtn.setStyle("-fx-text-fill: white; -fx-background-color: #090a09;");

        loginBtn.setOnAction(event -> logar(primaryStage, loginCampo.getText(), senhaCampo.getText()));
        cadastrarBtn.setOnAction(event -> cadastrar(primaryStage));

        root.getChildren().addAll(loginLabel, loginCampo, senhaLabel, senhaCampo, loginBtn, cadastrarBtn);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void logar(Stage primaryStage, String login, String senha) {
        if (controller.fazerLogin(login, senha)) {
            TelaMusicPlayer telaMusicPlayer = new TelaMusicPlayer(controller.getUsuarioLogado());
            telaMusicPlayer.start(new Stage());
            primaryStage.close();
        } else {
            // Exibe uma caixa de diálogo com a mensagem de erro
            exibirAlerta("Usuário ou senha incorretos!");
        }
    }

    private void cadastrar(Stage primaryStage) {
        TelaRegistro telaRegistro = new TelaRegistro();
        telaRegistro.start(new Stage());
        primaryStage.close();
    }

    private void exibirAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
