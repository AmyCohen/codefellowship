package com.amycohen.userauth.model;

import javax.persistence.*;

/*
An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
 */

@Entity
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String bio;

    public ApplicationUser(){}

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public String toString(){
        return "User: +" + username + "\n" +
                "Name: +" + firstName + lastName + "\n" +
                "Birthday: " + dateOfBirth + "\n" +
                "Bio: +" + bio;
    }

}
