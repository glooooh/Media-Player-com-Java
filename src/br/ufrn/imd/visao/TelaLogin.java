package br.ufrn.imd.visao;

import br.ufrn.imd.modelo.Diretorio;
import br.ufrn.imd.controle.UsuarioControle;

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
// import java.awt.FlowLayout;

public class TelaLogin extends JFrame implements ActionListener {
    private Diretorio diretorio;
    private UsuarioControle controller;

    public TelaLogin() {
        controller = new UsuarioControle();

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
        // loginBtn.setBounds(200, 300, 250, 70);
        loginBtn.setForeground(new Color(237, 241, 238));
        loginBtn.setBackground(new Color(9, 10, 9));

        JButton cadastrarBtn = new JButton("Cadastre-se!");
        // cadastrarBtn.setBounds(200, 300, 250, 70);
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
        if(controller.fazerLogin(loginCampo.getText(), senhaCampo.getText())){
            JOptionPane.showMessageDialog(null, "Login Bem-Sucedido" , "Titulo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos" , "Titulo", JOptionPane.ERROR_MESSAGE);
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