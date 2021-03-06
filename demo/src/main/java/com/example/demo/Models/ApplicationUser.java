package com.example.demo.Models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// first step : to have an entity for application user that extends the userDetails interface

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)  // this field has unique value
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private  String dateOfBirth;
    private String bio ;
    @OneToMany(mappedBy = "applicationUser")
    private List<Post> post;

    /////////////////////////////////////////////////////////////////////////////////////////////////////

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "followersfollowingtable",
            joinColumns = {@JoinColumn(name="followerid")},
            inverseJoinColumns = {@JoinColumn(name="followingid")})
    private Set <ApplicationUser> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers")
    private Set <ApplicationUser> following = new HashSet<>();





    //default constructor
    public ApplicationUser(){}

    // constructor to pass username and password
    public ApplicationUser(String username, String password, String firstname, String lastname, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    ////////////// for relationship
    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
    ///////////////////////////////////////

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // generate getter for id
    public Integer getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    /////////////////////////////////////


    public Set<ApplicationUser> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<ApplicationUser> followers) {
        this.followers = followers;
    }

    public Set<ApplicationUser> getFollowing() {
        return following;
    }

    public void setFollowing(Set<ApplicationUser> following) {
        this.following = following;
    }
}
