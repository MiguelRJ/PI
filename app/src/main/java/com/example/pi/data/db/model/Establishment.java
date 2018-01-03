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
 *      Constructor - with all atributes
 *      Getter and Setter
 * @date 17/11/17
 *      ToString
 *      Comparable - name - String.toLowerCase()
 *      Comparator - nif - String.toLowerCase()
 *      Added attribute creationAddDate
 *      Attribute creationAddDate wont be never added by constructor
 *      Change creationDate type from "Date" to "Calendar" https://developer.android.com/reference/java/util/Calendar.html
 *
 *      Added new constructor: with no creationDate
 *          this.creationDate = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND);
 *      Establishment(int id, int idUser, String name, String nif, String address)
 * @date 03/01/18
 *      Tag
 */

public class Establishment implements Comparable {

    public static String TAG = "Establishment";

    int id;
    int idUser;
    String name;
    String nif;
    String address;
    Calendar creationDate;

    /**
     * Complete constructor
     */
    public Establishment(int id, int idUser, String name, String nif, String address, GregorianCalendar creationAddDate) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.creationDate = creationAddDate;
    }

    /**
     * Constructor with no creationAddDate, it will be added automatic
     */
    public Establishment(int id, int idUser, String name, String nif, String address) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.creationDate = new GregorianCalendar();
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

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(GregorianCalendar creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Establishment{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", name='" + name + '\'' +
                ", nif='" + nif + '\'' +
                ", address='" + address + '\'' +
                ", creationAddDate=" + creationDate +
                '}';
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
