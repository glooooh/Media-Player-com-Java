package application.br.ufrn.imd.controle;

import application.br.ufrn.imd.dao.DiretorioDAO;

import application.br.ufrn.imd.modelo.Diretorio;
import application.br.ufrn.imd.modelo.Musica;

import java.util.ArrayList;

/**
 * A classe DiretorioControle é responsável por intermediar as operações entre a
 * interface
 * do usuário e o acesso aos dados relacionados a diretórios no banco de dados.
 */
public class DiretorioControle {
    /**
     * Cadastra um novo diretório no banco de dados e suas respectivas músicas.
     *
     * @param caminho O caminho do novo diretório a ser cadastrado.
     * @return True se o cadastro for bem-sucedido, false se o diretório já existir
     *         no banco.
     */
    public boolean cadastrarDiretorioNoBanco(String caminho) {
        DiretorioDAO diretorioDao = new DiretorioDAO();
        Diretorio diretorioNovo = new Diretorio(caminho);

        MusicaControle novasMusica = new MusicaControle();

        for (Musica musica : diretorioNovo.getMusicas()) {
            novasMusica.cadastrarMusicasNoBanco(musica.getNome(),
                    musica.getCaminho());
        }

        return diretorioDao.cadastrarDiretorio(diretorioNovo);
    }

    /**
     * Lista todos os diretórios cadastrados no banco de dados.
     *
     * @return Uma lista de objetos Diretorio representando os diretórios
     *         cadastrados.
     */
    public ArrayList<Diretorio> listarDiretoriosDoBanco() {
        DiretorioDAO diretorioDao = new DiretorioDAO();
        return diretorioDao.exibirDiretorios();
    }

    /**
     * Edita o caminho de um diretório no banco de dados.
     *
     * @param diretorio   O diretório a ser editado.
     * @param caminhoNovo O novo caminho a ser atribuído ao diretório.
     * @return True se a edição for bem-sucedida, false se o diretório não existir
     *         no banco.
     */
    public boolean editarDiretorioNoBanco(Diretorio diretorio, String caminhoNovo) {
        DiretorioDAO diretorioDao = new DiretorioDAO();

        return diretorioDao.editarDiretorio(diretorio, caminhoNovo);
    }

    /**
     * Busca um diretório no banco de dados com base no caminho fornecido.
     *
     * @param caminhoDiretorio O caminho do diretório a ser buscado.
     * @return Um objeto Diretorio se o diretório for encontrado, null se não
     *         existir.
     */
    public Diretorio buscarDiretorioNoBanco(String caminhoDiretorio) {
        DiretorioDAO diretorioDao = new DiretorioDAO();
        Diretorio diretorioNovo = diretorioDao.buscarDiretorio(caminhoDiretorio);
        return diretorioNovo;
    }

    /**
     * Remove um diretório do banco de dados com base no caminho fornecido.
     *
     * @param caminhoDiretorio O caminho do diretório a ser removido.
     * @return True se a remoção for bem-sucedida, false se o diretório não existir
     *         no banco.
     */
    public boolean removerDiretorioDoBanco(String caminhoDiretorio) {
        DiretorioDAO diretorioDao = new DiretorioDAO();
        Diretorio diretorioRemover = new Diretorio(caminhoDiretorio);
        return diretorioDao.removerDiretorio(diretorioRemover);
    }
}
