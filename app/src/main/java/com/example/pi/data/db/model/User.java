package com.example.pi.data.db.model;

import java.util.Date;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 16/11/17
 *      Attributes
 *      Constructor
 *      Getter and Setter
 * @date 17/11/17
 *      ToString
 *      dont need to do comparable or comparator of users
 */

public class User {

    int id;
    String user;
    String email;
    String password;
    String name;
    Date birthDate;
    String gender;
    int phoneNumber;
    String city;
    String socialMedia;
    boolean owner;

    public User(int id, String user, String email, String password, String name, Date birthDate, String gender, int phoneNumber, String city, String socialMedia, boolean owner) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.socialMedia = socialMedia;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", city='" + city + '\'' +
                ", socialMedia='" + socialMedia + '\'' +
                ", owner=" + owner +
                '}';
    }
}
