package application.br.ufrn.imd.controle;

import java.io.File;
import java.util.ArrayList;

import application.br.ufrn.imd.modelo.Diretorio;
import application.br.ufrn.imd.modelo.Musica;
import application.br.ufrn.imd.modelo.Musica;
import application.br.ufrn.imd.modelo.Diretorio;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.util.Duration;

public class MusicPlayerControle {

	@FXML
	private Button botaoAdicionarDiretorio;

	@FXML
	private Button botaoAdicionarMusica;

	@FXML
	private Button botaoAdicionarPlaylist;

	@FXML
	private Button botaoPassarMusica;

	@FXML
	private Button botaoPlay;

	@FXML
	private Button botaoVoltarMusica;

	@FXML
	private ListView<?> listaMusicasPlaylist;

	@FXML
	private ListView<?> listaPlaylists;

	@FXML
	private ListView<String> listaNomesMusicas;

	@FXML
	private ProgressBar progressoMusica;

	@FXML
	private Label nomeUsuario;

	private GerenciadorControle controller;

	private Media media;
	private MediaPlayer mediaPlayer;
	private boolean runing;

	public void setGerenciadorControle(GerenciadorControle controller) {
		this.controller = controller;
	}

	@FXML
	private void initialize(GerenciadorControle controller) {
	}

	public void setNomeUsuario() {
		nomeUsuario.setText(controller.getUsuarioLogado().getNome());
	}

	@FXML
	void adicionarDiretorio(ActionEvent event) {
		Stage primaryStage = new Stage();
		DirectoryChooser selecionarPasta = new DirectoryChooser();
		selecionarPasta.setTitle("Selecione uma pasta com músicas");
		File pastaMusica = selecionarPasta.showDialog(primaryStage);

		if (pastaMusica != null) {
			DiretorioControle diretorio_controle = new DiretorioControle();
			System.out.println(pastaMusica.getPath());
			diretorio_controle.cadastrarDiretorioNoBanco(pastaMusica.getPath());
			exibirMusicas(diretorio_controle);
		} else {
			exibirAlerta("Erro", "Nenhuma pasta selecionada", Alert.AlertType.ERROR);
		}
	}

	void exibirMusicas(DiretorioControle diretorio_controle) {
		ObservableList<String> items = listaNomesMusicas.getItems();
		ArrayList<Musica> listaMusicas = new ArrayList<>();

		for (Diretorio diretorio : diretorio_controle.listarDiretoriosDoBanco()) {
			for (Musica musica : diretorio.getMusicas()) {
				System.out.println("Olá");
				items.add(musica.getNome());
				listaMusicas.add(musica);
			}
		}

		listaNomesMusicas.setItems(items);

		controller.setListaMusicas(listaMusicas);
	}

	@FXML
	void adicionarMusica(ActionEvent event) {
		Stage primaryStage = new Stage();
		FileChooser selecionarMusica = new FileChooser();
		selecionarMusica.setTitle("Selecione uma música");
		File arquivoMusica = selecionarMusica.showOpenDialog(primaryStage);

		if (arquivoMusica != null) {
			media = new Media(arquivoMusica.toURI().toString());
			this.mediaPlayer = new MediaPlayer(media);
			this.mediaPlayer.play();
			this.runing = true;
			carregarProgresso();
			// Lógica para reproduzir a música
		} else {
			System.out.println("Nenhum arquivo selecionado");
		}
	}

	@FXML
	void adicionarPlaylist(ActionEvent event) {

	}

	@FXML
	void pausarTocarMusica(ActionEvent event) {
		if (runing) {
			this.botaoPlay.setText("Ⅱ");
			this.mediaPlayer.pause();
			runing = false;
		} else {
			this.botaoPlay.setText("►");
			this.mediaPlayer.play();
			runing = true;
		}
	}

	@FXML
	void pularMusica(ActionEvent event) {

	}

	@FXML
	void voltarMusica(ActionEvent event) {

	}

	void carregarProgresso() {
		mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
			@Override
			public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
				double progress;
				try {
					progress = (mediaPlayer.getCurrentTime().toSeconds() / mediaPlayer.getTotalDuration().toSeconds());
					progressoMusica.setProgress(progress);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
		Alert alerta = new Alert(tipo);
		alerta.setTitle(titulo);
		alerta.setContentText(mensagem);
		alerta.show();
	}

}