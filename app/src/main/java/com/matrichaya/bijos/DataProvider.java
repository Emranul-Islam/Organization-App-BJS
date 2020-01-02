package com.matrichaya.bijos;

public class DataProvider {
    private int photo;
    private String title;
    private String telePhone;

    public DataProvider(int movie_poster_resource, String movie_title, String telePhone) {
        this.setMovie_poster_resource(movie_poster_resource);
        this.setMovie_title(movie_title);
        this.telePhone = telePhone;
    }

    public int getPhoto() {
        return photo;
    }

    public String getTitle() {
        return title;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setMovie_poster_resource(int movie_poster_resource) {
        this.photo = movie_poster_resource;
    }

    public void setMovie_title(String movie_title) {
        this.title = movie_title;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }
}
