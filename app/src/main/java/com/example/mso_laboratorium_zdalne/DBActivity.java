package com.example.mso_laboratorium_zdalne;


import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DBActivity extends ListActivity {

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_b);
        sqLiteDatabase = openOrCreateDatabase("db",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS WPIS (Str VARCHAR, DateTime TEXT);");
    }

    public void addToDB(View view) {
        EditText editText= findViewById(R.id.editText);
        Date currentTime = Calendar.getInstance().getTime();
        sqLiteDatabase.execSQL("INSERT INTO WPIS (Str, DateTime) VALUES ('" + editText.getText() +"', datetime('now'));");
    }

    public void updateDB(View view){
        Cursor cursor = sqLiteDatabase.query("WPIS",new String[]{"Str","DateTime"},null,null,null,null,null);
        ArrayList<String> list = new ArrayList<>();
        while(cursor.moveToNext()){
            String data = "";
            data += cursor.getString(cursor.getColumnIndex("Str"));
            data += " " + cursor.getString(cursor.getColumnIndex("DateTime"));
            list.add(data);
        }
        ListView listView = findViewById(android.R.id.list);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void clearDB(View view){
        sqLiteDatabase.execSQL("DELETE FROM WPIS");
        sqLiteDatabase.execSQL("VACUUM");
    }
}
