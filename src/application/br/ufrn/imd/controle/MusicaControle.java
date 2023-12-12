package application.br.ufrn.imd.controle;

import java.util.ArrayList;

import application.br.ufrn.imd.dao.MusicaDAO;
import application.br.ufrn.imd.modelo.Musica;

/**
 * Controlador para operações relacionadas a músicas.
 */
public class MusicaControle {

    /**
     * Construtor padrão da classe.
     */
    public MusicaControle() {

    }

    /**
     * Cadastra uma nova música no banco de dados.
     *
     * @param nome    O nome da música.
     * @param caminho O caminho da música no sistema de arquivos.
     * @return true se o cadastro for bem-sucedido, false caso contrário.
     */
    public boolean cadastrarMusicasNoBanco(String nome, String caminho) {
        Musica musica = new Musica(nome, caminho);
        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.cadastrarMusica(musica);
    }

    /**
     * Lista todas as músicas cadastradas no banco de dados.
     *
     * @return Uma lista de todas as músicas cadastradas.
     */
    public ArrayList<Musica> listarMusicas() {
        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.exibirMusicas();
    }

    /**
     * Busca uma música no banco de dados com base no nome e caminho.
     *
     * @param nome    O nome da música a ser buscada.
     * @param caminho O caminho da música a ser buscada.
     * @return A música encontrada ou null se não encontrada.
     */
    public Musica buscarMusicaNoBanco(String nome, String caminho) {
        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.buscarMusica(nome, caminho);
    }

    /**
     * Edita os atributos de uma música no banco de dados.
     *
     * @param nome         O nome da música a ser editada.
     * @param caminho      O caminho da música a ser editada.
     * @param novoAtributo O novo valor do atributo a ser editado.
     * @param tipoAtributo O tipo de atributo a ser editado (nome, caminho, etc.).
     * @return true se a edição for bem-sucedida, false caso contrário.
     */
    public boolean editarMusicasNoBanco(String nome, String caminho, String novoAtributo, String tipoAtributo) {
        Musica musica = buscarMusicaNoBanco(nome, caminho);

        if (musica == null) {
            return false;
        }

        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.editarMusica(musica, novoAtributo, tipoAtributo);
    }

    /**
     * Remove uma música do banco de dados.
     *
     * @param nome    O nome da música a ser removida.
     * @param caminho O caminho da música a ser removida.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */
    public boolean removerMusicasDoBanco(String nome, String caminho) {
        Musica musica = buscarMusicaNoBanco(nome, caminho);

        if (musica == null) {
            return false;
        }

        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.removerMusica(musica);
    }
}