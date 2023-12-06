package application.br.ufrn.imd.controle;

import application.br.ufrn.imd.modelo.UsuarioComum;
import application.br.ufrn.imd.modelo.UsuarioVIP;

import java.util.ArrayList;

import application.br.ufrn.imd.modelo.Musica;
import application.br.ufrn.imd.modelo.Playlist;

public class GerenciadorControle {
    private UsuarioComum usuarioLogado;
    private Playlist playlistSelecionada;
    private Musica musicaTocando;
    private ArrayList<Musica> listaMusicas;
    private ArrayList<Musica> listaMusicasPlaylist;
    private ArrayList<Playlist> playlistsUsuario;

    public GerenciadorControle() {
        this.usuarioLogado = null;
        this.playlistSelecionada = null;
        this.musicaTocando = null;
        this.listaMusicas = new ArrayList<Musica>();
    }

    public ArrayList<Musica> getListaMusicas() {
        return listaMusicas;
    }

    public Musica getMusicaTocando() {
        return musicaTocando;
    }

    public ArrayList<Musica> getListaMusicasPlaylist() {
        return listaMusicasPlaylist;
    }

    public void setListaMusicasPlaylist(ArrayList<Musica> listaMusicasPlaylist) {
        this.listaMusicasPlaylist = listaMusicasPlaylist;
    }

    public ArrayList<Playlist> getPlaylistsUsuario() {
        return playlistsUsuario;
    }

    public void setPlaylistsUsuario(ArrayList<Playlist> playlistsUsuario) {
        this.playlistsUsuario = playlistsUsuario;
    }

    public Playlist getPlaylistSelecionada() {
        return playlistSelecionada;
    }

    public UsuarioComum getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(UsuarioComum usuario) {
        this.usuarioLogado = usuario;
    }

    public void setMusicaTocando(Musica musicaTocando) {
        this.musicaTocando = musicaTocando;
    }

    public void setPlaylistSelecionada(Playlist playlistSelecionada) {
        this.playlistSelecionada = playlistSelecionada;
    }

    public void setListaMusicas(ArrayList<Musica> listaMusicas) {
        this.listaMusicas = listaMusicas;
    }

    public boolean fazerLogin(String login, String senha) {
        UsuarioControle usuario_controle = new UsuarioControle();
        this.usuarioLogado = usuario_controle.fazerLogin(login, senha);

        if (this.usuarioLogado == null) {
            return false;
        }

        return true;
    }

    public void recarregarUsuario() {
        String login = usuarioLogado.getLogin();
        String senha = usuarioLogado.getLogin();
        UsuarioControle usuario_controle = new UsuarioControle();
        this.usuarioLogado = usuario_controle.fazerLogin(login, senha);
    }

    public boolean fazerLogout() {
        this.usuarioLogado = null;
        return true;
    }

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

//     public void adicionarPlaylist() {
//         if (this.getUsuarioLogado().ehVIP()) {
//             this.playlistsUsuario.add()
//             this.getUsuarioLogado().
//         }
//     }
}
