package com.example.pi.data.base;

import android.content.ContentValues;

import com.example.pi.data.model.PiggyBank;

import java.util.ArrayList;

/**
 * Created by Miguel on 24/02/2018.
 */

public interface PiggyBankDaoBase {
    ArrayList<PiggyBank> loadAll(String orderBy);
    long add(PiggyBank piggyBank);
    int update(PiggyBank piggyBank);
    int delete(PiggyBank piggyBank);
    ContentValues createContent(PiggyBank piggyBank);
}
