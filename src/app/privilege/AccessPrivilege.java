package app.privilege;

import app.privilege.entity.Privilege;
import app.user.admin.AdminView;
import app.user.employee.UserView;
import javafx.scene.layout.Pane;

public enum AccessPrivilege {
    ADMIN(1, "admin", new AdminView()),
    EMPLOYEE(2, "user", new UserView());

    private final int id;
    private final String name;
    private final Pane layoutPane;

    private AccessPrivilege(int id, String name, Pane layoutPane) {
        this.id = id;
        this.name = name;
        this.layoutPane = layoutPane;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

 

    public static Pane getLayout(Privilege privilege) {

        AccessPrivilege[] accessPrivileges = AccessPrivilege.values();
        for (AccessPrivilege accessPrivilege : accessPrivileges) {
            if (accessPrivilege.id == privilege.getId()) {
                return accessPrivilege.layoutPane;
            }
        }
        return new UserView();
    }
}
