package application.br.ufrn.imd.visao;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principal que inicia a aplicação JavaFX do MediaPlayer.
 */
public class MediaPlayer extends Application {

    /**
     * Método principal que inicia a aplicação JavaFX.
     *
     * @param primaryStage O palco principal (Stage) da aplicação.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Carrega o arquivo FXML
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/application/br/ufrn/imd/recursos/TelaLogin.fxml"));
            // loader.setController(new LoginControle());
            Parent root = loader.load();

            // Configura o palco (Stage)
            primaryStage.setTitle("Tela de Login");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método principal que inicia a execução da aplicação JavaFX.
     *
     * @param args Os argumentos da linha de comando (não são utilizados neste exemplo).
     */
    public static void main(String[] args) {
        launch(args);
    }
}

	// @Override
	// public void start(Stage primaryStage) {
	// TelaLogin telaLogin = new TelaLogin();
	// telaLogin.start(primaryStage); // Chame o método start e passe o Stage
	// }
	//
	// public static void main(String[] args) {
	// launch(args);
	// }

	// public void start(Stage primaryStage) {
	// try {
	// BorderPane root = new BorderPane();
	// Scene scene = new Scene(root,400,400);
	// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	// primaryStage.setScene(scene);
	// primaryStage.show();
	// } catch(Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public static void main(String[] args) {
	// launch(args);
	// }
}
