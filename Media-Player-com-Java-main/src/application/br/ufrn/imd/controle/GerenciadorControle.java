package application.br.ufrn.imd.controle;

import application.br.ufrn.imd.modelo.UsuarioComum;
import application.br.ufrn.imd.modelo.UsuarioVIP;

import java.util.ArrayList;

import application.br.ufrn.imd.modelo.Musica;
import application.br.ufrn.imd.modelo.Playlist;

/**
 * A classe GerenciadorControle é responsável por gerenciar as interações entre
 * a interface
 * do usuário e as operações relacionadas a usuários, playlists, músicas, e
 * autenticação no sistema.
 */
public class GerenciadorControle {

    private UsuarioComum usuarioLogado;
    private Playlist playlistSelecionada;
    private Musica musicaTocando;
    private ArrayList<Musica> listaMusicas;
    private ArrayList<Musica> listaMusicasPlaylist;
    private ArrayList<Playlist> playlistsUsuario;

    /**
     * Construtor padrão da classe GerenciadorControle.
     * Inicializa as variáveis de controle.
     */
    public GerenciadorControle() {
        this.usuarioLogado = null;
        this.playlistSelecionada = null;
        this.musicaTocando = null;
        this.listaMusicas = new ArrayList<Musica>();
    }

    /**
     * Obtém a lista de todas as músicas disponíveis.
     *
     * @return Uma lista de objetos Musica representando as músicas disponíveis.
     */
    public ArrayList<Musica> getListaMusicas() {
        return listaMusicas;
    }

    /**
     * Obtém a música que está sendo reproduzida no momento.
     *
     * @return O objeto Musica atualmente em reprodução.
     */
    public Musica getMusicaTocando() {
        return musicaTocando;
    }

    /**
     * Obtém a lista de músicas associadas à playlist atualmente selecionada.
     *
     * @return Uma lista de objetos Musica representando as músicas na playlist
     *         selecionada.
     */
    public ArrayList<Musica> getListaMusicasPlaylist() {
        return listaMusicasPlaylist;
    }

    /**
     * Define a lista de músicas associadas à playlist atualmente selecionada.
     *
     * @param listaMusicasPlaylist Uma lista de objetos Musica representando as
     *                             músicas na playlist selecionada.
     */
    public void setListaMusicasPlaylist(ArrayList<Musica> listaMusicasPlaylist) {
        this.listaMusicasPlaylist = listaMusicasPlaylist;
    }

    /**
     * Obtém a lista de playlists associadas ao usuário logado.
     *
     * @return Uma lista de objetos Playlist representando as playlists do usuário
     *         logado.
     */
    public ArrayList<Playlist> getPlaylistsUsuario() {
        return playlistsUsuario;
    }

    /**
     * Define a lista de playlists associadas ao usuário logado.
     *
     * @param playlistsUsuario Uma lista de objetos Playlist representando as
     *                         playlists do usuário logado.
     */
    public void setPlaylistsUsuario(ArrayList<Playlist> playlistsUsuario) {
        this.playlistsUsuario = playlistsUsuario;
    }

    /**
     * Obtém a playlist atualmente selecionada.
     *
     * @return O objeto Playlist atualmente selecionado.
     */
    public Playlist getPlaylistSelecionada() {
        return playlistSelecionada;
    }

    /**
     * Obtém o usuário comum atualmente logado.
     *
     * @return O objeto UsuarioComum representando o usuário logado.
     */
    public UsuarioComum getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * Define o usuário comum que está atualmente logado.
     *
     * @param usuario O objeto UsuarioComum representando o usuário a ser definido
     *                como logado.
     */
    public void setUsuarioLogado(UsuarioComum usuario) {
        this.usuarioLogado = usuario;
    }

    /**
     * Define a música que está atualmente em reprodução.
     *
     * @param musicaTocando O objeto Musica a ser definido como a música em
     *                      reprodução.
     */
    public void setMusicaTocando(Musica musicaTocando) {
        this.musicaTocando = musicaTocando;
    }

    /**
     * Define a playlist que está atualmente selecionada.
     *
     * @param playlistSelecionada O objeto Playlist a ser definido como a playlist
     *                            selecionada.
     */
    public void setPlaylistSelecionada(Playlist playlistSelecionada) {
        this.playlistSelecionada = playlistSelecionada;
    }

    /**
     * Define a lista de todas as músicas disponíveis.
     *
     * @param listaMusicas Uma lista de objetos Musica representando as músicas
     *                     disponíveis.
     */
    public void setListaMusicas(ArrayList<Musica> listaMusicas) {
        this.listaMusicas = listaMusicas;
    }

    /**
     * Realiza o login de um usuário no sistema.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return True se o login for bem-sucedido, false se falhar.
     */
    public boolean fazerLogin(String login, String senha) {
        UsuarioControle usuario_controle = new UsuarioControle();
        this.usuarioLogado = usuario_controle.fazerLogin(login, senha);

        if (this.usuarioLogado == null) {
            return false;
        }

        return true;
    }

    /**
     * Recarrega as informações do usuário logado.
     */
    public void recarregarUsuario() {
        String login = usuarioLogado.getLogin();
        String senha = usuarioLogado.getLogin();
        UsuarioControle usuario_controle = new UsuarioControle();
        this.usuarioLogado = usuario_controle.fazerLogin(login, senha);
    }

    /**
     * Realiza o logout do usuário atualmente logado.
     *
     * @return True se o logout for bem-sucedido, false se falhar.
     */
    public boolean fazerLogout() {
        this.usuarioLogado = null;
        return true;
    }

    /**
     * Edita a assinatura do usuário (de VIP para Comum ou vice-versa).
     *
     * @return True se a edição for bem-sucedida, false se falhar.
     */
    public boolean editarAssinatura() {
        UsuarioControle usuario_controle = new UsuarioControle();

        boolean statusSucesso = false;

        if (this.getUsuarioLogado().ehVIP() && usuario_controle.editarUsuario(this.getUsuarioLogado(), "C", "T")) {

            this.setUsuarioLogado(new UsuarioComum(this.getUsuarioLogado().getNome(),
                    this.getUsuarioLogado().getLogin(), this.getUsuarioLogado().getSenha()));

            return true;
        } else if (usuario_controle.editarUsuario(this.getUsuarioLogado(), "V", "T")) {

            this.setUsuarioLogado(new UsuarioVIP(this.getUsuarioLogado().getNome(), this.getUsuarioLogado().getLogin(),
                    this.getUsuarioLogado().getSenha()));

            return true;
        }

        return statusSucesso;
    }

    /**
     * Edita as informações do usuário logado.
     *
     * @param atributoNovo O novo valor do atributo a ser editado.
     * @param tipoAtributo O tipo de atributo a ser editado (Nome ou Senha).
     * @return True se a edição for bem-sucedida, false se falhar.
     */
    public boolean editarUsuario(String atributoNovo, String tipoAtributo) {
        UsuarioControle usuario_controle = new UsuarioControle();

        boolean statusSucesso = usuario_controle.editarUsuario(this.getUsuarioLogado(),
                atributoNovo, tipoAtributo);

        if (statusSucesso) {
            if (tipoAtributo.equals("N")) {
                this.getUsuarioLogado().setNome(atributoNovo);
            } else if (tipoAtributo.equals("S")) {
                this.getUsuarioLogado().setNome(atributoNovo);
            }
        }

        return statusSucesso;
    }

}
