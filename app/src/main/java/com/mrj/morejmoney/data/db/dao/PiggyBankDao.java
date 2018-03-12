package com.mrj.morejmoney.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mrj.morejmoney.data.base.PiggyBankDaoBase;
import com.mrj.morejmoney.data.db.PIContract;
import com.mrj.morejmoney.data.db.PIOpenHelper;
import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.data.prefs.AppPreferencesHelper;
import com.mrj.morejmoney.data.repository.TransactionRepository;
import com.mrj.morejmoney.ui.utils.AppConstants;

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
        String selection = PIContract.PiggyBankEntry.COL_ID_USER+"=?";
        String[] selectionArgs = new String[]{String.valueOf(AppPreferencesHelper.getInstance().getCurrentUserId())};
        Cursor cursor = db.query(
                PIContract.PiggyBankEntry.TABLE_NAME,
                PIContract.PiggyBankEntry.COL_ALL,
                selection,
                selectionArgs,
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
                        TransactionRepository.getInstance().sumTotalAmount(cursor.getInt(0)),
                        c
                ));
            } while (cursor.moveToNext());
        }
        PIOpenHelper.getInstance().closeDataBase();

        return piggyBanks;
    }

    @Override
    public PiggyBank loadPiggybank(int idPiggyBank){
        PiggyBank piggyBank = null;
        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();
        String selection = PIContract.PiggyBankEntry.COL_ID_USER+"=? AND "+PIContract.PiggyBankEntry.COL_ID+"=?";
        String[] selectionArgs = new String[]{String.valueOf(AppPreferencesHelper.getInstance().getCurrentUserId()),String.valueOf(idPiggyBank)};

        Cursor cursor = db.query(
                PIContract.PiggyBankEntry.TABLE_NAME,
                PIContract.PiggyBankEntry.COL_ALL,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()){
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(AppConstants.df.parse(cursor.getString(4)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                piggyBank = new PiggyBank(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        c
                );
        }

        PIOpenHelper.getInstance().closeDataBase();
        return piggyBank;
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
