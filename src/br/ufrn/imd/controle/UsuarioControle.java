package br.ufrn.imd.controle;

import br.ufrn.imd.dao.UsuarioDAO;
import br.ufrn.imd.modelo.Usuario;
import br.ufrn.imd.modelo.UsuarioComum;
import br.ufrn.imd.modelo.UsuarioVIP;

public class UsuarioControle {
    /*
     * A ideia dele é a seguinte, no arquivo de controller (no seu caso, o
     * ProdutoController),
     * você teria uma função para cada método (inserir, novo registro, atualizar,
     * listar, editar,
     * ou outra ações relacionadas).
     */

    public boolean cadastrarUsuarioNoBanco(String nome, String login, String senha, String tipo) {
        Usuario user;
        if (tipo == "V") {
            user = new UsuarioVIP(nome, login, senha);
        } else if (tipo == "C") {
            user = new UsuarioComum(nome, login, senha);
        } else {
            return false;
        }

        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.cadastrarUsuario(user);
    }
}
