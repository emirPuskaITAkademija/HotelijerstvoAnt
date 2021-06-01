package app.controller.event;

import app.privilege.entity.Privilege;
import app.user.entity.User;
import app.user.entity.dao.UserJpaDao;
import app.commons.constants.Constants;
import app.Controller;
import app.login.LoginView;
import app.privilege.AccessPrivilege;
import app.user.admin.AdminView;
import app.user.employee.UserView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class LoginEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        LoginView loginView = Controller.instance().getLoginView();
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        if (username.isEmpty() || password.isEmpty()) {
            loginView.setLoginMessage(Constants.EMPTY_LOGIN_FIELDS_MESSAGE);
            return;
        }
        UserJpaDao userJpaDao = new UserJpaDao();
        //amer.jahjefendic  amer123
        User user = userJpaDao.login(username, password);
        if (user == null) {
            loginView.setLoginMessage(Constants.BAD_USERNAME_AND_PASSWORD_COMBINATION);
            return;
        }
        Controller.instance().setLoggedUser(user);
        Privilege userPrivilege = user.getIdPrivilege();
        BorderPane view;
        if (AccessPrivilege.ADMIN.getId() == userPrivilege.getId()) {
            view = new AdminView();
            Controller.instance().setAdminView((AdminView) view);
            Controller.instance().getPrimaryStage().setTitle("Admin panel od korisnika : " + user.getName());
        } else if (AccessPrivilege.EMPLOYEE.getId() == userPrivilege.getId()) {
            view = new UserView();
            Controller.instance().setUserView((UserView) view);
            Controller.instance().getPrimaryStage().setTitle("User panel od korisnika: " + user.getName());
        } else {
            view = new UserView();
            Controller.instance().setUserView((UserView) view);
            Controller.instance().getPrimaryStage().setTitle("User panel od korisnika: " + user.getName());
        }
        Scene scene = new Scene(view, 650, 300);
        Controller.instance().getPrimaryStage().setScene(scene);
    }
}
