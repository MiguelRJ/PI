package com.mrj.morejmoney.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Comparator;

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
 * @date 03/01/18
 *      Tag
 * @date 05/01/18
 *      implement parcelable
 * @date 06/01/18
 *      amount from "double" to "Double"
 */

public class Transaction implements Comparable,Parcelable {

    public static String TAG = "Transaction";

    int id;
    int idUser;
    int idPiggyBank;
    int idEstablishment;
    boolean payment; // type of transaction true = payment, false = entry
    Double amount;
    Calendar creationDate;
    String comment;
    double latitude;
    double longitude;
    byte[] image; // wont be shown in toString()

    public Transaction(int id, int idUser, int idPiggyBank, int idEstablishment, boolean payment, double amount, Calendar creationDate, String comment, double latitude, double longitude, byte[] image) {
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

    protected Transaction(Parcel in) {
        id = in.readInt();
        idUser = in.readInt();
        idPiggyBank = in.readInt();
        idEstablishment = in.readInt();
        payment = in.readByte() != 0;
        amount = in.readDouble();
        comment = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        image = in.createByteArray();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idUser);
        dest.writeInt(idPiggyBank);
        dest.writeInt(idEstablishment);
        dest.writeByte((byte) (payment ? 1 : 0));
        dest.writeDouble(amount);
        dest.writeString(comment);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeByteArray(image);
    }

    public static class TransactionOrderByAmountDESC implements Comparator<Transaction> {

        @Override
        public int compare(Transaction t1, Transaction t2) {
            return Double.compare(t2.getAmount(),t1.getAmount());
        }
    }

    public static class TransactionOrderByAmountASC implements Comparator<Transaction> {

        @Override
        public int compare(Transaction t1, Transaction t2) {
            return Double.compare(t1.getAmount(),t2.getAmount());
        }
    }

    public static class TransactionOrderByCreationDateDESC implements Comparator<Transaction> {

        @Override
        public int compare(Transaction t1, Transaction t2) {
            return Long.compare(t2.getCreationDate().getTimeInMillis(),t1.getCreationDate().getTimeInMillis());
        }
    }

    public static class TransactionOrderByCreationDateASC implements Comparator<Transaction> {

        @Override
        public int compare(Transaction t1, Transaction t2) {
            return Long.compare(t1.getCreationDate().getTimeInMillis(),t2.getCreationDate().getTimeInMillis());
        }
    }
}
