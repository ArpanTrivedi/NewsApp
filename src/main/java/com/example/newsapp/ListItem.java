package com.example.newsapp;

public class ListItem {

    private String title;
    private String description;
    private String author;

    private String urlToImage;

    public ListItem(String title, String description, String author, String urlToImage) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.urlToImage = urlToImage;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrlToImage() {
        return urlToImage;
    }
}
