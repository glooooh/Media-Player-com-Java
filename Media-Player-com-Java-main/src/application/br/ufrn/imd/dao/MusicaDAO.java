package application.br.ufrn.imd.dao;

import application.br.ufrn.imd.modelo.Musica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Esta classe representa um Data Access Object (DAO) para operações
 * relacionadas a músicas.
 * Gerencia o cadastro, exibição, busca, edição e remoção de músicas em um
 * arquivo.
 */
public class MusicaDAO {
    /** A lista de músicas carregadas ou gerenciadas pelo DAO. */
    private ArrayList<Musica> listaMusicas;

    /**
     * Construtor da classe. Inicializa a lista de músicas e carrega as músicas
     * existentes.
     */
    public MusicaDAO() {
        this.listaMusicas = new ArrayList<>();
        carregarMusicas();
    }

    /**
     * Carrega as músicas a partir de um arquivo.
     */
    public void carregarMusicas() {
        String caminhoDir = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        String nomeArquivo = caminhoDir + separador + "musicas.txt";

        try {
            File arquivoMusicas = new File(nomeArquivo);
            if (!arquivoMusicas.exists()) {
                arquivoMusicas.createNewFile();
                return; // Se o arquivo não existe, não há músicas para carregar, então retornamos
            }

            FileReader fr = new FileReader(arquivoMusicas);
            BufferedReader br = new BufferedReader(fr);

            String linha;
            while ((linha = br.readLine()) != null) {
                // Supondo que o formato do arquivo seja "nome — caminho"
                String[] dadosMusica = linha.split(" - ");

                if (dadosMusica.length == 2) {
                    String nome = dadosMusica[0];
                    String caminho = dadosMusica[1];

                    Musica musica = new Musica(nome, caminho);
                    listaMusicas.add(musica);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cadastra uma nova música.
     *
     * @param musicaNova A música a ser cadastrada.
     * @return True se o cadastro for bem-sucedido, false se a música já existir.
     */
    public boolean cadastrarMusica(Musica musicaNova) {
        for (Musica musica : listaMusicas) {
            if (musicaNova.getNome().equals(musica.getNome()) && musicaNova.getCaminho().equals(musica.getCaminho())) {
                return false;
            }
        }

        listaMusicas.add(musicaNova);

        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        String nomeArquivo = caminho + separador + "musicas.txt";

        try {
            File arquivoMusicas = new File(nomeArquivo);
            if (!arquivoMusicas.exists()) {
                arquivoMusicas.createNewFile();
            }

            FileWriter fw = new FileWriter(arquivoMusicas.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Texto a ser escrito no arquivo
            String texto = musicaNova.getNome() + " - " + musicaNova.getCaminho();

            // Escrever no arquivo
            bw.write(texto);
            bw.newLine(); // Pular para a próxima linha

            // Fechar o BufferedWriter
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Exibe a lista de músicas cadastradas.
     *
     * @return A lista de músicas.
     */
    public ArrayList<Musica> exibirMusicas() {
        return listaMusicas;
    }

    /**
     * Busca uma música pelo nome e caminho.
     *
     * @param nome    O nome da música a ser buscada.
     * @param caminho O caminho da música a ser buscada.
     * @return A música encontrada ou null se não for encontrada.
     */
    public Musica buscarMusica(String nome, String caminho) {
        for (Musica musica : listaMusicas) {
            if (nome.equals(musica.getNome()) && caminho.equals(musica.getCaminho())) {
                return musica;
            }
        }
        return null;
    }

    /**
     * Edita uma música existente.
     *
     * @param musicaEditada A música a ser editada.
     * @param novoAtributo  O novo valor do atributo a ser editado.
     * @param tipoAtributo  O tipo de atributo a ser editado ("N" para nome, "C"
     *                      para caminho).
     * @return True se a edição for bem-sucedida, false caso contrário.
     */
    public boolean editarMusica(Musica musicaEditada, String novoAtributo, String tipoAtributo) {
        for (Musica musica : listaMusicas) {
            if ((musicaEditada.getNome()).equals(musica.getNome())
                    && (musicaEditada.getCaminho()).equals(musica.getCaminho())) {
                String caminhoDir = System.getProperty("user.dir");
                String separador = System.getProperty("file.separator");
                String nomeArquivo = caminhoDir + separador + "usuarios.txt";

                File arquivoMusicas = new File(nomeArquivo);

                try {
                    FileReader fr = new FileReader(arquivoMusicas);
                    BufferedReader reader = new BufferedReader(fr);

                    String linha = reader.readLine();
                    ArrayList<String> salvarLinhas = new ArrayList<>();

                    while (linha != null) {
                        // Falha de segurança
                        if (linha.indexOf(musicaEditada.getNome()) >= 0
                                && linha.indexOf(musicaEditada.getCaminho()) >= 0) {
                            if (tipoAtributo.equals("N")) {
                                linha = novoAtributo + " - " + musica.getCaminho();
                            } else if (tipoAtributo.equals("C")) {
                                linha = musica.getNome() + " - " + novoAtributo;
                            }
                            salvarLinhas.add(linha);
                        } else {
                            salvarLinhas.add(linha);
                        }
                    }

                    reader.close();
                    fr.close();

                    FileWriter apagarArquivo = new FileWriter(arquivoMusicas, true);
                    apagarArquivo.close();

                    FileWriter fw = new FileWriter(arquivoMusicas);
                    BufferedWriter writer = new BufferedWriter(fw);

                    for (int i = 0; i < salvarLinhas.size(); i++) {
                        writer.write(salvarLinhas.get(i));
                        writer.newLine();
                    }

                    fw.close();
                    writer.close();
                    return true;
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }

        return false;
    }

    /**
     * Remove uma música do sistema e atualiza o arquivo de usuários.
     *
     * Este método remove a música fornecida da lista de músicas e atualiza o
     * arquivo de usuários
     * para garantir a consistência dos dados.
     *
     * @param musicaRemovida A instância de Musica a ser removida do sistema.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */
    public boolean removerMusica(Musica musicaRemovida) {
        for (Musica musica : listaMusicas) {
            if ((musicaRemovida.getNome()).equals(musica.getNome())
                    && (musicaRemovida.getCaminho()).equals(musica.getCaminho())) {
                String caminhoDir = System.getProperty("user.dir");
                String separador = System.getProperty("file.separator");
                String nomeArquivo = caminhoDir + separador + "usuarios.txt";

                File arquivoUsuarios = new File(nomeArquivo);

                try {
                    FileReader fr = new FileReader(arquivoUsuarios);
                    BufferedReader reader = new BufferedReader(fr);

                    String linha = reader.readLine();
                    ArrayList<String> salvarLinhas = new ArrayList<>();

                    while (linha != null) {
                        // Falha de segurança
                        if (linha.indexOf(musicaRemovida.getNome()) == 0
                                || linha.indexOf(musicaRemovida.getCaminho()) == 0) {
                            salvarLinhas.add(linha);
                        }
                    }

                    reader.close();
                    fr.close();

                    FileWriter apagarArquivo = new FileWriter(arquivoUsuarios, true);
                    apagarArquivo.close();

                    FileWriter fw = new FileWriter(arquivoUsuarios);
                    BufferedWriter writer = new BufferedWriter(fw);

                    for (int i = 0; i < salvarLinhas.size(); i++) {
                        writer.write(salvarLinhas.get(i));
                        writer.newLine();
                    }

                    fw.close();
                    writer.close();
                    return true;
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }

        return false;
    }
}
