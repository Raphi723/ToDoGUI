package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int bearbeiter_id;
    private String name;
    private String strasse;
    private int plz;
    private String ort;

    public User(int bearbeiter_id, String name, String strasse, int plz, String ort){
        this.bearbeiter_id = bearbeiter_id;
        this.name = name;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
    }

    public int getBearbeiter_id() {
        return bearbeiter_id;
    }

    public void setBearbeiter_id(int bearbeiter_id) {
        this.bearbeiter_id = bearbeiter_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }


    public static ObservableList<User> getList() {
        ObservableList<User> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector("d0345763", "5AHEL2021", "rathgeb.at", 3306, "d0345763");
        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM g5_Bearbeiter");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                User tmp = new User(results.getInt("bearbeiter_id"), results.getString("name"), results.getString("strasse"), results.getInt("plz"), results.getString("ort"));

                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public String toString() {
        return "Id: " + bearbeiter_id + " - " + name ;
    }
}
