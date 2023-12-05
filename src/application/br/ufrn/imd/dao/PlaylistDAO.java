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

public class PlaylistDAO {
    ArrayList<Playlist> playlistsUsuario;

    public PlaylistDAO(UsuarioVIP usuario) {
        playlistsUsuario = new ArrayList<Playlist>();
        carregarPlaylists(usuario);
    }

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
                nomePlaylist = nomePlaylist.replace("playlist_", "");

                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String linha, loginArquivo, nomeUsuarioArquivo;

                loginArquivo = br.readLine();
                nomeUsuarioArquivo = br.readLine();

                if (usuario.getLogin().equals(loginArquivo) && usuario.getNome().equals(nomeUsuarioArquivo)) {
                    Playlist playlistNova = new Playlist(nomePlaylist, usuario);
                    while ((linha = br.readLine()) != null) {
                        // Supondo que o formato do arquivo seja "nome — artista — caminho"
                        String[] dadosMusica = linha.split(" — ");

                        if (dadosMusica.length == 3) {
                            String nome = dadosMusica[0];
                            String artista = dadosMusica[1];
                            String caminho = dadosMusica[2];

                            Musica musica = new Musica(nome, artista, caminho);
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

    public boolean cadastrarPlaylist(Playlist playlist) {
        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        File arquivo = new File(caminho + separador + "Playlists" + separador + "playlist_" + playlist.getNome());
        try {
            arquivo.createNewFile();
            FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(), true); // true para adicionar no final do
                                                                             // arquivo
            BufferedWriter bw = new BufferedWriter(fw);

            // Texto a ser escrito no arquivo
            String texto = playlist.getUsuario().getLogin() + "\n" + playlist.getUsuario().getNome();

            // Escrever no arquivo
            bw.write(texto);
            bw.newLine(); // Pular para a próxima linha

            // Fechar o BufferedWriter
            bw.close();
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    public boolean adicionarMusica(Playlist playlist, Musica musicaNova) {
        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        File arquivoPlaylist = new File(
                caminho + separador + "Playlists" + separador + "playlist_" + playlist.getNome());

        for (Musica musica : playlist.getMusicas()) {
            if (musicaNova.getNome().equals(musica.getNome()) && musicaNova.getArtista().equals(musica.getArtista())) {
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
                if (linha.indexOf(musicaRemovida.getNome()) == 0 || linha.indexOf(musicaRemovida.getArtista()) == 0) {
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

    public ArrayList<Playlist> exibirPlaylists() {
        return playlistsUsuario;
    }

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

    public Playlist buscarPlaylist(String playlistBuscada, UsuarioVIP usuario) {
        for (Playlist playlist : playlistsUsuario) {
            if (playlist.getNome().equals(playlistBuscada)) {
                return playlist;
            }
        }
        return null;
    }
}
