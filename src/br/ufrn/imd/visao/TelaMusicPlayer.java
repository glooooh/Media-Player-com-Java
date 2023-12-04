package br.ufrn.imd.visao;

import java.util.Optional;

import br.ufrn.imd.controle.GerenciadorControle;
import br.ufrn.imd.modelo.UsuarioComum;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

public class TelaMusicPlayer extends Application {

    private GerenciadorControle controller;
    private MenuBar menuBar; // Declarando a variável menuBar como campo da classe

    public TelaMusicPlayer(UsuarioComum usuario) {
        controller = new GerenciadorControle();
        controller.setUsuarioLogado(usuario);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Music Player");
        primaryStage.setResizable(false);

        VBox layout = new VBox(10);

        menuBar = new MenuBar(); // Inicializando a variável menuBar

        Menu opcoes = new Menu("Opções");
        MenuItem sairMenuItem = new MenuItem("Sair");

        sairMenuItem.setOnAction(event -> sair());

        MenuItem alterarNome = new MenuItem("Alterar Nome");
        MenuItem alterarSenha = new MenuItem("Alterar Senha");
        MenuItem alterarTipo = new MenuItem("Alterar Tipo de Usuário");

        alterarNome.setOnAction(event -> alterarNome());
        alterarSenha.setOnAction(event -> alterarSenha());
        alterarTipo.setOnAction(event -> alterarTipo());

        menuBar.getMenus().add(opcoes);
        opcoes.getItems().addAll(alterarNome, alterarSenha, alterarTipo);
        opcoes.getItems().add(sairMenuItem);

        layout.getChildren().addAll(menuBar);

        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void sair() {
        controller.fazerLogout();
        new TelaLogin().start(new Stage());
        ((Stage) menuBar.getScene().getWindow()).close();
    }
    
    public void exibirMensagem(String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void alterarNome() {
    	TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Alterar Nome");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite o novo nome:");

        Optional<String> resultado = dialog.showAndWait();
        resultado.ifPresent(novoNome -> {
        	controller.editarUsuario(novoNome, "N");
            // Aqui você pode usar o novo nome inserido (novoNome)
            exibirMensagem("Nome alterado para: " + novoNome);
        });
    }

    public void alterarSenha() {
    	TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Alterar Senha");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite a nova senha:");

        Optional<String> resultado = dialog.showAndWait();
        resultado.ifPresent(novaSenha -> {
        	controller.editarUsuario(novaSenha, "S");
            exibirMensagem("Nome alterada");
        });
    }

    public void alterarTipo() {
    	TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Alterar Senha");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite a nova senha:");

        Optional<String> resultado = dialog.showAndWait();
        resultado.ifPresent(novoTipo -> {
        	controller.editarUsuario(novoTipo, "T");
            exibirMensagem("Tipo alterado para: " + novoTipo);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}



//package br.ufrn.imd.visao;
//
//import javax.swing.BoxLayout;
////import javax.swing.JButton;
//
//// import br.ufrn.imd.modelo.Diretorio;
//
//import javax.swing.JFrame;
////import javax.swing.JLabel;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JPanel;
////import javax.swing.JPasswordField;
////import javax.swing.JTextField;
//
//import br.ufrn.imd.controle.GerenciadorControle;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class TelaMusicPlayer extends JFrame implements ActionListener {
//    private GerenciadorControle controller;
//
//    public TelaMusicPlayer() {
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
//        JMenuBar barra = new JMenuBar();
//
//        JMenu opcoes = new JMenu("Opções");
//        JMenuItem sairMenuItem = new JMenuItem("Sair");
//
//        sairMenuItem.addActionListener(action -> {
//            this.sair();
//        });
//
//        JMenuItem alterarNome = new JMenuItem("Alterar Nome");
//        JMenuItem alterarSenha = new JMenuItem("Alterar Senha");
//        JMenuItem alterarTipo = new JMenuItem("Alterar Tipo de Usuário");
//
//        alterarNome.addActionListener(action -> {
//            this.alterarNome();
//        });
//
//        alterarSenha.addActionListener(action -> {
//            this.alterarSenha();
//        });
//
//        alterarTipo.addActionListener(action -> {
//            this.alterarTipo();
//        });
//
//        setJMenuBar(barra);
//
//        barra.add(opcoes);
//        barra.add(sairMenuItem);
//
//        opcoes.add(alterarNome);
//        opcoes.add(alterarSenha);
//        opcoes.add(alterarTipo);
//
//        alterarNome.addActionListener(action -> {
//
//        });
//
//        // panel.add(loginLabel);
//
//        // add(panel);
//        setVisible(true);
//    }
//
//    public void sair() {
//        controller.fazerLogout();
//        new TelaLogin();
//        dispose();
//    }
//
//    public void alterarNome() {
//
//    }
//
//    public void alterarSenha() {
//
//    }
//
//    public void alterarTipo() {
//        
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//    }
//}