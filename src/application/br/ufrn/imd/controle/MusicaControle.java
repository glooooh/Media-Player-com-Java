package application.br.ufrn.imd.controle;

import java.util.ArrayList;

import application.br.ufrn.imd.dao.MusicaDAO;
import application.br.ufrn.imd.modelo.Musica;

public class MusicaControle {
    public MusicaControle() {

    }

    public boolean cadastrarMusicasNoBanco(String nome, String artista, String caminho) {
        Musica musica = new Musica(nome, artista, caminho);
        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.cadastrarMusica(musica);
    }

    public ArrayList<Musica> listarMusicas() {
        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.exibirMusicas();
    }

    public Musica buscarMusicaNoBanco(String nome, String artista) {
        MusicaDAO musicaDao = new MusicaDAO();
        Musica musica = musicaDao.buscarMusica(nome, artista);
        return musica;
    }

    public boolean editarMusicasNoBanco(String nome, String artista, String novoAtributo, String tipoAtributo) {
        Musica musica = buscarMusicaNoBanco(nome, artista);

        if (musica == null) {
            return false;
        }

        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.editarMusica(musica, novoAtributo, tipoAtributo);
    }

    public boolean removerMusicasDoBanco(String nome, String artista) {
        Musica musica = buscarMusicaNoBanco(nome, artista);

        if (musica == null) {
            return false;
        }

        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.removerMusica(musica);
    }
}
