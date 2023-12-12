package application.br.ufrn.imd.dao;

import application.br.ufrn.imd.modelo.Diretorio;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta classe representa um Data Access Object (DAO) para operações
 * relacionadas a diretórios.
 * Ele gerencia a carga, cadastro, exibição, edição, remoção e busca de
 * diretórios em um arquivo.
 */
public class DiretorioDAO {
    /** A lista de diretórios carregados ou gerenciados pelo DAO. */
    private ArrayList<Diretorio> diretorios;

    /**
     * Construtor da classe. Inicializa a lista de diretórios e carrega os
     * diretórios existentes.
     */
    public DiretorioDAO() {
        diretorios = new ArrayList<>();
        carregarDiretorios();
    }

    /**
     * Carrega os diretórios a partir de um arquivo.
     *
     * @return A lista de diretórios carregados.
     */
    public ArrayList<Diretorio> carregarDiretorios() {
        String caminhoDir = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");

        File pasta = new File(caminhoDir + separador + "diretorios.txt");

        try {
            FileReader fr = new FileReader(pasta);
            BufferedReader br = new BufferedReader(fr);

            String linha;
            while ((linha = br.readLine()) != null) {
                Diretorio diretorio = new Diretorio(linha);
                diretorios.add(diretorio);
            }

            br.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return this.diretorios;
    }

    /**
     * Cadastra um novo diretório.
     *
     * @param diretorioNova O diretório a ser cadastrado.
     * @return True se o cadastro for bem-sucedido, false se o diretório já existir.
     */
    public boolean cadastrarDiretorio(Diretorio diretorioNova) {
        for (Diretorio diretorio : diretorios) {
            if ((diretorioNova.getCaminho()).equals(diretorio.getCaminho())) {
                return false;
            }
        }

        diretorios.add(diretorioNova);

        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        String nomeArquivo = caminho + separador + "diretorios.txt";

        try {
            File arquivoDiretorios = new File(nomeArquivo);

            FileWriter fw = new FileWriter(arquivoDiretorios.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Texto a ser escrito no arquivo
            String texto = diretorioNova.getCaminho();

            // Escrever no arquivo
            bw.write(texto);
            bw.newLine();

            // Fechar o BufferedWriter
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Exibe a lista de diretórios.
     *
     * @return A lista de diretórios.
     */
    public ArrayList<Diretorio> exibirDiretorios() {
        return diretorios;
    }

    /**
     * Edita o caminho de um diretório existente.
     *
     * @param diretorio   O diretório a ser editado.
     * @param caminhoNovo O novo caminho do diretório.
     * @return True se a edição for bem-sucedida, false caso contrário.
     */
    public boolean editarDiretorio(Diretorio diretorio, String caminhoNovo) {
        if (removerDiretorio(diretorio)) {
            diretorio.setCaminho(caminhoNovo);
            if (cadastrarDiretorio(diretorio)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Remove um diretório existente.
     *
     * @param diretorioRemover O diretório a ser removido.
     * @return True se a remoção for bem-sucedida, false caso contrário.
     */
    public boolean removerDiretorio(Diretorio diretorioRemover) {
        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        File arquivoDiretorio = new File(caminho + separador + "diretorios.txt");

        try {
            FileReader fr = new FileReader(arquivoDiretorio);
            BufferedReader reader = new BufferedReader(fr);

            String linha = reader.readLine();
            ArrayList<String> salvarLinhas = new ArrayList<>();

            while (linha != null) {
                if (linha.equals(diretorioRemover.getCaminho())) {
                    salvarLinhas.add(linha);
                }
            }

            reader.close();
            fr.close();

            FileWriter apagarArquivo = new FileWriter(arquivoDiretorio, true);
            apagarArquivo.close();

            FileWriter fw = new FileWriter(arquivoDiretorio);
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
            return false;
        }
    }

    /**
     * Busca um diretório pelo caminho.
     *
     * @param caminhoBuscado O caminho do diretório a ser buscado.
     * @return O diretório encontrado ou null se não for encontrado.
     */
    public Diretorio buscarDiretorio(String caminhoBuscado) {
        for (Diretorio diretorio : this.diretorios) {
            if (diretorio.getCaminho().equals(caminhoBuscado)) {
                return diretorio;
            }
        }
        return null;

        // *!<CASO NÃO EXISTA>!*
        // ↓ Na minha cabeça faz sentido criar um novo ↓

        /*
         * Diretorio diretorioNovo = new Diretorio(caminhoBuscado);
         * cadastrarDiretorio(diretorioNovo);
         * return diretorioNovo;
         */
    }
}
