
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
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
}
