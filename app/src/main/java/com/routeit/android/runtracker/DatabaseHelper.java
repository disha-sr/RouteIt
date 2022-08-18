package com.raywenderlich.android.runtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="UserRoutedata.db";
    private static final int  DATABASE_VERSION=1;
private static final String TABLE_NAME="UserRouteDetails";
private static final String COLUMN_ID="_id";
    private static final String COLUMN_STATES="state_name";
    private static final String COLUMN_DISTRICT="district_name";
    private static final String COLUMN_ROUTE="route_name";
    private static final String COLUMN_SOURCE="source_name";
    private static final String COLUMN_VIA="via_name";
    private static final String COLUMN_DESTINATION="dest_name";

    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
String query=  "CREATE TABLE " + TABLE_NAME +
        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_STATES + " TEXT, " +
        COLUMN_DISTRICT + " TEXT, " +
        COLUMN_ROUTE + " TEXT, " +
        COLUMN_SOURCE + " TEXT, " +
        COLUMN_VIA + " TEXT, " +
        COLUMN_DESTINATION + " TEXT);";

MyDB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(MyDB);


    }

    public void insertData(String NameOfstate, String districtName, String routeName, String sourcename, String vianame, String destinationname) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STATES, NameOfstate);
        contentValues.put(COLUMN_DISTRICT, districtName);
        contentValues.put(COLUMN_ROUTE, routeName);
        contentValues.put(COLUMN_SOURCE, sourcename);
        contentValues.put(COLUMN_VIA, vianame);
        contentValues.put(COLUMN_DESTINATION, destinationname);

        long result = MyDB.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }


    public Cursor getdata() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = null;
        if(MyDB != null){
            cursor = MyDB.rawQuery(query, null);
        }
        return cursor;
    }



    void updateData(String row_id,String NameOfstate, String districtName, String routeName, String sourcename, String vianame, String destinationame) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STATES, NameOfstate);
        contentValues.put(COLUMN_DISTRICT, districtName);
        contentValues.put(COLUMN_ROUTE, routeName);
        contentValues.put(COLUMN_SOURCE, sourcename);
        contentValues.put(COLUMN_VIA, vianame);
        contentValues.put(COLUMN_DESTINATION, destinationame);
            long result = MyDB.update(TABLE_NAME, contentValues, "_id=?", new String[]{row_id});

            if (result == -1) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        }

   void deleteOneRow(String row_id) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

            long result = MyDB.delete(TABLE_NAME, "_id=?", new String[]{row_id});

            if (result == -1) {
                Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            }
        }
    void deleteAllData(){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Mydb.execSQL("DELETE FROM " + TABLE_NAME);




    }
}









