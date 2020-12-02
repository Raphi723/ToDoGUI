import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserController {
    public User selectedItem = null;

    public ListView userListView;

    public TextField nameTextField;
    public TextField zipTextField;
    public TextField streetTextField;
    public TextField cityTextField;

    public Button newButton;
    public Button deleteButton;

    public Button saveButton;
    public Button cancelButton;


    public void itemSelected(MouseEvent mouseEvent) {
    }

    public void newButtonClicked(ActionEvent actionEvent) {
    }

    public void deleteButtonClicked(ActionEvent actionEvent) {
        if (selectedItem != null) {
            // delete item
            AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
            try {
                PreparedStatement statement = conn.getConnection().prepareStatement(
                        "DELETE FROM g5_Bearbeiter WHERE bearbeiter_id = " + selectedItem.getBearbeiter_id());
                statement.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void saveButtonClicked (ActionEvent actionEvent){
        }

        public void cancelButtonClicked (ActionEvent actionEvent){
        }
    }
}
