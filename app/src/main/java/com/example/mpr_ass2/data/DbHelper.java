package com.example.mpr_ass2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "carts.db";
    public static final int DB_VERSION = 1;

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
                "id INTEGER," +
                "thumbnail TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "unitPrice LONG," +
                "quantity INTERGER)");

        // sqLiteDatabase.execSQL("INSERT INTO carts (id, content) VALUES(1, 'He He')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // DROP
        sqLiteDatabase.execSQL("DROP TABLE carts");

        onCreate(sqLiteDatabase);
    }
}
