package br.ufrn.imd.modelo;

public class UsuarioComum extends Usuario{
    public boolean fazerLogin() {
        return true;
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
