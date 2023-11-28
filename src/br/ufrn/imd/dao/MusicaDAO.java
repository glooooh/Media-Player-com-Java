package br.ufrn.imd.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.modelo.Musica;

public class MusicaDAO {
    private ArrayList<Musica> listaMusicas;

    public MusicaDAO() {
        this.listaMusicas = new ArrayList<>();
        carregarMusicas();
    }

    public void carregarMusicas() {
        String nomeArquivo = "musicas.txt";

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
                // Supondo que o formato do arquivo seja "nome — artista — caminho"
                String[] dadosMusica = linha.split(" — ");

                if (dadosMusica.length == 3) {
                    String nome = dadosMusica[0];
                    String artista = dadosMusica[1];
                    String caminho = dadosMusica[2];

                    Musica musica = new Musica(nome, artista, caminho);
                    listaMusicas.add(musica);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean cadastrarMusica(Musica musicaNova) {
        for (Musica musica : listaMusicas) {
            if (musicaNova.getNome().equals(musica.getNome()) && musicaNova.getArtista().equals(musica.getArtista())) {
                return false;
            }
        }

        listaMusicas.add(musicaNova);

        String nomeArquivo = "musicas.txt";

        try {
            File arquivoMusicas = new File(nomeArquivo);
            if (!arquivoMusicas.exists()) {
                arquivoMusicas.createNewFile();
            }

            FileWriter fw = new FileWriter(arquivoMusicas.getAbsoluteFile(), true); // true para adicionar no final do
                                                                                     // arquivo
            BufferedWriter bw = new BufferedWriter(fw);

            // Texto a ser escrito no arquivo
            String texto = musicaNova.getNome() + " — " + musicaNova.getArtista() + " — " + musicaNova.getCaminho();

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

    public ArrayList<Musica> exibirMusicas() {
        return listaMusicas;
    }

    public boolean editarMusica(String nome, String artista, String novoAtributo, String tipoAtributo) {
        for (Musica musica : listaMusicas) {
            if (nome.equals(musica.getNome()) && artista.equals(musica.getArtista())) {
                String caminho = System.getProperty("user.dir");
                String separador = System.getProperty("file.separator");

                File arquivoMusicas = new File(caminho + separador + "musicas.txt");

                try {
                    FileReader fr = new FileReader(arquivoMusicas);
                    BufferedReader reader = new BufferedReader(fr);

                    String linha = reader.readLine();
                    ArrayList<String> salvarLinhas = new ArrayList<>();

                    while (linha != null) {
                        // Falha de segurança
                        if (linha.indexOf(nome) >= 0 && linha.indexOf(artista) >= 0) {
                            if (tipoAtributo.equals("N")) {
                                linha = novoAtributo + " — " + musica.getArtista() + " — " + musica.getCaminho();
                            } else if (tipoAtributo.equals("A")) {
                                linha = musica.getNome() + " — " + novoAtributo + " — " + musica.getCaminho();
                            } else if (tipoAtributo.equals("C")) {
                                linha = musica.getNome() + " — " + musica.getArtista() + " — " + novoAtributo;
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

    /* TESTAR, VAI DAR ERRADO */
    public boolean removerMusica(String nome, String artista) {
        for (Musica musica : listaMusicas) {
            if (nome.equals(musica.getNome()) && artista.equals(musica.getArtista())) {
                String caminho = System.getProperty("user.dir");
                String separador = System.getProperty("file.separator");

                File arquivoUsuarios = new File(caminho + separador + "usuarios.txt");

                try {
                    FileReader fr = new FileReader(arquivoUsuarios);
                    BufferedReader reader = new BufferedReader(fr);

                    String linha = reader.readLine();
                    ArrayList<String> salvarLinhas = new ArrayList<>();

                    while (linha != null) {
                        // Falha de segurança
                        if (linha.indexOf(nome) == 0 || linha.indexOf(artista) == 0) {
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
