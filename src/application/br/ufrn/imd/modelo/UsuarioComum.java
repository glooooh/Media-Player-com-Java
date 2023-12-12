package application.br.ufrn.imd.modelo;

import java.util.ArrayList;

/**
 * Representa um usuário comum no sistema de Music Player.
 */
public class UsuarioComum {

    /** O nome do usuário comum. */
    private String nome;

    /** O nome de login do usuário comum. */
    private String login;

    /** A senha do usuário comum. */
    private String senha;

    /**
     * Construtor para a classe UsuarioComum.
     *
     * @param nome  O nome do usuário comum.
     * @param login O nome de login do usuário comum.
     * @param senha A senha do usuário comum.
     */
    public UsuarioComum(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    /**
     * Obtém o nome de login do usuário comum.
     *
     * @return O nome de login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Obtém o nome do usuário comum.
     *
     * @return O nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém a senha do usuário comum.
     *
     * @return A senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Verifica se o usuário é VIP.
     *
     * @return false, indicando que o usuário comum não é VIP.
     */
    public Boolean ehVIP() {
        return false;
    }

    /**
     * Define o nome de login do usuário comum.
     *
     * @param login O novo nome de login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Define o nome do usuário comum.
     *
     * @param nome O novo nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define a senha do usuário comum.
     *
     * @param senha A nova senha do usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Realiza o login do usuário comum, comparando a senha fornecida.
     *
     * @param senha A senha fornecida para login.
     * @return true se o login for bem-sucedido, false caso contrário.
     */
    public boolean fazerLogin(String senha) {
        return senha.equals(this.getSenha());
    }

    /**
     * Adiciona diretórios ao usuário comum.
     *
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public boolean adicionarDiretorios() {
        return true;
    }

    /**
     * Obtém a lista de músicas do usuário comum.
     *
     * @return A lista de músicas ou null se não houver músicas disponíveis.
     */
    public ArrayList<Musica> exibirMusicas() {
        return null;
    }

    /**
     * Toca uma música para o usuário comum.
     *
     * @return true se a reprodução for bem-sucedida, false caso contrário.
     */
    public boolean tocarMusica() {
        return true;
    }
}