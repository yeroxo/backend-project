package com.example.demoweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private Integer likes;
    private Date creationDate;

    public Post(){

    }

    public Post(Long id,String text,Date creationDate){
        this.id = id;
        this.text = text;
        this.creationDate = creationDate;
        this.likes = 0;
    }
    public String getText(){
        return this.text;
    }
    public Integer getLikes(){
        return this.likes;
    }
    public Date getCreationDate() {
        return this.creationDate;
    }
    public Long getId(){return this.id;}

    public void setLikes(Integer count){
        this.likes = count;
    }
}
