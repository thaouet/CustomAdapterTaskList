package com.example.thaouet.tasklistcustomadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by TAHAR on 05/05/2017.
 */

public class TaskSqliteHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "TaskDB";


    // Nom de la table
    private static final String TABLE_TASK = "Task";

    // Les colonnes de la table Task
    private static final String KEY_ID = "id";
    private static final String KEY_LIBELLE = "libelle";
    private static final String KEY_DATE = "date";
    private static final String KEY_HEURE = "heure";

    private static final String[] COLUMNS = {KEY_ID,KEY_LIBELLE,KEY_DATE,KEY_HEURE};



    public TaskSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create task table
        String CREATE_TASK_TABLE = "CREATE TABLE Task ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "libelle TEXT, "+
                "date TEXT, "+
                "heure TEXT )";

        // create task table
        db.execSQL(CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older joueur table if existed
        db.execSQL("DROP TABLE IF EXISTS Task");

        // create fresh books table
        this.onCreate(db);
    }

    //Ajouter une Task
    public long AddTask(Task task){


        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. créer un objet  ContentValues pour stocker objets de type cle "column"/valeur
        ContentValues values = new ContentValues();
        values.put(KEY_LIBELLE, task.getLibelle()); // get Libelle
        values.put(KEY_DATE, task.getDateTask()); // get Date
        values.put(KEY_HEURE, task.getHeureTask()); //get Heure
        // 3. insert
      long rowId =  db.insert(TABLE_TASK, // table
                null, //nullColumnHack
                values); // cle/valeur -> cle = column names/ valeurs = column values

        // 4. close
        db.close();
        return rowId;
    }


    public Task getTask(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_TASK, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build task object
        Task task = new Task(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                Boolean.FALSE);
         // 5. return task
        return task;
    }

    public ArrayList<Task> getListTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_TASK + " ORDER BY " + KEY_ID  +  " DESC";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Task task = null;
        if (cursor.moveToFirst()) {
            do {
                task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setLibelle(cursor.getString(1));
                task.setDateTask(cursor.getString(2));
                task.setHeureTask(cursor.getString(3));

                // Add book to books
                tasks.add(task);
            } while (cursor.moveToNext());
        }

        // return books
        return tasks;
    }

    public int updateTask(Task task) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("libelle", task.getLibelle()); // get libelle
        values.put("date", task.getDateTask()); // get date
        values.put("heure", task.getHeureTask());
        // 3. updating row
        int i = db.update(TABLE_TASK, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(task.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }


    public void supprimerTask(Task task) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_TASK, //table name
                KEY_ID + " = ?",  // selections
                new String[]{String.valueOf(task.getId())}); //selections args

        // 3. close
        db.close();






    }
}
