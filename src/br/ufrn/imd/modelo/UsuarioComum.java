package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class UsuarioComum {
    private String nome;
    private String login;
    private String senha;

    public UsuarioComum(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public Boolean ehVIP() {
        return false;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean fazerLogin(String senha) {
        if (senha.equals(this.getSenha())) {
            return true;
        }

        return false;
    }

    public boolean adicionarDiretorios() {
        return true;
    }

    public ArrayList<Musica> exibirMusicas() {
        return null;
    }

    public boolean tocarMusica() {
        return true;
    }
}
