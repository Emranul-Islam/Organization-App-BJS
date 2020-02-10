package com.muhammad_sohag.biteshwor_jobo_somaj;

public class PeopleModel {
    private String photo;
    private String name;
    private String phone;
    private String userId;

    public PeopleModel(String photo, String name, String phone, String userId) {
        this.photo = photo;
        this.name = name;
        this.phone = phone;
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
