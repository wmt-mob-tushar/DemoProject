package com.example.demoproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.demoproject.Model.SignupModel;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE = "User_db";
    public static final String TABLE = "user_table";
    public static final String FIRST_NAME = "FIRST NAME";
    public static final String LAST_NAME = "LAST NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public UserDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT, LAST_NAME TEXT, EMAIL TEXT, PASSWORD TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE;
        db.execSQL(query);
    }

    public void SignupInsertData(SignupModel signupModel){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("FIRST_NAME",signupModel.firstName);
        cv.put("LAST_NAME", signupModel.lastName);
        cv.put("EMAIL" ,signupModel.email);
        cv.put("PASSWORD", signupModel.password);

        database.insert(TABLE,null,cv);
    }

    public boolean Login(String email, String password){
//      get email and pass from database to check login user exit in database or not
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM "+TABLE+" WHERE EMAIL = '"+email+"' AND PASSWORD = '"+password+"'";

        if(database.rawQuery(query, null).getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

}
