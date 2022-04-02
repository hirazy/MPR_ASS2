package com.example.mpr_ass2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "carts.db";
    public static final int DB_VERSION = 2;

    public static final String TABLE_NAME = "carts";


    public static final String ID_COL = "id";
    public static final String THUMBNAIL_COL = "thumbnail";
    public static final String NAME_COL = "name";
    public static final String UNIT_PRICE_COL = "unitPrice";
    public static final String QUANTITY_COL = "quantity";

    public static DbHelper instance;

    public static DbHelper getInstance(Context context) {
        if(instance == null){
            instance = new DbHelper(context);
        }
        return instance;
    }

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE carts(" +
                ID_COL + " INTEGER," +
                THUMBNAIL_COL + " TEXT NOT NULL," +
                NAME_COL +" TEXT NOT NULL," +
                UNIT_PRICE_COL+ " LONG," +
                QUANTITY_COL + " INTERGER)");

        // sqLiteDatabase.execSQL("INSERT INTO carts (id, content) VALUES(1, 'He He')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // DROP
        sqLiteDatabase.execSQL("DROP TABLE carts");

        onCreate(sqLiteDatabase);
    }
}
