package app.controller.event;

import app.business.entity.Privilege;
import app.business.entity.User;
import app.business.entity.dao.UserJpaDao;
import app.constants.Constants;
import app.controller.Controller;
import app.gui.LoginView;
import app.gui.admin.AdminView;
import app.gui.user.UserView;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class LoginEvent implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent event) {
        LoginView loginView= Controller.instance().getLoginView();
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        if(username.isEmpty() || password.isEmpty()){
            loginView.setLoginMessage(Constants.EMPTY_LOGIN_FIELDS_MESSAGE);
            return;
        }
        UserJpaDao userJpaDao = new UserJpaDao();
        User user = userJpaDao.login(username, password);
        Controller.instance().setLoggedUser(user);
        if(user == null){
            loginView.setLoginMessage(Constants.BAD_USERNAME_AND_PASSWORD_COMBINATION);
            return;
        }
        Privilege userPrivilege = user.getIdPrivilege();
        BorderPane view;
        if("admin".equals(userPrivilege.getName())){
            view = new AdminView();
            Controller.instance().setAdminView((AdminView)view);
            Controller.instance().getPrimaryStage().setTitle("Admin panel od korisnika : " + user.getName());
        }else{
            view = new UserView();
            Controller.instance().setUserView((UserView)view);
            Controller.instance().getPrimaryStage().setTitle("User panel od korisnika: " + user.getName());
        }
        Scene scene = new Scene(view, 650, 300);
        Controller.instance().getPrimaryStage().setScene(scene);
    }
}
