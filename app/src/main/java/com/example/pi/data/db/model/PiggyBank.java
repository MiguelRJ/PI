package com.example.pi.data.db.model;

import android.support.annotation.NonNull;

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
 *      Comparable - name - String.toLowerCase()
 *      Comparator - creationDate - Date
 *      Comparator - totalAmount - double
 */

public class PiggyBank implements Comparable {

    int id;
    int idUser;
    String name;
    double totalAmount;
    Date creationDate;

    public PiggyBank(int id, int idUser, String name, double totalAmount, Date creationDate) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.totalAmount = totalAmount;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
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
