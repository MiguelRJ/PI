package com.example.pi.data.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pi.ui.pi.PIApplication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Miguel on 24/02/2018.
 */

public class PIOpenHelper extends SQLiteOpenHelper {

    private volatile static PIOpenHelper singleton;
    private SQLiteDatabase sqLiteDatabase;
    private AtomicInteger openCounter = new AtomicInteger();

    public PIOpenHelper() {
        super(PIApplication.getContext(), PIContract.DAB_NAME, null, PIContract.DB_VERSION);
    }

    public synchronized static PIOpenHelper getInstance(){
        if (singleton == null){
            singleton = new PIOpenHelper();
        }
        return singleton;
    }

    public synchronized SQLiteDatabase openDataBase() {
        if (openCounter.incrementAndGet() == 1){
            sqLiteDatabase = getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public synchronized void closeDataBase() {
        if (openCounter.decrementAndGet() == 0){
            sqLiteDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(PIContract.PiggyBankEntry.SQL_CREATE);
            db.execSQL(PIContract.PiggyBankEntry.SQL_INSERT);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.beginTransaction();
            db.execSQL(PIContract.PiggyBankEntry.SQL_DELETE);
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }
}
