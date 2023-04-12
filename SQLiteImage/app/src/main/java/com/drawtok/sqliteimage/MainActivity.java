package com.drawtok.sqliteimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ListView listItem;
    ArrayList<item> arrayList;
    ItemsAdapter itemsAdapter;
    public static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        listItem = findViewById(R.id.lv_item);

        arrayList = new ArrayList<>();
        itemsAdapter = new ItemsAdapter(MainActivity.this, R.layout.custom_list_item, arrayList);
        listItem.setAdapter(itemsAdapter);

        database = new Database(this, "items.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS Items" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " description VARCHAR(200)," +
                " image BLOB)");

        //get data
        Cursor cursor = database.getData("SELECT * FROM Items");
        while (cursor.moveToNext())
        {
            arrayList.add(new item(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getBlob(2))
            );
        }

        itemsAdapter.notifyDataSetChanged();

        btn.setOnClickListener(v->
        {
            startActivity(new Intent(MainActivity.this, saveImageActivity.class));
        });
    }
}