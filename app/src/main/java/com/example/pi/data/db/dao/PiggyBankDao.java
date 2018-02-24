package com.example.pi.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.pi.data.base.PiggyBankDaoBase;
import com.example.pi.data.db.PIContract;
import com.example.pi.data.db.PIOpenHelper;
import com.example.pi.data.model.PiggyBank;
import com.example.pi.ui.utils.AppConstants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Miguel on 24/02/2018.
 */

public class PiggyBankDao implements PiggyBankDaoBase {

    @Override
    public ArrayList<PiggyBank> loadAll(String orderBy) {
        ArrayList<PiggyBank> piggyBanks = new ArrayList<>();

        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();
        Cursor cursor = db.query(
                PIContract.PiggyBankEntry.TABLE_NAME,
                PIContract.PiggyBankEntry.COL_ALL,
                null,
                null,
                null,
                null,
                orderBy,
                null
        );
        piggyBanks.clear();

        if(cursor.moveToFirst()){
            do {
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(AppConstants.df.parse(cursor.getString(4)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                piggyBanks.add(new PiggyBank(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        c
                ));
            } while (cursor.moveToNext());
        }
        PIOpenHelper.getInstance().closeDataBase();

        return piggyBanks;
    }

    /**
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    @Override
    public long add(PiggyBank piggyBank) {
        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();
        long id = db.insert(
                PIContract.PiggyBankEntry.TABLE_NAME,
                null,
                createContent(piggyBank)
        );
        PIOpenHelper.getInstance().closeDataBase();
        return id;
    }

    /**
     * @return the number of rows affected
     */
    @Override
    public int update(PiggyBank piggyBank) {
        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();
        String where = PIContract.PiggyBankEntry.COL_ID+"=?";
        String[] whereArgs = new String[]{String.valueOf(piggyBank.getId())};
        int id = db.update(
                PIContract.PiggyBankEntry.TABLE_NAME,
                createContent(piggyBank),
                where,
                whereArgs
        );
        PIOpenHelper.getInstance().closeDataBase();
        return id;
    }

    /**
     * @return the number of rows affected if a whereClause is passed in, 0
     *         otherwise. To remove all rows and get a count pass "1" as the
     *         whereClause.
     */
    @Override
    public int delete(PiggyBank piggyBank) {
        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();
        String where = PIContract.PiggyBankEntry.COL_ID+"=?";
        String[] whereArgs = new String[]{String.valueOf(piggyBank.getId())};
        int id = db.delete(
                PIContract.PiggyBankEntry.TABLE_NAME,
                where,
                whereArgs
        );
        PIOpenHelper.getInstance().closeDataBase();
        return id;
    }

    @Override
    public ContentValues createContent(PiggyBank piggyBank) {
        ContentValues cv = new ContentValues();
        cv.put(PIContract.PiggyBankEntry.COL_ID_USER, piggyBank.getIdUser());
        cv.put(PIContract.PiggyBankEntry.COL_NAME, piggyBank.getName());
        cv.put(PIContract.PiggyBankEntry.COL_AMOUNT, piggyBank.getTotalAmount());
        cv.put(PIContract.PiggyBankEntry.COL_DATE, AppConstants.df.format(piggyBank.getCreationDate().getTime()));
        return cv;
    }
}
