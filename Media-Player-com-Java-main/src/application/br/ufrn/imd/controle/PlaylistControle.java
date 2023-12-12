package application.br.ufrn.imd.controle;

import application.br.ufrn.imd.modelo.Musica;
import application.br.ufrn.imd.modelo.Playlist;
import application.br.ufrn.imd.modelo.UsuarioVIP;
import application.br.ufrn.imd.dao.PlaylistDAO;

import java.util.ArrayList;

/**
 * Classe responsável por controlar as operações relacionadas a playlists,
 * incluindo cadastro, listagem,
 * adição e remoção de músicas, edição e remoção de playlists.
 */
public class PlaylistControle {

    /**
     * Usuário VIP logado associado a este controle.
     */
    private UsuarioVIP usuarioLogado;

    /**
     * Construtor da classe PlaylistControle.
     *
     * @param usuario Usuário VIP logado que será associado a este controle.
     */
    public PlaylistControle(UsuarioVIP usuario) {
        this.usuarioLogado = usuario;
    }

    /**
     * Cadastra uma nova playlist associada ao usuário logado.
     *
     * @param nomePlaylist Nome da nova playlist a ser cadastrada.
     * @return true se o cadastro for bem-sucedido, false caso contrário.
     */
    public boolean cadastrarPlaylist(String nomePlaylist) {
        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        Playlist playlist = new Playlist(nomePlaylist, this.usuarioLogado);

        return playlistDao.cadastrarPlaylist(playlist);
    }

    /**
     * Lista todas as playlists associadas ao usuário logado.
     *
     * @return ArrayList contendo todas as playlists do usuário logado.
     */
    public ArrayList<Playlist> listarPlaylist() {
        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        return playlistDao.exibirPlaylists();
    }

    /**
     * Busca uma playlist no banco de dados do usuário logado pelo nome.
     *
     * @param nomePlaylist Nome da playlist a ser buscada.
     * @return Playlist encontrada ou null se não existir.
     */
    public Playlist buscarPlaylistNoBanco(String nomePlaylist) {
        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        Playlist playlist = playlistDao.buscarPlaylist(nomePlaylist, this.usuarioLogado);
        return playlist;
    }

    /**
     * Adiciona uma música à playlist do usuário logado.
     *
     * @param nomePlaylist Nome da playlist à qual a música será adicionada.
     * @param musica       Música a ser adicionada à playlist.
     * @return true se a adição for bem-sucedida, false caso contrário.
     */
    public boolean adicionarMusica(String nomePlaylist, Musica musica) {
        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        Playlist playlist = buscarPlaylistNoBanco(nomePlaylist);

        if (playlist == null) {
            return false;
        }

        playlistDao.adicionarMusica(playlist, musica);

        return true;
    }

    /**
     * Remove uma música da playlist do usuário logado.
     *
     * @param nomePlaylist Nome da playlist da qual a música será removida.
     * @param musica       Música a ser removida da playlist.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */
    public boolean removerMusica(String nomePlaylist, Musica musica) {
        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        Playlist playlist = buscarPlaylistNoBanco(nomePlaylist);

        if (playlist == null) {
            return false;
        }

        playlistDao.removerMusica(playlist, musica);

        return true;
    }

    /**
     * Edita o nome de uma playlist do usuário logado.
     *
     * @param nomePlaylist Nome atual da playlist a ser editada.
     * @param nomeNovo     Novo nome para a playlist.
     * @return true se a edição for bem-sucedida, false caso contrário.
     */
    public boolean editarPlaylistNoBanco(String nomePlaylist, String nomeNovo) {
        Playlist playlist = buscarPlaylistNoBanco(nomePlaylist);

        if (playlist == null) {
            return false;
        }

        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        return playlistDao.editarPlaylist(playlist, nomeNovo);
    }

    /**
     * Remove uma playlist do usuário logado.
     *
     * @param nomePlaylist Nome da playlist a ser removida.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */
    public boolean removerPlaylistDoBanco(String nomePlaylist) {
        Playlist playlist = buscarPlaylistNoBanco(nomePlaylist);

        if (playlist == null) {
            return false;
        }

        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        return playlistDao.removerPlaylist(playlist);
    }
}