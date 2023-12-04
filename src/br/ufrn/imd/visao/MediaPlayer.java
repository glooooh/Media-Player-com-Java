package br.ufrn.imd.visao;

import javafx.application.Application;
import javafx.stage.Stage;

public class MediaPlayer extends Application {
	
	@Override
    public void start(Stage primaryStage) {
        try {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.start(primaryStage); // Chama o método start da TelaLogin
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
        launch(args);
    }
}


//
//public class MediaPlayer {
//  public void exibirInterfaceGrafica() {
//      new TelaLogin();
//  }
//
//  public static void main(String[] args) {
//      MediaPlayer mediaPlayer = new MediaPlayer();
//      mediaPlayer.exibirInterfaceGrafica();
//  }
//}