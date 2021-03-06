package com.mrj.morejmoney.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mrj.morejmoney.data.base.TransactionDaoBase;
import com.mrj.morejmoney.data.db.PIContract;
import com.mrj.morejmoney.data.db.PIOpenHelper;
import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.data.prefs.AppPreferencesHelper;
import com.mrj.morejmoney.ui.utils.AppConstants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Miguel on 24/02/2018.
 */

public class TransactionDao implements TransactionDaoBase {

    /**
     *
     * @param orderBy
     * @param limit
     * @param orderDESC true = DESC false = ASC
     * @return
     */
    @Override
    public ArrayList<Transaction> loadAll(String orderBy, int limit, boolean orderDESC) {
        String strLimit;
        String strOrder;
        if (limit== 0){
            strLimit = null;
        } else {
            strLimit = String.valueOf(limit);
        }
        if (orderDESC){
            strOrder=" DESC";
        } else {
            strOrder=" ASC";
        }
        ArrayList<Transaction> transactions = new ArrayList<>();
        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();
        String selection = PIContract.TransactionEntry.COL_ID_USER+"=?";
        String[] selectionArgs = new String[]{String.valueOf(AppPreferencesHelper.getInstance().getCurrentUserId())};
        Cursor cursor = db.query(
                "'"+PIContract.TransactionEntry.TABLE_NAME+"'",
                PIContract.TransactionEntry.COL_ALL,
                selection,
                selectionArgs,
                null,
                null,
                orderBy+ strOrder,
                strLimit
        );
        transactions.clear();

        if(cursor.moveToFirst()){
            do {
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(AppConstants.cf.parse(cursor.getString(6)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                transactions.add(new Transaction(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4)==1,
                        cursor.getDouble(5),
                        c,
                        cursor.getString(7),
                        cursor.getDouble(8),
                        cursor.getDouble(9),
                        cursor.getBlob(10)
                ));
            } while (cursor.moveToNext());
        }
        PIOpenHelper.getInstance().closeDataBase();
        return transactions;
    }

    @Override
    public long add(Transaction transaction) {
        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();
        long id = db.insert(
                "'"+PIContract.TransactionEntry.TABLE_NAME+"'",
                null,
                createContent(transaction)
        );
        PIOpenHelper.getInstance().closeDataBase();
        return id;
    }

    @Override
    public int update(Transaction transaction) {
        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();
        String where = PIContract.TransactionEntry.COL_ID+"=?";
        String[] whereArgs = new String[]{String.valueOf(transaction.getId())};
        int id = db.update(
                "'"+PIContract.TransactionEntry.TABLE_NAME+"'",
                createContent(transaction),
                where,
                whereArgs
        );
        PIOpenHelper.getInstance().closeDataBase();
        return id;
    }

    @Override
    public int delete(Transaction transaction) {
        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();
        String where = PIContract.TransactionEntry.COL_ID+"=?";
        String[] whereArgs = new String[]{String.valueOf(transaction.getId())};
        int id = db.delete(
                "'"+PIContract.TransactionEntry.TABLE_NAME+"'",
                where,
                whereArgs
        );
        PIOpenHelper.getInstance().closeDataBase();
        return id;
    }

    @Override
    public double sumTotalAmount(int idPiggyBank) {
        double deposits = 0; // 0, false
        double payments = 0; // 1 , true

        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();

        Cursor cursor = db.rawQuery(
                "SELECT sum(" + PIContract.TransactionEntry.COL_AMOUNT +
                        ") FROM '" + PIContract.TransactionEntry.TABLE_NAME + "'" +
                        " WHERE " + PIContract.TransactionEntry.COL_ID_PIGGYBANK + "=" + String.valueOf(idPiggyBank)+
                        " AND "+PIContract.TransactionEntry.COL_PAYMENT+"='0'",
                null
        );
        if (cursor.moveToFirst()){
            deposits=  cursor.getDouble(0);
        }

        cursor = db.rawQuery(
                "SELECT sum(" + PIContract.TransactionEntry.COL_AMOUNT +
                        ") FROM '" + PIContract.TransactionEntry.TABLE_NAME + "'" +
                        " WHERE " + PIContract.TransactionEntry.COL_ID_PIGGYBANK + "=" + String.valueOf(idPiggyBank)+
                        " AND "+PIContract.TransactionEntry.COL_PAYMENT+"='1'",
                null
        );
        if (cursor.moveToFirst()){
            payments =  cursor.getDouble(0);
        }
        PIOpenHelper.getInstance().closeDataBase();
        return deposits - payments;
    }

    @Override
    public boolean exists(int id) {
        boolean resultado = false;
        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();
        String query = "SELECT * FROM '"+PIContract.TransactionEntry.TABLE_NAME+"' "+
                " WHERE "+PIContract.TransactionEntry.COL_ID_PIGGYBANK+"="+id;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>0){
            resultado = true;
        }
        PIOpenHelper.getInstance().closeDataBase();
        return resultado;
    }

    @Override
    public int deleteAll(int id) {
        SQLiteDatabase db = PIOpenHelper.getInstance().openDataBase();
        String where = PIContract.TransactionEntry.COL_ID_PIGGYBANK+"=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        int count = db.delete(
                "'"+PIContract.TransactionEntry.TABLE_NAME+"'",
                where,
                whereArgs
        );
        PIOpenHelper.getInstance().closeDataBase();
        return count;
    }


    @Override
    public ContentValues createContent(Transaction transaction) {
        ContentValues cv = new ContentValues();
        cv.put(PIContract.TransactionEntry.COL_ID_USER,transaction.getIdUser());
        cv.put(PIContract.TransactionEntry.COL_ID_PIGGYBANK,transaction.getIdPiggyBank());
        cv.put(PIContract.TransactionEntry.COL_ID_ESTABLISHMENT,transaction.getIdEstablishment());
        cv.put(PIContract.TransactionEntry.COL_PAYMENT,transaction.isPayment());
        cv.put(PIContract.TransactionEntry.COL_AMOUNT,transaction.getAmount());
        cv.put(PIContract.TransactionEntry.COL_DATE,AppConstants.cf.format(transaction.getCreationDate().getTime()));
        cv.put(PIContract.TransactionEntry.COL_COMMENT,transaction.getComment());
        cv.put(PIContract.TransactionEntry.COL_LATITUDE,transaction.getLatitude());
        cv.put(PIContract.TransactionEntry.COL_LONGITUDE,transaction.getLongitude());
        cv.put(PIContract.TransactionEntry.COL_IMAGE,transaction.getImage());
        return cv;
    }
}
