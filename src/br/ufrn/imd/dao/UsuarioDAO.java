package br.ufrn.imd.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import br.ufrn.imd.modelo.Usuario;
import br.ufrn.imd.modelo.UsuarioComum;
import br.ufrn.imd.modelo.UsuarioVIP;

public class UsuarioDAO {
    private ArrayList<Usuario> lista_de_usuarios;

    public UsuarioDAO() {
        lista_de_usuarios = new ArrayList<>();
        carregarUsuarios();
    }

    public void carregarUsuarios() {
        String caminho = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");

        File arquivoUsuarios = new File(caminho + separador + "usuarios.txt");

        try {
            Scanner scanEntrada = new Scanner(arquivoUsuarios);

            String login, senha, nome, tipo;

            while (scanEntrada.hasNext()) {
                login = scanEntrada.next();
                senha = scanEntrada.next();
                nome = scanEntrada.next();
                tipo = scanEntrada.next();
                if (tipo.equals("V")) {
                    lista_de_usuarios.add(new UsuarioVIP(nome, login, senha));
                } else if (tipo.equals("C")) {
                    lista_de_usuarios.add(new UsuarioComum(nome, login, senha));
                }
            }

            scanEntrada.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Formato inválido do arquivo: " + e.getMessage());
        }
    }

    public boolean cadastrarUsuario(Usuario usuarioNovo) {
        for (Usuario usuario : lista_de_usuarios) {
            if (usuarioNovo.getLogin() == usuario.getLogin()) {
                return false;
            }
        }

        lista_de_usuarios.add(usuarioNovo);

        String nomeArquivo = "usuarios.txt"; 

        try {
            File arquivoUsuarios = new File(nomeArquivo);
            if (!arquivoUsuarios.exists()) {
                arquivoUsuarios.createNewFile();
            }

            FileWriter fw = new FileWriter(arquivoUsuarios.getAbsoluteFile(), true); // true para adicionar no final do arquivo
            BufferedWriter bw = new BufferedWriter(fw);

            // Texto a ser escrito no arquivo
            String tipo = "";
            if (usuarioNovo instanceof UsuarioVIP) {
                tipo = "V";
            } else {
                tipo = "C";
            }
            String texto = usuarioNovo.getLogin() + " " + usuarioNovo.getSenha() + " " + usuarioNovo.getNome() + " " + tipo;

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

    public boolean fazerLoginUsuario(String login, String senha) {
        carregarUsuarios();
        for (Usuario usuario : lista_de_usuarios) {
            if (login.equals(usuario.getLogin())) {
                return usuario.fazerLogin(senha);
            }
        }

        return false;
    }

}
