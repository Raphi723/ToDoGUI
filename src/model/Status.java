package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Status {

    private int id;
    private String name;

    public Status(int id, String name){
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<Status> getList() {
        ObservableList<Status> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector("d0345763","5AHEL2021", "rathgeb.at",3306 ,"d0345763");

        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM g5_Status");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Status tmp = new Status(resultSet.getInt("status_id"), resultSet.getString("name"));

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
                    "UPDATE g5_Status SET name = '" + name + "' WHERE status_id = " + id);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newStatus(String name){
        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement(
                    "INSERT INTO g5_Status (name) VALUES ('" + name + "')");
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id){
        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement(
                    "DELETE FROM g5_Status WHERE status_id = " + id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
