package com.davideferrara.jx.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @MapsId
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    // da eliminare
    private String status;

    @Column(name="description", columnDefinition = "varchar(255) default ''")
    private String description;

    // Numero di post nel profilo
    @Column(name="num_of_posts")
    private int numOfPosts;

    // Numero di persone che il profilo segue
    @Column(name="num_of_followers")
    private int numOfFollowers;

    // Numero di persone che seguono il profilo
    @Column(name="num_of_following")
    private int numOfFollowing;

    @OneToMany(mappedBy="profile")
    private Set<ProfilePost> profilePost; // collection that contains no duplicate elements.

    public Profile() {};

    public Profile(String description, int numOfPosts, int numOfFollowers, int numOfFollowing) {
        this.description = description;
        this.numOfPosts = numOfPosts;
        this.numOfFollowers = numOfFollowers;
        this.numOfFollowing = numOfFollowing;
    }

    public Profile(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfPosts() {
        return numOfPosts;
    }

    public void setNumOfPosts(int posts) {
        this.numOfPosts = posts;
    }

    public int getNumOfFollowers() {
        return numOfFollowers;
    }

    public void setNumOfFollowers(int followers) {
        this.numOfFollowers = followers;
    }

    public int getNumOfFollowing() {
        return numOfFollowing;
    }

    public void setNumOfFollowing(int following) {
        this.numOfFollowing = following;
    }

    public Set<ProfilePost> getProfilePost() {
        return profilePost;
    }

    public void setProfilePost(Set<ProfilePost> profilePost) {
        this.profilePost = profilePost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return numOfPosts == profile.numOfPosts && numOfFollowers == profile.numOfFollowers && numOfFollowing == profile.numOfFollowing && Objects.equals(id, profile.id) && Objects.equals(user, profile.user) && Objects.equals(status, profile.status) && Objects.equals(description, profile.description) && Objects.equals(profilePost, profile.profilePost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, status, description, numOfPosts, numOfFollowers, numOfFollowing, profilePost);
    }
}
