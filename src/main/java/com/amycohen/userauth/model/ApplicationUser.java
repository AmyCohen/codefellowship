package com.amycohen.userauth.model;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

/*
An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
 */

@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    //Source: http://forums.ohdsi.org/t/specify-transactional-on-jpa-repository-for-operations-involving-lob-fields/370/4
    //Comment by Chris_Knoll on March 15
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String bio;
    private String fullname;

    public ApplicationUser(){}

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.fullname = this.firstName + " " + this.lastName;
    }

    public String toString(){
        return "User: " + this.username + "\n" +
                "Name: " + this.firstName + " " + this.lastName + "\n" +
                "Birthday: " + this.dateOfBirth + "\n" +
                "Bio: " + this.bio;
    }

    //Interface methods auto implemented

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getFullName() {
        return this.fullname;
    }

    public void setFullName (String fullName) {
        this.fullname = fullName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getBirthDate(){
        return this.dateOfBirth;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return this.bio;
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


}
