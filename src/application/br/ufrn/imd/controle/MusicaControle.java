package application.br.ufrn.imd.controle;

import java.util.ArrayList;

import application.br.ufrn.imd.dao.MusicaDAO;
import application.br.ufrn.imd.modelo.Musica;

public class MusicaControle {
    public MusicaControle() {

    }

    public boolean cadastrarMusicasNoBanco(String nome, String caminho) {
        Musica musica = new Musica(nome, caminho);
        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.cadastrarMusica(musica);
    }

    public ArrayList<Musica> listarMusicas() {
        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.exibirMusicas();
    }

    public Musica buscarMusicaNoBanco(String nome, String caminho) {
        MusicaDAO musicaDao = new MusicaDAO();
        Musica musica = musicaDao.buscarMusica(nome, caminho);
        return musica;
    }

    public boolean editarMusicasNoBanco(String nome, String caminho ,String novoAtributo, String tipoAtributo) {
        Musica musica = buscarMusicaNoBanco(nome, caminho);

        if (musica == null) {
            return false;
        }

        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.editarMusica(musica, novoAtributo, tipoAtributo);
    }

    public boolean removerMusicasDoBanco(String nome, String caminho) {
        Musica musica = buscarMusicaNoBanco(nome, caminho);

        if (musica == null) {
            return false;
        }

        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.removerMusica(musica);
    }
}
