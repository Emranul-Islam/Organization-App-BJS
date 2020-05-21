package com.muhammad_sohag.biteshwor_jobo_somaj.model;

public class ChadaModel  {
    private int kromik_no;
    private String grahok;
    private String time;
    private String taka;
    private String uid;

    public ChadaModel() {
    }

    public ChadaModel(int kromik_no, String grahok, String time, String taka, String uid) {
        this.kromik_no = kromik_no;
        this.grahok = grahok;
        this.time = time;
        this.taka = taka;
        this.uid = uid;
    }

    public String getKromik_no() {
        return String.valueOf(kromik_no);
    }

    public void setKromik_no(int kromik_no) {
        this.kromik_no = kromik_no;
    }

    public String getGrahok() {
        return grahok;
    }

    public void setGrahok(String grahok) {
        this.grahok = grahok;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTaka() {
        return taka;
    }

    public void setTaka(String taka) {
        this.taka = taka;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
