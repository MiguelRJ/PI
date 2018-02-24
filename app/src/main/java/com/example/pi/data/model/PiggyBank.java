package com.example.pi.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.pi.ui.utils.AppConstants;

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 16/11/17
 *      Attributes
 *      Constructor - with all attributes
 *      Getter and Setter
 * @date 17/11/17
 *      ToString
 *      Comparable - name - String.toLowerCase()
 *      Comparator - creationDate - Date
 *      Comparator - totalAmount - double
 *      Attribute creationDate wont be never added by constructor
 *      Change creationDate type from "Date" to "Calendar" https://developer.android.com/reference/java/util/Calendar.html
 *      Attribute totalAmount wont be able to add by a constructor, its calculated by all Transactions in this PiggyBank
 *
 *      Added new constructor : with no creationDate
 *              this.creationDate = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND);
 *      PiggyBank(int id, int idUser, String name)
 * @date 03/01/18
 *      Tag
 */

public class PiggyBank implements Comparable, Parcelable {

    public static String TAG = "PiggyBank";

    int id;
    int idUser;
    String name;
    double totalAmount; // wont be added by constructor
    Calendar creationDate;

    /**
     * Complete constructor
     */
    public PiggyBank(int id, int idUser, String name, double amount, Calendar creationDate) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        //this.totalAmount =  Double.parseDouble(AppConstants.decimalformat.format(Math.random()*100).toString().replace(",","."));
        this.totalAmount = amount;
        this.creationDate = creationDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "PiggyBank{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", name='" + name + '\'' +
                ", totalAmount=" + totalAmount +
                ", creation=" + creationDate +
                '}';
    }


    @Override
    public int compareTo(@NonNull Object o) {
        return name.toLowerCase().compareTo(((PiggyBank)o).getName().toLowerCase());
    }

    public static class PiggyBankOrderByCreationDate implements Comparator<PiggyBank> {

        @Override
        public int compare(PiggyBank pb1, PiggyBank pb2) {
            return pb1.getCreationDate().compareTo(pb2.getCreationDate());
        }
    }

    public static class PiggyBankOrderByTotalAmount implements Comparator<PiggyBank> {

        @Override
        public int compare(PiggyBank pb1, PiggyBank pb2) {
            return Double.compare(pb1.getTotalAmount(),pb2.getTotalAmount());
        }
    }

    public static class PiggyBankOrderById implements Comparator<PiggyBank> {

        @Override
        public int compare(PiggyBank pb1, PiggyBank pb2) {
            return Double.compare(pb1.getId(),pb2.getId());
        }
    }

    protected PiggyBank(Parcel in) {
        id = in.readInt();
        idUser = in.readInt();
        name = in.readString();
        totalAmount = in.readDouble();
        creationDate = (Calendar) in.readValue(Calendar.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idUser);
        dest.writeString(name);
        dest.writeDouble(totalAmount);
        dest.writeValue(creationDate);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PiggyBank> CREATOR = new Parcelable.Creator<PiggyBank>() {
        @Override
        public PiggyBank createFromParcel(Parcel in) {
            return new PiggyBank(in);
        }

        @Override
        public PiggyBank[] newArray(int size) {
            return new PiggyBank[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PiggyBank)) return false;

        PiggyBank piggyBank = (PiggyBank) o;

        if (getId() != piggyBank.getId()) return false;
        if (getIdUser() != piggyBank.getIdUser()) return false;
        if (Double.compare(piggyBank.getTotalAmount(), getTotalAmount()) != 0) return false;
        if (!getName().equals(piggyBank.getName())) return false;
        return getCreationDate().equals(piggyBank.getCreationDate());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + getIdUser();
        result = 31 * result + getName().hashCode();
        temp = Double.doubleToLongBits(getTotalAmount());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }
}
