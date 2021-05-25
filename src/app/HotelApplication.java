package app;

import app.controller.Controller;
import app.gui.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HotelApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Controller.instance().setPrimaryStage(primaryStage);
        LoginView loginView = new LoginView();
        Controller.instance().setLoginView(loginView);
        Scene scene = new Scene(loginView, 650, 180);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ekran za prijavu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
