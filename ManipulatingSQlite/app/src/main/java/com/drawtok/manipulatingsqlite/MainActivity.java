package com.drawtok.manipulatingsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvNote;
    Database database;
    ArrayList<CongViec> list;
    CongViecAdapter congViecAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNote = findViewById(R.id.lv_note);

        list = new ArrayList<>();
        congViecAdapter = new CongViecAdapter(this, R.layout.custom_listview, list);
        lvNote.setAdapter(congViecAdapter);
        //tạo database note
        database = new Database(this, "note.sqlite", null, 1);
        //tạo bảng
        database.QueryData(
                "CREATE TABLE IF NOT EXISTS CongViec(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "tenCV VARCHAR(200))");

        // insert database
        String title = "";
        database.QueryData("INSERT INTO CongViec VALUES(null, '"+title+"')");
        //select data
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");
        while (dataCongViec.moveToNext())
        {
            String name = dataCongViec.getString(1);
            list.add(new CongViec(name));
        }
        congViecAdapter.notifyDataSetChanged();
    }
}