package com.mrj.morejmoney.data.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.mrj.morejmoney.ui.pi.PIApplication;

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
            db.execSQL(PIContract.UserEntry.SQL_CREATE);
            db.execSQL(PIContract.UserEntry.SQL_INSERT);
            db.execSQL(PIContract.PiggyBankEntry.SQL_CREATE);
            db.execSQL(PIContract.PiggyBankEntry.SQL_INSERT);
            db.execSQL(PIContract.TransactionEntry.SQL_CREATE);
            db.execSQL(PIContract.TransactionEntry.SQL_INSERT);
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
            db.execSQL(PIContract.TransactionEntry.SQL_DELETE);
            db.execSQL(PIContract.PiggyBankEntry.SQL_DELETE);
            db.execSQL(PIContract.UserEntry.SQL_DELETE);
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=1");
            }
        }
    }
}
