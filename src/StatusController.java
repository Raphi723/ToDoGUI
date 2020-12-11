import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Priority;
import model.Status;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusController {

    public ListView<Status> status_ListView;
    public TextField status_textField;
    public Status selectedItem = null;
    public Button cancelButton;


    public void initialize(){
        status_ListView.setItems(Status.getList());
    }

    public void item_selected(MouseEvent mouseEvent) {
        Status s = status_ListView.getSelectionModel().getSelectedItem();

        if(s != null){
            status_textField.setText(s.getName());
            selectedItem = s;
        }

    }

    public void newItem(ActionEvent actionEvent) {
        selectedItem = null;

        status_textField.clear();
        status_ListView.getSelectionModel().clearSelection();
    }

    public void delete(ActionEvent actionEvent) {

        status_ListView.getItems().remove(selectedItem);


        if(selectedItem != null){

            Status.delete(selectedItem.getId());

        }

        status_ListView.setItems(Status.getList());
    }

    public void save(ActionEvent actionEvent) {
        if(selectedItem != null){
            //update existing item

            Status.update(status_textField.getText(), selectedItem.getId());

        }else{
            //insert new

            Status.newStatus(status_textField.getText());

        }
        status_ListView.setItems(Status.getList());
    }

    public void cancel(ActionEvent actionEvent) {
        //close dialog
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }
}
