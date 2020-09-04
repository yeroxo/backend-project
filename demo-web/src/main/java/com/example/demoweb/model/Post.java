package com.example.demoweb.model;

public class Post {
    private String text;
    private Integer likes;

    public Post(String text,Integer likes){
        this.text = text;
        this.likes = likes;
    }

    public String getText(){
        return this.text;
    }
    public Integer getLikes(){
        return this.likes;
    }
}
