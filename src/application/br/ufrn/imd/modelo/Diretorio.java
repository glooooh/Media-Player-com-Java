package application.br.ufrn.imd.modelo;

import java.io.File;
import java.util.ArrayList;

public class Diretorio {
    private String caminho;
    private ArrayList<Musica> musicas;

    public Diretorio(String caminho) {
        this.caminho = caminho;
        musicas = new ArrayList<>();

        File diretorio = new File(caminho);
        File[] files = diretorio.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && ehMusica(file)) {
                    musicas.add(
                            new Musica(file.getName().substring(0, file.getName().length() - 4), file.toString()));
                    // Artista????
                }
            }
        }
    }

    private boolean ehMusica(File file) {
        String nomeArquivo = file.getName().toLowerCase();
        return nomeArquivo.endsWith(".mp3") || nomeArquivo.endsWith(".wav") || nomeArquivo.endsWith(".ogg")
                || nomeArquivo.endsWith(".m4a");
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
