import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Priority;
import model.Status;
import model.Todo;

public class TodoController {
    public TextField nameTextfield;
    public TextArea descriptionTextarea;
    public ComboBox statusCombobox;
    public ComboBox priorityCombobox;
    private Todo selected = null;

    public void setTodo(Todo item) {
        selected = item;
        displayItem();
    }

    private void displayItem() {
        /**
         * Hier sollen die Daten von "selected" angezeigt werden
         */

        nameTextfield.setText(selected.getName());
        descriptionTextarea.setText(selected.getDescription());
        statusCombobox.setItems(Status.getList());
        priorityCombobox.setItems(Priority.getList());
    }
}
