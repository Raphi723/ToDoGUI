
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Priority;
import model.Status;
import model.Todo;

import java.io.IOException;

public class Controller {
    public ListView<Todo> todoListview; // befüllen
    public ComboBox<Priority> priorityComboBox; // befüllen
    public ComboBox<Status> statusComboBox; // befüllen
    public TextField todoTextfield;
    public Pane contentPane;

    public void onStatusClicked(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("status.fxml"));

            Stage s = new Stage();
            s.setTitle("Status");
            s.setScene(new Scene(root, 600, 400));
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPriorityClicked(ActionEvent actionEvent) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("priority.fxml"));

            Stage s = new Stage();
            s.setTitle("Prioritäten");
            s.setScene(new Scene(root, 600, 400));
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onUserClicked(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("user.fxml"));

            Stage s = new Stage();
            s.setTitle("Benutzer");
            s.setScene(new Scene(root, 600, 400));
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        todoListview.setItems(Todo.getList());
        statusComboBox.setItems(Status.getList());
        priorityComboBox.setItems(Priority.getList());
    }



}
