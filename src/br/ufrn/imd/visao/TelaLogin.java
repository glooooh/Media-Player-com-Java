package br.ufrn.imd.visao;

import br.ufrn.imd.controle.GerenciadorControle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class TelaLogin extends JFrame implements ActionListener {
    private GerenciadorControle controller;

    public TelaLogin() {
        controller = new GerenciadorControle();

        setTitle("Music Player");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel loginLabel = new JLabel("Login:");
        JTextField loginCampo = new JTextField(10);
        loginCampo.setMaximumSize(new Dimension(350, 20));

        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaCampo = new JPasswordField(15);
        senhaCampo.setMaximumSize(new Dimension(350, 20));

        JButton loginBtn = new JButton("Login");
        loginBtn.setForeground(new Color(237, 241, 238));
        loginBtn.setBackground(new Color(9, 10, 9));

        JButton cadastrarBtn = new JButton("Cadastre-se!");
        cadastrarBtn.setForeground(new Color(237, 241, 238));
        cadastrarBtn.setBackground(new Color(9, 10, 9));

        loginBtn.addActionListener(action -> {
            this.logar(action, loginCampo, senhaCampo);
        });

        cadastrarBtn.addActionListener(action -> {
            this.cadastrar(action);
        });

        panel.add(loginLabel);
        panel.add(loginCampo);
        panel.add(senhaLabel);
        panel.add(senhaCampo);
        panel.add(loginBtn);
        panel.add(cadastrarBtn);

        add(panel);
        setVisible(true);
    }

    private void logar(ActionEvent ActionEvente, JTextField loginCampo, JTextField senhaCampo) {
        if (controller.fazerLogin(loginCampo.getText(), senhaCampo.getText())) {
            new TelaMusicPlayer(controller.getUsuarioLogado());
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos", "Titulo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cadastrar(ActionEvent ActionEvente) {
        new TelaRegistro();
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}