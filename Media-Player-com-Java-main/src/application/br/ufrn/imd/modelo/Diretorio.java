package application.br.ufrn.imd.modelo;

import java.io.File;
import java.util.ArrayList;

/**
 * Representa um diretório que contém músicas.
 */
public class Diretorio {

    /** O caminho do diretório. */
    private String caminho;

    /** A lista de músicas no diretório. */
    private ArrayList<Musica> musicas;

    /**
     * Construtor para a classe Diretorio.
     *
     * @param caminho O caminho do diretório.
     */
    public Diretorio(String caminho) {
        this.caminho = caminho;
        this.musicas = new ArrayList<>();

        // Lê os arquivos no diretório e adiciona as músicas à lista
        File diretorio = new File(caminho);
        File[] files = diretorio.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && ehMusica(file)) {
                    musicas.add(new Musica(file.getName().substring(0, file.getName().length() - 4), file.toString()));
                    // Artista????
                }
            }
        }
    }

    /**
     * Verifica se o arquivo é uma música com base na extensão do arquivo.
     *
     * @param file O arquivo a ser verificado.
     * @return true se for uma música, false caso contrário.
     */
    private boolean ehMusica(File file) {
        String nomeArquivo = file.getName().toLowerCase();
        return nomeArquivo.endsWith(".mp3") || nomeArquivo.endsWith(".wav") || nomeArquivo.endsWith(".ogg")
                || nomeArquivo.endsWith(".m4a");
    }

    /**
     * Obtém o caminho do diretório.
     *
     * @return O caminho do diretório.
     */
    public String getCaminho() {
        return caminho;
    }

    /**
     * Obtém a lista de músicas no diretório.
     *
     * @return A lista de músicas.
     */
    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    /**
     * Define o caminho do diretório.
     *
     * @param caminho O novo caminho do diretório.
     */
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    /**
     * Define a lista de músicas no diretório.
     *
     * @param musicas A nova lista de músicas.
     */
    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }
}