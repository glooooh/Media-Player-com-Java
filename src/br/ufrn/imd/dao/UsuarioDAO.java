package br.ufrn.imd.dao;

import java.util.ArrayList;

import br.ufrn.imd.modelo.Usuario;
import br.ufrn.imd.modelo.UsuarioComum;
import br.ufrn.imd.modelo.UsuarioVIP;

public class UsuarioDAO {
    private ArrayList<Usuario> lista_de_usuarios;

    public UsuarioDAO() {
        lista_de_usuarios = new ArrayList<>();
    }

    public boolean cadastrarUsuario(Usuario usuarioNovo) {
        for (Usuario usuario : lista_de_usuarios) {
            if (usuarioNovo.getLogin() == usuario.getLogin()) {
                return false;
            }
        }

        lista_de_usuarios.add(usuarioNovo);

        return true;
    }

    public boolean fazerLoginUsuario(String login, String senha) {
        for (Usuario usuario : lista_de_usuarios) {
            if (login == usuario.getLogin()) {
                return usuario.fazerLogin(senha);
            }
        }

        return false;
    }

}
