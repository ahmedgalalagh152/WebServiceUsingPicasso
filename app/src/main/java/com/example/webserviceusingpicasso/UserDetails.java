package com.example.webserviceusingpicasso;

public class UserDetails {

    private String name;
    private String imageURL;
    private  int likes;

    public UserDetails(String name, String imageURL, int likes) {
        this.name = name;
        this.imageURL = imageURL;
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getLikes() {
        return likes;
    }
}
