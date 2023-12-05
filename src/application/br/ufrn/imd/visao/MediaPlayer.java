package br.ufrn.imd.visao;

public class MediaPlayer {
    public void exibirInterfaceGrafica() {
        new TelaLogin();
    }

    public static void main(String[] args) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.exibirInterfaceGrafica();
    }
}