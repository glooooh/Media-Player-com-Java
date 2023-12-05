package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class Diretorio {
    private String caminho;
    private ArrayList<Musica> musicas;
    
    public Diretorio(String caminho) {
        this.caminho = caminho;
        musicas = new ArrayList<>();
    }

    public String getCaminho() {
        return caminho;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }
}
