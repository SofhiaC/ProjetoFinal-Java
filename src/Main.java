import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Interface tela = new Interface();
            Scene scene = new Scene(tela.criarInterface(), 1000, 400);

            primaryStage.setTitle("Biblioteca de Jogos");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao carregar a interface");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
