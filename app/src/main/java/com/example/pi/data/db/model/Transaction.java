package com.example.pi.data.db.model;

import android.support.annotation.NonNull;

import java.sql.Time;
import java.util.Arrays;
import java.util.Comparator;
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
 *      - creationTime - Date has a constructor with hours minutes and seconds
 *      Comparable - creationDate
 *      Comparator - amount
 */

public class Transaction implements Comparable {

    int id;
    int idUser;
    int idPiggyBank;
    int idEstablishment;
    boolean payment; // type of transaction true = payment, false = entry
    double amount;
    Date creationDate;
    String comment;
    double latitude;
    double longitude;
    byte[] image;

    public Transaction(int id, int idUser, int idPiggyBank, int idEstablishment, boolean payment, double amount, Date creationDate, String comment, double latitude, double longitude, byte[] image) {
        this.id = id;
        this.idUser = idUser;
        this.idPiggyBank = idPiggyBank;
        this.idEstablishment = idEstablishment;
        this.payment = payment;
        this.amount = amount;
        this.creationDate = creationDate;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idPiggyBank=" + idPiggyBank +
                ", idEstablishment=" + idEstablishment +
                ", payment=" + payment +
                ", amount=" + amount +
                ", creationDate=" + creationDate +
                ", comment='" + comment + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return creationDate.compareTo(((Transaction)o).getCreationDate());
    }

    public static class TransactionOrderByCreationDate implements Comparator<Transaction> {

        @Override
        public int compare(Transaction t1, Transaction t2) {
            return Double.compare(t1.getAmount(),t2.getAmount());
        }
    }
}
