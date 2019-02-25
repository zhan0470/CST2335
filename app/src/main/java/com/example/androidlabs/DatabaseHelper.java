package com.example.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "MessagesDB";
    private static final String DB_TABLE = "Messages_Table";

    //columns
    private static final String COL_MESSAGE = "Message";
    private static final String COL_ISSENT = "IsSent";
    private static final String COL_MESSAGEID = "MessageID";
    private static final int VERSION = 2;

    //queries
    private static final String CREATE_TABLE = "CREATE TABLE "+DB_TABLE+" ("+COL_MESSAGEID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_MESSAGE+" TEXT, "+ COL_ISSENT +" BIT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }

    //insert data
    public boolean insertData(String message, boolean isSent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MESSAGE, message);
        if (isSent)
            contentValues.put(COL_ISSENT, 0);
        else
            contentValues.put(COL_ISSENT, 1);

        long result = db.insert(DB_TABLE, null, contentValues);

        return result != -1; //if result = -1 data doesn't insert
    }

    //view data
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        printCursor(cursor, db.getVersion());
        return cursor;
    }

    public void printCursor(Cursor cursor, int version) {
        Log.i("DB version #", "" + version);
        Log.i("Column #", "" + cursor.getColumnCount());
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.i("Column " + i + " name", cursor.getColumnName(i));
        }
        Log.i("Row # is", "" + cursor.getCount());
        int row = 1;
        while (cursor.moveToNext()) {
            Log.i("Row " + row, "" + cursor.getInt(0) + ", " + cursor.getString(1) + ", " + cursor.getInt(2));
            row++;
        }

        cursor.moveToFirst(); // have to go back to the first row of the cursor when finished
    }

}
