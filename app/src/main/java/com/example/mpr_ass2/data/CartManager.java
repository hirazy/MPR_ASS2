package com.example.mpr_ass2.data;

import static com.example.mpr_ass2.data.DbHelper.DB_NAME;
import static com.example.mpr_ass2.data.DbHelper.ID_COL;
import static com.example.mpr_ass2.data.DbHelper.QUANTITY_COL;
import static com.example.mpr_ass2.data.DbHelper.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mpr_ass2.model.Product;

import java.util.ArrayList;

public class CartManager {

    private static CartManager instance;

    public static CartManager getInstance(Context context) {
        if (instance == null) {
            instance = new CartManager(context);
        }
        return instance;
    }

    DbHelper dbHelper;
    SQLiteDatabase db;

    private CartManager(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<Product> getAll() {
        ArrayList<Product> products = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM carts", null);

        int idIndex = cursor.getColumnIndex("id");
        int thumbnailIndex = cursor.getColumnIndex("thumbnail");
        int nameIndex = cursor.getColumnIndex("name");
        int unitPriceIndex = cursor.getColumnIndex("unitPrice");
        int quantityIndex = cursor.getColumnIndex("quantity");

        while (cursor.moveToNext()) {

            int id = cursor.getInt(idIndex);
            String thumbnail = cursor.getString(thumbnailIndex);
            String name = cursor.getString(nameIndex);
            Long unitPrice = cursor.getLong(unitPriceIndex);
            int quantity = cursor.getInt(quantityIndex);

            products.add(new Product(id, thumbnail, name, unitPrice, quantity));
        }

        return products;
    }

    public boolean update(Product product, boolean isPlus) {
        if (isPlus) {
            Cursor cursor = db.rawQuery("SELECT * FROM carts WHERE id = " + product.getId(), null);

            if (cursor.getCount() == 0) {
                Log.e("onClickAdd", "Add");

                db.execSQL("INSERT INTO carts(id, thumbnail, name, unitPrice, quantity)" +
                        " VALUES (" +
                        product.getId() + "," +
                        "'" + product.getThumbnail() + "'" + "," +
                        "'" + product.getName() + "'" + "," +
                        product.getUnitPrice() + "," +
                        1
                        + ")");
            } else {

                ContentValues values = new ContentValues();

                values.put(QUANTITY_COL, product.getQuantity() + 1);

                int isUpdated = db.update(TABLE_NAME, values, ID_COL + "= ?", new String[]{Integer.toString(product.getId())});
            }
        } else {
            if (product.getQuantity() == 1) {
                int isDeleted = db.delete(TABLE_NAME, ID_COL + " = ?", new String[]{Integer.toString(product.getId())});
            } else {
                ContentValues values = new ContentValues();

                values.put(QUANTITY_COL, product.getQuantity() - 1);

                int isUpdated = db.update(TABLE_NAME, values, ID_COL + "= ?", new String[]{Integer.toString(product.getId())});
            }
        }
        return true;
    }
}
