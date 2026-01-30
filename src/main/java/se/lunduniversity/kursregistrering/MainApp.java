package se.lunduniversity.kursregistrering;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                MainApp.class.getResource("/se/lunduniversity/kursregistrering/main-view.fxml")
        );

        stage.setScene(new Scene(loader.load(), 900, 500));
        stage.setTitle("Kursregistrering");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
