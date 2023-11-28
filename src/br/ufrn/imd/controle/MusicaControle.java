package br.ufrn.imd.controle;

import java.util.ArrayList;

import br.ufrn.imd.dao.MusicaDAO;
import br.ufrn.imd.modelo.Musica;

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

    public boolean editarMusicas(String nome, String artista, String novoAtributo, String tipoAtributo) {
        MusicaDAO musicaDao = new MusicaDAO();
        return musicaDao.editarMusica(nome, artista, novoAtributo, tipoAtributo);
    }
}
