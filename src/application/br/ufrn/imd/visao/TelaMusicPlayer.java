package application.br.ufrn.imd.visao;

import application.br.ufrn.imd.controle.GerenciadorControle;
import application.br.ufrn.imd.controle.UsuarioControle;
import application.br.ufrn.imd.modelo.UsuarioComum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class TelaMusicPlayer extends Application {

    private GerenciadorControle controller;

    public TelaMusicPlayer(UsuarioComum usuario) {
        controller = new GerenciadorControle();
        controller.setUsuarioLogado(usuario);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Music Player");
        primaryStage.setResizable(false);

        VBox panel = new VBox(10);

        MenuBar barra = new MenuBar();

        Menu opcoes = new Menu("Opções");
        MenuItem sairMenuItem = new MenuItem("Sair");

        sairMenuItem.setOnAction(action -> sair(primaryStage));

        MenuItem alterarNome = new MenuItem("Alterar Nome");
        MenuItem alterarSenha = new MenuItem("Alterar Senha");
        MenuItem alterarTipo = new MenuItem("Alterar Tipo de Usuário");

        ListView<String> listaMusicasPlaylist = new ListView<>();
        ListView<String> listaDiretorio = new ListView<>();
        ListView<String> listaPlaylist = new ListView<>();

        Button adicionarMusica = new Button("Add File");
        Button adicionarDirectory = new Button("Add Directory");
        Button adicionarPlaylist = new Button("Add Playlist");

        alterarNome.setOnAction(action -> alterarNome(primaryStage));
        alterarSenha.setOnAction(action -> alterarSenha(primaryStage));
        alterarTipo.setOnAction(action -> alterarTipo(primaryStage));

        adicionarMusica.setOnAction(action -> adicionarMusica(primaryStage));
        adicionarDirectory.setOnAction(action -> adicionarDiretorio(primaryStage));
        adicionarPlaylist.setOnAction(action -> adicionarPlaylist(primaryStage));

        opcoes.getItems().addAll(alterarNome, alterarSenha, alterarTipo);
        barra.getMenus().addAll(opcoes);

        panel.getChildren().addAll(barra, listaMusicasPlaylist, listaDiretorio, listaPlaylist, adicionarMusica,
                adicionarDirectory, adicionarPlaylist);

        Scene scene = new Scene(panel, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sair(Stage primaryStage) {
        controller.fazerLogout();
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.start(primaryStage);
    }

    private void alterarNome(Stage primaryStage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Alterar Nome");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite o novo nome:");

        dialog.showAndWait().ifPresent(novoNome -> {
            UsuarioControle usuario_controle = new UsuarioControle();
            usuario_controle.editarUsuario(this.controller.getUsuarioLogado(), novoNome, "N");
            exibirAlerta("Sucesso", "Nome alterado com sucesso para: " + novoNome, Alert.AlertType.INFORMATION);
        });
    }

    private void alterarSenha(Stage primaryStage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Alterar Senha");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite a nova senha:");

        dialog.showAndWait().ifPresent(novaSenha -> {
            UsuarioControle usuario_controle = new UsuarioControle();
            usuario_controle.editarUsuario(this.controller.getUsuarioLogado(), novaSenha, "S");
            exibirAlerta("Sucesso", "Senha alterada com sucesso.", Alert.AlertType.INFORMATION);
        });
    }

    private void alterarTipo(Stage primaryStage) {
        Optional<ButtonType> resultado = exibirAlerta("Assinatura", "Você deseja mudar a assinatura?",
                Alert.AlertType.CONFIRMATION);

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            UsuarioControle usuario_controle = new UsuarioControle();
            usuario_controle.editarAssinatura(this.controller.getUsuarioLogado());
            exibirAlerta("Sucesso", "Assinatura alterada com sucesso.", Alert.AlertType.INFORMATION);
        }
    }

    private void adicionarMusica(Stage primaryStage) {
        FileChooser selecionarMusica = new FileChooser();
        selecionarMusica.setTitle("Selecione uma música");
        File arquivoMusica = selecionarMusica.showOpenDialog(primaryStage);

        if (arquivoMusica != null) {
            System.out.println("CAMINHO DO ARQUIVO ABERTO = " + arquivoMusica.getAbsolutePath());
            // Lógica para reproduzir a música
        } else {
            System.out.println("Nenhum arquivo selecionado");
        }
    }

    private void adicionarDiretorio(Stage primaryStage) {
        // Lógica para adicionar diretório
    }

    private void adicionarPlaylist(Stage primaryStage) {
        // Lógica para adicionar playlist
    }

    private Optional<ButtonType> exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        return alert.showAndWait();
    }
}
