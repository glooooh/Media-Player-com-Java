package application.br.ufrn.imd.modelo;

import java.util.ArrayList;

public class UsuarioVIP extends UsuarioComum {
    private ArrayList<Playlist> playlists;

    public UsuarioVIP(String nome, String login, String senha) {
        super(nome, login, senha);
        playlists = new ArrayList<>();
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    @Override
    public Boolean ehVIP() {
        return true;
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
    
    public ArrayList<Musica> exibirMusicas() {
        return null;
    }
    
    public boolean tocarMusica() {
        return true;
    }
}
