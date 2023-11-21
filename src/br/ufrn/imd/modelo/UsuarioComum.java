package br.ufrn.imd.modelo;

public class UsuarioComum extends Usuario{
    public UsuarioComum(String nome, String login, String senha) {
        super(nome, login, senha);
    }

    public boolean adicionarDiretorios() {
        return true;
    }
    
    public void exibirMusicas() {

    }
    
    public boolean tocarMusica() {
        return true;
    }
}
