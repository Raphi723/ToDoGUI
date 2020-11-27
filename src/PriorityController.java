import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Priority;

import java.awt.*;

public class PriorityController {

    public ListView<Priority> priorityListView;
    public TextField priorityName;

    public void initialize() {
        priorityListView.setItems(Priority.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {
        Priority p = priorityListView.getSelectionModel().getSelectedItem();
        if (p != null) {
            priorityName.setText(p.getName());
        }
    }
}
