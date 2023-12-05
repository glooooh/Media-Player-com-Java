package br.ufrn.imd.visao;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.ufrn.imd.controle.GerenciadorControle;
import br.ufrn.imd.modelo.UsuarioComum;
import javafx.stage.FileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class TelaMusicPlayer extends JFrame implements ActionListener {
    private GerenciadorControle controller;

    public TelaMusicPlayer(UsuarioComum usuario) {
        controller = new GerenciadorControle();
        controller.setUsuarioLogado(usuario);

        setTitle("Music Player");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JMenuBar barra = new JMenuBar();

        JMenu opcoes = new JMenu("Opções");
        JMenuItem sairMenuItem = new JMenuItem("Sair");

        sairMenuItem.addActionListener(action -> {
            this.sair();
        });

        JMenuItem alterarNome = new JMenuItem("Alterar Nome");
        JMenuItem alterarSenha = new JMenuItem("Alterar Senha");
        JMenuItem alterarTipo = new JMenuItem("Alterar Tipo de Usuário");

        JList<String> listaMusicasPlaylist = new JList<String>();
        JList<String> listaDiretorio = new JList<String>();
        JList<String> listaPlaylist = new JList<String>();

        JButton adicionarMusica = new JButton("Add File");
        JButton adicionarDirectory = new JButton("Add Directory");
        JButton adicionarPlaylist = new JButton("Add Playlist");

        alterarNome.addActionListener(action -> {
            this.alterarNome();
        });

        alterarSenha.addActionListener(action -> {
            this.alterarSenha();
        });

        alterarTipo.addActionListener(action -> {
            this.alterarTipo();
        });

        adicionarMusica.addActionListener(action -> {
            this.adicionarMusica(action);
        });

        adicionarDirectory.addActionListener(action -> {
            this.adicionarDiretorio(action);
        });

        adicionarPlaylist.addActionListener(action -> {
            this.adicionarPlaylist(action);
        });

        setJMenuBar(barra);

        barra.add(opcoes);
        barra.add(sairMenuItem);

        opcoes.add(alterarNome);
        opcoes.add(alterarSenha);
        opcoes.add(alterarTipo);

        panel.add(listaMusicasPlaylist);
        panel.add(listaDiretorio);
        panel.add(listaPlaylist);

        panel.add(adicionarMusica);
        panel.add(adicionarDirectory);
        panel.add(adicionarPlaylist);

        add(panel);

        setVisible(true);
    }

    public void sair() {
        controller.fazerLogout();
        new TelaLogin();
        dispose();
    }

    public void alterarNome() {
        String novoNome = JOptionPane.showInputDialog(this, "Digite o novo nome:");

        if (novoNome != null) {
            controller.editarUsuario(novoNome, "N");
            // Exemplo: controller.alterarNome(novoNome);
            JOptionPane.showMessageDialog(this, "Nome alterado com sucesso para: " + novoNome);
        }
    }

    public void alterarSenha() {
        String novaSenha = JOptionPane.showInputDialog(this, "Digite a nova senha:");

        if (novaSenha != null) {
            controller.editarUsuario(novaSenha, "S");
            // Exemplo: controller.alterarNome(novoNome);
            JOptionPane.showMessageDialog(this, "Senha alterada com sucesso.");
        }
    }

    public void alterarTipo() {
        int resposta = JOptionPane.showConfirmDialog(null, "Você deseja mudar a assinatura?", "Assinatura",
                JOptionPane.YES_NO_OPTION);

        if (resposta == 0) {
            controller.editarAssinatura();
            // Exemplo: controller.alterarNome(novoNome);
            JOptionPane.showMessageDialog(this, "Assinatura alterada com sucesso.");
        }
    }

    private void adicionarMusica(ActionEvent ActionEvente) {
        JFileChooser selecionarMusica = new JFileChooser();

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Apenas .mp3", "mp3");
        selecionarMusica.setAcceptAllFileFilterUsed(false);
        selecionarMusica.setFileFilter(filtro);

        int respostaDoFileChooser = selecionarMusica.showOpenDialog(null);

        if (respostaDoFileChooser == selecionarMusica.APPROVE_OPTION) {
            File arquivoMusica = selecionarMusica.getSelectedFile();
            System.out.println("CAMINHO DO ARQUIVO ABERTO = " + arquivoMusica.getAbsolutePath());
            try {

                new Player(new BufferedInputStream(new FileInputStream(arquivoMusica.getAbsolutePath()))).play();
            } catch (Exception e) {
                // TODO: handle exception
            }
            JLayer musica = new JLayer<>();
        } else {
            System.out.println("Nenhum arquivo Selecionado");
        }
    }

    private void adicionarDiretorio(ActionEvent ActionEvente) {

    }

    private void adicionarPlaylist(ActionEvent ActionEvente) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}