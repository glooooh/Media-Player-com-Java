package application.br.ufrn.imd.controle;

import java.io.IOException;

import application.br.ufrn.imd.visao.TelaMusicPlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControle {
	private GerenciadorControle controller;
    
    @FXML
    private TextField campoUsuario;
    
    @FXML
    private PasswordField campoSenha;
    
    @FXML
    private ChoiceBox<String> choiceBox;
    
    public void setController(GerenciadorControle controller) {
        this.controller = controller;
    }

    // Método para inicializar o controlador após o carregamento do arquivo FXML
    @FXML
    public void initialize() {
    	this.setController(new GerenciadorControle());
        // Aqui você pode adicionar opções à ChoiceBox
    	if (choiceBox != null) {
            // Faça algo com o choiceBox
            choiceBox.getItems().addAll("Comum", "VIP");
        }
    }
    
    @FXML
    public void processarLogin() {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();

        if (controller.fazerLogin(usuario, senha)) {
            Stage primaryStage = (Stage) campoUsuario.getScene().getWindow();
            TelaMusicPlayer telaMusicPlayer = new TelaMusicPlayer(controller.getUsuarioLogado());
            try {
                telaMusicPlayer.start(new Stage());
                primaryStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            exibirAlerta("Usuário ou senha incorretos!");
        }
    }
    
    @FXML
    public void processarRegistro() throws IOException {
        Stage registroStage = new Stage();
        
        Stage loginStage = (Stage) campoUsuario.getScene().getWindow();
        
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/br/ufrn/imd/recursos/TelaRegistro.fxml"));
        Parent root = loader.load();
        
        registroStage.setTitle("Tela de Registro");
        registroStage.setScene(new Scene(root, 600, 400));
        registroStage.show();

        RegistroControle registroControle = new RegistroControle();
        loader.setController(registroControle);
        
        loginStage.close();
    }


    private void exibirAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    
    
}
