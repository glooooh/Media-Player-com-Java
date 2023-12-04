package br.ufrn.imd.visao;

import br.ufrn.imd.controle.GerenciadorControle;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;

public class TelaLogin extends Application {

    private GerenciadorControle controller;

    public TelaLogin() {
        controller = new GerenciadorControle();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Music Player");
        primaryStage.setResizable(false);

        VBox layout = new VBox(10);

        Label loginLabel = new Label("Login:");
        TextField loginCampo = new TextField();
        loginCampo.setMaxWidth(350);

        Label senhaLabel = new Label("Senha:");
        PasswordField senhaCampo = new PasswordField();
        senhaCampo.setMaxWidth(350);

        Button loginBtn = new Button("Login");
        loginBtn.setTextFill(Color.rgb(237, 241, 238));
        loginBtn.setStyle("-fx-background-color: rgb(9, 10, 9);");

        Button cadastrarBtn = new Button("Cadastre-se!");
        cadastrarBtn.setTextFill(Color.rgb(237, 241, 238));
        cadastrarBtn.setStyle("-fx-background-color: rgb(9, 10, 9);");

        loginBtn.setOnAction(event -> logar(event, loginCampo, senhaCampo));
        cadastrarBtn.setOnAction(event -> cadastrar(event));

        layout.getChildren().addAll(
            loginLabel, loginCampo, senhaLabel, senhaCampo, loginBtn, cadastrarBtn
        );

        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void logar(ActionEvent event, TextField loginCampo, PasswordField senhaCampo) {
        if (controller.fazerLogin(loginCampo.getText(), senhaCampo.getText())) {
            Stage musicPlayerStage = new Stage();
            new TelaMusicPlayer(controller.getUsuarioLogado()).start(musicPlayerStage);
            ((Stage) loginCampo.getScene().getWindow()).close();
        } else {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Usuário ou senha incorretos");
            alert.showAndWait();
        }
    }

    private void cadastrar(ActionEvent event) {
        Stage registroStage = new Stage();
        new TelaRegistro().start(registroStage);
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


//package br.ufrn.imd.visao;
//
//import br.ufrn.imd.controle.GerenciadorControle;
//
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JTextField;
//import javax.swing.JPasswordField;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.Dimension;
//// import java.awt.FlowLayout;
//
//public class TelaLogin extends JFrame implements ActionListener {
//    //private Diretorio diretorio;
//    private GerenciadorControle controller;
//
//    public TelaLogin() {
//        controller = new GerenciadorControle();
//
//        setTitle("Music Player");
//        setSize(800, 400);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
//        setLocationRelativeTo(null);
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//        JLabel loginLabel = new JLabel("Login:");
//        JTextField loginCampo = new JTextField(10);
//        loginCampo.setMaximumSize(new Dimension(350, 20));
//
//        JLabel senhaLabel = new JLabel("Senha:");
//        JPasswordField senhaCampo = new JPasswordField(15);
//        senhaCampo.setMaximumSize(new Dimension(350, 20));
//
//        JButton loginBtn = new JButton("Login");
//        // loginBtn.setBounds(200, 300, 250, 70);
//        loginBtn.setForeground(new Color(237, 241, 238));
//        loginBtn.setBackground(new Color(9, 10, 9));
//
//        JButton cadastrarBtn = new JButton("Cadastre-se!");
//        // cadastrarBtn.setBounds(200, 300, 250, 70);
//        cadastrarBtn.setForeground(new Color(237, 241, 238));
//        cadastrarBtn.setBackground(new Color(9, 10, 9));
//
//        loginBtn.addActionListener(action -> {
//            this.logar(action, loginCampo, senhaCampo);
//        });
//
//        cadastrarBtn.addActionListener(action -> {
//            this.cadastrar(action);
//        });
//
//        panel.add(loginLabel);
//        panel.add(loginCampo);
//        panel.add(senhaLabel);
//        panel.add(senhaCampo);
//        panel.add(loginBtn);
//        panel.add(cadastrarBtn);
//
//        add(panel);
//        setVisible(true);
//    }
//
//    private void logar(ActionEvent ActionEvente, JTextField loginCampo, JTextField senhaCampo) {
//        if (controller.fazerLogin(loginCampo.getText(), senhaCampo.getText())) {
//            new TelaMusicPlayer();
//            dispose();
//        } else {
//            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos", "Titulo", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void cadastrar(ActionEvent ActionEvente) {
//        new TelaRegistro();
//        dispose();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//    }
//
//}