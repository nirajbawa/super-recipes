package com.superrecipesapp.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.superrecipesapp.app.Adapters.FoodItemsListAdapter;
import com.superrecipesapp.app.Models.FoodItemsListModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    static final String dbName = "superRescipes.db";
    static final int version = 1;

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table orders " +
                "(id integer primary key autoincrement, " +
                "name text," +
                "phone text," +
                "price integer," +
                "description integer," +
                "title text," +
                "thumbnail integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists orders");
        onCreate(sqLiteDatabase);
    }

    public boolean insertOrders(String name, String phone, int price, int thubmnail, String description, String title) throws Exception {
            SQLiteDatabase database = getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("phone", phone);
            values.put("price", price);
            values.put("thumbnail", thubmnail);
            values.put("description", description);
            values.put("title", title);

            long id = database.insert("orders", null, values);
            if (id < 0) {
                return false;
            } else {
                return true;
            }
    }

    public ArrayList<FoodItemsListModel> getOrders() throws Exception
    {
        ArrayList<FoodItemsListModel> list = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select id, title, price, thumbnail from Orders", null);
        if(cursor.moveToFirst())
        {
            while (cursor.moveToNext())
            {
                FoodItemsListModel model = new FoodItemsListModel(
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getInt(cursor.getColumnIndex("price")),
                        cursor.getInt(cursor.getColumnIndex("thumbnail"))
                        );
                list.add(model);
            }
        }
        cursor.close();
        database.close();
        return list;
    }
    public boolean updateOrders(String name, String phone, int id)
    {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        long row = database.update("orders", values, "id="+id, null);
        if(row<=0)
        {
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor getOrderById(int id)
    {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select id, title, thumbnail, price, description, name, phone from orders where id = "+id, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public boolean deleteOrder(int id) throws Exception
    {
        SQLiteDatabase database = getWritableDatabase();
        int row = database.delete("orders", "id="+id, null);
        return row<=0?false:true;
    }
}
