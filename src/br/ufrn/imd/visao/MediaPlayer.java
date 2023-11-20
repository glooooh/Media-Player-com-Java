package br.ufrn.imd.visao;

import br.ufrn.imd.modelo.Screen;

public class MediaPlayer {
    public void exibirInterfaceGrafica() {
        new Screen();
    }

    public static void main(String[] args) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.exibirInterfaceGrafica();
    }
}