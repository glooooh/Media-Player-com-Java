package application.br.ufrn.imd.dao;

import application.br.ufrn.imd.modelo.Musica;
import application.br.ufrn.imd.modelo.Playlist;
import application.br.ufrn.imd.modelo.UsuarioVIP;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta classe representa um Data Access Object (DAO) para operações
 * relacionadas a playlists.
 * Gerencia o cadastro, exibição, busca, edição e remoção de playlists em
 * arquivos.
 */
public class PlaylistDAO {
    /** A lista de playlists carregadas ou gerenciadas pelo DAO. */
    private ArrayList<Playlist> playlistsUsuario;

    /**
     * Construtor da classe. Inicializa a lista de playlists e carrega as playlists
     * existentes para o usuário.
     *
     * @param usuario O usuário associado às playlists.
     */
    public PlaylistDAO(UsuarioVIP usuario) {
        playlistsUsuario = new ArrayList<Playlist>();
        carregarPlaylists(usuario);
    }

    /**
     * Carrega as playlists do usuário a partir de arquivos.
     *
     * @param usuario O usuário associado às playlists.
     * @return A lista de playlists carregadas.
     */
    public ArrayList<Playlist> carregarPlaylists(UsuarioVIP usuario) {
        String caminhoDir = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");

        File pasta = new File(caminhoDir + separador + "Playlists");

        for (File file : pasta.listFiles()) {
            try {
                if (!file.exists()) {
                    return null; // Se o arquivo não existe, não há músicas para carregar, então retornamos
                }

                String nomePlaylist = file.getName();
                nomePlaylist = nomePlaylist.replace("playlist_", "").replace(".txt", "");

                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String linha, loginArquivo, nomeUsuarioArquivo;

                loginArquivo = br.readLine();
                nomeUsuarioArquivo = br.readLine();

                if (usuario.getLogin().equals(loginArquivo) && usuario.getNome().equals(nomeUsuarioArquivo)) {
                    Playlist playlistNova = new Playlist(nomePlaylist, usuario);
                    while ((linha = br.readLine()) != null) {
                        // Supondo que o formato do arquivo seja "nome — caminho"
                        String[] dadosMusica = linha.split(" - ");

                        if (dadosMusica.length == 2) {
                            String nome = dadosMusica[0];
                            String caminho = dadosMusica[1];

                            Musica musica = new Musica(nome, caminho);
                            playlistNova.adicionarMusica(musica);
                        }
                    }
                    this.playlistsUsuario.add(playlistNova);
                }

                br.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        return this.playlistsUsuario;
    }

    /**
     * Cadastra uma nova playlist para o usuário.
     *
     * @param playlist A playlist a ser cadastrada.
     * @return True se o cadastro for bem-sucedido, false se a playlist já existir.
     */
    public boolean cadastrarPlaylist(Playlist playlist) {
        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        File arquivo = new File(
                caminho + separador + "Playlists" + separador + "playlist_" + playlist.getNome() + ".txt");
        try {
            arquivo.createNewFile();
            FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(), true); // true para adicionar no final do arquivo
            BufferedWriter bw = new BufferedWriter(fw);

            // Texto a ser escrito no arquivo
            String texto = playlist.getUsuario().getLogin() + "\n" + playlist.getUsuario().getNome();

            // Escrever no arquivo
            bw.write(texto);
            bw.newLine(); // Pular para a próxima linha

            // Fechar o BufferedWriter
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Adiciona uma nova música à playlist.
     *
     * @param playlist   A playlist à qual a música será adicionada.
     * @param musicaNova A música a ser adicionada.
     * @return True se a adição for bem-sucedida, false se a música já existir na
     *         playlist.
     */
    public boolean adicionarMusica(Playlist playlist, Musica musicaNova) {
        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        File arquivoPlaylist = new File(
                caminho + separador + "Playlists" + separador + "playlist_" + playlist.getNome() + ".txt");

        for (Musica musica : playlist.getMusicas()) {
            if (musicaNova.getNome().equals(musica.getNome()) && musicaNova.getCaminho().equals(musica.getCaminho())) {
                return false;
            }
        }

        playlist.adicionarMusica(musicaNova);

        try {
            if (!arquivoPlaylist.exists()) {
                arquivoPlaylist.createNewFile();
            }

            FileWriter fw = new FileWriter(arquivoPlaylist.getAbsoluteFile(), true); // true para adicionar no final do
                                                                                     // arquivo
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
     * Remove uma música da playlist.
     *
     * @param playlist       A playlist da qual a música será removida.
     * @param musicaRemovida A música a ser removida.
     * @return True se a remoção for bem-sucedida, false se a música não existir na
     *         playlist.
     */
    public boolean removerMusica(Playlist playlist, Musica musicaRemovida) {
        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        File arquivoPlaylist = new File(
                caminho + separador + "Playlists" + separador + "playlist_" + playlist.getNome());
        try {
            FileReader fr = new FileReader(arquivoPlaylist);
            BufferedReader reader = new BufferedReader(fr);

            String linha = reader.readLine();
            ArrayList<String> salvarLinhas = new ArrayList<>();

            while (linha != null) {
                if (linha.indexOf(musicaRemovida.getNome()) == 0 || linha.indexOf(musicaRemovida.getCaminho()) == 0) {
                    salvarLinhas.add(linha);
                }
            }

            reader.close();
            fr.close();

            FileWriter apagarArquivo = new FileWriter(arquivoPlaylist, true);
            apagarArquivo.close();

            FileWriter fw = new FileWriter(arquivoPlaylist);
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
     * Exibe a lista de playlists associadas ao usuário.
     *
     * @return A lista de playlists do usuário.
     */
    public ArrayList<Playlist> exibirPlaylists() {
        return playlistsUsuario;
    }

    /**
     * Edita o nome de uma playlist existente.
     *
     * @param playlist A playlist a ser editada.
     * @param nomeNovo O novo nome da playlist.
     * @return True se a edição for bem-sucedida, false se o novo nome já pertencer
     *         a outra playlist.
     */
    public boolean editarPlaylist(Playlist playlist, String nomeNovo) {
        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");

        try {
            File arquivoPlaylist = new File(
                    caminho + separador + "Playlists" + separador + "playlist_" + playlist.getNome());

            playlist.setNome(nomeNovo);
            arquivoPlaylist.renameTo(new File("playlist_" + nomeNovo));
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    /**
     * Remove uma playlist existente.
     *
     * @param playlist A playlist a ser removida.
     * @return True se a remoção for bem-sucedida, false se a playlist não existir.
     */
    public boolean removerPlaylist(Playlist playlist) {
        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");

        try {
            File arquivoPlaylist = new File(
                    caminho + separador + "Playlists" + separador + "playlist_" + playlist.getNome());
            return arquivoPlaylist.delete();
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    /**
     * Busca uma playlist pelo nome.
     *
     * @param playlistBuscada O nome da playlist a ser buscada.
     * @param usuario         O usuário associado à playlist.
     * @return A playlist encontrada ou null se não for encontrada.
     */
    public Playlist buscarPlaylist(String playlistBuscada, UsuarioVIP usuario) {
        for (Playlist playlist : playlistsUsuario) {
            if (playlist.getNome().equals(playlistBuscada)) {
                return playlist;
            }
        }
        return null;
    }
}