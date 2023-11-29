package br.ufrn.imd.visao;

import javax.swing.BoxLayout;
//import javax.swing.JButton;

// import br.ufrn.imd.modelo.Diretorio;

import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;

import br.ufrn.imd.controle.UsuarioControle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMusicPlayer extends JFrame implements ActionListener {
    private UsuarioControle controller;

    public TelaMusicPlayer() {
        controller = new UsuarioControle();

        setTitle("Music Player");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JMenuBar barra = new JMenuBar();

        JMenu opcoes = new JMenu("Opções");
        JMenu sair = new JMenu("Sair");

        JMenuItem alterarNome = new JMenuItem("Alterar Nome");
        JMenuItem alterarSenha = new JMenuItem("Alterar Senha");
        JMenuItem alterarTipo = new JMenuItem("Alterar Tipo de Usuário");

        setJMenuBar(barra);

        barra.add(opcoes);
        barra.add(sair);

        opcoes.add(alterarNome);
        opcoes.add(alterarSenha);
        opcoes.add(alterarTipo);

        alterarNome.addActionListener(action -> {

        });

        // panel.add(loginLabel);

        // add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}