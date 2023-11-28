package br.ufrn.imd.controle;

import java.util.ArrayList;

import br.ufrn.imd.dao.UsuarioDAO;
import br.ufrn.imd.modelo.UsuarioComum;
import br.ufrn.imd.modelo.UsuarioVIP;

/*
 * A ideia dele é a seguinte, no arquivo de controller (no seu caso, o
 * ProdutoController),
 * você teria uma função para cada método (inserir, novo registro, atualizar,
 * listar, editar,
 * ou outra ações relacionadas).
 */

public class UsuarioControle {

    public boolean cadastrarUsuarioNoBanco(String nome, String login, String senha, String tipo) {
        UsuarioComum user;
        if (tipo.equals("V")) {
            user = new UsuarioVIP(nome, login, senha);
        } else if (tipo.equals("C")) {
            user = new UsuarioComum(nome, login, senha);
        } else {
            return false;
        }

        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.cadastrarUsuario(user);
    }

    public ArrayList<UsuarioComum> listarUsuarios() {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.exibirUsuarios();
    }

    public boolean editarUsuario(String login, String atributoNovo, String tipoAtributo) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.editarUsuario(login, atributoNovo, tipoAtributo);
    }

    public boolean removerUsuario(String login, String senha) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.removerUsuario(login, senha);
    }

    public boolean fazerLogin(String login, String senha) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.fazerLoginUsuario(login, senha);
    }
}
