package com.metacoders.home.model;

public class modelForBookMark {

    String title, image, description, postuid;



    public modelForBookMark() {
    }

    public modelForBookMark(String title, String image, String description, String postuid) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.postuid = postuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostuid() {
        return postuid;
    }

    public void setPostuid(String postuid) {
        this.postuid = postuid;
    }
}
