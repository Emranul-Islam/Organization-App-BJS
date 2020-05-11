package com.muhammad_sohag.biteshwor_jobo_somaj.model;

public class BloodModel {
    private String name;
    private String number;
    private String elaka;

    public BloodModel() {
    }

    public BloodModel(String name, String number, String elaka) {
        this.name = name;
        this.number = number;
        this.elaka = elaka;
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

    public String getElaka() {
        return elaka;
    }

    public void setElaka(String elaka) {
        this.elaka = elaka;
    }
}
