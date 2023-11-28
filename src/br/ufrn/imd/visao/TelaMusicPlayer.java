package br.ufrn.imd.visao;

import javax.swing.BoxLayout;
import javax.swing.JButton;

// import br.ufrn.imd.modelo.Diretorio;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.ufrn.imd.controle.UsuarioControle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMusicPlayer extends JFrame implements ActionListener {

    public TelaMusicPlayer() {
        controller = new UsuarioControle();

        setTitle("Music Player");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JMenuBar barra = new

        loginBtn.addActionListener(action -> {
            this.logar(action, loginCampo, senhaCampo);
        });

        panel.add(loginLabel);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}