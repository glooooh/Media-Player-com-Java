package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.UsuarioComum;
import br.ufrn.imd.modelo.Musica;
import br.ufrn.imd.modelo.Playlist;

public class GerenciadorControle {
    private UsuarioComum usuarioLogado;
    private Playlist playlistSelecionada;
    private Musica musicaTocando;

    public GerenciadorControle() {
        this.usuarioLogado = null;
        this.playlistSelecionada = null;
        this.musicaTocando = null;
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

    public boolean fazerLogin(String login, String senha) {
        UsuarioControle usuario_controle = new UsuarioControle();
        this.usuarioLogado = usuario_controle.fazerLogin(login, senha);

        System.out.println(usuarioLogado.getLogin());

        if (this.usuarioLogado == null) {
            return false;
        }
        
        return true;
    }

    public boolean fazerLogout() {
        this.usuarioLogado = null;
        return true;
    }
    
    public boolean editarUsuario(String atributoNovo, String tipoAtributo) {
    	UsuarioControle usuario_controle = new UsuarioControle();
    	return usuario_controle.editarUsuario(this.usuarioLogado.getLogin(), atributoNovo, tipoAtributo);
    }
}
