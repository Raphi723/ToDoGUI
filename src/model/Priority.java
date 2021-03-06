package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Priority {
    private int id;
    private String name;

    public Priority(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<Priority> getList() {
        ObservableList<Priority> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM g5_Prioritaet");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Priority tmp = new Priority(results.getInt("prioritaet_id"), results.getString("name"));

                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void update(String name, int id) {
        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement(
                    "UPDATE g5_Prioritaet SET name = '" + name + "' WHERE prioritaet_id = " + id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newPriority(String name) {
        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement(
                    "INSERT INTO g5_Prioritaet (name) VALUES ('" + name + "')");
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id){
        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement(
                    "DELETE FROM g5_Prioritaet WHERE prioritaet_id = " + id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
