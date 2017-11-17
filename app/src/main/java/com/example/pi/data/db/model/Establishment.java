package com.example.pi.data.db.model;

import android.support.annotation.NonNull;

import java.util.Comparator;

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
 */

public class Establishment implements Comparable {

    int id;
    int idUser;
    String name;
    String nif;
    String address;


    public Establishment(int id, int idUser, String name, String nif, String address) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.nif = nif;
        this.address = address;
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

    @Override
    public String toString() {
        return "Establishment{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", name='" + name + '\'' +
                ", nif='" + nif + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return name.toLowerCase().compareTo(((Establishment)o).getName().toLowerCase());
    }

    public class EstablishmentOrderByNif implements Comparator<Establishment> {

        @Override
        public int compare(Establishment e1, Establishment e2) {
            return e1.getNif().toLowerCase().compareTo(e2.getNif().toLowerCase());
        }
    }
}
