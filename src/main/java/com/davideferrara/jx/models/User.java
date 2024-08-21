package com.davideferrara.jx.models;

import com.davideferrara.jx.exceptions.UserException;
import com.davideferrara.jx.models.Profile;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User extends Person{
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    private Profile profile;

    public User(){}

    public User(String firstName, String lastName, LocalDate dob, String email, String userName){
        super(firstName, lastName, dob);
        this.email = email;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);

        this.email = email;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName.contains(NOT_ALLOWED_CHARS)){
            throw new UserException("Username contains invalid characters!");
        }
        this.userName = userName;
    }

    public static void validateEmail(String email) {
        if (email == null || !email.contains("@") || !email.substring(email.indexOf("@")).contains(".")) {
            throw new UserException("Email is not valid!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nUser{" +
                "email='" + email + '\'' +
                ", profile=" + profile +
                '}';
    }
}
