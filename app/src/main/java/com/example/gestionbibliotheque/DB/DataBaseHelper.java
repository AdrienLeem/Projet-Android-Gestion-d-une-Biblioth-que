package com.example.gestionbibliotheque.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    //Initialize all the fields needed for database
    public static final String DATABASE_NAME = "Users.db";
    public static final String TABLE_NAME = "user";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Username";
    public static final String COL_3 = "Password";
    public static final String COL_4 = "Email";
    public static final String COL_5 = "Admin";
    public static final String LBR = "(";
    public static final String RBR = ")";
    public static final String COM = ",";

    //Just pass context of the app to make it simpler
    public DataBaseHelper(Context context) {
        super( context, DATABASE_NAME, null, 2 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating table

        db.execSQL( "create table " + TABLE_NAME + LBR + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT" + COM +
                COL_2 + " TEXT" + COM + COL_3 + " TEXT" + COM + COL_4 + " INTEGER" + COM + COL_5 + " BOOLEAN" + RBR );

        // Another way of writing the CREATE TABLE query
       /* db.execSQL( "create table student_data (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Surname TEXT," +
                "Marks INTEGER, Date TEXT)" );*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //Dropping old table
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate( db );

    }

    //Insert data in database
    public boolean instertUser(String username, String password, String email, Boolean admin){

        //Get the instance of SQL Database which we have created
        SQLiteDatabase db = getWritableDatabase();

        //To pass all the values in database
        ContentValues contentValues = new ContentValues();
        contentValues.put( COL_2, username );
        contentValues.put( COL_3, password );
        contentValues.put( COL_4, email );
        contentValues.put( COL_5, admin );

        long result = db.insert( TABLE_NAME, null, contentValues );

        return result != -1;
    }

    //Cursor class is used to move around in the database
    public Cursor getAllData(){
        //Get the data from database
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery( "select * from " + TABLE_NAME, null );
    }

    public Cursor getAllUsername() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery( "select Username from " + TABLE_NAME, null );
    }

    public Cursor getAllEmail() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery( "select Email from " + TABLE_NAME, null );
    }

    public Cursor getPasswordByUser(String username) {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery( "select Password from " + TABLE_NAME + " where Username = ?", new String[] { username } );
    }

    //Update fields of database using ID (Unique identifier)
    public boolean updateData(String id, String username, String password, String email){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues(  );
        // When you want to update only name field
        if(password.equals( "" ) && email.equals( "" )){
            contentValues.put( COL_1, id );
            contentValues.put( COL_2, username );
        }
        // When you want to update only surname field
        if(username.equals( "" ) && email.equals( "" )){
            contentValues.put( COL_1, id );
            contentValues.put( COL_3, password );
        }
        // When you want to update only marks field
        if(username.equals( "" ) && password.equals( "" )){
            contentValues.put( COL_1, id );
            contentValues.put( COL_4, email );
        }
        // When you want to update name and surname field
        if(email.equals( "" ) && !username.isEmpty() && !password.isEmpty()){
            contentValues.put( COL_1, id );
            contentValues.put( COL_2, username );
            contentValues.put( COL_3, password );
        }
        // When you want to update marks and surname field
        if(username.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            contentValues.put( COL_1, id );
            contentValues.put( COL_3, password );
            contentValues.put( COL_4, email );
        }
        // When you want to update name and marks field
        if(password.isEmpty() && !username.isEmpty() && !email.isEmpty()){
            contentValues.put( COL_1, id );
            contentValues.put( COL_2, username );
            contentValues.put( COL_4, email );
        }
        // When you want to update every data field
        if(!id.isEmpty() && !username.isEmpty() && !password.isEmpty() && !email.isEmpty()){
            contentValues.put( COL_1, id );
            contentValues.put( COL_2, username );
            contentValues.put( COL_3, password );
            contentValues.put( COL_4, email );
        }

        // UPDATE query
        db.update( TABLE_NAME, contentValues, "ID = ?", new String[]{id} );
        return true;
    }

    //Delete data from the databse using ID (Primary Key)
    public Integer deleteData(String id){

        SQLiteDatabase db = getWritableDatabase();
        return db.delete( TABLE_NAME, "ID = ?", new String [] {id} );
    }
}