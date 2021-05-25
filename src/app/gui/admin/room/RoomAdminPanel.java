package app.gui.admin.room;

import app.business.entity.Room;
import app.business.entity.User;
import app.business.entity.dao.RoomJpaDao;
import app.business.entity.dao.UserJpaDao;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class RoomAdminPanel extends VBox{
     private final Label titleLabel = new Label("Administracija soba");
    private TableView<Room> roomTableView = new TableView<>();
    private ObservableList<Room> rooms = null;

    public RoomAdminPanel() {
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10));
        List<Room> roomList = new RoomJpaDao().getAll();
        rooms = FXCollections.observableArrayList(roomList);
        roomTableView.setItems(rooms);

        TableColumn<Room, String> codeColumn = new TableColumn<>("Šifra sobe");
        codeColumn.setMinWidth(150);
        codeColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("username"));

        TableColumn<Room, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));

        TableColumn<Room, String> surnameColumn = new TableColumn<>("Prezime");
        surnameColumn.setMinWidth(150);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("surname"));

        TableColumn<Room, String> privilegeColumn = new TableColumn<>("Privilegija");
        privilegeColumn.setMinWidth(150);
        privilegeColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("idPrivilege"));

        roomTableView.getColumns().addAll(codeColumn, nameColumn, surnameColumn, privilegeColumn);
        getChildren().addAll(titleLabel, roomTableView);
    }

}
