package app.controller;

import app.business.entity.User;
import app.controller.event.EventBus;
import app.gui.LoginView;
import app.gui.admin.AdminView;
import app.gui.user.UserView;
import javafx.stage.Stage;

public class Controller {

    //VIEW
    private Stage primaryStage;
    private LoginView loginView;
    private AdminView adminView;
    private UserView userView;
    private final EventBus eventBus = new EventBus();
    //MODEL DATA
    private User loggedUser;

    private Controller() {
        super();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    

    public EventBus getEventBus() {
        return eventBus;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public AdminView getAdminView() {
        return adminView;
    }

    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    /**
     * Convinient method for acces to our SINGLE controller instance
     */
    private static Controller INSTANCE = null;

    public static Controller instance() {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }
}
