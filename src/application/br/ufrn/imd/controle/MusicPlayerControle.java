package application.br.ufrn.imd.controle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import application.br.ufrn.imd.modelo.Diretorio;
import application.br.ufrn.imd.modelo.Musica;
import application.br.ufrn.imd.modelo.Playlist;
import application.br.ufrn.imd.modelo.UsuarioVIP;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import javafx.util.Duration;

public class MusicPlayerControle {

	@FXML
	private MenuItem alterarNomeItem;

	@FXML
	private MenuItem alterarSenhaItem;

	@FXML
	private MenuItem alterarAssinaturaItem;

	@FXML
	private Button sairItem;

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
	private Button botaoAdicionarMusicaPlaylist;

	@FXML
	private ListView<String> listaMusicasPlaylist;

	@FXML
	private ListView<String> listaPlaylists;

	@FXML
	private ListView<String> listaNomesMusicas;

	@FXML
	private ProgressBar progressoMusica;

	@FXML
	private Label nomeUsuario;

	@FXML
	private Label tipoUser;

	private GerenciadorControle controller;

	private Media media;
	private MediaPlayer mediaPlayer;
	private boolean running;
	private int musicaSelecionada;
	private int playlistSelecionada;

	@FXML
	private void initialize(GerenciadorControle controller) {
	}

	public void setGerenciadorControle(GerenciadorControle controller) {
		this.controller = controller;
		DiretorioControle diretorioControle = new DiretorioControle();
		ArrayList<Diretorio> diretorios = diretorioControle.listarDiretoriosDoBanco();

		alterarTipoUsuario();

		if (!diretorios.isEmpty()) {
			ObservableList<String> items = listaNomesMusicas.getItems();
			ArrayList<Musica> listaMusicas = new ArrayList<>();

			for (Diretorio diretorio : diretorios) {
				for (Musica musica : diretorio.getMusicas()) {
					items.add(musica.getNome());
					listaMusicas.add(musica);
				}
			}

			listaNomesMusicas.setItems(items);

			controller.setListaMusicas(listaMusicas);
			diretorioAdicionado();
		}

		carregarPlaylists();

	}

	private void carregarPlaylists() {
		if (controller.getUsuarioLogado().ehVIP()) {
			PlaylistControle playlistControle = new PlaylistControle((UsuarioVIP) controller.getUsuarioLogado());
			ArrayList<Playlist> playlists = playlistControle.listarPlaylist();

			if (!playlists.isEmpty()) {
				ObservableList<String> items = listaPlaylists.getItems();
				ArrayList<Playlist> playlistsUsuario = new ArrayList<>();

				for (Playlist playlist : playlists) {
					items.add(playlist.getNome());
					playlistsUsuario.add(playlist);
				}

				listaPlaylists.setItems(items);

				controller.setPlaylistsUsuario(playlists);
				listaPlaylistsAdicionada();
			}
		}
	}

	public void carregarMusicasPlaylist(Playlist playlist) {
		if (this.controller.getUsuarioLogado().ehVIP()) {

			ObservableList<String> items = listaMusicasPlaylist.getItems();
			ArrayList<Musica> musicasPlaylist = new ArrayList<>();

			listaMusicasPlaylist.getItems().clear();

			for (Musica musica : playlist.getMusicas()) {
				items.add(musica.getNome());
				musicasPlaylist.add(musica);
			}

			for (int i = 0; i < controller.getPlaylistsUsuario().size(); i++) {
				if (playlist.getNome().equals(controller.getPlaylistsUsuario().get(i).getNome())) {
					controller.getPlaylistsUsuario().get(i).setMusicas(playlist.getMusicas());
				}
			}

			listaMusicasPlaylist.setItems(items);

			controller.setListaMusicasPlaylist(musicasPlaylist);

			listaMusicasPlaylistSelecionada();
		}
	}

	@FXML
	public void alterarNome(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Alterar nome do usuário");
		dialog.setHeaderText(null);
		dialog.setContentText("Digite o novo nome de usuário:");

		dialog.showAndWait().ifPresent(nomeUsuario -> {
			if (controller.editarUsuario(nomeUsuario, "N")) {
				exibirAlerta("Sucesso", "Nome do usuário alterado com sucesso para: " + nomeUsuario,
						Alert.AlertType.INFORMATION);
				// TODO: Alterar essa gambiarra
				controller.getUsuarioLogado().setNome(nomeUsuario);
				setNomeUsuario();
			} else {
				exibirAlerta("Erro", "Preencha os campos devidamente", Alert.AlertType.ERROR);
			}
		});
	}

	@FXML
	public void alterarSenha(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Alterar senha do usuário");
		dialog.setHeaderText(null);
		dialog.setContentText("Digite a nova senha do usuário:");

		dialog.showAndWait().ifPresent(senhaUsuario -> {
			if (controller.editarUsuario(senhaUsuario, "S")) {
				exibirAlerta("Sucesso", "Senha do usuário alterada com sucesso",
						Alert.AlertType.INFORMATION);
			} else {
				exibirAlerta("Erro", "Preencha os campos devidamente", Alert.AlertType.ERROR);
			}
		});
	}

	@FXML
	public void alterarAssinatura(ActionEvent event) {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Alterar assinatura do usuário");
		dialog.setHeaderText(null);
		if (this.controller.getUsuarioLogado() instanceof UsuarioVIP) {
			dialog.setContentText("Tem certeza de que deseja se tornar um usuário comum?");
		} else {
			dialog.setContentText("Tem certeza de que deseja se tornar um usuário VIP?");
		}

		ButtonType simButtonType = new ButtonType("Sim", ButtonData.OK_DONE);
		ButtonType naoButtonType = new ButtonType("Não", ButtonData.CANCEL_CLOSE);

		dialog.getDialogPane().getButtonTypes().addAll(simButtonType, naoButtonType);

		dialog.showAndWait().ifPresent(response -> {
			if (response == simButtonType) {
				if (controller.editarAssinatura()) {
					alterarTipoUsuario();
					exibirAlerta("Sucesso", "Assinatura do usuário alterada com sucesso",
							Alert.AlertType.INFORMATION);
				}
			}
		});
	}

	public void alterarTipoUsuario() {
		if (this.controller.getUsuarioLogado().ehVIP()) {
			tipoUser.setText("VIP");
			botaoAdicionarPlaylist.setDisable(false);
			botaoAdicionarMusicaPlaylist.setDisable(false);
			listaPlaylists.setDisable(false);
			listaMusicasPlaylist.setDisable(false);
		} else {
			tipoUser.setText("Comum");
			botaoAdicionarPlaylist.setDisable(true);
			botaoAdicionarMusicaPlaylist.setDisable(true);
			listaPlaylists.setDisable(true);
			listaMusicasPlaylist.setDisable(true);
			// ObservableList<String> itemsPlaylists = FXCollections.observableArrayList();
			// listaPlaylists.setItems(itemsPlaylists);

			// ObservableList<String> itemsMusicasPlaylist =
			// FXCollections.observableArrayList();
			// listaMusicasPlaylist.setItems(itemsMusicasPlaylist);
		}
	}

	@FXML
	public void processarLogOut(ActionEvent event) {
		Stage musicPlayerStage = (Stage) sairItem.getScene().getWindow();
		musicPlayerStage.close();

		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/application/br/ufrn/imd/recursos/TelaLogin.fxml"));
			Parent root = loader.load();

			Stage loginStage = new Stage();
			loginStage.setTitle("Tela de Login");
			loginStage.setScene(new Scene(root));
			loginStage.show();

			mediaPlayer.stop();

			controller.fazerLogout();

			musicPlayerStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void diretorioAdicionado() {
		listaNomesMusicas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				int musicaSelecionadaIndex = listaNomesMusicas.getSelectionModel().getSelectedIndex();

				if (musicaSelecionadaIndex >= 0 && musicaSelecionadaIndex < controller.getListaMusicas().size()) {
					musicaSelecionada = musicaSelecionadaIndex;
					tocarMusica(controller.getListaMusicas().get(musicaSelecionada).getCaminho());

					if (!listaMusicasPlaylist.getSelectionModel().isEmpty()) {
						listaMusicasPlaylist.getSelectionModel().clearSelection();
					}
				}
			}
		});
	}

	private void listaMusicasPlaylistSelecionada() {
		listaMusicasPlaylist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				int musicaSelecionadaIndex = listaMusicasPlaylist.getSelectionModel().getSelectedIndex();

				if (playlistSelecionada >= 0 && playlistSelecionada < controller.getPlaylistsUsuario().size()
						&& musicaSelecionadaIndex >= 0
						&& musicaSelecionadaIndex < controller.getPlaylistsUsuario().get(playlistSelecionada)
								.getMusicas().size()) {
					musicaSelecionada = musicaSelecionadaIndex;
					tocarMusica(controller.getPlaylistsUsuario().get(playlistSelecionada).getMusicas()
							.get(musicaSelecionada).getCaminho());

					if (!listaNomesMusicas.getSelectionModel().isEmpty()) {
						listaNomesMusicas.getSelectionModel().clearSelection();
					}
				}
			}
		});
	}

	private void listaPlaylistsAdicionada() {
		listaPlaylists.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				playlistSelecionada = listaPlaylists.getSelectionModel().getSelectedIndex();
				controller.setPlaylistSelecionada(controller.getPlaylistsUsuario().get(playlistSelecionada));
				exibirMusicasPlaylist(controller.getPlaylistsUsuario().get(playlistSelecionada));
			}

		});
	}

	private void tocarMusica(String caminho) {
		File arquivoMusica = new File(caminho);

		if (running) {
			this.mediaPlayer.stop();
		}

		if (arquivoMusica != null) {
			media = new Media(arquivoMusica.toURI().toString());
			this.mediaPlayer = new MediaPlayer(media);
			this.mediaPlayer.play();
			this.running = true;
			this.botaoPlay.setText("Ⅱ");
			carregarProgresso();
		}
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
			diretorio_controle.cadastrarDiretorioNoBanco(pastaMusica.getPath());
			exibirMusicas(diretorio_controle);
			diretorioAdicionado();
		} else {
			exibirAlerta("Erro", "Nenhuma pasta selecionada", Alert.AlertType.ERROR);
		}
	}

	void exibirMusicasPlaylist(Playlist playlist) {
		ObservableList<String> items = listaMusicasPlaylist.getItems();
		ArrayList<Musica> musicasPlaylist = new ArrayList<>();

		listaMusicasPlaylist.getItems().clear(); // limpando

		for (Musica musica : playlist.getMusicas()) {
			items.add(musica.getNome());
			musicasPlaylist.add(musica);
		}

		listaMusicasPlaylist.setItems(items);

		controller.setListaMusicasPlaylist(musicasPlaylist);

		listaMusicasPlaylistSelecionada();
	}

	void exibirMusicas(DiretorioControle diretorio_controle) {
		ObservableList<String> items = listaNomesMusicas.getItems();
		ArrayList<Musica> listaMusicas = new ArrayList<>();

		listaNomesMusicas.getItems().clear(); // limpando

		for (Diretorio diretorio : diretorio_controle.listarDiretoriosDoBanco()) {
			for (Musica musica : diretorio.getMusicas()) {
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

		File dadosMusica = selecionarMusica.showOpenDialog(primaryStage);

		MusicaControle novaMusica = new MusicaControle();

		novaMusica.cadastrarMusicasNoBanco(dadosMusica.getName().substring(0, dadosMusica.getName().length() - 4),
				dadosMusica.toString());

		tocarMusica(dadosMusica.toString());
	}

	@FXML
	void adicionarPlaylist(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Criar Playlist");
		dialog.setHeaderText(null);
		dialog.setContentText("Digite o nome da playlist:");

		dialog.showAndWait().ifPresent(nomePlaylist -> {
			UsuarioControle usuario_controle = new UsuarioControle();
			usuario_controle.cadastrarPlaylist(nomePlaylist, (UsuarioVIP) controller.getUsuarioLogado());
			exibirPlaylists(usuario_controle);
			listaPlaylistsAdicionada();
		});
	}

	void exibirPlaylists(UsuarioControle usuario_controle) {
		ObservableList<String> items = listaPlaylists.getItems();
		ArrayList<Playlist> playlistsUsuario = new ArrayList<>();

		listaPlaylists.getItems().clear(); // limpando

		for (Playlist playlist : usuario_controle.listarPlaylists((UsuarioVIP) controller.getUsuarioLogado())) {
			items.add(playlist.getNome());
			playlistsUsuario.add(playlist);
		}

		listaPlaylists.setItems(items);

		controller.setPlaylistsUsuario(playlistsUsuario);
	}

	@FXML
	void pausarTocarMusica(ActionEvent event) {
		if (running) {
			this.botaoPlay.setText("►");
			this.mediaPlayer.pause();
			running = false;
		} else {
			this.botaoPlay.setText("Ⅱ");
			this.mediaPlayer.play();
			running = true;
		}
	}

	@FXML
	void pularMusica(ActionEvent event) {
		if (controller != null) {
			if (!listaNomesMusicas.getSelectionModel().isEmpty()) {
				int tamanhoLista = controller.getListaMusicas().size();
				if (tamanhoLista > 0) {
					musicaSelecionada = (musicaSelecionada + 1) % tamanhoLista;
					listaNomesMusicas.getSelectionModel().select(musicaSelecionada);
					tocarMusica(controller.getListaMusicas().get(musicaSelecionada).getCaminho());
				}
			} else if (!listaMusicasPlaylist.getSelectionModel().isEmpty()) {
				int tamanhoLista = controller.getListaMusicasPlaylist().size();
				if (tamanhoLista > 0) {
					musicaSelecionada = (musicaSelecionada + 1) % tamanhoLista;
					listaMusicasPlaylist.getSelectionModel().select(musicaSelecionada);
					tocarMusica(controller.getListaMusicasPlaylist().get(musicaSelecionada).getCaminho());
				}
			}
		}
	}

	@FXML
	void voltarMusica(ActionEvent event) {
		if (controller != null) {
			if (!listaNomesMusicas.getSelectionModel().isEmpty()) {
				int tamanhoLista = controller.getListaMusicas().size();
				if (tamanhoLista > 0) {
					musicaSelecionada = (musicaSelecionada - 1 + tamanhoLista) % tamanhoLista;
					listaNomesMusicas.getSelectionModel().select(musicaSelecionada);
					tocarMusica(controller.getListaMusicas().get(musicaSelecionada).getCaminho());
				}
			} else if (!listaMusicasPlaylist.getSelectionModel().isEmpty()) {
				int tamanhoLista = controller.getListaMusicasPlaylist().size();
				if (tamanhoLista > 0) {
					musicaSelecionada = (musicaSelecionada - 1 + tamanhoLista) % tamanhoLista;
					listaMusicasPlaylist.getSelectionModel().select(musicaSelecionada);
					tocarMusica(controller.getListaMusicasPlaylist().get(musicaSelecionada).getCaminho());
				}
			}

		}
	}

	// private void avancarMusica(int passo) {
	// ObservableList<String> listaSelecionada = getListaMusicasTocando();

	// if (listaSelecionada != null) {
	// int tamanhoLista = listaSelecionada.size();
	// if (tamanhoLista > 0) {
	// musicaSelecionada = (musicaSelecionada + passo + tamanhoLista) %
	// tamanhoLista;
	// listaSelecionada.getSelectionModel().select(musicaSelecionada);
	// tocarMusica(listaSelecionada.get(musicaSelecionada));
	// }
	// }
	// }

	// private ObservableList<String> getListaMusicasTocando() {
	// if (controller != null) {
	// ObservableList<String> listaSelecionada = null;

	// if (!listaNomesMusicas.getSelectionModel().isEmpty()) {
	// listaSelecionada = listaNomesMusicas.getItems();
	// } else if (!listaMusicasPlaylist.getSelectionModel().isEmpty()) {
	// listaSelecionada = listaMusicasPlaylist.getItems();
	// }

	// return listaSelecionada;
	// }
	// return null;
	// }

	@FXML
	void adicionarMusicaPlaylist(ActionEvent event) {
		// Verifica se o usuário é VIP
		if (this.controller.getUsuarioLogado() instanceof UsuarioVIP) {
			String nomePlaylist = listaPlaylists.getSelectionModel().getSelectedItem();
			String nomeMusica = listaNomesMusicas.getSelectionModel().getSelectedItem();

			int indexMusicaSelecionada = listaNomesMusicas.getSelectionModel().getSelectedIndex();
			String caminho = controller.getListaMusicas().get(indexMusicaSelecionada).getCaminho();

			UsuarioControle usuario_controle = new UsuarioControle();
			usuario_controle.adicionarMusica((UsuarioVIP) controller.getUsuarioLogado(), nomePlaylist, nomeMusica,
					caminho);

			PlaylistControle playlistControle = new PlaylistControle((UsuarioVIP) controller.getUsuarioLogado());

			carregarMusicasPlaylist(playlistControle.buscarPlaylistNoBanco(nomePlaylist));
		} else {
			exibirAlerta("Erro", "Você não é um usuário VIP.", Alert.AlertType.ERROR);
		}

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