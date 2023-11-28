package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class Playlist {
    private String nome;
    private UsuarioComum usuario;
    private ArrayList<Musica> musicas;

    public Playlist(String nome, UsuarioComum usuario) {
        this.nome = nome;
        this.usuario = usuario;
        this.musicas = new ArrayList<>();
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public String getNome() {
        return nome;
    }

    public UsuarioComum getUsuario() {
        return usuario;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsuario(UsuarioComum usuario) {
        this.usuario = usuario;
    }
}
