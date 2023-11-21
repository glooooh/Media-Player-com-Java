package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class Playlist {
    private String nome;
    private ArrayList<Musica> musicas;

    public Playlist() {
        musicas = new ArrayList<>();
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public String getNome() {
        return nome;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
