package br.ufrn.imd.visao;

import br.ufrn.imd.modelo.Diretorio;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
// import java.awt.FlowLayout;

public class TelaLogin extends JFrame implements ActionListener {
    private Diretorio diretorio;

    public TelaLogin() {
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
        JTextField senhaCampo = new JTextField(15);
        senhaCampo.setMaximumSize(new Dimension(350, 20));

        JButton loginBtn = new JButton("Login");
        // loginBtn.setBounds(200, 300, 250, 70);
        loginBtn.setForeground(new Color(237, 241, 238));
        loginBtn.setBackground(new Color(9, 10, 9));

        loginBtn.addActionListener(action -> {
            this.logar(action, loginCampo, senhaCampo);
        });

        panel.add(loginLabel);
        panel.add(loginCampo);
        panel.add(senhaLabel);
        panel.add(senhaCampo);
        panel.add(loginBtn);

        add(panel);
        setVisible(true);
    }

    private void logar(ActionEvent ActionEvente, JTextField loginCampo, JTextField senhaCampo) {
        JOptionPane.showMessageDialog(null, loginCampo.getText(), "Titulo", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}