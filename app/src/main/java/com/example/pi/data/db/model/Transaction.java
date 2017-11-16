package com.example.pi.data.db.model;

import java.sql.Time;
import java.util.Date;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 16/11/17
 *      Attributes
 *      Constructor
 *      Getter and Setter
 */

public class Transaction {

    int id;
    int idUser;
    int idPiggyBank;
    int idEstablishment;
    boolean payment; // type of transaction true = payment, false = entry
    double amount;
    Date creationDate;
    Time creationTime;
    String comment;
    double latitude;
    double longitude;
    byte[] image;

    public Transaction(int id, int idUser, int idPiggyBank, int idEstablishment, boolean payment, double amount, Date creationDate, Time creationTime, String comment, double latitude, double longitude, byte[] image) {
        this.id = id;
        this.idUser = idUser;
        this.idPiggyBank = idPiggyBank;
        this.idEstablishment = idEstablishment;
        this.payment = payment;
        this.amount = amount;
        this.creationDate = creationDate;
        this.creationTime = creationTime;
        this.comment = comment;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPiggyBank() {
        return idPiggyBank;
    }

    public void setIdPiggyBank(int idPiggyBank) {
        this.idPiggyBank = idPiggyBank;
    }

    public int getIdEstablishment() {
        return idEstablishment;
    }

    public void setIdEstablishment(int idEstablishment) {
        this.idEstablishment = idEstablishment;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Time getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Time creationTime) {
        this.creationTime = creationTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
