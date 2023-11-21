package br.ufrn.imd.modelo;

public abstract class Usuario {
    private String nome;
    private String login;
    private String senha;

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean fazerLogin(String senha) {
        if (senha == this.getSenha()) {
            return true;
        }

        return false;
    }

    public abstract boolean adicionarDiretorios();

    public abstract boolean tocarMusica();

    public abstract void exibirMusicas();
}
