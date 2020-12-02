package model;

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




}
