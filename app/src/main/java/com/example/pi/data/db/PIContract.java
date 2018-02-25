package com.example.pi.data.db;

import android.provider.BaseColumns;

/**
 * Created by Miguel on 24/02/2018.
 */

public class PIContract {

    public PIContract(){}

    public static final int DB_VERSION = 11;
    public static final String DAB_NAME= "pi.db";

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COL_ID = BaseColumns._ID;
        public static final String COL_USER = "user";
        public static final String COL_EMAIL = "email";
        public static final String COL_PASS = "password";
        public static final String COL_NAME = "name";
        public static final String COL_BIRTH = "birthDate";
        public static final String COL_GENDER = "gender";
        public static final String COL_PHONE = "phone";
        public static final String COL_CITY = "city";
        public static final String COL_SOCIAL = "social";
        public static final String COL_OWNER = "owner";
        public static final String[] COL_ALL = new String[] {
                COL_ID,
                COL_USER,
                COL_EMAIL,
                COL_PASS,
                COL_NAME,
                COL_BIRTH,
                COL_GENDER,
                COL_PHONE,
                COL_CITY,
                COL_SOCIAL,
                COL_OWNER
        };

        public static final String DEFAULT_SORT = COL_ID;

        public static final String SQL_CREATE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, ",
                TABLE_NAME,
                COL_ID
        )+ String.format(" %s TEXT NOT NULL, ",COL_USER) +
                String.format(" %s TEXT NOT NULL, ",COL_EMAIL) +
                String.format(" %s TEXT NOT NULL, ",COL_PASS) +
                String.format(" %s TEXT, ",COL_NAME) +
                String.format(" %s TEXT, ",COL_BIRTH) +
                String.format(" %s TEXT, ",COL_GENDER) +
                String.format(" %s TEXT, ",COL_PHONE) +
                String.format(" %s TEXT, ",COL_CITY) +
                String.format(" %s TEXT, ",COL_SOCIAL) +
                String.format(" %s INTEGER) ",COL_OWNER);

        public static final String SQL_DELETE = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);

        public static final String SQL_INSERT = String.format(
                "INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) VALUES ",
                TABLE_NAME,
                COL_USER,
                COL_EMAIL,
                COL_PASS,
                COL_NAME,
                COL_BIRTH,
                COL_GENDER,
                COL_PHONE,
                COL_CITY,
                COL_SOCIAL,
                COL_OWNER
        )+String.format(
                "('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'),",
                "Miguel",
                "miguel@gmail.com",
                "Miguel1",
                "Miguel Rodriguez",
                "07/09/1996",
                "Hombre",
                "666666666",
                "Malaga",
                "social",
                1
        )+String.format(
                "('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                "Lourdes",
                "lourdes@gmail.com",
                "Lourdes1",
                "Lourdes Rodriguez",
                "07/09/1996",
                "Mujer",
                "666666666",
                "Malaga",
                "social",
                0
        );
    }

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
                        "%s TEXT NOT NULL," +
                        "FOREIGN KEY (%s) REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT)",
                TABLE_NAME,
                COL_ID,
                COL_ID_USER,
                COL_NAME,
                COL_AMOUNT,
                COL_DATE,
                COL_ID_USER,
                UserEntry.TABLE_NAME,
                UserEntry.COL_ID
        );

        public static final String SQL_DELETE = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);

        public static final String SQL_INSERT = String.format(
                "INSERT INTO %s (%s,%s,%s,%s) VALUES ",
                TABLE_NAME,
                COL_ID_USER,
                COL_NAME,
                COL_AMOUNT,
                COL_DATE
        )+String.format(
                "(%s,'%s',%s,'%s'),",
                0,
                "Cartera",
                0.54,
                "20/02/2018"
        )+String.format(
                "(%s,'%s',%s,'%s')",
                0,
                "Cajon",
                0,
                "20/02/2016"
        );
    }

    public static class TransactionEntry implements BaseColumns {
        public static final String TABLE_NAME = "transaction";
        public static final String COL_ID = BaseColumns._ID;
        public static final String COL_ID_USER = "idUser";
        public static final String COL_ID_PIGGYBANK = "idPiggyBank";
        public static final String COL_ID_ESTABLISHMENT = "idEstablishment";
        public static final String COL_PAYMENT = "payment";
        public static final String COL_AMOUNT = "amount";
        public static final String COL_DATE = "date";
        public static final String COL_COMMENT = "comment";
        public static final String COL_LATITUDE = "laitude";
        public static final String COL_LONGITUDE = "longitude";
        public static final String COL_IMAGE = "image";
        public static final String[] COL_ALL = new String[]{
                COL_ID,
                COL_ID_USER,
                COL_ID_PIGGYBANK,
                COL_ID_ESTABLISHMENT,
                COL_PAYMENT,
                COL_AMOUNT,
                COL_DATE,
                COL_COMMENT,
                COL_LATITUDE,
                COL_LONGITUDE,
                COL_IMAGE
        };

        public static final String DEFAULT_SORT = COL_ID;

        public static final String SQL_CREATE = String.format(
                "CREATE TABLE '%s' (%s INTEGER PRIMARY KEY AUTOINCREMENT, ",
                TABLE_NAME,
                COL_ID
        )+ String.format(" %s INTEGER NOT NULL, ",COL_ID_USER) +
                String.format(" %s INTEGER NOT NULL, ",COL_ID_PIGGYBANK) +
                String.format(" %s INTEGER NOT NULL, ",COL_ID_ESTABLISHMENT) +
                String.format(" %s BOOL NOT NULL, ",COL_PAYMENT) +
                String.format(" %s REAL NOT NULL, ",COL_AMOUNT) +
                String.format(" %s TEXT NOT NULL, ",COL_DATE) +
                String.format(" %s TEXT NOT NULL, ",COL_COMMENT) +
                String.format(" %s REAL, ",COL_LATITUDE) +
                String.format(" %s REAL, ",COL_LONGITUDE) +
                String.format(" %s BLOB, ",COL_IMAGE)+
                String.format(" FOREIGN KEY (%s) REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT, ",
                        COL_ID_USER, UserEntry.TABLE_NAME, UserEntry.COL_ID)+
                String.format(" FOREIGN KEY (%s) REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT) ",
                        COL_ID_PIGGYBANK, PiggyBankEntry.TABLE_NAME, PiggyBankEntry.COL_ID);

        public static final String SQL_DELETE = String.format("DROP TABLE IF EXISTS '%s'",TABLE_NAME);

        public static final String SQL_INSERT = String.format(
                "INSERT INTO '%s' (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) VALUES ",
                TABLE_NAME,
                COL_ID_USER,
                COL_ID_PIGGYBANK,
                COL_ID_ESTABLISHMENT,
                COL_PAYMENT,
                COL_AMOUNT,
                COL_DATE,
                COL_COMMENT,
                COL_LATITUDE,
                COL_LONGITUDE,
                COL_IMAGE
        )+String.format(
                "(%s,%s,%s,%s,%s,'%s','%s',%s,%s,'%s'),",
                0,
                1,
                1,
                1,
                5.45,
                "20/02/2018 15:08",
                "compra barata",
                null,
                null,
                null
        )+String.format(
                "(%s,%s,%s,%s,%s,'%s','%s',%s,%s,'%s')",
                0,
                1,
                1,
                1,
                0.45,
                "20/03/2018 16:09",
                "compra barata",
                null,
                null,
                null
        );
    }
}
