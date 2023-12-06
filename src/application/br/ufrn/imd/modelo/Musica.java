package application.br.ufrn.imd.modelo;

public class Musica {
    private String nome;
    private String caminho;

    public Musica(String caminho) {
        this.caminho = caminho;
    }

    public Musica(String nome, String caminho) {
        this.nome = nome;
        this.caminho = caminho;
    }

    public String getCaminho() {
        return caminho;
    }

    public String getNome() {
        return nome;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
