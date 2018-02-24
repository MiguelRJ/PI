package com.example.pi.data.db;

import android.provider.BaseColumns;

/**
 * Created by Miguel on 24/02/2018.
 */

public class PIContract {

    public PIContract(){}

    public static final int DB_VERSION = 1;
    public static final String DAB_NAME= "pi.db";

    public static class PiggyBankEntry implements BaseColumns {
        public static final String TABLE_NAME = "piggybank";
        public static final String COL_ID = BaseColumns._ID;
        public static final String COL_ID_USER = "idUser";
        public static final String COL_NAME = "name";
        public static final String COL_AMOUNT = "amount";
        public static final String COL_DATE = "date";
        public static final String[] COL_ALL = new String[]{
                COL_ID,
                COL_ID_USER,
                COL_NAME,
                COL_AMOUNT,
                COL_DATE
        };

        public static final String DEFAULT_SORT = COL_ID;

        public static final String SQL_CREATE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s REAL NOT NULL, " +
                        "%s TEXT NOT NULL)",
                TABLE_NAME,
                COL_ID,
                COL_ID_USER,
                COL_NAME,
                COL_AMOUNT,
                COL_DATE
        );

        public static final String SQL_DELETE = String.format("DELETE TABLE IF EXISTS %s",TABLE_NAME);

        public static final String SQL_INSERT = String.format(
                "INSERT INTO %s (%s,%s,%s,%s) VALUES ",
                TABLE_NAME,
                COL_ID_USER,
                COL_NAME,
                COL_AMOUNT,
                COL_DATE
        )+String.format(
                "(%s,'%s',%s,'%s'),",
                1,
                "Cartera",
                0.54,
                "20/02/2018"
        )+String.format(
                "(%s,'%s',%s,'%s')",
                1,
                "Cajon",
                0,
                "20/02/2016"
        );

    }
}
