package application.br.ufrn.imd.controle;

import application.br.ufrn.imd.modelo.UsuarioComum;

import java.util.ArrayList;

import application.br.ufrn.imd.modelo.Musica;
import application.br.ufrn.imd.modelo.Playlist;

public class GerenciadorControle {
    private UsuarioComum usuarioLogado;
    private Playlist playlistSelecionada;
    private Musica musicaTocando;
    private ArrayList<Musica> listaMusicas;

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

    public boolean fazerLogout() {
        this.usuarioLogado = null;
        return true;
    }

    // public boolean editarAssinatura() {
    // UsuarioControle usuario_controle = new UsuarioControle();
    // if (this.usuarioLogado.ehVIP()) {
    // usuarioLogado = new UsuarioComum(this.usuarioLogado.getNome(),
    // this.usuarioLogado.getLogin(),
    // this.usuarioLogado.getSenha());
    // return usuario_controle.editarUsuario(this.usuarioLogado.getLogin(), "C",
    // "T");
    // } else {
    // usuarioLogado = new UsuarioVIP(this.usuarioLogado.getNome(),
    // this.usuarioLogado.getLogin(),
    // this.usuarioLogado.getSenha());
    // return usuario_controle.editarUsuario(this.usuarioLogado.getLogin(), "V",
    // "T");
    // }
    // }

    // public boolean editarUsuario(String atributoNovo, String tipoAtributo) {
    // UsuarioControle usuario_controle = new UsuarioControle();
    // return usuario_controle.editarUsuario(this.usuarioLogado.getLogin(),
    // atributoNovo, tipoAtributo);
    // }
}
