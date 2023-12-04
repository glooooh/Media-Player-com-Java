package br.ufrn.imd.visao;

import br.ufrn.imd.controle.UsuarioControle;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

public class TelaRegistro extends Application {

    private UsuarioControle controller;
    private Button registrarBtn; // Adicionando a variável como membro da classe

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

        String[] opcoes = { "VIP", "Comum" };
        ComboBox<String> tipoBox = new ComboBox<>();
        tipoBox.getItems().addAll(opcoes);
        tipoBox.setMaxWidth(100);

        registrarBtn = new Button("Registrar"); // Inicializando o botão
        registrarBtn.setTextFill(Color.rgb(237, 241, 238));
        registrarBtn.setStyle("-fx-background-color: rgb(9, 10, 9);");

        Button loginBtn = new Button("Login");
        loginBtn.setTextFill(Color.rgb(237, 241, 238));
        loginBtn.setStyle("-fx-background-color: rgb(9, 10, 9);");

        registrarBtn.setOnAction(event -> cadastrar(nomeCampo, loginCampo, senhaCampo, tipoBox));
        loginBtn.setOnAction(event -> login(primaryStage)); // Passando o Stage como parâmetro

        panel.getChildren().addAll(
            loginLabel, loginCampo, senhaLabel, senhaCampo,
            nomeLabel, nomeCampo, tipoBox, registrarBtn, loginBtn
        );

        Scene scene = new Scene(panel, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cadastrar(TextField nomeCampo, TextField loginCampo, PasswordField senhaCampo, ComboBox<String> tipoCampo) {
        String tipo = (tipoCampo.getSelectionModel().getSelectedIndex() == 0) ? "V" : "C";

        // Realize a validação das senhas (caracteres inválidos) aqui

        if (controller.cadastrarUsuarioNoBanco(nomeCampo.getText(), loginCampo.getText(), senhaCampo.getText(), tipo)) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Registro Efetuado com Sucesso");
            alert.showAndWait();
        } else {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha no Registro");
            alert.showAndWait();
        }
    }

    private void login(Stage primaryStage) {
        new TelaLogin().start(new Stage());
        primaryStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


//package br.ufrn.imd.visao;
//
//import javax.swing.JFrame;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//// import br.ufrn.imd.modelo.Diretorio;
//import br.ufrn.imd.controle.UsuarioControle;
//
//// import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JTextField;
//import javax.swing.JPasswordField;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//import java.awt.Color;
//// import java.awt.event.ActionEvent;
//// import java.awt.event.ActionListener;
//import java.awt.Dimension;
//// import java.awt.FlowLayout;
//
//public class TelaRegistro extends JFrame implements ActionListener {
//    //private Diretorio diretorio;
//    private UsuarioControle controller;
//
//    public TelaRegistro() {
//        controller = new UsuarioControle();
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
//        JLabel nomeLabel = new JLabel("Nome do Usuário:");
//        JTextField nomeCampo = new JTextField(10);
//        nomeCampo.setMaximumSize(new Dimension(350, 20));
//
//        String[] opcoes = { "VIP", "Comum" };
//        JComboBox<String> tipoBox = new JComboBox<>(opcoes);
//        tipoBox.setMaximumSize(new Dimension(100, 20));
//
//        JButton registrarBtn = new JButton("Registrar");
//        // registrarBtn.setBounds(200, 300, 250, 70);
//        registrarBtn.setForeground(new Color(237, 241, 238));
//        registrarBtn.setBackground(new Color(9, 10, 9));
//
//        JButton loginBtn = new JButton("Login");
//        // loginBtn.setBounds(200, 300, 250, 70);
//        loginBtn.setForeground(new Color(237, 241, 238));
//        loginBtn.setBackground(new Color(9, 10, 9));
//
//        registrarBtn.addActionListener(action -> {
//            this.cadastrar(action, nomeCampo, loginCampo, senhaCampo, tipoBox);
//        });
//
//        registrarBtn.addActionListener(action -> {
//            this.login(action);
//        });
//
//        panel.add(loginLabel);
//        panel.add(loginCampo);
//        panel.add(senhaLabel);
//        panel.add(senhaCampo);
//        panel.add(nomeLabel);
//        panel.add(nomeCampo);
//        panel.add(tipoBox);
//        panel.add(registrarBtn);
//        panel.add(loginBtn);
//
//        add(panel);
//        setVisible(true);
//    }
//
//    private void cadastrar(ActionEvent ActionEvente, JTextField nomeCampo, JTextField loginCampo, JTextField senhaCampo,
//            JComboBox<String> tipoCampo) {
//        String tipo;
//
//        //Realizar validação das senhas (Caracteres Inválidos)
//
//        if(tipoCampo.getSelectedIndex() == 0){
//            tipo = "V";
//        }else{
//            tipo = "C";
//        }
//
//        if(controller.cadastrarUsuarioNoBanco(nomeCampo.getText(), loginCampo.getText(), senhaCampo.getText(), tipo)) {
//            JOptionPane.showMessageDialog(null, "Registro Efetuado com Sucesso", "Titulo", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(null, "Falha no Registro", "Titulo", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void login(ActionEvent ActionEvente) {
//        new TelaLogin();
//        dispose();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//    }
//
//}