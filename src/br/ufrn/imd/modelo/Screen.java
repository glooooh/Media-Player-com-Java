package br.ufrn.imd.modelo;

import javax.swing.JFrame;

public class Screen{
    public Screen(){
        JFrame login = new JFrame("Login");
        login.setVisible(true);
        login.setSize(800, 400);
        login.setDefaultCloseOperation(login.EXIT_ON_CLOSE);
        login.setResizable(false);
        login.setLocationRelativeTo(null);
    }
    

}