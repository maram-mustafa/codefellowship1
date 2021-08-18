package com.example.demo.Models;



import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;
    private  int createdAt;
    @ManyToOne
    private ApplicationUser applicationUser;    // create instance from user


    public Post(String body, ApplicationUser applicationUser) {
        this.body = body;
        this.applicationUser=applicationUser;
    }

    public Post(){

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }
}
