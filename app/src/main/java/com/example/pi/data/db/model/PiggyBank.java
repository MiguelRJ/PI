package com.example.pi.data.db.model;

import java.util.Date;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 16/11/17
 *      Attributes
 *      Constructor
 *      Getter and Setter
 */

public class PiggyBank {

    int id;
    int idUser;
    String name;
    double totalAmount;
    Date creation;

    public PiggyBank(int id, int idUser, String name, double totalAmount, Date creation) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.totalAmount = totalAmount;
        this.creation = creation;
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

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
}
