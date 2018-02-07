package com.shahrukhzarir.assignmenttwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by shahrukhzarir on 2017-11-15.
 */

public class ProductDBHelpher extends SQLiteOpenHelper {
    private static  final int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "Products.db";
    public static String TABLE_NAME = "products";
    public  static  String COLUMN_ID = "_id";
    public  static  String COLUMN_PRODUCTNAME = "name";
    public  static  String COLUMN_PRODUCTDESC = "description";
    public  static  String COLUMN_PRODUCTPRICE = "price";

    public ProductDBHelpher(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + " ( " +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_PRODUCTNAME + " varchar(100), " +
                COLUMN_PRODUCTDESC + " varchar(100), " +
                COLUMN_PRODUCTPRICE + " real " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public void addProduct(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCTNAME, product.getName());
        contentValues.put(COLUMN_PRODUCTDESC, product.getDescription());
        contentValues.put(COLUMN_PRODUCTPRICE, product.getPrice());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE 1";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            String productName = cursor.getString(1);
            String productDescription = cursor.getString(2);
            float productPrice = cursor.getFloat(3);

            Product product = new Product(productName, productDescription, productPrice);
            products.add(product);
            cursor.moveToNext();
        }

        return products;
    }
}