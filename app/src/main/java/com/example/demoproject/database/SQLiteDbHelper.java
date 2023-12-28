package com.example.demoproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.demoproject.screens.Home.TodoModel;
import com.example.demoproject.screens.Login.LoginModal;
import com.example.demoproject.screens.SignUp.SignupModel;
import com.example.demoproject.screens.Update.UpdateModel;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SQLiteDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE = "todo_db";
    public static final String UserTable = "user_table";
    public static final String TodoTable = "todo_table";
//  for user table
    public static final String FIRST_NAME = "FIRST NAME";
    public static final String LAST_NAME = "LAST NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    // for todo table
    public static final String TITLE = "TITLE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String DATE = "DATE";

    public SQLiteDbHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createUserTable(db);
        createTodoTable(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserTable);
        db.execSQL("DROP TABLE IF EXISTS " + TodoTable);
    }
    private void createUserTable(SQLiteDatabase db) {
        String query = "CREATE TABLE "+UserTable+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT, LAST_NAME TEXT, EMAIL TEXT, PASSWORD TEXT)";
        db.execSQL(query);
    }
    private void createTodoTable(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TodoTable+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DESCRIPTION TEXT, DATE TEXT)";
        db.execSQL(query);
    }
    public void SignupInsertData(SignupModel signupModel){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("FIRST_NAME",signupModel.firstName);
        cv.put("LAST_NAME", signupModel.lastName);
        cv.put("EMAIL" ,signupModel.email);
        cv.put("PASSWORD", signupModel.password);

        database.insert(UserTable,null,cv);
    }

    public boolean Login(LoginModal loginModal){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM "+UserTable+" WHERE EMAIL = '"+loginModal.getEmail()+"' AND PASSWORD = '"+ loginModal.getPassword()+"'";
        if(database.rawQuery(query, null).getCount() > 0){
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            loginModal.setId(cursor.getInt(0));
            return true;
        }else {
            return false;
        }
    }
    public boolean checkUser(String email) {
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM "+UserTable+" WHERE EMAIL = '"+email+"'";

        if(database.rawQuery(query, null).getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public UpdateModel getUserDataOnID(Integer id){
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM "+UserTable+" WHERE ID = '"+id+"'";

        UpdateModel UpdateModel = new UpdateModel();

        if(database.rawQuery(query, null).getCount() > 0){
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            UpdateModel.id = cursor.getInt(0);
            UpdateModel.firstName = cursor.getString(1);
            UpdateModel.lastName = cursor.getString(2);
            UpdateModel.email = cursor.getString(3);
            UpdateModel.password = cursor.getString(4);
        }

        return UpdateModel;
    }
    public void updateData(UpdateModel updateModel){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("FIRST_NAME",updateModel.getFirstName());
        cv.put("LAST_NAME",updateModel.getLastName());
        cv.put("EMAIL",updateModel.getEmail());
        cv.put("PASSWORD",updateModel.getPassword());

        database.update(UserTable,cv,"ID = ?",new String[]{String.valueOf(updateModel.getId())});
    }

    //to do database code
    public void insertTodo(TodoModel todoModel) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String dateTime = dateFormat.format(new Date(System.currentTimeMillis()));

        cv.put("TITLE", todoModel.getTitle());
        cv.put("DESCRIPTION", todoModel.getDescription());
        cv.put("DATE", dateTime);
        database.insert(TodoTable, null, cv);
    }
    public void updateTodo(TodoModel todoModel) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("TITLE", todoModel.getTitle());
        cv.put("DESCRIPTION",todoModel.getDescription());

        database.update(TodoTable, cv, "ID = ?", new String[]{String.valueOf(todoModel.getId())});
    }

    public void deleteTodo(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        database.delete(TodoTable, "ID = ?", new String[]{String.valueOf(id)});
    }

    public ArrayList<TodoModel> getAllTodo() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<TodoModel> arrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TodoTable + " ORDER BY DATE DESC";

        Cursor cursor = database.rawQuery(query, null);

        //this loop will run until the last row of the table
        while (cursor.moveToNext()) {
            TodoModel todoModel = new TodoModel();
            todoModel.id = cursor.getInt(0);
            todoModel.title = cursor.getString(1);
            todoModel.description = cursor.getString(2);
            todoModel.date = cursor.getString(3);
            arrayList.add(todoModel);
        }
        return arrayList;
    }

}
