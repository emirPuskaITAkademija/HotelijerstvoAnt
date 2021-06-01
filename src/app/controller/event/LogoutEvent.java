package app.controller.event;

import app.Controller;
import app.login.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class LogoutEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        Controller.instance().setLoggedUser(null);
        LoginView loginView = new LoginView();
        Controller.instance().setLoginView(loginView);
        Scene scene = new Scene(loginView, 650, 180);
        Controller.instance().getPrimaryStage().setScene(scene);
    }

}
