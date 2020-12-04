import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Status;
import model.User;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserController {
    public User selectedItem = null;

    public ListView<User> userListView;

    public TextField nameTextField;
    public TextField streetTextField;
    public TextField zipTextField;
    public TextField cityTextField;

    public Button newButton;
    public Button deleteButton;

    public Button saveButton;
    public Button cancelButton;

    public void initialize(){
        userListView.setItems(User.getList());
    }


    public void itemSelected(MouseEvent mouseEvent) {
        User u = userListView.getSelectionModel().getSelectedItem();

        if(u != null){
            nameTextField.setText(u.getName());
            streetTextField.setText(u.getStrasse());
            zipTextField.setText(String.valueOf(u.getPlz()));
            cityTextField.setText(u.getOrt());


            selectedItem = u;
        }
    }

    public void newButtonClicked(ActionEvent actionEvent) {
        selectedItem = null;

        nameTextField.clear();
        streetTextField.clear();
        zipTextField.clear();
        cityTextField.clear();

        userListView.getSelectionModel().clearSelection();
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

        userListView.setItems(User.getList());

    }

    public void saveButtonClicked (ActionEvent actionEvent){
        if(selectedItem != null){
            //update existing item
            // priorityListView mus noch geändert werden
            //priorityListView.getSelectionModel().getSelectedItem().set(priorityName.getText());

            //status_ListView.getSelectionModel().clearSelection();
            //status_ListView.getSelectionModel().getSelectedItem().setName(status_textField.getText());


            AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
            try {
                PreparedStatement statement = conn.getConnection().prepareStatement(//UPDATE g5_Bearbeiter SET name = '$name', strasse = '$strasse', plz = '$plz', ort = '$ort',  = '$';
                        "UPDATE g5_Bearbeiter " +
                                "SET name = '"+ nameTextField.getText() + "', " +
                                "strasse = '" + streetTextField.getText() + "', " +
                                "plz = '" + Integer.parseInt(zipTextField.getText()) + "', " +
                                "ort = '" + cityTextField.getText() +"'"+
                                " WHERE bearbeiter_id = " + selectedItem.getBearbeiter_id());

                statement.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            //insert new

            AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
            try {
                PreparedStatement statement = conn.getConnection().prepareStatement(
                        "INSERT INTO g5_Bearbeiter (name, strasse, plz, ort) VALUES ('" +
                               nameTextField.getText() + "," + streetTextField.getText() + "','" + Integer.parseInt(zipTextField.getText()) + "','" + cityTextField.getText() +"')");
                statement.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        userListView.setItems(User.getList());
    }

    public void cancelButtonClicked (ActionEvent actionEvent){
        //close dialog
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
