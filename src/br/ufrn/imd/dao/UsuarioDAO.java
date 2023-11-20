package br.ufrn.imd.dao;

import java.util.ArrayList;

import br.ufrn.imd.modelo.Usuario;

public class UsuarioDAO {
    private ArrayList<Usuario> lista_de_usuarios;

    UsuarioDAO () {
        lista_de_usuarios = new ArrayList<>();
    }

    public boolean cadastrarUsuario() {
        return true;
    }

    public boolean fazerLoginUsuario() {
        return true;
    }

    
}
