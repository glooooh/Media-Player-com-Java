package br.ufrn.imd.visao;

import br.ufrn.imd.modelo.Diretorio;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufrn.imd.modelo.Diretorio;
import br.ufrn.imd.controle.UsuarioControle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
// import java.awt.FlowLayout;

public class TelaRegistro extends JFrame implements ActionListener {
    private Diretorio diretorio;
    private UsuarioControle controller;

    public TelaRegistro() {
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

        JLabel nomeLabel = new JLabel("Nome do Usuário:");
        JTextField nomeCampo = new JTextField(10);
        nomeCampo.setMaximumSize(new Dimension(350, 20));

        String[] opcoes = { "VIP", "Comum" };
        JComboBox<String> tipoBox = new JComboBox<>(opcoes);
        tipoBox.setMaximumSize(new Dimension(100, 20));

        JButton registrarBtn = new JButton("Registrar");
        // registrarBtn.setBounds(200, 300, 250, 70);
        registrarBtn.setForeground(new Color(237, 241, 238));
        registrarBtn.setBackground(new Color(9, 10, 9));

        JButton loginBtn = new JButton("Login");
        // loginBtn.setBounds(200, 300, 250, 70);
        loginBtn.setForeground(new Color(237, 241, 238));
        loginBtn.setBackground(new Color(9, 10, 9));

        registrarBtn.addActionListener(action -> {
            this.cadastrar(action, nomeCampo, loginCampo, senhaCampo, tipoBox);
        });

        registrarBtn.addActionListener(action -> {
            this.login(action);
        });

        panel.add(loginLabel);
        panel.add(loginCampo);
        panel.add(senhaLabel);
        panel.add(senhaCampo);
        panel.add(nomeLabel);
        panel.add(nomeCampo);
        panel.add(tipoBox);
        panel.add(registrarBtn);
        panel.add(loginBtn);

        add(panel);
        setVisible(true);
    }

    private void cadastrar(ActionEvent ActionEvente, JTextField nomeCampo, JTextField loginCampo, JTextField senhaCampo,
            JComboBox<String> tipoCampo) {
        String tipo;

        //Realizar validação das senhas (Caracteres Inválidos)

        if(tipoCampo.getSelectedIndex() == 0){
            tipo = "V";
        }else{
            tipo = "C";
        }

        if(controller.cadastrarUsuarioNoBanco(nomeCampo.getText(), loginCampo.getText(), senhaCampo.getText(), tipo)) {
            JOptionPane.showMessageDialog(null, "Registro Efetuado com Sucesso", "Titulo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Falha no Registro", "Titulo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void login(ActionEvent ActionEvente) {
        new TelaLogin();
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}