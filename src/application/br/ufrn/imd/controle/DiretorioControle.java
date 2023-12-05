package application.br.ufrn.imd.controle;

import application.br.ufrn.imd.dao.DiretorioDAO;

import application.br.ufrn.imd.modelo.Diretorio;

import java.util.ArrayList;

public class DiretorioControle {
     public boolean cadastrarDiretorioNoBanco(String caminho) {
        DiretorioDAO diretorioDao = new DiretorioDAO();
        Diretorio diretorioNovo = new Diretorio(caminho);

        return diretorioDao.cadastrarDiretorio(diretorioNovo);
    }

    public ArrayList<Diretorio> listarDiretoriosDoBanco() {
        DiretorioDAO diretorioDao = new DiretorioDAO();
        return diretorioDao.exibirDiretorios();
    }

    public boolean editarDiretorioNoBanco(Diretorio diretorio, String caminhoNovo) {
        DiretorioDAO diretorioDao = new DiretorioDAO();

        return diretorioDao.editarDiretorio(diretorio, caminhoNovo);
    }

    public Diretorio buscarDiretorioNoBanco(String caminhoDiretorio) {
        DiretorioDAO diretorioDao = new DiretorioDAO();
        Diretorio diretorioNovo = diretorioDao.buscarDiretorio(caminhoDiretorio);
        return diretorioNovo;
    }

    public boolean removerDiretorioDoBanco(String caminhoDiretorio) {
        DiretorioDAO diretorioDao = new DiretorioDAO();
        Diretorio diretorioRemover = new Diretorio(caminhoDiretorio);
        return diretorioDao.removerDiretorio(diretorioRemover);
    }

    // Ia implementar o adicionar musicas ao diretorio mas não sei se fica aqui mesmo
}
