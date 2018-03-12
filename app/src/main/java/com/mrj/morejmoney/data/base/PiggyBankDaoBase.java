package com.mrj.morejmoney.data.base;

import android.content.ContentValues;

import com.mrj.morejmoney.data.model.PiggyBank;

import java.util.ArrayList;

/**
 * Created by Miguel on 24/02/2018.
 */

public interface PiggyBankDaoBase {
    ArrayList<PiggyBank> loadAll(String orderBy);
    long add(PiggyBank piggyBank);
    int update(PiggyBank piggyBank);
    int delete(PiggyBank piggyBank);
    PiggyBank loadPiggybank(int idPiggyBank);
    ContentValues createContent(PiggyBank piggyBank);
}
