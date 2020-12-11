import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Priority;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PriorityController {

    public ListView<Priority> priorityListView;
    public TextField priorityName;
    public Priority selectedItem = null;
    public Button cancelButton;

    public void initialize() {
        priorityListView.setItems(Priority.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {
        Priority p = priorityListView.getSelectionModel().getSelectedItem();
        if (p != null) {
            priorityName.setText(p.getName());
            selectedItem = p;
        }
    }

    public void cancelClicked(MouseEvent mouseEvent) {
        // close dialog
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void saveClicked(MouseEvent mouseEvent) {
        if (selectedItem != null) {
            // update existing item

            Priority.update(priorityName.getText(), selectedItem.getId());

        } else {
            // insert new item

            Priority.newPriority(priorityName.getText());
        }

        priorityListView.setItems(Priority.getList());
    }

    public void deleteClicked(MouseEvent mouseEvent) {
        if (selectedItem != null) {
            // delete item

            Priority.delete(selectedItem.getId());

        }
        priorityListView.setItems(Priority.getList());
    }

    public void newClicked(MouseEvent mouseEvent) {
        selectedItem = null;
        priorityName.clear();
        priorityListView.getSelectionModel().clearSelection();
    }
}
