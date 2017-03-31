package com.geek.recycleview.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.geek.recycleview.model.Person;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by PK on 3/31/2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "person_database";
    public final static int VERSION = 1;
    public final String TABLE_NAME = "person";
    public final String COLUMN_ID = "ID";
    public final String COLUMN_NAME = "NAME";
    public final String COLUMN_AGE = "AGE";
    public final String COLUMN_GENDER = "GENDER";
    public final String COLUMN_ADDRESS = "ADDRESS";
    public final String COLUMN_PHONE = "PHONE";
    public final String COLUMN_EMAIL = "EMAIL";


    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT," +
                COLUMN_ADDRESS + " TEXT," +
                COLUMN_AGE + " INTEGER," +
                COLUMN_GENDER + " TEXT," +
                COLUMN_PHONE + " TEXT," +
                COLUMN_EMAIL + " TEXT);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

    public void addNewPerson(Person newPerson) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newPerson.getName());
        values.put(COLUMN_ADDRESS, newPerson.getAddress());
        values.put(COLUMN_AGE, newPerson.getAge());
        values.put(COLUMN_GENDER, newPerson.getGender());
        values.put(COLUMN_PHONE, newPerson.getPhone());
        values.put(COLUMN_EMAIL, newPerson.getMail());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Person getPerson(int id) {
        Person person = new Person();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID, COLUMN_NAME, COLUMN_ADDRESS, COLUMN_AGE, COLUMN_GENDER,
                        COLUMN_EMAIL,
                        COLUMN_PHONE}, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null,
                null,
                null);
        if (cursor != null)
            cursor.moveToFirst();
        cursor.moveToNext();
        person.setId(id);
        person.setName(cursor.getString(2));
        person.setAddress(cursor.getString(3));
        person.setAge(cursor.getInt(4));
        person.setGender(cursor.getString(5));
        person.setMail(cursor.getString(6));
        person.setPhone(cursor.getString(7));
        return person;
    }

    public List<Person> getListPerson() {
        List<Person> listPerson = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getCount() > 0)
            do {
                Person person = new Person();
                person.setId(cursor.getInt(0));
                person.setName(cursor.getString(1));
                person.setAddress(cursor.getString(2));
                person.setAge(cursor.getInt(3));
                person.setGender(cursor.getString(4));
                person.setMail(cursor.getString(5));
                person.setPhone(cursor.getString(6));
                listPerson.add(person);
            } while (cursor.moveToNext());
        return listPerson;
    }

    public int update(int id, Person newPerson) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updateValue = new ContentValues();
        updateValue.put(COLUMN_NAME, newPerson.getName());
        updateValue.put(COLUMN_ADDRESS, newPerson.getAddress());
        updateValue.put(COLUMN_AGE, newPerson.getAge());
        updateValue.put(COLUMN_GENDER, newPerson.getGender());
        updateValue.put(COLUMN_EMAIL, newPerson.getMail());
        updateValue.put(COLUMN_PHONE, newPerson.getPhone());
        return db.update(TABLE_NAME, updateValue, COLUMN_ID + "=?", new String[]{String.valueOf
                (id)});
    }

    public void delete(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(person.getId())});
        db.close();
    }

    public int getPersonCounter() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

}
