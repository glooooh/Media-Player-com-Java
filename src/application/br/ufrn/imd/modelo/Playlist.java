package application.br.ufrn.imd.modelo;

import java.util.ArrayList;

/**
 * Representa uma playlist de músicas associada a um usuário VIP.
 */
public class Playlist {

    /** O nome da playlist. */
    private String nome;

    /** O usuário VIP associado à playlist. */
    private UsuarioVIP usuario;

    /** A lista de músicas na playlist. */
    private ArrayList<Musica> musicas;

    /**
     * Construtor para a classe Playlist.
     *
     * @param nome    O nome da playlist.
     * @param usuario O usuário VIP associado à playlist.
     */
    public Playlist(String nome, UsuarioVIP usuario) {
        this.nome = nome;
        this.usuario = usuario;
        this.musicas = new ArrayList<>();
    }

    /**
     * Obtém a lista de músicas na playlist.
     *
     * @return A lista de músicas.
     */
    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    /**
     * Obtém o nome da playlist.
     *
     * @return O nome da playlist.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o usuário associado à playlist.
     *
     * @return O usuário VIP associado à playlist.
     */
    public UsuarioComum getUsuario() {
        return usuario;
    }

    /**
     * Define a lista de músicas na playlist.
     *
     * @param musicas A nova lista de músicas.
     */
    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    /**
     * Define o nome da playlist.
     *
     * @param nome O novo nome da playlist.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o usuário associado à playlist.
     *
     * @param usuario O novo usuário associado à playlist.
     */
    public void setUsuario(UsuarioVIP usuario) {
        this.usuario = usuario;
    }

    /**
     * Adiciona uma música à playlist.
     *
     * @param musica A música a ser adicionada à playlist.
     */
    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
    }
}