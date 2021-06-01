package app.user.admin;

import app.Controller;
import app.room.layout.RoomAdminPanel;
import app.user.layout.UserAdminPanel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class AdminView extends BorderPane{
    private ToggleButton userButton = new ToggleButton("Korisnici");
    private ToggleButton roomButton = new ToggleButton("Sobe");
    private Button logoutButton = new Button("Odjava");
    
    private UserAdminPanel userAdminPanel;
    private RoomAdminPanel roomAdminPanel;

    public AdminView() {
        userAdminPanel = new UserAdminPanel();
        setCenter(userAdminPanel);
        
        ToggleGroup toggleGroup = new ToggleGroup();
        userButton.setToggleGroup(toggleGroup);
        userButton.setSelected(true);
        userButton.setOnAction(this::onUserButtonClick);
        roomButton.setToggleGroup(toggleGroup);
        roomButton.setOnAction(this::onRoomButtonClick);
        
        HBox mainMenuBox = new HBox();
        mainMenuBox.setSpacing(5);
        mainMenuBox.setPadding(new Insets(10));
        mainMenuBox.getChildren().addAll(userButton, roomButton);
        
        logoutButton.setText("Odjava(" + Controller.instance().getLoggedUser()+")");
        logoutButton.setOnAction(Controller.instance().getEventBus().getLogoutEvent());
        HBox logoutBox = new HBox(logoutButton);
        logoutBox.setAlignment(Pos.CENTER);
        logoutBox.setPadding(new Insets(10));
        
        GridPane topPane = new GridPane();
        topPane.add(mainMenuBox, 0, 0);
        topPane.add(logoutBox, 1, 0);
        
        setTop(topPane);
    }
    
    private void onUserButtonClick(ActionEvent e){
        userAdminPanel = new UserAdminPanel();
        setCenter(userAdminPanel);
    }
    
    private void onRoomButtonClick(ActionEvent e){
        roomAdminPanel = new RoomAdminPanel();
        setCenter(roomAdminPanel);
    }

    public UserAdminPanel getUserAdminPanel() {
        return userAdminPanel;
    }

    public RoomAdminPanel getRoomAdminPanel() {
        return roomAdminPanel;
    }

    public void setUserAdminPanel(UserAdminPanel userAdminPanel) {
        this.userAdminPanel = userAdminPanel;
    }

    public void setRoomAdminPanel(RoomAdminPanel roomAdminPanel) {
        this.roomAdminPanel = roomAdminPanel;
    }
    
    
    
    
}
