package com.example.pi.data.db.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.example.pi.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 16/11/17
 *      Attributes
 *      Constructor - with all atributes
 *      Getter and Setter
 * @date 17/11/17
 *      ToString
 *      Comparable - creationDate
 *      Comparator - amount
 *      Change creationDate type from "Date" to "Calendar" https://developer.android.com/reference/java/util/Calendar.html
 *      Attribute image wont be shown in toString()
 *      Attribute creationDate wont be never added by constructor
 *
 *      Added new constructor: with no creationDate
 *          this.creationDate = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND);
 *      Transaction(int id, int idUser, int idPiggyBank, int idEstablishment, boolean payment, double amount, String comment, double latitude, double longitude, byte[] image)
 *
 */

public class Transaction implements Comparable {

    int id;
    int idUser;
    int idPiggyBank;
    int idEstablishment;
    boolean payment; // type of transaction true = payment, false = entry
    double amount;
    Calendar creationDate;
    String comment;
    double latitude;
    double longitude;
    byte[] image; // wont be shown in toString()

    public Transaction(int id, int idUser, int idPiggyBank, int idEstablishment, boolean payment, double amount, GregorianCalendar creationDate, String comment, double latitude, double longitude, byte[] image) {
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

    public Transaction(int id, int idUser, int idPiggyBank, int idEstablishment, boolean payment, double amount, String comment, double latitude, double longitude, byte[] image) {
        this.id = id;
        this.idUser = idUser;
        this.idPiggyBank = idPiggyBank;
        this.idEstablishment = idEstablishment;
        this.payment = payment;
        this.amount = amount;
        this.creationDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                Calendar.getInstance().get(Calendar.SECOND));
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

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
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
                /*", image=" + Arrays.toString(image) +*/
                '}';
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return creationDate.compareTo(((Transaction)o).getCreationDate());
    }

    public static class TransactionOrderByAmount implements Comparator<Transaction> {

        @Override
        public int compare(Transaction t1, Transaction t2) {
            return Double.compare(t1.getAmount(),t2.getAmount());
        }
    }
}
