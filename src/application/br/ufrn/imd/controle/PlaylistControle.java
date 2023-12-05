package application.br.ufrn.imd.controle;

import application.br.ufrn.imd.modelo.Musica;
import application.br.ufrn.imd.modelo.Playlist;
import application.br.ufrn.imd.modelo.UsuarioVIP;
import application.br.ufrn.imd.dao.PlaylistDAO;

import java.util.ArrayList;

public class PlaylistControle {
    UsuarioVIP usuarioLogado;

    public PlaylistControle(UsuarioVIP usuario) {
        this.usuarioLogado = usuario;
    }

    public boolean cadastrarPlaylist(String nomePlaylist) {
        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        Playlist playlist = new Playlist(nomePlaylist, this.usuarioLogado);

        return playlistDao.cadastrarPlaylist(playlist);
    }

    public ArrayList<Playlist> listarPlaylist() {
        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        return playlistDao.exibirPlaylists();
    }

    public Playlist buscarPlaylistNoBanco(String nomePlaylist) {
        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        Playlist playlist = playlistDao.buscarPlaylist(nomePlaylist, this.usuarioLogado);
        return playlist;
    }

    public boolean adicionarMusica(String nomePlaylist, Musica musica) {
        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        Playlist playlist = buscarPlaylistNoBanco(nomePlaylist);

        if (playlist == null) {
            return false;
        }

        playlistDao.adicionarMusica(playlist, musica);

        return true;
    }

    public boolean removerMusica(String nomePlaylist, Musica musica) {
        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        Playlist playlist = buscarPlaylistNoBanco(nomePlaylist);

        if (playlist == null) {
            return false;
        }

        playlistDao.removerMusica(playlist, musica);

        return true;
    }

    public boolean editarPlaylistNoBanco(String nomePlaylist, String nomeNovo) {
        Playlist playlist = buscarPlaylistNoBanco(nomePlaylist);

        if (playlist == null) {
            return false;
        }

        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        return playlistDao.editarPlaylist(playlist, nomeNovo);
    }

    public boolean removerPlaylistDoBanco(String nomePlaylist) {
        Playlist playlist = buscarPlaylistNoBanco(nomePlaylist);

        if (playlist == null) {
            return false;
        }

        PlaylistDAO playlistDao = new PlaylistDAO(this.usuarioLogado);
        return playlistDao.removerPlaylist(playlist);
    }
}
