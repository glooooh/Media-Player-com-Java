package application.br.ufrn.imd.modelo;

public class Musica {
    private String nome;
    private String artista;
    private String caminho;

    public Musica(String caminho) {
        this.caminho = caminho;
    }

    public Musica(String nome, String artista, String caminho) {
        this.nome = nome;
        this.artista = artista;
        this.caminho = caminho;
    }

    public String getCaminho() {
        return caminho;
    }

    public String getNome() {
        return nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
}
