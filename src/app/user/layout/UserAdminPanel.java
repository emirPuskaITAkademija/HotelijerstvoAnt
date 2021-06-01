package app.user.layout;

import app.Controller;
import app.privilege.entity.Privilege;
import app.privilege.entity.dao.PrivilegeJpaDao;
import app.user.entity.User;
import app.user.entity.dao.UserJpaDao;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UserAdminPanel extends VBox {

    private final Label titleLabel = new Label("Administracija korisnika");
    private TableView<User> userTableView = new TableView<>();
    private ObservableList<User> users = null;

    private TextField usernameInput = new TextField();
    private PasswordField passwordInput = new PasswordField();
    private TextField nameInput = new TextField();
    private TextField surnameInput = new TextField();
    private ChoiceBox<Privilege> privilegeSelector = new ChoiceBox<>();

    private Button addUserButton = new Button("Dodaj");
    private Button deleteUserButton = new Button("Obriši");

    public UserAdminPanel() {
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10));
        loadTableItems();

        TableColumn<User, String> usernameColumn = new TableColumn<>("Korisničko ime");
        usernameColumn.setMinWidth(150);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));

        TableColumn<User, String> surnameColumn = new TableColumn<>("Prezime");
        surnameColumn.setMinWidth(150);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));

        TableColumn<User, String> privilegeColumn = new TableColumn<>("Privilegija");
        privilegeColumn.setMinWidth(150);
        privilegeColumn.setCellValueFactory(new PropertyValueFactory<User, String>("idPrivilege"));

        userTableView.getColumns().addAll(usernameColumn, nameColumn, surnameColumn, privilegeColumn);
        getChildren().addAll(titleLabel, userTableView, getUserCreationForm());
    }

    private HBox getUserCreationForm() {
        HBox hBox = new HBox();
        hBox.setSpacing(3);

        usernameInput.setMaxWidth(100);
        usernameInput.setPromptText("Kor.ime..");
        passwordInput.setMaxWidth(100);
        passwordInput.setPromptText("Lozinka");
        nameInput.setMaxWidth(100);
        nameInput.setPromptText("Ime..");
        surnameInput.setMaxWidth(100);
        surnameInput.setPromptText("Prezime..");
        //List<Privilege> iz baze 
        PrivilegeJpaDao privilegeJpaDao = new PrivilegeJpaDao();
        List<Privilege> privileges = privilegeJpaDao.getAll();
        ObservableList<Privilege> observablePrivileges = FXCollections.observableArrayList(privileges);
        privilegeSelector.setItems(observablePrivileges);
        privilegeSelector.setMaxWidth(100);
        addUserButton.setOnAction(this::insertOrUpdateUser);
        deleteUserButton.setOnAction(this::removeUser);
        hBox
                .getChildren()
                .addAll(usernameInput, passwordInput,
                        nameInput, surnameInput,
                        privilegeSelector,
                        addUserButton, deleteUserButton);
        return hBox;
    }

    private void insertOrUpdateUser(ActionEvent e) {
        String username = usernameInput.getText();
        UserJpaDao userJpaDao = new UserJpaDao();
        User user = userJpaDao.findByUsername(username);
        if (user == null) {
            user = new User();
        }
        user.setUsername(usernameInput.getText());
        user.setPassword(passwordInput.getText());
        user.setName(nameInput.getText());
        user.setSurname(surnameInput.getText());
        user.setIdPrivilege(privilegeSelector.getSelectionModel().getSelectedItem());
        if (user.getId() != null) {
            userJpaDao.update(user);
        } else {
            userJpaDao.save(user);
        }
        clearInputs();
        loadTableItems();
    }

    private void removeUser(ActionEvent event) {
        User user = userTableView.getSelectionModel().getSelectedItem();
        if (user != null) {
            UserJpaDao userJpaDao = new UserJpaDao();
            userJpaDao.delete(user);
        }
        loadTableItems();
    }

    private void loadTableItems() {
        users = FXCollections.observableArrayList(new UserJpaDao().getAll());
        users.remove(Controller.instance().getLoggedUser());
        userTableView.setItems(users);
    }

    private void clearInputs() {
        usernameInput.clear();
        passwordInput.clear();
        nameInput.clear();
        surnameInput.clear();

    }

}
