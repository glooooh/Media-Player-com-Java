package br.ufrn.imd.modelo;

public abstract class Usuario {
    private String login;
    private String senha;

    Usuario() {

    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public abstract boolean adicionarDiretorios();

    public abstract boolean tocarMusica();

    public abstract void exibirMusicas();
}
