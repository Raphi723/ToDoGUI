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

            User.delete(selectedItem.getBearbeiter_id());

        }

        userListView.setItems(User.getList());

    }

    public void saveButtonClicked (ActionEvent actionEvent){
        if(selectedItem != null){
            //update existing item

            User.update(nameTextField.getText(), streetTextField.getText(), Integer.parseInt(zipTextField.getText()), cityTextField.getText(), selectedItem.getBearbeiter_id());



        }else{
            //insert new

            User.newUser(nameTextField.getText(), streetTextField.getText(), Integer.parseInt(zipTextField.getText()), cityTextField.getText());

        }
        userListView.setItems(User.getList());
    }

    public void cancelButtonClicked (ActionEvent actionEvent){
        //close dialog
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
