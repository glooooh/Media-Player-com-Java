package application.br.ufrn.imd.dao;

import application.br.ufrn.imd.modelo.Diretorio;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DiretorioDAO {
    private ArrayList<Diretorio> diretorios;

    public DiretorioDAO() {
        diretorios = new ArrayList<>();
        carregarDiretorios();
    }

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
                (this.diretorios).add(diretorio);
            }

            br.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return this.diretorios;
    }

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

            FileWriter fw = new FileWriter(arquivoDiretorios.getAbsoluteFile(), true); // true para adicionar no final do
                                                                                    // arquivo
            BufferedWriter bw = new BufferedWriter(fw);

            // Texto a ser escrito no arquivo
            String texto = diretorioNova.getCaminho();

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

    public ArrayList<Diretorio> exibirDiretorios() {
        return diretorios;
    }

    public boolean editarDiretorio(Diretorio diretorio,String caminhoNovo) {
        if(removerDiretorio(diretorio)){
            diretorio.setCaminho(caminhoNovo);
            if(cadastrarDiretorio(diretorio)){
                return true;
            }
        }

        return false;
    }

    public boolean removerDiretorio(Diretorio diretorioRemover) {
        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        File arquivoDiretorio = new File(
                caminho + separador + "diretorios.txt");
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
        Diretorio diretorioNovo = new Diretorio(caminhoBuscado);
        cadastrarDiretorio(diretorioNovo);
        return diretorioNovo;
        */
    }
}
