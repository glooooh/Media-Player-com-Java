package application.br.ufrn.imd.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;

public class MusicPlayerControle {

	@FXML
	private MenuBar menuBar;

	@FXML
	private MenuItem alterarNomeMenuItem;

	@FXML
	private MenuItem alterarSenhaMenuItem;

	@FXML
	private MenuItem alterarAssinaturaMenuItem;

	@FXML
	private MenuItem sairMenuItem;

	@FXML
	private TitledPane diretoriosTitledPane;

	@FXML
	private TitledPane playlistsTitledPane;

	@FXML
	private TitledPane musicasTitledPane;

	@FXML
	private Button adicionarMusicaButton;

	@FXML
	private Button adicionarDiretorioButton;

	@FXML
	private Button adicionarPlaylistButton;

	@FXML
	private void initialize() {
		// Ações de inicialização, se necessário
	}

	@FXML
	private void handleAlterarNome(ActionEvent event) {
		// Lógica para manipular o evento do MenuItem 'Alterar Nome'
	}

	@FXML
	private void handleAlterarSenha(ActionEvent event) {
		// Lógica para manipular o evento do MenuItem 'Alterar Senha'
	}

	@FXML
	private void handleAlterarAssinatura(ActionEvent event) {
		// Lógica para manipular o evento do MenuItem 'Alterar Assinatura'
	}

	@FXML
	private void handleSair(ActionEvent event) {
		// Lógica para manipular o evento do MenuItem 'Sair'
	}

	@FXML
	private void handleAdicionarMusica(ActionEvent event) {
		// Lógica para manipular o evento do Button 'Adicionar Música'
	}

	@FXML
	private void handleAdicionarDiretorio(ActionEvent event) {
		// Lógica para manipular o evento do Button 'Adicionar Diretório'
	}

	@FXML
	private void handleAdicionarPlaylist(ActionEvent event) {
		// Lógica para manipular o evento do Button 'Adicionar Playlist'
	}
}
