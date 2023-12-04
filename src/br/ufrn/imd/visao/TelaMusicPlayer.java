package br.ufrn.imd.visao;

import javax.swing.BoxLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.ufrn.imd.controle.GerenciadorControle;
import br.ufrn.imd.modelo.UsuarioComum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        alterarNome.addActionListener(action -> {
            this.alterarNome();
        });

        alterarSenha.addActionListener(action -> {
            this.alterarSenha();
        });

        alterarTipo.addActionListener(action -> {
            this.alterarTipo();
        });

        setJMenuBar(barra);

        barra.add(opcoes);
        barra.add(sairMenuItem);

        opcoes.add(alterarNome);
        opcoes.add(alterarSenha);
        opcoes.add(alterarTipo);

        alterarNome.addActionListener(action -> {

        });

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

    }

    public void alterarTipo() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}