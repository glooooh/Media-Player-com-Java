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

/**
 * Classe que representa a interface gráfica do Music Player em JavaFX.
 */
public class TelaMusicPlayer extends Application {

    private GerenciadorControle controller;

    /**
     * Construtor da classe.
     *
     * @param usuario O usuário associado à tela.
     */
    public TelaMusicPlayer(UsuarioComum usuario) {
        controller = new GerenciadorControle();
        controller.setUsuarioLogado(usuario);
    }

    /**
     * Método principal que inicia a interface gráfica da tela do Music Player.
     *
     * @param primaryStage O palco principal (Stage) da aplicação.
     */
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

    // Métodos de ação...

    /**
     * Método para exibir um alerta.
     *
     * @param titulo Título do alerta.
     * @param mensagem Mensagem do alerta.
     * @param tipo Tipo do alerta.
     * @return Resultado do alerta.
     */
    private Optional<ButtonType> exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        return alert.showAndWait();
    }
}