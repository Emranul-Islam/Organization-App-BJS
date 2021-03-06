package xyz.emranul.bijos.model;

public class BloodModel {
    private String name;
    private String number;
    private String thikana;
    private String blood;

    public BloodModel() {
    }

    public BloodModel(String name, String number, String thikana, String blood) {
        this.name = name;
        this.number = number;
        this.thikana = thikana;
        this.blood = blood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getThikana() {
        return thikana;
    }

    public void setThikana(String thikana) {
        this.thikana = thikana;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }
}