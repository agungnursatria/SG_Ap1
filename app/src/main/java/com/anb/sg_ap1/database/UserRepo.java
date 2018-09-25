package com.anb.sg_ap1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.anb.sg_ap1.model.User;

import java.util.ArrayList;

public class UserRepo {
    private DBHelper dbHelper;

    public UserRepo(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public int insert(User user){

        // Buka database buat menulis data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_nama, user.nama);
        values.put(User.KEY_gender, user.gender);
        values.put(User.KEY_umur, user.umur);

        long id = db.insert(User.TABLE, null, values);
        db.close();
        return (int) id;

    }

    public void delete(int id){

        // Buka database buat menulis data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(User.TABLE, User.KEY_ID + "= ?", new String[] { String.valueOf(id) });
        db.close();

    }

    public void update(User user){

        // Buka database buat menulis data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.KEY_nama, user.nama);
        values.put(User.KEY_gender, user.gender);
        values.put(User.KEY_umur, user.umur);

        db.update(User.TABLE, values, User.KEY_ID + "= ?", new String[] { String.valueOf(user.id) });
        db.close();

    }

    public ArrayList<User> getUserList(){

        // Buka database buat menulis data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // BIKIN QUERY SELECT
        String selectQuery =  "SELECT * FROM " + User.TABLE;

        // Inisialisasi user list
        ArrayList<User> userList = new ArrayList<>();

        // Eksekusi query
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Jika datanya ada dan cursor ngarah ke data pertama
        if (cursor.moveToFirst()){
            // looping selama masih ada row / data masih tersedia
            do {
                User user = new User();
                user.id = cursor.getInt(cursor.getColumnIndex(User.KEY_ID));
                user.nama = cursor.getString(cursor.getColumnIndex(User.KEY_nama));
                user.gender = cursor.getString(cursor.getColumnIndex(User.KEY_gender));
                user.umur = cursor.getInt(cursor.getColumnIndex(User.KEY_umur));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }
}
