package com.example.pi.data.db.model;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

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
 *      Comparator - nif - String.toLowerCase()
 *      Added attribute creationAddDate
 *      Change creationDate type from "Date" to "Calendar" https://developer.android.com/reference/java/util/Calendar.html
 *      Added a new constructor Establishment(int id, int idUser, String name, String nif, String address)
 */

public class Establishment implements Comparable {

    int id;
    int idUser;
    String name;
    String nif;
    String address;
    Calendar creationAddDate;


    public Establishment(int id, int idUser, String name, String nif, String address, GregorianCalendar creationAddDate) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.creationAddDate = creationAddDate;
    }

    public Establishment(int id, int idUser, String name, String nif, String address) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.creationAddDate = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND);
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Calendar getCreationAddDate() {
        return creationAddDate;
    }

    @Override
    public String toString() {
        return "Establishment{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", name='" + name + '\'' +
                ", nif='" + nif + '\'' +
                ", address='" + address + '\'' +
                ", creationAddDate=" + creationAddDate +
                '}';
    }

    public void setCreationAddDate(GregorianCalendar creationAddDate) {
        this.creationAddDate = creationAddDate;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return name.toLowerCase().compareTo(((Establishment)o).getName().toLowerCase());
    }

    public static class EstablishmentOrderByNif implements Comparator<Establishment> {

        @Override
        public int compare(Establishment e1, Establishment e2) {
            return e1.getNif().toLowerCase().compareTo(e2.getNif().toLowerCase());
        }
    }
}
