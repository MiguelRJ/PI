package com.example.pi.data.db.model;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
 */

public class PiggyBank implements Comparable {

    int id;
    int idUser;
    String name;
    double totalAmount; // wont be added by constructor
    Calendar creationDate;

    /**
     * Complete constructor
     */
    public PiggyBank(int id, int idUser, String name, GregorianCalendar creationDate) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.totalAmount = Math.random()*100;
        this.creationDate = creationDate;
    }

    /**
     * Constructor with no creationDate, it will be added automatic
     */
    public PiggyBank(int id, int idUser, String name) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.totalAmount = Math.random()*100;
        this.creationDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                Calendar.getInstance().get(Calendar.SECOND));
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

}
