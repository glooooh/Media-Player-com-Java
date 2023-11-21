package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class UsuarioVIP extends Usuario {
    private ArrayList<Playlist> playlists;

    public UsuarioVIP(String nome, String login, String senha) {
        super(nome, login, senha);
        playlists = new ArrayList<>();
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }
    
    public boolean fazerLogin() {
        return true;
    }

    public boolean adicionarDiretorios() {
        return true;
    }
    
    public void exibirMusicas() {

    }
    
    public boolean tocarMusica() {
        return true;
    }
}
