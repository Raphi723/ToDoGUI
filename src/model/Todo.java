package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Todo {
    private int id;
    private String name;
    private String description;
    private Status status;
    private Priority priority;

    public Todo(int id, String name, String description, int statusId, int priorityId) {
        this.id = id;
        this.name = name;
        this.description = description;

        /**
         * Illegale Sami Lösung
         * this.status = Status.getList().stream().filter(s -> s.getId() == statusId).findAny().get();
         */

        for (int i = 0; i <= Status.getList().size(); i++) {
            if (Status.getList().get(i).getId() == statusId) {
                this.status = Status.getList().get(i);
            }
        }

        for (int i = 0; i <= Priority.getList().size(); i++) {
            if (Priority.getList().get(i).getId() == priorityId) {
                this.priority = Priority.getList().get(i);
            }
        }


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<Todo> getList() {
        ObservableList<Todo> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM g5_ToDo");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Todo tmp = new Todo(results.getInt("todo_id"), results.getString("name"),
                        results.getString("beschreibung"), results.getInt("status_id"),
                        results.getInt("prioritaet_id"));

                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
