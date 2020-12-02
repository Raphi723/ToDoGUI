import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
            AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
            try {
                PreparedStatement statement = conn.getConnection().prepareStatement(
                        "DELETE FROM g5_Status WHERE status_id = " + selectedItem.getId());
                statement.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void save(ActionEvent actionEvent) {
        if(selectedItem != null){
            //update existing item
            // priorityListView mus noch geändert werden
            //priorityListView.getSelectionModel().getSelectedItem().set(priorityName.getText());

            //status_ListView.getSelectionModel().clearSelection();
            //status_ListView.getSelectionModel().getSelectedItem().setName(status_textField.getText());

            AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
            try {
                PreparedStatement statement = conn.getConnection().prepareStatement(
                        "UPDATE g5_Status SET name = '" + status_textField.getText() + "' WHERE status_id = " + selectedItem.getId());

                statement.execute();


            } catch (SQLException e) {
                e.printStackTrace();
            }


        }else{
            //insert new

            AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
            try {
                PreparedStatement statement = conn.getConnection().prepareStatement(
                        "INSERT INTO g5_Status (name) VALUES ('" + status_textField.getText() + "')");
                statement.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }



        }
    }

    public void cancel(ActionEvent actionEvent) {
        //close dialog
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }
}
