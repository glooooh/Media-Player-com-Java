package application.br.ufrn.imd.controle;

import java.util.ArrayList;

import application.br.ufrn.imd.dao.UsuarioDAO;
import application.br.ufrn.imd.modelo.Musica;
import application.br.ufrn.imd.modelo.Playlist;
import application.br.ufrn.imd.modelo.UsuarioComum;
import application.br.ufrn.imd.modelo.UsuarioVIP;

/**
 * Controlador responsável por operações relacionadas aos usuários e playlists.
 */
public class UsuarioControle {

    /**
     * Cadastra um novo usuário no banco.
     *
     * @param nome  O nome do usuário.
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @param tipo  O tipo de usuário (C para Comum, V para VIP).
     * @return True se o cadastro for bem-sucedido, false caso contrário.
     */
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

    /**
     * Lista todos os usuários cadastrados.
     *
     * @return Lista de usuários cadastrados.
     */
    public ArrayList<UsuarioComum> listarUsuarios() {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.exibirUsuarios();
    }

    /**
     * Edita a assinatura do usuário (torna VIP ou Comum).
     *
     * @param usuario O usuário a ser editado.
     * @return True se a edição for bem-sucedida, false caso contrário.
     */
    public boolean editarAssinatura(UsuarioComum usuario) {
        if (usuario.ehVIP()) {
            return editarUsuario(usuario, "C", "T");
        } else {
            return editarUsuario(usuario, "V", "T");
        }
    }

    /**
     * Edita um atributo específico do usuário.
     *
     * @param usuario      O usuário a ser editado.
     * @param atributoNovo O novo valor do atributo.
     * @param tipoAtributo O tipo do atributo (N para nome, S para senha, T para
     *                     tipo).
     * @return True se a edição for bem-sucedida, false caso contrário.
     */
    public boolean editarUsuario(UsuarioComum usuario, String atributoNovo, String tipoAtributo) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.editarUsuario(usuario, atributoNovo, tipoAtributo);
    }

    /**
     * Remove um usuário do banco.
     *
     * @param usuario O usuário a ser removido.
     * @param senha   A senha do usuário para confirmação.
     * @return True se a remoção for bem-sucedida, false caso contrário.
     */
    public boolean removerUsuario(UsuarioComum usuario, String senha) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.removerUsuario(usuario, senha);
    }

    /**
     * Realiza o login do usuário.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return O usuário logado, ou null se o login falhar.
     */
    public UsuarioComum fazerLogin(String login, String senha) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.fazerLoginUsuario(login, senha);
    }

    /**
     * Cadastra uma nova playlist para o usuário VIP.
     *
     * @param nomePlaylist  O nome da playlist.
     * @param usuarioLogado O usuário VIP logado.
     * @return True se o cadastro for bem-sucedido, false caso contrário.
     */
    public boolean cadastrarPlaylist(String nomePlaylist, UsuarioVIP usuarioLogado) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        return playlist_controle.cadastrarPlaylist(nomePlaylist);
    }

    /**
     * Lista todas as playlists do usuário VIP.
     *
     * @param usuarioLogado O usuário VIP logado.
     * @return Lista de playlists do usuário VIP.
     */
    public ArrayList<Playlist> listarPlaylists(UsuarioVIP usuarioLogado) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        return playlist_controle.listarPlaylist();
    }

    /**
     * Adiciona uma música à playlist do usuário VIP.
     *
     * @param usuarioLogado O usuário VIP logado.
     * @param nomePlaylist  O nome da playlist.
     * @param nomeMusica    O nome da música.
     * @param caminho       O caminho da música.
     * @return True se a adição for bem-sucedida, false caso contrário.
     */
    public boolean adicionarMusica(UsuarioVIP usuarioLogado, String nomePlaylist, String nomeMusica, String caminho) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        MusicaControle musica_controle = new MusicaControle();
        Musica musica = musica_controle.buscarMusicaNoBanco(nomeMusica, caminho);
        return playlist_controle.adicionarMusica(nomePlaylist, musica);
    }

    /**
     * Remove uma música da playlist do usuário VIP.
     *
     * @param usuarioLogado O usuário VIP logado.
     * @param nomePlaylist  O nome da playlist.
     * @param nomeMusica    O nome da música.
     * @param caminho       O caminho da música.
     * @return True se a remoção for bem-sucedida, false caso contrário.
     */
    public boolean removerMusica(UsuarioVIP usuarioLogado, String nomePlaylist, String nomeMusica, String caminho) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        MusicaControle musica_controle = new MusicaControle();
        Musica musica = musica_controle.buscarMusicaNoBanco(nomeMusica, caminho);
        return playlist_controle.removerMusica(nomePlaylist, musica);
    }

    /**
     * Edita o nome de uma playlist do usuário VIP.
     *
     * @param usuarioLogado O usuário VIP logado.
     * @param nomePlaylist  O nome atual da playlist.
     * @param nomeNovo      O novo nome da playlist.
     * @return True se a edição for bem-sucedida, false caso contrário.
     */
    public boolean editarPlaylist(UsuarioVIP usuarioLogado, String nomePlaylist, String nomeNovo) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        return playlist_controle.editarPlaylistNoBanco(nomePlaylist, nomeNovo);
    }

    /**
     * Remove uma playlist do banco de dados.
     *
     * @param usuarioLogado O usuário VIP logado.
     * @param nomePlaylist  O nome da playlist a ser removida.
     * @return true se a playlist foi removida com sucesso, false caso contrário.
     */
    public boolean removerPlaylist(UsuarioVIP usuarioLogado, String nomePlaylist) {
        PlaylistControle playlist_controle = new PlaylistControle(usuarioLogado);
        return playlist_controle.removerPlaylistDoBanco(nomePlaylist);
    }
}
