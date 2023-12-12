package application.br.ufrn.imd.modelo;

/**
 * Representa uma música associada a um caminho de arquivo.
 */
public class Musica {

    /** O nome da música. */
    private String nome;

    /** O caminho do arquivo associado à música. */
    private String caminho;

    /**
     * Construtor para a classe Musica.
     *
     * @param caminho O caminho do arquivo associado à música.
     */
    public Musica(String caminho) {
        this.caminho = caminho;
    }

    /**
     * Construtor para a classe Musica.
     *
     * @param nome    O nome da música.
     * @param caminho O caminho do arquivo associado à música.
     */
    public Musica(String nome, String caminho) {
        this.nome = nome;
        this.caminho = caminho;
    }

    /**
     * Obtém o caminho do arquivo associado à música.
     *
     * @return O caminho do arquivo.
     */
    public String getCaminho() {
        return caminho;
    }

    /**
     * Obtém o nome da música.
     *
     * @return O nome da música.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o caminho do arquivo associado à música.
     *
     * @param caminho O novo caminho do arquivo.
     */
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    /**
     * Define o nome da música.
     *
     * @param nome O novo nome da música.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}