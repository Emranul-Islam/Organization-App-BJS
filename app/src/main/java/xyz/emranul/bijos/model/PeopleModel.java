package xyz.emranul.bijos.model;

public class PeopleModel {
        private String name;
        private String number;
        private String number2;
        private String blood;
        private String url;
        private String uid;


    public PeopleModel() {
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

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public PeopleModel(String name, String number, String number2, String blood, String url, String uid) {
        this.name = name;
        this.number = number;
        this.number2 = number2;
        this.blood = blood;
        this.url = url;
        this.uid = uid;


    }
}
