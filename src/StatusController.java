import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Status;

public class StatusController {

    public ListView<Status> status_ListView;
    public TextField status_textField;
    public Status selectedItem = null;


    public void initialize(){
        status_ListView.setItems(Status.getList());
    }

    public void item_selected(MouseEvent mouseEvent) {
        Status s = status_ListView.getSelectionModel().getSelectedItem();

        if(s != null){
            status_textField.setText(s.toString());
            selectedItem = s;
        }

    }

    public void newItem(ActionEvent actionEvent) {
        selectedItem = null;
        status_textField.clear();
        status_ListView.getSelectionModel().clearSelection();
    }

    public void delete(ActionEvent actionEvent) {
        if(selectedItem != null){

        }
    }

    public void save(ActionEvent actionEvent) {
        if(selectedItem != null){
            //update existing item
        }else{
            //insert new
        }
    }

    public void cancel(ActionEvent actionEvent) {
        //close dialog
    }
}
