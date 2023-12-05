package br.ufrn.imd.controle;

import java.util.ArrayList;

import br.ufrn.imd.dao.UsuarioDAO;
import br.ufrn.imd.modelo.Musica;
import br.ufrn.imd.modelo.Playlist;
import br.ufrn.imd.modelo.UsuarioComum;
import br.ufrn.imd.modelo.UsuarioVIP;

/*
 * A ideia dele é a seguinte, no arquivo de controller (no seu caso, o
 * ProdutoController),
 * você teria uma função para cada método (inserir, novo registro, atualizar,
 * listar, editar,
 * ou outra ações relacionadas).
 */

public class UsuarioControle {

    public boolean cadastrarUsuarioNoBanco(String nome, String login, String senha, String tipo) {
        UsuarioComum user;
        if (tipo.equals("V")) {
            user = new UsuarioVIP(nome, login, senha);
        } else if (tipo.equals("C")) {
            user = new UsuarioComum(nome, login, senha);
        } else {
            return false;
        }

        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.cadastrarUsuario(user);
    }

    public ArrayList<UsuarioComum> listarUsuarios() {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.exibirUsuarios();
    }

    public boolean editarUsuario(String login, String atributoNovo, String tipoAtributo) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.editarUsuario(login, atributoNovo, tipoAtributo);
    }

    public boolean removerUsuario(String login, String senha) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.removerUsuario(login, senha);
    }

    public UsuarioComum fazerLogin(String login, String senha) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.fazerLoginUsuario(login, senha);
    }

    public boolean cadastrarPlaylist(String nomePlaylist, UsuarioVIP usuarioLogado) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        
        return playlist_controle.cadastrarPlaylist(nomePlaylist);
    }

    public ArrayList<Playlist> listarPlaylists(UsuarioVIP usuarioLogado) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        return playlist_controle.listarPlaylist();
    }

    public boolean adicionarMusica(UsuarioVIP usuarioLogado, String nomePlaylist, String nomeMusica, String artista) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        MusicaControle musica_controle = new MusicaControle();
        Musica musica = musica_controle.buscarMusicaNoBanco(nomeMusica, artista);
        return playlist_controle.adicionarMusica(nomePlaylist, musica);
    }

    public boolean removerMusica(UsuarioVIP usuarioLogado, String nomePlaylist, String nomeMusica, String artista) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        MusicaControle musica_controle = new MusicaControle();
        Musica musica = musica_controle.buscarMusicaNoBanco(nomeMusica, artista);
        return playlist_controle.removerMusica(nomePlaylist, musica);
    }

    public boolean editarPlaylist(UsuarioVIP usuarioLogado, String nomePlaylist, String nomeNovo) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        return playlist_controle.editarPlaylistNoBanco(nomePlaylist, nomeNovo);
    }

    public boolean removerPlaylist(UsuarioVIP usuarioLogado, String nomePlaylist) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        return playlist_controle.removerPlaylistDoBanco(nomePlaylist);
    }
}
