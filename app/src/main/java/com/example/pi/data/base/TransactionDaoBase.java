package com.example.pi.data.base;

import android.content.ContentValues;

import com.example.pi.data.model.Transaction;

import java.util.ArrayList;

/**
 * Created by Miguel on 24/02/2018.
 */

public interface TransactionDaoBase {
    ArrayList<Transaction> loadAll(String orderBy, int limit, boolean orderDESC);
    long add(Transaction transaction);
    int update(Transaction transaction);
    int delete(Transaction transaction);
    double sumTotalAmount(int idPiggyBank);
    boolean exists(int id);
    int deleteAll(int id);
    ContentValues createContent(Transaction transaction);
}
