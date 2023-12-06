package application.br.ufrn.imd.dao;

import application.br.ufrn.imd.modelo.UsuarioComum;
import application.br.ufrn.imd.modelo.UsuarioVIP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UsuarioDAO {
    private ArrayList<UsuarioComum> lista_de_usuarios;

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

    public boolean cadastrarUsuario(UsuarioComum usuarioNovo) {
        for (UsuarioComum usuario : lista_de_usuarios) {
            if (usuarioNovo.getLogin().equals(usuario.getLogin())) {
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

            FileWriter fw = new FileWriter(arquivoUsuarios.getAbsoluteFile(), true); // true para adicionar no final do
                                                                                     // arquivo
            BufferedWriter bw = new BufferedWriter(fw);

            // Texto a ser escrito no arquivo
            String tipo = "";
            if (usuarioNovo instanceof UsuarioVIP) {
                tipo = "V";
            } else {
                tipo = "C";
            }
            String texto = usuarioNovo.getLogin() + " " + usuarioNovo.getSenha() + " " + usuarioNovo.getNome() + " "
                    + tipo;

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

    public ArrayList<UsuarioComum> exibirUsuarios() {
        return lista_de_usuarios;
    }

    public boolean removerUsuario(UsuarioComum usuarioLogado, String senha) {
        for (UsuarioComum usuario : lista_de_usuarios) {
            if ((usuarioLogado.getLogin()).equals(usuario.getLogin()) && senha.equals(usuario.getSenha())) {
                String caminho = System.getProperty("user.dir");
                String separador = System.getProperty("file.separator");

                File arquivoUsuarios = new File(caminho + separador + "usuarios.txt");

                try {
                    FileReader fr = new FileReader(arquivoUsuarios);
                    BufferedReader reader = new BufferedReader(fr);

                    String linha = reader.readLine();
                    ArrayList<String> salvarLinhas = new ArrayList<>();

                    while (linha != null) {
                        String palavras[] = linha.split(" ");

                        // Falha de segurança
                        if (palavras[0].equals(usuarioLogado.getLogin()) || palavras[1].equals(usuarioLogado.getSenha())) {
                            salvarLinhas.add(linha);
                        }
                        linha = reader.readLine();
                    }

                    reader.close();
                    fr.close();

                    FileWriter apagarArquivo = new FileWriter(arquivoUsuarios, true);
                    apagarArquivo.close();

                    FileWriter fw = new FileWriter(arquivoUsuarios);
                    BufferedWriter writer = new BufferedWriter(fw);

                    for (int i = 0; i < salvarLinhas.size(); i++) {
                        writer.write(salvarLinhas.get(i));
                        writer.newLine();
                    }

                    fw.close();
                    writer.close();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public boolean editarUsuario(UsuarioComum usuarioLogado, String atributoNovo, String tipoAtributo) {
        for (UsuarioComum usuario : lista_de_usuarios) {
            if ((usuarioLogado.getLogin()).equals(usuario.getLogin())) {
                String caminho = System.getProperty("user.dir");
                String separador = System.getProperty("file.separator");

                File arquivoUsuarios = new File(caminho + separador + "usuarios.txt");

                try {
                    FileReader fr = new FileReader(arquivoUsuarios);
                    BufferedReader reader = new BufferedReader(fr);

                    String linha = reader.readLine();
                    ArrayList<String> salvarLinhas = new ArrayList<>();

                    String tipo = usuario.ehVIP() ? "V" : "C";

                    while (linha != null) {
                        String palavras[] = linha.split(" ");

                        if (palavras[0].equals(usuarioLogado.getLogin())) {
                            if (tipoAtributo.equals("N")) {
                                linha = usuario.getLogin() + " " + usuario.getSenha() + " " + atributoNovo + " "
                                        + tipo;
                            } else if (tipoAtributo.equals("S")) {
                                linha = usuario.getLogin() + " " + atributoNovo + " " + usuario.getNome() + " "
                                        + tipo;
                            } else if (tipoAtributo.equals("T")) {
                                linha = usuario.getLogin() + " " + usuario.getSenha() + " " + usuario.getNome() + " "
                                        + atributoNovo;
                            }
                            salvarLinhas.add(linha);
                        } else {
                            salvarLinhas.add(linha);
                        }
                        linha = reader.readLine();
                    }

                    reader.close();
                    fr.close();

                    FileWriter apagarArquivo = new FileWriter(arquivoUsuarios, true);
                    apagarArquivo.close();

                    FileWriter fw = new FileWriter(arquivoUsuarios);
                    BufferedWriter writer = new BufferedWriter(fw);

                    for (int i = 0; i < salvarLinhas.size(); i++) {
                        writer.write(salvarLinhas.get(i));
                        writer.newLine();
                    }

                    writer.close();
                    fw.close();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public UsuarioComum fazerLoginUsuario(String login, String senha) {
        for (UsuarioComum usuario : lista_de_usuarios) {
            if (login.equals(usuario.getLogin()) && usuario.fazerLogin(senha)) {
                return usuario;
            }
        }

        return null;
    }
}