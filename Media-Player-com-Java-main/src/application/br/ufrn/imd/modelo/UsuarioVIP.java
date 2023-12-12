package application.br.ufrn.imd.modelo;

import java.util.ArrayList;

/**
 * Representa um usuário VIP no sistema de Music Player.
 * Estende a classe UsuarioComum.
 */
public class UsuarioVIP extends UsuarioComum {

    /** Lista de playlists associadas ao usuário VIP. */
    private ArrayList<Playlist> playlists;

    /**
     * Construtor para a classe UsuarioVIP.
     *
     * @param nome  O nome do usuário VIP.
     * @param login O nome de login do usuário VIP.
     * @param senha A senha do usuário VIP.
     */
    public UsuarioVIP(String nome, String login, String senha) {
        super(nome, login, senha);
        playlists = new ArrayList<>();
    }

    /**
     * Obtém a lista de playlists do usuário VIP.
     *
     * @return A lista de playlists.
     */
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Verifica se o usuário é VIP.
     *
     * @return true, indicando que o usuário é VIP.
     */
    @Override
    public Boolean ehVIP() {
        return true;
    }

    /**
     * Define a lista de playlists do usuário VIP.
     *
     * @param playlists A nova lista de playlists.
     */
    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    /**
     * Realiza o login do usuário VIP.
     *
     * @return true se o login for bem-sucedido, false caso contrário.
     */
    public boolean fazerLogin() {
        return true;
    }

    /**
     * Adiciona diretórios associados ao usuário VIP.
     *
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public boolean adicionarDiretorios() {
        return true;
    }

    /**
     * Obtém a lista de músicas do usuário VIP.
     *
     * @return A lista de músicas ou null se não houver músicas disponíveis.
     */
    public ArrayList<Musica> exibirMusicas() {
        return null;
    }

    /**
     * Toca uma música para o usuário VIP.
     *
     * @return true se a reprodução for bem-sucedida, false caso contrário.
     */
    public boolean tocarMusica() {
        return true;
    }
}