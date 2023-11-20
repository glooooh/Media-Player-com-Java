package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class UsuarioVIP extends Usuario {
    private ArrayList<Playlist> playlists;
    
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
